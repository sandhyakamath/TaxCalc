package com.godel.engine.JNI;

import com.godel.common.ComputationContext;
import com.godel.dto.TaxDTO;

public class SeniorCitizenJNICaller {
    // load the shared library
    static {
        System.loadLibrary("native");
    }
    // declare native method that receives no arguments and returns void
    private native double computeTax(TaxDTO taxDTO);

    public boolean nativeMethodCall(ComputationContext computationContext) {
        TaxDTO taxDTO = (TaxDTO) computationContext.get("tax_cargo");
        taxDTO.setLiability(new SeniorCitizenJNICaller().computeTax(taxDTO));
        return true;
    }
}
