package ASTStatements;

import AST.Expression;
import Context.RUNTIME_CONTEXT;
import Lexer.SymbolInfo;
import Lexer.TYPE_INFO;
import Visitor.IExpressionVisitor;

public class PrintLineStatement extends Statement{

    private Expression exp;

    public PrintLineStatement(Expression exp) {
        this.exp = exp;
    }

    @Override
    public SymbolInfo execute(IExpressionVisitor visitor, RUNTIME_CONTEXT context) throws Exception {
        SymbolInfo val = exp.accept(visitor, context);
        System.out.println((val.type == TYPE_INFO.TYPE_NUMERIC  ) ? String.valueOf(val.dblValue) :
                ( val.type == TYPE_INFO.TYPE_STRING ) ? val.strValue : val.bolValue ? "TRUE" : "FALSE" );
        return null;
    }
}
