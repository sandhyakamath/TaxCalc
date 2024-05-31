package ASTStatements;

import AST.Variable;
import Context.RUNTIME_CONTEXT;
import Lexer.SymbolInfo;
import Visitor.IExpressionVisitor;

public class VariableDeclStatement extends Statement{

    SymbolInfo info = null;
    Variable var = null;
    public VariableDeclStatement(SymbolInfo info)
    {
        this.info = info;
    }

    @Override
    public SymbolInfo execute(IExpressionVisitor visitor, RUNTIME_CONTEXT context) throws Exception {
        context.getTable().add(info);
        var = new Variable(info);
        return null;
    }
}
