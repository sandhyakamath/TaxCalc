package com.godel.engine.scripts;

import com.godel.dto.TaxDTO;
import com.godel.common.ComputationContext;

import java.util.HashMap;
import java.util.Map;

public class PyInvoker {
    public static boolean Invoke(String filename , ComputationContext context)  {
        final Map<String, Object> map = new HashMap<String,Object>();
        TaxDTO taxDTO = (TaxDTO) context.get("tax_cargo");
        map.put("result","");
        map.put("tax_cargo",taxDTO);
        ScriptingInterpreter sp = new ScriptingInterpreter(filename);
        try {
            boolean bRet = sp.Execute(map);
            if ( bRet == false) {
                System.out.println("Error File");
                return false;
            }
            /*String code= (String) map.get("RET_CODE");
            if ( code == null) {
                return false;
            }*/
           // System.out.println(code);
            double liability = (double) map.get("result");
            taxDTO.setLiability(liability);
            return true;
        }
        catch(Exception e ) {
            System.out.println("Exception caught.....");
            return false;

        }

    }


}
