package Builder;

import AST.Expression;
import ASTStatements.Statement;
import Compilation.Procedure;
import Context.COMPILATION_CONTEXT;
import Lexer.SymbolInfo;
import Lexer.TYPE_INFO;
import Scope.SymbolTable;

import java.util.ArrayList;

public class ProcedureBuilder extends AbstractBuilder{
    private String procName = "";
    COMPILATION_CONTEXT context = null;
    ArrayList mFormals = null;
    ArrayList mStatements = new ArrayList();
    TYPE_INFO info = TYPE_INFO.TYPE_ILLEGAL;

    public ProcedureBuilder(String name, COMPILATION_CONTEXT _ctx)
    {
        context = _ctx;
        procName = name;
    }

    public boolean addLocal(SymbolInfo info)
    {
        context.getTable().add(info);
        return true;
    }

    public TYPE_INFO typeCheck(Expression e) throws Exception {
          return e.typeCheck(context);

    }
    public void addStatement(Statement st)
    {
        mStatements.add(st);
    }

    public SymbolInfo getSymbol(String strname)
    {
        return context.getTable().get(strname);
    }

    public boolean checkProto(String name)
    {
        return true;

    }

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public Procedure getProcedure()
    {
        Procedure ret = new Procedure(procName,
                mStatements, context.getTable(), info);

        return ret;
    }

    public SymbolTable getTable()
    {
        return context.getTable();
    }

    public COMPILATION_CONTEXT getContext()
    {
       return context;
    }

}
