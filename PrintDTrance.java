package com.ariyanCod;

import java.util.*;

public class PrintDTrance {
    private ConvertingToDFA toDFA;
    private String[] strings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private Map<String, List<String>> map;
    private String[]nameOfFinalState;
    private String startState;


    public PrintDTrance(ConvertingToDFA toDFA,String[]nameOfFinalState,String startState) {
        this.toDFA = toDFA;
        map = new HashMap<>();
        this.nameOfFinalState=nameOfFinalState;
        this.startState=startState;

    }

    public void print() {
        String[][] dT = new String[toDFA.getMainItems().size() + 1][toDFA.getNameOfcharacter().length];
        System.out.println("\n* * * * * * * * * * * * \t Convert to DFA \t * * * * * * * * * * * * \n");

        for (int i = 0; i < toDFA.getMainItems().size(); i++) {
            map.put(strings[i], toDFA.getMainItems().get(i));

        }
        for (int i = 1; i < toDFA.getNameOfcharacter().length; i++)
            dT[0][i] = toDFA.getNameOfcharacter()[i];
        int index = 1;
        for (String s : map.keySet())
            dT[index++][0] = s;
        List<String> list = new LinkedList<>();
        int current = toDFA.getMainItems().size()*2;
        int index2 = 0;
        while (current-- > 0) {
            for (int i = 0; i < toDFA.getMainItems().size(); i++) {
                if (index2 < toDFA.getItems().size()) {

                    var boli = check(toDFA.getMainItems().get(i), toDFA.getItems().get((index2)));
                    if (!boli) {
                        list.add(strings[i]);
                        index2++;
                    }

                }
            }

        }
        int index3=1;
        for (int i=1;i<toDFA.getMainItems().size()+1;i++)
            for (int j=1;j<toDFA.getNameOfcharacter().length;j++)
                if (index3<toDFA.getItems().size())
                    dT[i][j]=list.get(index3++);
        System.out.println("table DTrance =>"+Arrays.deepToString(dT));
        List<String>stringList= List.of(nameOfFinalState);
        List<String>newList=new LinkedList<>();
        for (int i = 0; i < toDFA.getMainItems().size(); i++) {
                var boli = check2(toDFA.getMainItems().get(i), stringList);
                if (boli) {
                    newList.add(strings[i]);
                }
        }
        System.out.println("state start state in dfa =>"+dT[1][0]);
        System.out.println("state final state in dfa =>"+newList);
    }

    private boolean check(List<String> items, List<String> zapas) {
        if (items.size()==0&&zapas.size()==0)
            return false;
        int current = items.size();
        A:
        while (current-- > 0) {
            for (int i = 0; i < items.size(); i++) {
                if (items.size() == zapas.size()) {
                    int current2 = zapas.size();
                    int tu = 0;
                    int index = 0;
                    while (current2-- > 0) {
                        for (int j = 0; j < zapas.size(); j++) {
                            if (items.get(j).equals(zapas.get(index))) {
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
    private boolean check2(List<String> items, List<String> zapas) {

        int current = zapas.size();
        int index = 0;
        A:
        while (current-- > 0) {
            for (int i = 0; i < items.size(); i++) {
//                if (items.size() == zapas.size()) {
                int current2 = items.size();
                int tu = 0;
                    while (current2-- > 0) {
                        for (int j = 0; j < items.size(); j++) {
                            if (items.get(j).equals(zapas.get(index))) {
                                tu++;
                            }
                        }
                    }
                    if (tu>0) {
                        return true;
                    }
//                }
            }
            index++;
        }
        return false;
    }
}
