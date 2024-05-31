package ASTStatements;

import AST.Expression;
import Context.RUNTIME_CONTEXT;
import Lexer.SymbolInfo;
import Lexer.TYPE_INFO;
import Visitor.IExpressionVisitor;

import java.util.ArrayList;

public class IfStatement extends Statement {
    private Expression cond;
    private ArrayList stmnts;
    private ArrayList elsePart;

    public IfStatement(Expression c, ArrayList s, ArrayList e)
    {
        cond = c;
        stmnts = s;
        elsePart = e;
    }
    @Override
    public SymbolInfo execute(IExpressionVisitor visitor, RUNTIME_CONTEXT cont) throws Exception {

        SymbolInfo mCond = cond.accept(visitor, cont);

        if (mCond == null || mCond.type != TYPE_INFO.TYPE_BOOL)
            return null;

        if (mCond.bolValue == true) {
            for (Object s : stmnts) {
                Statement stmt = (Statement) s;
                stmt.execute(visitor, cont);

            }
        } else if (elsePart != null) {
            for (Object s : stmnts) {
                Statement stmt = (Statement) s;
                stmt.execute(visitor, cont);

            }

        }

        return null;


    }




}

