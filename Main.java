package com.ariyanCod;

import java.util.*;

public class Main {
    //        Ariyan Bostan
    //        Foruzan Hashemi
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("numberOfState:");
        int numberOfState = scanner.nextInt();
        String[] nameStates = new String[numberOfState];
        additem(nameStates, scanner, " name state ");

        System.out.println("-----------");

        System.out.print("enter number of character:");
        int numberOfCharacter = scanner.nextInt();
        String[] nameOfCharater = new String[numberOfCharacter + 1];
        additem(nameOfCharater, scanner, " name character ");
        nameOfCharater[0] = "landa";

        System.out.println("-----------");

        System.out.print("select start state :");
        String startState = checkingFirstState(nameStates, scanner);

        System.out.println("-----------");

        System.out.print("enter number of final state :");
        int numberOfFinalState = checkingNumberOfFinalState(numberOfState, scanner);
        String[] nameOfFinalStates = new String[numberOfFinalState];
        checkingNameFinalState(nameStates, nameOfFinalStates, numberOfFinalState, scanner);

        System.out.println("-----------");

        AddMane addMane = new AddMane(nameStates, nameOfCharater);
        System.out.println("enter manes :>");
        addMane(addMane, scanner);
//        System.out.println(addMane);
        ConvertingToDFA converting = new ConvertingToDFA(addMane.getLists(), startState, nameOfCharater);
        converting.convert();
        PrintDTrance print = new PrintDTrance(converting, nameOfFinalStates, startState);
        print.print();

    }

    private static void additem(String[] strings, Scanner scanner, String string) {

        if (string.equals(" name character ")) {
            for (int i = 1; i < strings.length; i++) {
                System.out.print("enter " + string + (i) + ":");
                strings[i] = scanner.next();
            }
        } else
            for (int i = 0; i < strings.length; i++) {
                System.out.print("enter " + string + (i + 1) + ":");
                strings[i] = scanner.next();
            }

    }

    private static String checkingFirstState(String[] nameStates, Scanner scanner) {
        String startState;
        A:
        while (true) {
            startState = scanner.next();
            for (int i = 0; i < nameStates.length; i++)
                if (startState.equals(nameStates[i]))
                    break A;
            System.out.println("you dont have name state please try again.");
        }
        return startState;
    }

    private static int checkingNumberOfFinalState(int numberOfState, Scanner scanner) {
        int numberOfFinalState;
        while (true) {
            numberOfFinalState = scanner.nextInt();
            if (numberOfFinalState <= numberOfState && numberOfFinalState > 0)
                break;
            System.out.println("number of state equals " + numberOfState +
                    " please select between 1 to " + numberOfState);
        }
        return numberOfFinalState;
    }

    private static void checkingNameFinalState(String[] nameStates, String[] nameOfFinalStates
            , int numberOfFinalState, Scanner scanner) {
        int index = 0;
        while (true) {
            System.out.print("enter name final state :");
            String string = scanner.next();
            for (int i = 0; i < nameStates.length; i++) {
                if (string.equals(nameStates[i])) {
                    nameOfFinalStates[index++] = string;
                }
            }
            if (index >= numberOfFinalState) {
                break;
            }
            System.out.println("dont have state please try again");


        }
    }

    private static void addMane(AddMane converting, Scanner scanner) {
        while (true) {
            System.out.print("enter first state:");
            String start = scanner.next();
            if (start.equalsIgnoreCase("finish"))
                break;
            System.out.print("enter charachter:");
            String character = scanner.next();
            System.out.print("enter end state:");
            String end = scanner.next();
            converting.add(start, character, end);
            System.out.println("------------------");
        }
    }
}