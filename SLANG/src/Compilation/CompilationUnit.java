package Compilation;

import Context.RUNTIME_CONTEXT;
import Lexer.SymbolInfo;
import Scope.SymbolTable;
import Visitor.IExpressionVisitor;

public abstract class CompilationUnit {
    public abstract SymbolTable Execute(IExpressionVisitor visitor, RUNTIME_CONTEXT cont) throws Exception;
    // public abstract boolean Compile(DNET_EXECUTABLE_GENERATION_CONTEXT cont);
}
