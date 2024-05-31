package AST;

////////////////////////////////////////////////////////
// Expression is what evaluates for its value
//


import Builder.ProcedureBuilder;
import Context.COMPILATION_CONTEXT;
import Context.RUNTIME_CONTEXT;
import Lexer.SymbolInfo;
import Lexer.TYPE_INFO;
import Visitor.IExpressionVisitor;

public abstract class Expression {
    public abstract SymbolInfo accept(IExpressionVisitor visitor, RUNTIME_CONTEXT context) throws Exception;
    public abstract TYPE_INFO accept(IExpressionVisitor visitor, COMPILATION_CONTEXT context) throws Exception;

    public abstract TYPE_INFO typeCheck(COMPILATION_CONTEXT context) throws Exception;

}