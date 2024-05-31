package Compilation;

import Context.RUNTIME_CONTEXT;
import Lexer.SymbolInfo;
import Scope.SymbolTable;
import Visitor.IExpressionVisitor;

import java.util.ArrayList;

public class TModule extends CompilationUnit{

    private ArrayList mProcs = null;
    private ArrayList compiledProcs = null;
    // private ExeGenerator _exe = null;

    public TModule(ArrayList procs)
    {
        mProcs = procs;

    }
    public boolean createExecutable(String name)
    {

        // exe = new ExeGenerator(this,name);
        // Compile The module...
        // compile(null);
        // Save the Executable...
        // exe.Save();
        return true;
    }

    public boolean Compile( )
    {
       // compiled_procs = new ArrayList();
       /* foreach (Procedure p in m_procs)
        {
            DNET_EXECUTABLE_GENERATION_CONTEXT con = new DNET_EXECUTABLE_GENERATION_CONTEXT(this,p, _exe.type_bulder);
            compiled_procs.Add(con);
            p.Compile(con);

        }*/
        return true;

    }


    @Override
    public SymbolTable Execute(IExpressionVisitor visitor, RUNTIME_CONTEXT cont) throws Exception {
        Procedure p = find("Main");

        if (p != null)
        {

            return p.execute(visitor, cont);
        }

        return null;
    }

    /*public MethodBuilder _get_entry_point(String _funcname)
    {
        foreach (DNET_EXECUTABLE_GENERATION_CONTEXT u in compiled_procs)
        {
            if (u.MethodName.Equals(_funcname))
            {
                return u.MethodHandle;
            }

        }

        return null;


    }*/

    public Procedure find(String  str)
    {
        for (Object p : mProcs)
        {
           Procedure procedure = (Procedure) p;
            String pname = procedure.mName;

            if (pname.toUpperCase().compareTo(str.toUpperCase()) == 0)
                return procedure;

        }

        return null;

    }
}
