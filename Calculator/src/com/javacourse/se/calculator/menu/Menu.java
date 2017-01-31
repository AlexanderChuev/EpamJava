package com.javacourse.se.calculator.menu;

import com.javacourse.se.calculator.actions.IAction;

public class Menu {

    private int number;
    private String name;
    private IAction operation;

    public Menu(int number, String name, IAction operation) {
        this.number = number;
        this.name = name;
        this.operation = operation;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public IAction getOperation() {
        return operation;
    }
}
