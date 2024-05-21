result = "false"
taxDTO = tax_cargo
accumulated = taxDTO.getBasic() + taxDTO.getAllowance() + taxDTO.getDA() + taxDTO.getHRA()
deducted = taxDTO.getDeduction() + taxDTO.getCESS() + taxDTO.getSurcharge()
net = accumulated - deducted
taxDTO.setLiability(net * 0.5)
result = "true"