package com.godel.common.api;

import com.godel.dto.TaxDTO;
import com.godel.common.CommandDispatcher;
import com.godel.common.ComputationContext;

public class TaxComputationFacade {

    private static String computeArchetype(TaxDTO taxDTO) {
        if (taxDTO.getSex() == 'F' && taxDTO.getAge() > 59 ) {
            return "SeniorCitizenFemale";
        } else if (taxDTO.getAge() > 59 ){
            return "SeniorCitizen";
        } else if (taxDTO.getAge() < 18) {
            return "JuvenileCitizen";
        } else {
            return "OrdinaryCitizen";
        }
    }

    public static boolean compute(TaxDTO taxDTO) {
        String archetype = computeArchetype(taxDTO);
        ComputationContext context = new ComputationContext();
        context.put("tax_cargo", taxDTO);
        return CommandDispatcher.dispatch(context, archetype);
    }
}
