package com.javacourse.se.calculator.menu;

import com.javacourse.se.calculator.actions.impl.*;

import java.util.ArrayList;

public class MenuBuilder {

    private final String PROGRAM_NAME = "Hello, you're in the program calculator";
    private final String WANT_TO_QUIT = "Enter 'y' if you want to stop the application or any other if not";

    public ArrayList<Menu> createMenu(){

        ArrayList<Menu> menuList = new ArrayList<>();

        Menu add = new Menu(1, "ADD", new AddAction());
        Menu subtract = new Menu(2,"SUBTRACT", new SubtractAction());
        Menu multiply = new Menu(3,"MULTIPLY", new MultiplyAction());
        Menu divide = new Menu(4, "DIVIDE", new DivideAction());
        Menu squareRoot = new Menu(5, "SQUARE ROOT", new SquareRootAction());
        Menu sine = new Menu(6,"SINE", new Sine());

        menuList.add(add);
        menuList.add(subtract);
        menuList.add(multiply);
        menuList.add(divide);
        menuList.add(squareRoot);
        menuList.add(sine);

        return menuList;
    }

    public String getPROGRAM_NAME() {
        return PROGRAM_NAME;
    }

    public String getWANT_TO_QUIT() {
        return WANT_TO_QUIT;
    }
}
