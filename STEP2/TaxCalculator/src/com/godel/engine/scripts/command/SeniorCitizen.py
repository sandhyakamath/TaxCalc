taxDTO = tax_cargo
print(taxDTO.getBasic())
accumulated = taxDTO.getBasic() + taxDTO.getAllowance() + taxDTO.getDA() + taxDTO.getHRA()
deducted = taxDTO.getDeduction() + taxDTO.getCESS() + taxDTO.getSurcharge()
net = accumulated - deducted
result = net * 0.5
