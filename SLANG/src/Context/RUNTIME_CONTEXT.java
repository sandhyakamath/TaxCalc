package Context;

///////////////////////////////////////////
// Class that stores the stack frame
//

import Compilation.TModule;
import Scope.SymbolTable;

public class RUNTIME_CONTEXT {

    private SymbolTable table;
    private TModule prog = null;

    public RUNTIME_CONTEXT() {
        table = new SymbolTable();
    }

    public RUNTIME_CONTEXT(TModule pgrm, SymbolTable tb)
    {
        table = tb;
        prog = pgrm;
    }

    public TModule getProgram()
    {
        return prog;
    }

    public SymbolTable getTable() {
        return table;
    }

    public void setTable(SymbolTable table) {
        this.table = table;
    }
}
