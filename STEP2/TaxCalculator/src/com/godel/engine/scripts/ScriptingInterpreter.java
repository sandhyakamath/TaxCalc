package com.godel.engine.scripts;

import org.python.core.PyFloat;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Map;

public class ScriptingInterpreter {
    private String _filename;
    public ScriptingInterpreter(String filename) {
        _filename = filename;
    }
    private PythonInterpreter createInterpreter(Map<String, Object> vars) {
        PythonInterpreter pi = new PythonInterpreter();
        Iterator<Map.Entry<String,Object>> it  =vars.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String,Object> ent = it.next();
            pi.set(ent.getKey(), ent.getValue());
        }
        return pi;
    }

    public boolean Execute(Map<String, Object>  vars) throws Exception{
        PythonInterpreter ps = createInterpreter(vars);
        FileInputStream is = new FileInputStream(_filename);
        if ( is == null) {
            System.out.println("Could not load File");
            return false;
        }
        try {
            ps.execfile(is);
        }
        catch(Exception e) {
            System.out.println("Warning....................Could not interpret");
            e.printStackTrace();
            return false;
        }
        String m = ps.get("result").asString();
        vars.put("result",m);
        return true;
    }

}
