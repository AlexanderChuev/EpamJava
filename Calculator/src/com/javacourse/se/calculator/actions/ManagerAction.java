package com.javacourse.se.calculator.actions;

import com.javacourse.se.calculator.additionally.Operations;
import com.javacourse.se.calculator.additionally.Printer;
import com.javacourse.se.calculator.additionally.SingleScanner;

public abstract class ManagerAction implements IAction {

    private SingleScanner scanner = new SingleScanner();
    private Printer printer = new Printer();
    private Operations operations = new Operations();
    private final String ENTER_VARIABLE = "Enter variable";
    private final String ENTER_FIRST_VARIABLE = "Enter first variable";
    private final String ENTER_SECOND_VARIABLE = "Enter second variable";
    private final String RESULT = "Result:";

    @Override
    public void makeOperation() {}

    public SingleScanner getScanner() {
        return scanner;
    }

    public Printer getPrinter() {
        return printer;
    }

    public String getENTER_VARIABLE() {
        return ENTER_VARIABLE;
    }

    public String getENTER_FIRST_VARIABLE() {
        return ENTER_FIRST_VARIABLE;
    }

    public String getENTER_SECOND_VARIABLE() {
        return ENTER_SECOND_VARIABLE;
    }

    public Operations getOperations() {
        return operations;
    }

    public String getRESULT() {
        return RESULT;
    }
}
