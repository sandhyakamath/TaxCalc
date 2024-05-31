package Visitor;

import AST.Binary.*;
import AST.Boolean.LogicalExp;
import AST.Boolean.LogicalNot;
import AST.Boolean.RelationalExp;
import AST.Constant.BooleanConstant;
import AST.Constant.NumericConstant;
import AST.Constant.StringLiteral;
import AST.Unary.UnaryMinus;
import AST.Unary.UnaryPlus;
import AST.Variable;
import Context.COMPILATION_CONTEXT;
import Context.RUNTIME_CONTEXT;
import Lexer.RELATION_OPERATOR;
import Lexer.SymbolInfo;
import Lexer.TOKEN;
import Lexer.TYPE_INFO;

public class TreeEvaluatorVisitor implements IExpressionVisitor {

    // numeric
    @Override
    public SymbolInfo visit(NumericConstant num, RUNTIME_CONTEXT context) {
        return num.getInfo();
    }

    @Override
    public TYPE_INFO visit(NumericConstant num, COMPILATION_CONTEXT context) {
        return num.getInfo().type;
    }

    // boolean

    @Override
    public SymbolInfo visit(BooleanConstant bool, RUNTIME_CONTEXT context) throws Exception {
        return bool.getInfo();
    }

    @Override
    public TYPE_INFO visit(BooleanConstant bool, COMPILATION_CONTEXT context) {
        return bool.getInfo().type;
    }

    // string literal
    @Override
    public SymbolInfo visit(StringLiteral str, RUNTIME_CONTEXT context) throws Exception {
        return str.getInfo();
    }

    @Override
    public TYPE_INFO visit(StringLiteral str, COMPILATION_CONTEXT context) {
        return str.getInfo().type;
    }

    // Binary PLUS

    @Override
    public SymbolInfo visit(BinaryPlus plus, RUNTIME_CONTEXT context) throws Exception {
        SymbolInfo evalLeft = plus.getLeft().accept(this, context);
        SymbolInfo evalRight = plus.getRight().accept(this, context);

        if (evalLeft.type == TYPE_INFO.TYPE_STRING &&
                evalRight.type == TYPE_INFO.TYPE_STRING)
        {
            SymbolInfo ret_val = new SymbolInfo();
            ret_val.strValue = evalLeft.strValue + evalRight.strValue;
            ret_val.type = TYPE_INFO.TYPE_STRING;
            ret_val.symbolName = "";
            return ret_val;
        }
        else if (evalLeft.type == TYPE_INFO.TYPE_NUMERIC &&
                evalRight.type == TYPE_INFO.TYPE_NUMERIC)
        {
            SymbolInfo ret_val = new SymbolInfo();
            ret_val.dblValue = evalLeft.dblValue + evalRight.dblValue;
            ret_val.type = TYPE_INFO.TYPE_NUMERIC;
            ret_val.symbolName = "";
            return ret_val;

        }
        else
        {
            throw new Exception("Type mismatch");
        }

    }

    @Override
    public TYPE_INFO visit(BinaryPlus plus, COMPILATION_CONTEXT context) throws Exception {
        TYPE_INFO evalLeft = plus.getLeft().accept(this, context);
        TYPE_INFO evalRight = plus.getRight().accept(this, context);

        if (evalLeft == evalRight && evalLeft != TYPE_INFO.TYPE_BOOL)
        {
            return evalLeft;
        }
        else
        {
            throw new Exception("Type mismatch failure");

        }
    }

    // Variable

    @Override
    public SymbolInfo visit(Variable var, RUNTIME_CONTEXT context) {
        if (context.getTable() == null) {
            return null;
        } else
        {
            SymbolInfo a = context.getTable().get(var.getName());
            return a;
        }
    }

    @Override
    public TYPE_INFO visit(Variable var, COMPILATION_CONTEXT context) {
        if (context.getTable() == null)
        {
            return TYPE_INFO.TYPE_ILLEGAL;
        }
        else
        {
            SymbolInfo a = context.getTable().get(var.getName());
            if (a != null)
            {
                return a.type;

            }

            return TYPE_INFO.TYPE_ILLEGAL;

        }
    }

    // Binary Minus

    @Override
    public SymbolInfo visit(BinaryMinus minus, RUNTIME_CONTEXT context) throws Exception {
        SymbolInfo eval_left = minus.getLeft().accept(this, context);
        SymbolInfo eval_right = minus.getRight().accept(this, context);

       if (eval_left.type == TYPE_INFO.TYPE_NUMERIC &&
                eval_right.type == TYPE_INFO.TYPE_NUMERIC) {
            SymbolInfo ret_val = new SymbolInfo();
            ret_val.dblValue = eval_left.dblValue - eval_right.dblValue;
            ret_val.type = TYPE_INFO.TYPE_NUMERIC;
            ret_val.symbolName = "";
            return ret_val;

        }
        else
        {
            throw new Exception("Type mismatch");
        }
    }

