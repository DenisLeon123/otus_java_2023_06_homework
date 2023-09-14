package org.example;

import org.example.core.Atm;

public class Main {
    public static void main(String[] args) {
        Atm atm = new Atm().defaultInit();
        System.out.println(atm.getBalance());
        atm.getBanknote(100);
        System.out.println(atm.getBalance());
    }
}