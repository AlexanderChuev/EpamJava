package com.javacourse.se.calculator.additionally;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SingleScanner {

    private Scanner scanner = new Scanner(System.in);

    public double getVariableValue(){
        if (scanner.hasNextDouble()){
            return scanner.nextDouble();
        } else {
            throw new InputMismatchException();
        }
    }

    public int getEnteredValue(){
        if (scanner.hasNextInt()){
            return scanner.nextInt();
        } else {
            throw new InputMismatchException();
        }
    }

    public String getExitValue(){
        if (scanner.hasNext()) {
            return scanner.next();
        } else {
            throw new InputMismatchException();
        }
    }

    public void closeScanner(){
        scanner.close();
    }

}
