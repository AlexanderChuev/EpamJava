package com.javacourse.se.calculator.additionally;

import com.javacourse.se.calculator.menu.Menu;

import java.util.ArrayList;

public class Printer {

    public void print(ArrayList<Menu> menuList){
        for (Menu menu: menuList){
            System.out.println(menu.getNumber()+" "+menu.getName());
        }
    }

    public void printMessage(String message){
        System.out.println(message);
    }

    public void printResul(double result){
        System.out.println(result);
    }

    public void printExceptionMessage(String message){
        System.err.println(message);
    }
}