    @Override
    public TYPE_INFO visit(BinaryMinus minus, COMPILATION_CONTEXT context) throws Exception {
        TYPE_INFO evalLeft = minus.getLeft().accept(this, context);
        TYPE_INFO evalRight = minus.getRight().accept(this, context);

        if (evalLeft == evalRight && evalLeft != TYPE_INFO.TYPE_BOOL)
        {
            return evalLeft;
        }
        else
        {
            throw new Exception("Type mismatch failure");

        }
    }

    // Binary Mul

    @Override
    public SymbolInfo visit(BinaryMul mul, RUNTIME_CONTEXT context) throws Exception {
        SymbolInfo eval_left = mul.getLeft().accept(this, context);
        SymbolInfo eval_right = mul.getRight().accept(this, context);

        if (eval_left.type == TYPE_INFO.TYPE_NUMERIC &&
                eval_right.type == TYPE_INFO.TYPE_NUMERIC) {
            SymbolInfo ret_val = new SymbolInfo();
            ret_val.dblValue = eval_left.dblValue * eval_right.dblValue;
            ret_val.type = TYPE_INFO.TYPE_NUMERIC;
            ret_val.symbolName = "";
            return ret_val;

        }
        else
        {
            throw new Exception("Type mismatch");
        }
    }

    @Override
    public TYPE_INFO visit(BinaryMul mul, COMPILATION_CONTEXT context) throws Exception {
        TYPE_INFO evalLeft = mul.getLeft().accept(this, context);
        TYPE_INFO evalRight = mul.getRight().accept(this, context);

        if (evalLeft == evalRight && evalLeft == TYPE_INFO.TYPE_NUMERIC)
        {
            return evalLeft;
        }
        else
        {
            throw new Exception("Type mismatch failure");

        }
    }

    // Binary Div

    @Override
    public SymbolInfo visit(BinaryDiv div, RUNTIME_CONTEXT context) throws Exception {
        SymbolInfo eval_left = div.getLeft().accept(this, context);
        SymbolInfo eval_right = div.getRight().accept(this, context);

        if (eval_left.type == TYPE_INFO.TYPE_NUMERIC &&
                eval_right.type == TYPE_INFO.TYPE_NUMERIC) {
            SymbolInfo ret_val = new SymbolInfo();
            ret_val.dblValue = eval_left.dblValue / eval_right.dblValue;
            ret_val.type = TYPE_INFO.TYPE_NUMERIC;
            ret_val.symbolName = "";
            return ret_val;

        }
        else
        {
            throw new Exception("Type mismatch");
        }
    }

    @Override
    public TYPE_INFO visit(BinaryDiv div, COMPILATION_CONTEXT context) throws Exception {
        TYPE_INFO evalLeft = div.getLeft().accept(this, context);
        TYPE_INFO evalRight = div.getRight().accept(this, context);

        if (evalLeft == evalRight && evalLeft == TYPE_INFO.TYPE_NUMERIC)
        {
            return evalLeft;
        }
        else
        {
            throw new Exception("Type mismatch failure");

        }
    }

    // Unary Plus

    @Override
    public SymbolInfo visit(UnaryPlus plus, RUNTIME_CONTEXT context) throws Exception {
        SymbolInfo evalRight = plus.getRight().accept(this, context);
        if (evalRight.type == TYPE_INFO.TYPE_NUMERIC)
        {
            SymbolInfo ret_val = new SymbolInfo();
            ret_val.dblValue = evalRight.dblValue;
            ret_val.type = TYPE_INFO.TYPE_NUMERIC;
            ret_val.symbolName = "";
            return ret_val;

        }
        else
        {
            throw new Exception("Type mismatch");
        }
    }

    @Override
    public TYPE_INFO visit(UnaryPlus plus, COMPILATION_CONTEXT context) throws Exception {
        TYPE_INFO evalRight = plus.getRight().accept(this, context);

        if (evalRight == TYPE_INFO.TYPE_NUMERIC)
        {
            return evalRight;
        }
        else
        {
            throw new Exception("Type mismatch failure");

        }
    }

    // Unary Minus

    @Override
    public SymbolInfo visit(UnaryMinus minus, RUNTIME_CONTEXT context) throws Exception {
        SymbolInfo evalLeft =minus.getRight().accept(this, context);
        if (evalLeft.type == TYPE_INFO.TYPE_NUMERIC)
        {
            SymbolInfo ret_val = new SymbolInfo();
            ret_val.dblValue = -evalLeft.dblValue;
            ret_val.type = TYPE_INFO.TYPE_NUMERIC;
            ret_val.symbolName = "";
            return ret_val;

        }
        else
        {
            throw new Exception("Type mismatch");
        }
    }

    @Override
    public TYPE_INFO visit(UnaryMinus minus, COMPILATION_CONTEXT context) throws Exception {
        TYPE_INFO evalRight = minus.getRight().accept(this, context);

        if (evalRight == TYPE_INFO.TYPE_NUMERIC)
        {
            return evalRight;
        }
        else
        {
            throw new Exception("Type mismatch failure");

        }
    }

