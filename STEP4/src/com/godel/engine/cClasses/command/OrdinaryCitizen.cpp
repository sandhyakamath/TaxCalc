

 class OrdinaryCitizen {

    public:
     double compute(double basic, double allowance, double da, double hra, double deduction, double cess, double surcharge) {
        double accumulated = basic + allowance + da + hra;
        double deducted = deduction + cess + surcharge;
        double net = accumulated - deducted;
        return net * 0.5;
    }
};