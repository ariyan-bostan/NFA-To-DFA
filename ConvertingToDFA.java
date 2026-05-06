package com.ariyanCod;

import java.util.*;

public class ConvertingToDFA {
    private List<List<String>> listsStates;
    private List<List<String>> mainItems = new LinkedList<>();
    private List<List<String>> items = new LinkedList<>();
    private List<String> listLandaClosuresStartState = new LinkedList<>();
    private String[] nameOfcharacter;
    private String firstState;


    public ConvertingToDFA(List<List<String>> listsStates, String startState, String[] nameOfCharacter) {
        this.listsStates = listsStates;
        this.firstState = startState;
        this.nameOfcharacter = nameOfCharacter;

    }

    public void convert() {
        var getStartState = landaClosure(firstState);
        move();
    }

    private void move() {

        LinkedList<String> newList = new LinkedList<>();
//        System.out.println(Arrays.toString(nameOfcharacter));
        mainItems.add(listLandaClosuresStartState);
        items.add(listLandaClosuresStartState);
        int sizeCharacter = nameOfcharacter.length - 1;
        int indexCharacter = 1;
        int index = 0;
        int indexMainItem = 0;
        int mainCurrent = 0;
        while (mainCurrent++ < mainItems.size()) {
            while (sizeCharacter-- > 0) {
                Stack<String> zapas = new Stack<>();
                int current = mainItems.get(indexMainItem).size();
                while (current-- > 0) {
                    if (index > mainItems.get(indexMainItem).size())
                        index = 0;
                    for (int i = 0; i < listsStates.size(); i++)
                        if (listsStates.get(i).get(0).equals(mainItems.get(indexMainItem).get(index)) &&
                                listsStates.get(i).get(1).equals(nameOfcharacter[indexCharacter])) {
                            for (int j = 2; j < listsStates.get(i).size(); j++)
                                newList.add(listsStates.get(i).get(j));
//                            System.out.println("new list" + newList);
//                            System.out.println("landa :" + landaClosur2(newList));
                            var m = landaClosur2(newList);
                            for (int j = 0; j < landaClosur2(newList).size(); j++) {
                                var boli = checkLandaClosure(zapas, m.get(j));
                                if (boli)
                                    zapas.push(m.get(j));
                            }
//                            System.out.println("zapas:" + zapas);
                            while (!newList.isEmpty())
                                newList.remove();
                        }

                    index++;
                }
                var boli = check(mainItems, zapas);
                Stack<String> stack = zapas;
                if (boli)
                    mainItems.add(stack);
                items.add(stack);
//                System.out.println(mainItems);
                index = 0;
                indexCharacter++;
            }
            sizeCharacter = nameOfcharacter.length - 1;
            indexMainItem++;
            indexCharacter = 1;
        }
//        System.out.println("--------------");
//        System.out.println("mainItem:" + mainItems);
//        System.out.println("item:" + items);
    }

    private List<String> landaClosure(String startState) {
        for (int i = 0; i < listsStates.size(); i++)
            if (listsStates.get(i).get(0).equals(startState) && listsStates.get(i).get(1).equals("landa")) {
                listLandaClosuresStartState.add(startState);
                for (int j = 2; j < listsStates.get(i).size(); j++)
                    listLandaClosuresStartState.add(listsStates.get(i).get(j));
                break;
            }
        if (listLandaClosuresStartState.size() > 1) {
            int index = 1;
            while (index < listLandaClosuresStartState.size()) {
                for (int i = 0; i < listsStates.size(); i++)
                    if (listsStates.get(i).get(0).equals(listLandaClosuresStartState.get(index)) && listsStates.get(i).get(1).equals("landa")) {
                        for (int j = 2; j < listsStates.get(i).size(); j++) {
                            var boli = checkLandaClosure(listLandaClosuresStartState, listsStates.get(i).get(j));
                            if (boli)
                                listLandaClosuresStartState.add(listsStates.get(i).get(j));
                        }
                    }
                index++;
            }
        }
//        System.out.println(listLandaClosuresStartState);
        return listLandaClosuresStartState;
    }

    private List<String> landaClosur2(LinkedList<String> list) {
        List<String> newListLandaclosure2 = new LinkedList<>();
        int mainIndex = 0;
        while (mainIndex < list.size()) {
            newListLandaclosure2.add(list.get(mainIndex));
            A:
            for (int i = 0; i < listsStates.size(); i++) {
                if (listsStates.get(i).get(0).equals(list.get(mainIndex)) && listsStates.get(i).get(1).equals("landa")) {
                    for (int j = 2; j < listsStates.get(i).size(); j++)
                        newListLandaclosure2.add(listsStates.get(i).get(j));
                    break A;
                }
            }
            if (newListLandaclosure2.size() > 1) {
                int index = 1;
                while (index < newListLandaclosure2.size()) {
                    for (int i = 0; i < listsStates.size(); i++)
                        if (listsStates.get(i).get(0).equals(newListLandaclosure2.get(index)) && listsStates.get(i).get(1).equals("landa")) {
                            for (int j = 2; j < listsStates.get(i).size(); j++) {
                                var boli = checkLandaClosure(newListLandaclosure2, listsStates.get(i).get(j));
                                if (boli)
                                    newListLandaclosure2.add(listsStates.get(i).get(j));
                            }
                        }
                    index++;
                }
            }
            mainIndex++;
        }
        return newListLandaclosure2;
    }

    private boolean checkLandaClosure(List<String> listLandaClosuresStartState, String s) {
        for (int i = 0; i < listLandaClosuresStartState.size(); i++)
            if (listLandaClosuresStartState.get(i).equals(s))
                return false;
        return true;
    }

    private boolean check(List<List<String>> items, List<String> zapas) {

        int current = items.size();
        A:
        while (current-- > 0) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).size() == zapas.size()) {
                    int current2 = zapas.size();
                    int tu = 0;
                    int index = 0;
                    while (current2-- > 0) {
                        for (int j = 0; j < zapas.size(); j++) {
                            if (items.get(i).get(j).equals(zapas.get(index))) {
                                tu++;
                            }
                        }
                        index++;
                    }
                    if (tu == zapas.size()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public List<List<String>> getMainItems() {
        return mainItems;
    }

    public List<List<String>> getItems() {
        return items;
    }

    public String[] getNameOfcharacter() {
        return nameOfcharacter;
    }
}
