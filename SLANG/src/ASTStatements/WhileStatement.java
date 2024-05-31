package ASTStatements;

import AST.Expression;
import Context.RUNTIME_CONTEXT;
import Lexer.SymbolInfo;
import Lexer.TYPE_INFO;
import Visitor.IExpressionVisitor;

import java.util.ArrayList;

public class WhileStatement extends Statement{
    private Expression cond;
    private ArrayList stmnts;

    public WhileStatement(Expression c, ArrayList s)
    {
        cond = c;
        stmnts = s;
    }

    @Override
    public SymbolInfo execute(IExpressionVisitor visitor, RUNTIME_CONTEXT cont) throws Exception {

        restart:
        while (true) {
            SymbolInfo info = cond.accept(visitor, cont);


            if (info == null || info.type != TYPE_INFO.TYPE_BOOL)
                return null;

            if (info.bolValue != true)
                return null;

            SymbolInfo tsp = null;
            for (Object smt : stmnts) {
                Statement rst = (Statement) smt;
                tsp = rst.execute(visitor, cont);
                if (tsp != null) {
                    return tsp;
                }
            }
        }
    }
}
