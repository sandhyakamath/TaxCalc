package com.godel.engine.command;

import com.godel.dto.TaxDTO;
import com.godel.engine.ComputationContext;

public class SeniorCitizenFemaleCommand extends BaseComputationCommand {

    @Override
    public boolean execute(ComputationContext context) {
        TaxDTO taxDTO = (TaxDTO) context.get("tax_cargo");
        double accumulated = taxDTO.getBasic() + taxDTO.getAllowance() + taxDTO.getDA() + taxDTO.getHRA();
        double deducted = taxDTO.getDeduction() + taxDTO.getCESS() + taxDTO.getSurcharge();
        double net = accumulated - deducted;
        taxDTO.setLiability(net * 0.1);
        return true;
    }
}
