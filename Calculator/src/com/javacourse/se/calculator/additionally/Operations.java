package com.javacourse.se.calculator.additionally;

public class Operations {

    public double addition(double a, double b) {
        return a + b;
    }

    public double subtraction(double a, double b) {
        return a - b;
    }

    public double multiplication(double a, double b) {
        return a * b;
    }

    public double division(double a, double b) {
        return a / b;
    }

    public double squareRoot(double a) {
        return Math.sqrt(a);
    }

    public double getSin(double a) {
        return Math.sin(Math.toRadians(a));
    }
}