    @Override
    public SymbolInfo visit(RelationalExp relationalExp, RUNTIME_CONTEXT context) throws Exception {
        SymbolInfo eval_left = relationalExp.left.accept(this, context);
        SymbolInfo eval_right = relationalExp.right.accept(this, context);

        SymbolInfo ret_val = new SymbolInfo();
        if (eval_left.type == TYPE_INFO.TYPE_NUMERIC &&
                eval_right.type == TYPE_INFO.TYPE_NUMERIC)
        {

            ret_val.type = TYPE_INFO.TYPE_BOOL;
            ret_val.symbolName = "";

            if (relationalExp.op == RELATION_OPERATOR.TOK_EQ)
                ret_val.bolValue = eval_left.dblValue == eval_right.dblValue;
            else if (relationalExp.op  == RELATION_OPERATOR.TOK_NEQ)
                ret_val.bolValue = eval_left.dblValue != eval_right.dblValue;
            else if (relationalExp.op  == RELATION_OPERATOR.TOK_GT)
                ret_val.bolValue = eval_left.dblValue > eval_right.dblValue;
            else if (relationalExp.op  == RELATION_OPERATOR.TOK_GTE)
                ret_val.bolValue = eval_left.dblValue >= eval_right.dblValue;
            else if (relationalExp.op  == RELATION_OPERATOR.TOK_LTE)
                ret_val.bolValue = eval_left.dblValue <= eval_right.dblValue;
            else if (relationalExp.op  == RELATION_OPERATOR.TOK_LT)
                ret_val.bolValue = eval_left.dblValue < eval_right.dblValue;


            return ret_val;

        }
        else if (eval_left.type == TYPE_INFO.TYPE_STRING &&
                eval_right.type == TYPE_INFO.TYPE_STRING)
        {

            ret_val.type = TYPE_INFO.TYPE_BOOL;
            ret_val.symbolName = "";

            if (relationalExp.op == RELATION_OPERATOR.TOK_EQ)
            {
                ret_val.bolValue =
                        eval_left.strValue.equals(eval_right.strValue);

            }
            else if (relationalExp.op == RELATION_OPERATOR.TOK_NEQ)
            {
                ret_val.bolValue = !(eval_left.strValue.equals(eval_right.strValue));

            }
            else
            {
                ret_val.bolValue = false;

            }


            return ret_val;

        }
        if (eval_left.type == TYPE_INFO.TYPE_BOOL &&
                eval_right.type == TYPE_INFO.TYPE_BOOL)
        {

            ret_val.type = TYPE_INFO.TYPE_BOOL;
            ret_val.symbolName = "";

            if (relationalExp.op  == RELATION_OPERATOR.TOK_EQ)
                ret_val.bolValue = eval_left.bolValue == eval_right.bolValue;
            else if (relationalExp.op  == RELATION_OPERATOR.TOK_NEQ)
                ret_val.bolValue = eval_left.bolValue != eval_right.bolValue;
            else
            {
                ret_val.bolValue = false;

            }
            return ret_val;

        }
        return null;
    }

    @Override
    public TYPE_INFO visit(RelationalExp relationalExp, COMPILATION_CONTEXT context) throws Exception {
        return null;
    }

    @Override
    public TYPE_INFO visit(LogicalExp logicalExp, COMPILATION_CONTEXT context) throws Exception {
        return null;
    }

    @Override
    public SymbolInfo visit(LogicalExp logicalExp, RUNTIME_CONTEXT context) throws Exception {
        SymbolInfo eval_left = logicalExp.left.accept(this, context);
        SymbolInfo eval_right = logicalExp.right.accept(this, context);

        if (eval_left.type == TYPE_INFO.TYPE_BOOL &&
                eval_right.type == TYPE_INFO.TYPE_BOOL)
        {
            SymbolInfo ret_val = new SymbolInfo();
            ret_val.type = TYPE_INFO.TYPE_BOOL;
            ret_val.symbolName = "";

            if (logicalExp.op == TOKEN.TOK_AND)
                ret_val.bolValue = ( eval_left.bolValue && eval_right.bolValue);
            else if (logicalExp.op == TOKEN.TOK_OR)
                ret_val.bolValue = (eval_left.bolValue || eval_right.bolValue);
            else
            {
                return null;

            }
            return ret_val;

        }

        return null;
    }

    @Override
    public SymbolInfo visit(LogicalNot logicalNot, RUNTIME_CONTEXT context) throws Exception {
        SymbolInfo eval_left = logicalNot.exp.accept(this, context);


        if (eval_left.type == TYPE_INFO.TYPE_BOOL)
        {
            SymbolInfo ret_val = new SymbolInfo();
            ret_val.type = TYPE_INFO.TYPE_BOOL;
            ret_val.symbolName = "";
            ret_val.bolValue = !eval_left.bolValue;
            return ret_val;
        }
        else
        {
            return null;

        }
    }

    @Override
    public TYPE_INFO visit(LogicalNot logicalNot, COMPILATION_CONTEXT context) throws Exception {
        return null;
    }

}
