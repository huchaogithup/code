package org.example;

import java.util.*;

public class DTest {

    public static void main(String[] args) {

        System.out.println(new DTest().way3(4).size());
        System.out.println(1);

//        for (int i = 0; i < 100; i++) {
//            int a = new Random().nextInt(10);
//            int b = new Random().nextInt(10) + 1;
//            if (!new HashSet<>(new DTest().p(b, a).detail).equals(new DTest().way2(b, a))) {
//                throw new RuntimeException("asd");
//            }
//        }
//        System.out.println(new DTest().p(2, 2).detail);
//        System.out.println();
    }


    public Set<List<Integer>> way2(int b, int a) {
        return p2(b, a, new ArrayList<>());
    }

    public List<List<List<Integer>>> way3(int n) {
        this.n = n;
        return p3(0);
    }

    int n;

    public List<List<List<Integer>>> p3(int n) {
        if (n == this.n) {
            List<List<List<Integer>>> res = new ArrayList<>();
            ArrayList<List<Integer>> lists = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                lists.add(new ArrayList<>());
            }
            res.add(lists);
            return res;
        }
        List<List<List<Integer>>> res = new ArrayList<>();
        for (int i = n; i < this.n; i++) {
            List<List<List<Integer>>> p1 = p3(n + 1);
            for (int i1 = 0; i1 < p1.size(); i1++) {
                List<List<Integer>> p1l = p1.get(i1);
                List<List<Integer>> p1res = new ArrayList<>();
                for (int i2 = 0; i2 < p1l.size(); i2++) {
                    p1res.add(new ArrayList<>(p1l.get(i2)));
                }
                p1res.get(i).add(n);
                res.add(p1res);
            }
        }

        return res;
    }


    public Set<List<Integer>> p2(int b, int a, List<Integer> nums) {
        Set<List<Integer>> res = new HashSet<>();
        if (b == 0) {
            ArrayList<Integer> p = new ArrayList<>(nums);
            p.sort((s1, s2) -> Integer.compare(s1, s2));
            res.add(p);
            return res;
        }
        for (int i = 0; i <= a; i++) {
            nums.add(i);
            Set<List<Integer>> sets = p2(b - 1, a - i, nums);
            res.addAll(sets);
            nums.removeLast();
        }
        return res;
    }


    public static class Info {
        int res;
        List<List<Integer>> detail = new ArrayList<>();
    }

    public Info p(int b, int a) {
        if (b == 0) {
            Info info = new Info();
            info.res = 1;
            info.detail.add(new ArrayList<>());
            return info;
        }
        Info res = new Info();

        Info p1 = p(b - 1, a);
        res.res += p1.res;
        for (int i = 0; i < p1.detail.size(); i++) {
            ArrayList<Integer> p = new ArrayList<>(p1.detail.get(i));
            p.addFirst(0);
            res.detail.add(p);
        }
        if (a >= b) {
            Info p2 = p(b, a - b);
            res.res += p2.res;
            for (int i = 0; i < p2.detail.size(); i++) {
                ArrayList<Integer> p = new ArrayList<>(p2.detail.get(i));
                for (int i1 = 0; i1 < p.size(); i1++) {
                    p.set(i1, p.get(i1) + 1);
                }
                res.detail.add(p);
            }
        }
        return res;
    }

}
