package com.godel.engine.JNI;

import com.godel.common.ComputationContext;
import com.godel.dto.TaxDTO;

public class SeniorCitizenFemaleJNICaller {
    // load the shared library
    static {
        System.loadLibrary("native");
    }
    // declare native method that receives no arguments and returns void
    private native double computeTax(TaxDTO taxDTO);

    public boolean nativeMethodCall(ComputationContext computationContext) {
        TaxDTO taxDTO = (TaxDTO) computationContext.get("tax_cargo");
        taxDTO.setLiability(new SeniorCitizenFemaleJNICaller().computeTax(taxDTO));
        return true;
    }

    public static void main(String[] args) {
        TaxDTO taxDTO = new TaxDTO();
        taxDTO.setBasic(1000);
        taxDTO.setAllowance(100);
        taxDTO.setDA(10);
        taxDTO.setHRA(1);

        taxDTO.setSurcharge(1);
        taxDTO.setCESS(1);
        taxDTO.setDeduction(1);

        System.out.println(new SeniorCitizenFemaleJNICaller().computeTax(taxDTO));
    }
}
