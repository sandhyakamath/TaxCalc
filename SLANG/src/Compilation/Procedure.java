package Compilation;

import ASTStatements.Statement;
import Context.COMPILATION_CONTEXT;
import Context.RUNTIME_CONTEXT;
import Lexer.SymbolInfo;
import Lexer.TYPE_INFO;
import Scope.SymbolTable;
import Visitor.IExpressionVisitor;

import java.util.ArrayList;

public class Procedure extends PROC {
    public String mName;
    public ArrayList mFormals = null;
    public ArrayList mStatements = null;
    public SymbolTable mLocals = null;
    public SymbolInfo returnValue = null;
    public TYPE_INFO type = TYPE_INFO.TYPE_ILLEGAL;

    public Procedure(String name,
                     ArrayList stats,
                     SymbolTable locals,
                     TYPE_INFO type)
    {
        mName = name;
        mFormals = null;
        mStatements = stats;
        mLocals = locals;
        this.type = type;
    }

    public ArrayList getmFormals() {
        return mFormals;
    }

    public void setmFormals(ArrayList mFormals) {
        this.mFormals = mFormals;
    }

    public TYPE_INFO getType() {
        return type;
    }

    public void setType(TYPE_INFO type) {
        this.type = type;
    }

    public TYPE_INFO TypeCheck(COMPILATION_CONTEXT cont)
    {

        return TYPE_INFO.TYPE_NUMERIC;
    }


    public boolean Compile()
    {

        for (Object e1  :  mStatements)
        {
            Statement stmt = (Statement) e1;
            //stmt.execute();
        }

        return true;

    }

    @Override
    public SymbolTable execute(IExpressionVisitor visitor, RUNTIME_CONTEXT context) throws Exception {

        for (Object stmt : mStatements) {
            Statement s = (Statement) stmt;
            s.execute(visitor, context);
        }

        return context.getTable();

    }
}
