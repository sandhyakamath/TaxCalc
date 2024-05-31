package AST.Boolean;

import AST.Expression;
import Context.COMPILATION_CONTEXT;
import Context.RUNTIME_CONTEXT;
import Lexer.SymbolInfo;
import Lexer.TYPE_INFO;
import Visitor.IExpressionVisitor;

public class LogicalNot extends Expression {
    public Expression exp;
    TYPE_INFO type;

    public LogicalNot(Expression e1)
    {

        exp = e1;
    }

    @Override
    public SymbolInfo accept(IExpressionVisitor visitor, RUNTIME_CONTEXT context) throws Exception {
        return visitor.visit(this, context);
    }

    @Override
    public TYPE_INFO accept(IExpressionVisitor visitor, COMPILATION_CONTEXT context) throws Exception {
        return visitor.visit(this, context);
    }

    @Override
    public TYPE_INFO typeCheck(COMPILATION_CONTEXT context) throws Exception {
        TYPE_INFO eval_left = exp.typeCheck(context);


        if (eval_left == TYPE_INFO.TYPE_BOOL) {
            type = TYPE_INFO.TYPE_BOOL;
            return type;
        }
        else
        {
            throw new Exception("Wrong Type in expression");

        }
    }
}
