package org.example;

import org.w3c.dom.Node;

import java.util.*;
import java.util.stream.*;

class Mainccc {
    public static void main(String[] args) {
        //System.out.println(split("aabbaaabbbasdabb", 0, 3));
        //System.out.println(new Mainccc().strangePrinter("addabbbcaa"));
        System.out.println(new Mainccc().strangePrinter("tbgtgb"));
        //System.out.println(new Mainccc().strangePrinter("dcbdbababadcbcabdbdcdbabdb"));
        //System.out.println(new Mainccc().strangePrinter("aaabbb"));
        //System.out.println(new Mainccc().strangePrinter("baacdddaaddaaaaccbddbcabdaabdbbcdcbbbacbddcabcaaa"));
//        getArr(6);
//        for (int i = 0; i < 1000; i++) {
//            int c = new Random().nextInt(5) + 1;
//            char[] arr = getArr(c);
//            if (new Mainccc().strangePrinter(new String(arr)) != c) {
//                System.out.println(new String(arr));
//                System.out.println(c);
//                System.out.println(new Mainccc().strangePrinter(new String(arr)));
//                throw new RuntimeException("asd");
//            }
//        }

//        for (int i = 0; i < 1000; i++) {
//            char[] array = Stream.generate(() -> new Random().nextInt(4) + 'a').limit(5).map(s->String.valueOf((char) s.intValue())).collect(Collectors.joining()).toCharArray();
//            System.out.println(i);
//            if(new Mainccc().strangePrinter(new String(array))!=baoli(new String(array))){
//                System.out.println(new String(array));
//                System.out.println(baoli(new String(array)));
//                System.out.println(new Mainccc().strangePrinter(new String(array)));
//                throw new RuntimeException("asd");
//            }
//        }


    }


    public static int baoli(String s) {
        int max = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) != s.charAt(i)) {
                max++;
            }
        }
        return p2(s, new char[s.length()], max);
    }

    private static int p2(String s, char[] chars, int max) {
        if (max == 0) {
            return 0;
        }
        if (new String(chars).equals(s)) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                for (int k = 'a'; k <= 'a' + 3; k++) {
                    char[] clone = chars.clone();
                    Arrays.fill(clone, i, j + 1, (char) k);
                    res = Math.min(res, p2(s, clone, max - 1) + 1);
                }
            }
        }
        return res;
    }


    private static char[] getArr(int i) {
        out:
        for (; ; ) {
            char[] arr = new char[10];
            Arrays.fill(arr, 'a');
            for (int j = 1; j < i; j++) {
                char c = (char) ('a' + j);
                int start = new Random().nextInt(arr.length - 1);
                int end = new Random().nextInt(arr.length - start - 1) + start;
                Arrays.fill(arr, start, end, c);
            }
            Set<Character> check = new HashSet<>();
            for (int i1 = 0; i1 < arr.length; i1++) {
                check.add(arr[i1]);
            }
            for (int j = 0; j < i; j++) {
                char c = (char) ('a' + j);
                if (!check.contains(c)) {
                    continue out;
                }
            }
            return arr;
        }

    }

    public int strangePrinter(String s) {
        return p(s, 0, s.length() - 1);
    }

    private int p(String s, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return 1;
        }
        List<Info> split = split(s, start, end);

        int res = Integer.MAX_VALUE;
        for (int f = 0; f < split.size(); f++) {
            int p = p(s, split.get(f).end + 1, end) + 1;
            List<List<List<Info>>> allCase = new ArrayList<>();
            pAllCase(split.subList(0, f + 1), 0, new ArrayList<>(), allCase);
            for (int i = 0; i < allCase.size(); i++) {
                int tmp = 0;
                List<List<Info>> case1 = allCase.get(i);
                for (int i1 = 0; i1 < case1.size(); i1++) {
                    List<Info> group = case1.get(i1);
                    tmp += p(s, group.getFirst().end + 1, group.getLast().start - 1);
                }

                for (int j = 1; j < case1.size(); j++) {
                    tmp += p(s, case1.get(j - 1).getLast().end + 1, case1.get(j).getFirst().start - 1);
                }
                res = Math.min(res, tmp + p);
            }

        }
        return res;
    }

    private void pAllCase(List<Info> split, int i, ArrayList<List<Info>> path, List<List<List<Info>>> res) {
        if (split.size() == i) {
            ArrayList<List<Info>> p = new ArrayList<>();
            for (int i1 = 0; i1 < path.size(); i1++) {
                List<Info> infos = path.get(i1);
                p.add(new ArrayList<>(infos));
            }
            res.add(p);
            return;
        }
        List<Info> group = new ArrayList<>();
        for (int j = i; j < split.size(); j++) {
            group.add(split.get(j));
            path.add(group);
            pAllCase(split, j + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    public static class Info {
        int start;
        int end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static List<Info> split(String s, int start, int end) {
        List<Info> res = new ArrayList<>();
        char first = s.charAt(start);
        int fist = start;
        for (int i = start + 1; i < end + 1; i++) {
            if (s.charAt(i - 1) != s.charAt(i)) {
                if (s.charAt(i - 1) == first) {
                    res.add(new Info(fist, i - 1));
                }

                if (s.charAt(i) == first) {
                    fist = i;
                }
            }
        }
        if (s.charAt(end) == first) {
            res.add(new Info(fist, end));
        }
        return res;
    }


}