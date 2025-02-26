package org.iis.frandm;

public class Calculator {
    public double add(double n1, double n2){
        return n1+n2;
    }
    public double subtract(double n1, double n2){
        return n1-n2;
    }
    public double multiply(double n1, double n2){
        return n1*n2;
    }
    public double divide(double n1, double n2){
        if (n2==0){
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return n1/n2;
    }

}

