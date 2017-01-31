package com.javacourse.se.calculator.main;

import com.javacourse.se.calculator.additionally.Printer;
import com.javacourse.se.calculator.additionally.SingleScanner;
import com.javacourse.se.calculator.menu.Menu;
import com.javacourse.se.calculator.menu.MenuBuilder;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {

    private static final String EXCEPTION_MESSAGE = "You entered an invalid value";

    public static void main(String[] args) {

        SingleScanner singleScanner = new SingleScanner();

        MenuBuilder menuBuilder = new MenuBuilder();
        ArrayList<Menu> listMenu = menuBuilder.createMenu();

        Printer printer = new Printer();
        printer.printMessage(menuBuilder.getPROGRAM_NAME());

        String exit = "";

        while (!exit.equals("y")) {

            printer.print(listMenu);
            int enteredValue = singleScanner.getEnteredValue();

            if (enteredValue < 1 || enteredValue > listMenu.size()) {
                printer.printExceptionMessage(EXCEPTION_MESSAGE);
            } else {

                for (Menu menu : listMenu) {
                    if (menu.getNumber() == enteredValue) {
                        try {
                            menu.getOperation().makeOperation();
                        } catch (InputMismatchException e) {
                            printer.printExceptionMessage(EXCEPTION_MESSAGE);
                        }
                    }
                }
            }
            printer.printMessage(menuBuilder.getWANT_TO_QUIT());
            exit = singleScanner.getExitValue();
        }
        singleScanner.closeScanner();
    }
}
