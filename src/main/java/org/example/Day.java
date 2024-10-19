package org.example;

import java.util.*;
import java.util.stream.IntStream;


public class Day {


    public static void main(String[] args) {
        int[] array = IntStream.generate(() -> new Random().nextInt(3)).limit(10).toArray();
        System.out.println(Arrays.toString(array));
        int[] res = new int[array.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            System.out.print(p(array, i)+" ");
        }
        System.out.println(Arrays.toString(res));
    }

    private static int p(int[] array, int i) {
        if (i == array.length) {
            return -1;
        }
        for (int j = i + 1; j < array.length; j++) {
            if (array[i] == array[j]) {
                return j;
            }
        }
        return -1;
    }


    public String convertDateToBinary(String date) {
        String res = "";
        String[] split = date.split("-");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            res += Integer.toBinaryString(Integer.valueOf(s));
            if (i != split.length - 1) {
                res += "-";
            }
        }
        return res;
    }

    public int maxPossibleScore(int[] start, int d) {
        Arrays.sort(start);
        int l = start[start.length - 1] + d - start[0];

        int L = 0;
        int R = l;
        int res = 0;
        for (; L <= R; ) {
            if (L == R) {
                if (check(start, d, L)) {
                    res = Math.max(res, L);
                }
                break;
            }
            int MID = L + (R - L) / 2;
            if (check(start, d, MID)) {
                L = MID + 1;
                res = Math.max(res, MID);
            } else {
                R = MID;
            }
        }

        return res;

    }

    private boolean check(int[] start, int d, int m) {
        if ((long) start[0] + m <= (long) start[1] + d) {
            return p(start, 2, m, (int) Math.max((long) start[0] + m, (long) start[1]), d);
        }
        return false;
    }

    private boolean p(int[] start, int i, int m, int i1, int d) {
        if (i == start.length) {
            return true;
        }
        if ((long) i1 + m <= (long) start[i] + d) {
            return p(start, i + 1, m, (int) Math.max((long) i1 + m, (long) start[i]), d);
        }
        return false;
    }

    public long findMaximumScore(List<Integer> nums) {
        return p(nums, 0);
    }

    private long p(List<Integer> nums, int i) {
        if (i == nums.size()) {
            return 0;
        }
        long res = 0;
        for (int j = i + 1; j < nums.size(); j++) {
            res = Math.max(res, (long) (j - i) * nums.get(i) + p(nums, j));
        }
        return res;
    }

}

//public class Day {
//
//
//    public static void main(String[] args) {
//        System.out.println(new Day().maximumLength(new int[]{89, 89, 90, 88, 88, 88, 88, 90, 90}, 2));
//    }
//
//
//    int[] nums;
//
//    public int maximumLength(int[] nums, int k) {
//        int res = 0;
//        this.nums = nums;
//        for (int i = 0; i < nums.length; i++) {
//            res = Math.max(res, p(i, k));
//        }
//        return res;
//    }
//
//    private int p(int i, int k) {
//        if (i == nums.length) {
//            return 0;
//        }
//
//        if (k < 0) {
//            return Integer.MIN_VALUE;
//        }
//        int res = 0;
//        int a = nums[i];
//        for (int j = i + 1; j < nums.length; j++) {
//            int b = nums[j];
//            if (a == b) {
//                res = Math.max(res, p(j, k));
//            } else {
//                res = Math.max(res, p(j, k - 1));
//            }
//        }
//        return res + 1;
//    }
//
////    public int maximumLength(int[] nums, int k) {
////        Map<Integer, List<Integer>> indexMap = new HashMap<>();
////        for (int i = 0; i < nums.length; i++) {
////            List<Integer> integers = indexMap.get(nums[i]);
////            if (integers == null) {
////                integers = new ArrayList<>();
////                indexMap.put(nums[i], integers);
////            }
////            integers.add(i);
////        }
////        int[] ids = new int[nums.length];
////        Arrays.fill(ids, -1);
////        for (int i = 0; i < nums.length; i++) {
////            if (ids[i] != -1) {
////                continue;
////            }
////            List<Integer> integers = indexMap.get(nums[i]);
////            int pre = integers.get(0);
////            for (int j = 1; j < integers.size(); j++) {
////                ids[pre] = integers.get(j);
////                pre = ids[pre];
////            }
////        }
////        db = new int[nums.length][k + 1];
////        int res = 0;
////        for (int i = 0; i < nums.length; i++) {
////            res = Math.max(res, p(nums, ids, i, k));
////        }
////        return res;
////    }
////
////    int[][] db;
////    Map<Integer, int[]> cache = new HashMap<>();
////
////    private int p(int[] nums, int[] ids, int i, int k) {
////        if (i == nums.length) {
////            return 0;
////        }
////
////        if (k < 0) {
////            return Integer.MIN_VALUE;
////        }
////
////        if (db[i][k] != 0) {
////            return db[i][k];
////        }
////
////        int[] c = null;
////        if (cache.containsKey(k)) {
////            c = cache.get(k);
////        } else {
////            c = new int[nums.length + 1];
////            c[nums.length] = p(nums, ids, nums.length, k - 1);
////            for (int j = nums.length - 1; j >= 0; j--) {
////                c[j] = Math.max(c[j + 1], p(nums, ids, j, k - 1));
////            }
////            cache.put(k, c);
////        }
////
////        int res = 0;
////        if (ids[i] != -1) {
////            res = Math.max(res, p(nums, ids, ids[i], k));
////        }
////
////        res = Math.max(res, c[i + 1]);
////        db[i][k] = res + 1;
////        return res + 1;
////    }
//
//
////    public int maximumLength(int[] nums, int k) {
////        int res = 0;
////        cache = new int[nums.length][nums.length][k + 1];
////        for (int i = 0; i < cache.length; i++) {
////            for (int i1 = 0; i1 < cache[i].length; i1++) {
////                for (int i2 = 0; i2 < cache[i][i1].length; i2++) {
////                    cache[i][i1][i2] = -1;
////                }
////            }
////        }
////        for (int i = 0; i < nums.length; i++) {
////            res = Math.max(res, p(nums, i + 1, i, k) + 1);
////        }
////        return res;
////    }
////
////    int[][][] cache;
////
////    private int p(int[] nums, int i, int p, int k) {
////        if (i == nums.length) {
////            return 0;
////        }
////
////        if (cache[i][p][k] != -1) {
////            return cache[i][p][k];
////        }
////
////        int res = 0;
////        res = p(nums, i + 1, p, k);
////        if (nums[i] == nums[p]) {
////            res = Math.max(res, p(nums, i + 1, i, k) + 1);
////        } else {
////            if (k > 0) {
////                res = Math.max(res, p(nums, i + 1, i, k - 1) + 1);
////            }
////        }
////        cache[i][p][k] = res;
////        return res;
////    }
//
//
////    public int maximumLength(int[] nums, int k) {
////        int res = 0;
////        for (int i = 0; i < nums.length; i++) {
////            res = Math.max(res, p(nums, i, k)+1);
////        }
////        return res;
////    }
////
////    private int p(int[] nums, int i, int k) {
////        if (i == nums.length) {
////            return 0;
////        }
////        int res = 0;
////        for (int j = i + 1; j < nums.length; j++) {
////            if (nums[i] == nums[j]) {
////                res = Math.max(res, p(nums, j, k) + 1);
////            } else {
////                if (k > 0) {
////                    res = Math.max(res, p(nums, j, k - 1) + 1);
////                }
////            }
////        }
////        return res;
////    }
//
//}
