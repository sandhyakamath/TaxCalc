package com.godel.UI;

import com.godel.common.api.TaxComputationFacade;
import com.godel.dto.TaxDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaxCalcUI implements ActionListener {
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JLabel lblID = new JLabel("ID");
    private JTextField txtID = new JTextField(20);
    private JLabel lblName = new JLabel("Name");
    private JTextField txtName = new JTextField(20);
    private JLabel lblAge = new JLabel("Age");
    private JTextField txtAge = new JTextField(20);
    private JLabel lblSex = new JLabel("Sex");
    private JTextField txtSex = new JTextField(20);
    private JLabel lblLocation = new JLabel("Location");
    private JTextField txtLocation = new JTextField(20);
    private JLabel lblBasic = new JLabel("Basic");
    private JTextField txtBasic = new JTextField(20);
    private JLabel lblDA = new JLabel("DA");
    private JTextField txtDA = new JTextField(20);
    private JLabel lblHRA = new JLabel("HRA");
    private JTextField txtHRA = new JTextField(20);
    private JLabel lblAllowance = new JLabel("Allowance");
    private JTextField txtAllowance = new JTextField(20);
    private JLabel lblDeduction = new JLabel("Deduction");
    private JTextField txtDeduction = new JTextField(20);
    private JLabel lblCESS = new JLabel("CESS");
    private JTextField txtCESS = new JTextField(20);
    private JLabel lblSurcharge = new JLabel("Surcharge");
    private JTextField txtSurcharge = new JTextField(20);
    private JButton button = new JButton("Submit");
    private JLabel lblOutput = new JLabel("Output : 0");

    public TaxCalcUI() {
        initialise();
    }

    private void initialise() {
        panel.setBorder(BorderFactory.createEmptyBorder(30,100,130,100));
        frame.setSize(500,500);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tax App GUI");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        lblID.setBounds(10, 20, 80, 25);
        panel.add(lblID);
        txtID.setBounds(100, 20, 165, 25);
        panel.add(txtID);

        lblName.setBounds(10, 20, 80, 25);
        panel.add(lblName);
        txtName.setBounds(100, 20, 165, 25);
        panel.add(txtName);

        lblAge.setBounds(110, 20, 80, 25);
        panel.add(lblAge);
        txtAge.setBounds(200, 20, 165, 25);
        panel.add(txtAge);

        lblSex.setBounds(10, 30, 80, 25);
        panel.add(lblSex);
        txtSex.setBounds(100, 30, 165, 25);
        panel.add(txtSex);

        lblLocation.setBounds(110, 20, 80, 25);
        panel.add(lblLocation);
        txtLocation.setBounds(200, 20, 165, 25);
        panel.add(txtLocation);

        lblBasic.setBounds(110, 20, 80, 25);
        panel.add(lblBasic);
        txtBasic.setBounds(200, 20, 165, 25);
        panel.add(txtBasic);

        lblDA.setBounds(110, 20, 80, 25);
        panel.add(lblDA);
        txtDA.setBounds(200, 20, 165, 25);
        panel.add(txtDA);

        lblHRA.setBounds(110, 20, 80, 25);
        panel.add(lblHRA);
        txtHRA.setBounds(200, 20, 165, 25);
        panel.add(txtHRA);

        lblAllowance.setBounds(110, 20, 80, 25);
        panel.add(lblAllowance);
        txtAllowance.setBounds(200, 20, 165, 25);
        panel.add(txtAllowance);

        lblDeduction.setBounds(110, 20, 80, 25);
        panel.add(lblDeduction);
        txtDeduction.setBounds(200, 20, 165, 25);
        panel.add(txtDeduction);

        lblCESS.setBounds(110, 20, 80, 25);
        panel.add(lblCESS);
        txtCESS.setBounds(200, 20, 165, 25);
        panel.add(txtCESS);

        lblSurcharge.setBounds(110, 20, 80, 25);
        panel.add(lblSurcharge);
        txtSurcharge.setBounds(200, 20, 165, 25);
        panel.add(txtSurcharge);

        button.addActionListener(this);
        button.setBounds(200, 20, 165, 25);
        panel.add(button);
        lblOutput.setBounds(200, 20, 165, 25);
        panel.add(lblOutput);
        frame.pack();
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        TaxCalcUI ui = new TaxCalcUI();
        ui.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        lblOutput.setText("Calculating the TAX Liability.....");
        TaxDTO taxDTO = getEntityFromUI();
        viewHandler(taxDTO);
    }
    private TaxDTO getEntityFromUI() {
        TaxDTO tax = new TaxDTO();
        tax.setId(Integer.parseInt(txtID.getText()));
        tax.setName(txtSurcharge.getText());
        tax.setAge(Integer.parseInt(txtAge.getText()));
        tax.setSex(txtSex.getText().charAt(0));
        tax.setLocation(txtLocation.getText());
        tax.setBasic(Double.parseDouble(txtBasic.getText()));
        tax.setDA(Double.parseDouble(txtDA.getText()));
        tax.setHRA(Double.parseDouble(txtHRA.getText()));
        tax.setCESS(Double.parseDouble(txtCESS.getText()));
        tax.setAllowance(Double.parseDouble(txtAllowance.getText()));
        tax.setDeduction(Double.parseDouble(txtDeduction.getText()));
        tax.setSurcharge(Double.parseDouble(txtSurcharge.getText()));
        return tax;
    }

    void viewHandler(TaxDTO taxDTO) {
        boolean result = TaxComputationFacade.compute(taxDTO);
        if (result) {
            lblOutput.setText(String.valueOf(taxDTO.getLiability()));
        }
    }

}
