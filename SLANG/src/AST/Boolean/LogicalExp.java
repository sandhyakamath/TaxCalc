package AST.Boolean;

import AST.Expression;
import Context.COMPILATION_CONTEXT;
import Context.RUNTIME_CONTEXT;
import Lexer.SymbolInfo;
import Lexer.TYPE_INFO;
import Lexer.TOKEN;
import Visitor.IExpressionVisitor;

public class LogicalExp extends Expression {

    public TOKEN op; // &&, ||
    public Expression left, right;
    TYPE_INFO type;

    public LogicalExp(TOKEN op, Expression e1, Expression e2)
    {
        op = op;
        left = e1;
        right = e2;

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
        TYPE_INFO eval_left = left.typeCheck(context);
        TYPE_INFO eval_right = right.typeCheck(context);

        // The Types should be Boolean...
        // Logical Operators only make sense
        // with Boolean Types

        if (eval_left == eval_right &&
                eval_left == TYPE_INFO.TYPE_BOOL  )
        {
            type = TYPE_INFO.TYPE_BOOL;
            return type;
        }
        else
        {
            throw new Exception("Wrong Type in expression");

        }
    }
}
