package com.javacourse.se.calculator.actions.impl;

import com.javacourse.se.calculator.actions.ManagerAction;

public class DivideAction extends ManagerAction {

    @Override
    public void makeOperation() {
        getPrinter().printMessage(getENTER_FIRST_VARIABLE());
        double firstValue = getScanner().getVariableValue();

        getPrinter().printMessage(getENTER_SECOND_VARIABLE());
        double secondValue =getScanner().getVariableValue();

        getPrinter().printMessage(getRESULT());
        getPrinter().printResul(getOperations().division(firstValue,secondValue));
    }
}
