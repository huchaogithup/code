package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Eeeeea {


    public static void main(String[] args) throws IOException {
//        for (int i = 0; i < 1000000; i++) {
//            String s1 = IntStream.generate(() -> new Random().nextInt(10)).limit(10).mapToObj(s -> ((char) (s + 'a')) + "").collect(Collectors.joining());
//            String s2 = IntStream.generate(() -> new Random().nextInt(10)).limit(3).mapToObj(s -> ((char) (s + 'a')) + "").collect(Collectors.joining());
//            if (new Eeeeea().validSubstringCount(s1, s2) != new Eeeeea().baoli(s1, s2)) {
//                System.out.println(s1);
//                System.out.println(s2);
//                throw new RuntimeException("asd");
//            }
//        }
//


        for (int i = 0; i < 100000; i++) {
            int[] array = IntStream.generate(() -> new Random().nextInt(20)).limit(10).toArray();
            int[][] array1 = IntStream.generate(() -> new Random().nextInt(5)).limit(new Random().nextInt(5) + 1).mapToObj(s -> {
                return new int[]{new Random().nextInt(5), new Random().nextInt(array.length / 4), new Random().nextInt(array.length)};
            }).toArray(int[][]::new);
            try {
                if (new Eeeeea().way1(array, array1) != new Eeeeea().way2(array, array1)) {
                    System.out.println(Arrays.toString(array));
                    System.out.println(Arrays.stream(array1).map(Arrays::toString).toList());
                    throw new RuntimeException("Asd");
                }
            } catch (NullPointerException e) {
                System.out.println(Arrays.toString(array));
                System.out.println(Arrays.stream(array1).map(Arrays::toString).toList());
            }
        }
//        System.out.println(new Eeeeea().way1(new int[]{8, 8, 1, 4, 7, 7, 7, 8, 7, 9}, new int[][]{{0, 1, 0}, {2, 0, 1}}));
//        System.out.println(new Eeeeea().way2(new int[]{8, 8, 1, 4, 7, 7, 7, 8, 7, 9}, new int[][]{{0, 1, 0}, {2, 0, 1}}));

    }

    int[] loads;
    int[] preSum;
    int[][] works;

    public int way1(int[] loads, int[][] works) {
        Arrays.sort(works, (s1, s2) -> Integer.compare(s1[2], s2[2]));
        this.loads = loads;
        this.works = works;

        preSum = new int[loads.length];
        preSum[0] = loads[0];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + loads[i];
        }

        return p(0, 0);
    }

    public int way2(int[] loads, int[][] works) {
        Arrays.sort(works, (s1, s2) -> Integer.compare(s1[2], s2[2]));
        this.loads = loads;
        this.works = works;

        preSum = new int[loads.length];
        preSum[0] = loads[0];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + loads[i];
        }

        return p2(0, 0);
    }

    Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();

    private int p2(int i, int j) {
        if (i >= loads.length) {
            return 0;
        }

        if (j == works.length) {
            return 0;
        }


        if (cachep.computeIfAbsent(i, k -> new HashMap<>()).containsKey(j)) {
            return cachep.computeIfAbsent(i, k -> new HashMap<>()).get(j);
        }

        int value = works[j][0];
        int range = works[j][1];
        int mustL = works[j][2];

        Map<Integer, Integer> c = cache.computeIfAbsent(j, k -> new HashMap<>());
        if (c.isEmpty()) {
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            for (int k = mustL + range - 1; k >= mustL; k--) {
                for (; !queue.isEmpty() && p(queue.peekFirst() + 1, j + 1) + value * preSum(queue.peekFirst()) < p(k + 1, j + 1) + value * preSum(k); ) {
                    queue.pollFirst();
                }
                queue.addFirst(k);
            }

            for (int k = mustL; k >= 0 && k + range - 1 >= mustL; k--) {
                c.put(k, p(queue.peekLast() + 1, j + 1) + value * preSum(queue.peekLast()));
                if (queue.peekLast() == k + range - 1) {
                    queue.pollLast();
                }
            }

        }


        int res = p(i, j + 1);
        res = Math.max(res, p(i + 1, j));
        if (mustL < i + range) {
            res = Math.max(res, c.get(i) - preSum(i - 1) * value);
        }

        cachep.computeIfAbsent(i, k -> new HashMap<>()).put(j, res);

        return res;
    }

    Map<Integer, Map<Integer, Integer>> cachep = new HashMap<>();

    private int p(int i, int j) {
        if (i >= loads.length) {
            return 0;
        }

        if (j == works.length) {
            return 0;
        }

        if (cachep.computeIfAbsent(i, k -> new HashMap<>()).containsKey(j)) {
            return cachep.computeIfAbsent(i, k -> new HashMap<>()).get(j);
        }

        int value = works[j][0];
        int range = works[j][1];
        int mustL = works[j][2];
        int res = p(i, j + 1);
        res = Math.max(res, p(i + 1, j));
        for (int k = mustL; k < i + range; k++) {
            res = Math.max(res, p(k + 1, j + 1) + value * (preSum(k) - preSum(i - 1)));
        }

        cachep.computeIfAbsent(i, k -> new HashMap<>()).put(j, res);
        return res;
    }

    public int preSum(int i) {
        if (i >= preSum.length) {
            return preSum[preSum.length - 1];
        }
        if (i < 0) {
            return 0;
        }
        return preSum[i];
    }


}
