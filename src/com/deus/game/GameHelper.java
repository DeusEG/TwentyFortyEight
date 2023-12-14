package com.deus.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameHelper {

    public List<Integer> moveAndMergeEqual(List<Integer> list) {
       int size = list.size();
       List<Integer> currentList;
       List<Integer> mergedList = new ArrayList<>();

       if (list.isEmpty()) {
           return list;
       }

       currentList = delNullElement(list, size);

       for (int i = 0; i < currentList.size() - 1; i++) {
           if (currentList.get(i) != null && Objects.equals(currentList.get(i), currentList.get(i + 1))) {
               currentList.set(i, currentList.get(i) * 2);
               currentList.remove(i + 1);
               currentList.add(null);
           }
       }

       for (int j = 0; j < list.size(); j++) {
           if (j < currentList.size()) {
               mergedList.add(currentList.get(j));
           } else {
               mergedList.add(null);
           }
       }

       return mergedList;
   }

   private List<Integer> delNullElement (List<Integer> list, Integer size) {
       List<Integer> currentList = new ArrayList<>();
       for (int i = 0; i < size; i++) {
           if (list.get(i) != null) {
               currentList.add(list.get(i));
           }
       }
       return currentList;
   }
}