package com.zaraev.epam;

public class Main {
    public static void main(String[] args) {
        var mainHelper = new MainHelper();
        mainHelper.callRaceCondition();
        mainHelper.callsSynchronizedRaceCondition();
        mainHelper.breakingTheDeadlock();
        mainHelper.deadlock();
        mainHelper.generateChat();
    }
}