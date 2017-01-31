package com.javacourse.se.calculator.actions.impl;

import com.javacourse.se.calculator.actions.ManagerAction;

public class SquareRootAction extends ManagerAction {

    @Override
    public void makeOperation() {
        getPrinter().printMessage(getENTER_VARIABLE());
        double value = getScanner().getVariableValue();

        getPrinter().printMessage(getRESULT());
        getPrinter().printResul(getOperations().squareRoot(value));
    }
}
