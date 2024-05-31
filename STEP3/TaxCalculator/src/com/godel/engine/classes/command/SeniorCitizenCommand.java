package com.godel.engine.classes.command;

import com.godel.dto.TaxDTO;
import com.godel.common.ComputationContext;

public class SeniorCitizenCommand extends BaseComputationCommand {
    @Override
    public boolean execute(ComputationContext context) {
        TaxDTO taxDTO = (TaxDTO) context.get("tax_cargo");
        double accumulated = taxDTO.getBasic() + taxDTO.getAllowance() + taxDTO.getDA() + taxDTO.getHRA();
        double deducted = taxDTO.getDeduction() + taxDTO.getCESS() + taxDTO.getSurcharge();
        double net = accumulated - deducted;
        taxDTO.setLiability(net * 0.5);
        return true;
    }
}
