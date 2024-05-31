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
import Lexer.SymbolInfo;
import Lexer.TYPE_INFO;

public interface IExpressionVisitor {

    SymbolInfo visit(NumericConstant num, RUNTIME_CONTEXT context) throws Exception;
    TYPE_INFO visit(NumericConstant num, COMPILATION_CONTEXT context);
    SymbolInfo visit(BooleanConstant bool, RUNTIME_CONTEXT context) throws Exception;
    TYPE_INFO visit(BooleanConstant bool, COMPILATION_CONTEXT context);
    SymbolInfo visit(StringLiteral str, RUNTIME_CONTEXT context) throws Exception;
    TYPE_INFO visit(StringLiteral str, COMPILATION_CONTEXT context);
    SymbolInfo visit(BinaryPlus plus, RUNTIME_CONTEXT context) throws Exception;
    TYPE_INFO visit(BinaryPlus plus, COMPILATION_CONTEXT context) throws Exception;
    SymbolInfo visit(Variable var, RUNTIME_CONTEXT context) throws Exception;
    TYPE_INFO visit(Variable var, COMPILATION_CONTEXT context);
    SymbolInfo visit(BinaryMinus minus, RUNTIME_CONTEXT context) throws Exception;
    TYPE_INFO visit(BinaryMinus minus, COMPILATION_CONTEXT context) throws Exception;
    SymbolInfo visit(BinaryMul mul, RUNTIME_CONTEXT context) throws Exception;
    TYPE_INFO visit(BinaryMul mul, COMPILATION_CONTEXT context) throws Exception;
    SymbolInfo visit(BinaryDiv div, RUNTIME_CONTEXT context) throws Exception;
    TYPE_INFO visit(BinaryDiv div, COMPILATION_CONTEXT context) throws Exception;
    SymbolInfo visit(UnaryPlus plus, RUNTIME_CONTEXT context) throws Exception;
    TYPE_INFO visit(UnaryPlus plus, COMPILATION_CONTEXT context) throws Exception;
    SymbolInfo visit(UnaryMinus minus, RUNTIME_CONTEXT context) throws Exception;
    TYPE_INFO visit(UnaryMinus minus, COMPILATION_CONTEXT context) throws Exception;

    SymbolInfo visit(RelationalExp relationalExp, RUNTIME_CONTEXT context) throws Exception;

    TYPE_INFO visit(RelationalExp relationalExp, COMPILATION_CONTEXT context) throws Exception;

    TYPE_INFO visit(LogicalExp logicalExp, COMPILATION_CONTEXT context) throws Exception;

    SymbolInfo visit(LogicalExp logicalExp, RUNTIME_CONTEXT context) throws Exception;

    SymbolInfo visit(LogicalNot logicalNot, RUNTIME_CONTEXT context) throws Exception;

    TYPE_INFO visit(LogicalNot logicalNot, COMPILATION_CONTEXT context) throws Exception;
}
