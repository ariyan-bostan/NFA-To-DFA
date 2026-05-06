package com.ariyanCod;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AddMane {
    private List<List<String>>lists=new LinkedList<>();
    private String[] nameStates;
    private String[]nameCharacter;
    public AddMane(String[]nameStates, String[]nameCharacter){
        this.nameStates=nameStates;
        this.nameCharacter=nameCharacter;
    }
    public void add(String nameState1,String character,String nameState2 ){
        checking(nameState1,nameStates);
        checking(character,nameCharacter);
        checking(nameState2,nameStates);

       for (int i=0;i<lists.size();i++)
           if (lists.get(i).get(0).equals(nameState1) &&lists.get(i).get(1).equals(character)) {
               lists.get(i).add(nameState2);
               return;
           }
        List<String>newList=new LinkedList<>();
        newList.add(nameState1);
        newList.add(character);
        newList.add(nameState2);
        lists.add(newList);
    }
    private void checking(String string,String[]strings){
        A:while (true){
            for (int i=0;i<strings.length;i++)
                if (string.equals(strings[i]))
                    break A;
            throw new IllegalStateException("Massege ==>** don have "+string+" please try agian **");
        }
    }
    @Override
    public String toString(){
        return lists.toString();
    }

    public List<List<String>> getLists() {
        return lists;
    }
}
