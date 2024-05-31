package Builder;

import Compilation.Procedure;
import Compilation.TModule;

import java.util.ArrayList;

public class TModuleBuilder {
    private ArrayList procs; // holds procedures
    private ArrayList protos=null; // holds prototypes

    /**
     *  Ctor
     */
    public TModuleBuilder() {
        procs = new ArrayList();
        protos = null;
    }

    /**
     *
     * @param p
     * @return
     */
    public boolean add(Procedure p) {
        procs.add(p);
        return true;
    }

    public TModule getProgram() {
        return new TModule(procs);
    }

    public Procedure getProc(String name) {
        for (Object p : procs) {
            Procedure procedure = (Procedure) p;
            if (procedure.mStatements.equals(name)) {
                return procedure;
            }

        }

        return null;

    }

}
