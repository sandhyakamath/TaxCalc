package com.godel.dto;

public class TaxDTO {
    int id;
    String name;
    int age;
    char sex;
    String location;
    double basic;
    double da;
    double hra;
    double cess;
    double allowance;
    double deduction;
    double surcharge;
    double liability;

    public double getLiability() {
        return liability;
    }

    public void setLiability(double liability) {
        this.liability = liability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getBasic() {
        return basic;
    }

    public void setBasic(double basic) {
        this.basic = basic;
    }

    public double getDA() {
        return da;
    }

    public void setDA(double da) {
        this.da = da;
    }

    public double getHRA() {
        return hra;
    }

    public void setHRA(double hra) {
        this.hra = hra;
    }

    public double getCESS() {
        return cess;
    }

    public void setCESS(double cess) {
        this.cess = cess;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    public double getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(double surcharge) {
        this.surcharge = surcharge;
    }
}