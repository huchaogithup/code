package org.example;

import java.util.*;

class FF {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4);
        List<List<List<Integer>>> res = new ArrayList<>();
        p(list, 0, new ArrayList<>(), res);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    private static void p(List<Integer> list, int i, List<List<Integer>> path, List<List<List<Integer>>> lists) {
        if (i == list.size()) {
            List<List<Integer>> p = new ArrayList<>();
            for (List<Integer> integers : path) {
                p.add(new ArrayList<>(integers));
            }
            lists.add(p);
            return;
        }
        List<Integer> group = new ArrayList<>();
        for (int i1 = i; i1 < list.size(); i1++) {
            group.add(list.get(i1));
            path.add(group);
            p(list,i1+1,path,lists);
            path.remove(path.size()-1);
        }
    }
}