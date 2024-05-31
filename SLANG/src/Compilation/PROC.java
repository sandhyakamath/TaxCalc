package Compilation;

import Context.RUNTIME_CONTEXT;
import Lexer.SymbolInfo;
import Scope.SymbolTable;
import Visitor.IExpressionVisitor;

public abstract class PROC {
    public abstract SymbolTable execute(IExpressionVisitor visitor, RUNTIME_CONTEXT cont) throws Exception;
    // public abstract boolean Compile(DNET_EXECUTABLE_GENERATION_CONTEXT cont);
}
