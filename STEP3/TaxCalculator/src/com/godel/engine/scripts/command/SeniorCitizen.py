result = "false"
taxDTO = tax_cargo
print(taxDTO.getBasic())
accumulated = taxDTO.getBasic() + taxDTO.getAllowance() + taxDTO.getDA() + taxDTO.getHRA()
deducted = taxDTO.getDeduction() + taxDTO.getCESS() + taxDTO.getSurcharge()
net = accumulated - deducted
taxDTO.setLiability(net * 0.2)
result = "true"
