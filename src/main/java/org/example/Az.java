package org.example;

import java.util.*;
import java.util.stream.IntStream;

public class Az {

    public static void main(String[] args) {
        System.out.println(new Az().p1(new int[]{-2, -3, 8, 9}, 0).size());
        p(0, 10);
        System.out.println(cache);
    }

    static Map<String, Long> cache = new HashMap<>();

    private static void p(int start, int end) {
        if (start == end) {
            out(start + "-" + end);
            return;
        }
        out(start + "-" + end);
        p(start + 1, end);
        p(start, end - 1);
    }

    private static void out(String s) {
        cache.merge(s, 1L, Long::sum);
    }


    public long maxStrength(int[] nums) {
        return p(nums, 0)[0];
    }

    private long[] p(int[] nums, int i) {
        if (i == nums.length - 1) {
            return new long[]{nums[i], nums[i]};
        }
        long[] p1 = p(nums, i + 1);
        long[] p2 = p(nums, i + 1);
        long[] res = new long[]{Long.MIN_VALUE, Long.MAX_VALUE};
        res[0] = Math.max(res[0], p1[0]);
        res[0] = Math.max(res[0], p2[0] * nums[i]);
        res[0] = Math.max(res[0], p2[1] * nums[i]);
        res[0] = Math.max(res[0], nums[i]);
        res[1] = Math.min(res[1], p1[1]);
        res[1] = Math.min(res[1], p2[0] * nums[i]);
        res[1] = Math.min(res[1], p2[1] * nums[i]);
        res[1] = Math.min(res[1], nums[i]);
        return res;
    }


    private List<List<Integer>> p1(int[] nums, int i) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (i == nums.length - 1) {
            res.add(Arrays.asList(nums[i]));
            return res;
        }
        List<List<Integer>> p1 = p1(nums, i + 1);
        List<List<Integer>> p2 = p1(nums, i + 1);
        for (int i1 = 0; i1 < p2.size(); i1++) {
            ArrayList<Integer> p = new ArrayList<>(p2.get(i1));
            p.add(nums[i]);
            res.add(p);
        }
        List<List<Integer>> p3 = new ArrayList<>();
        p3.add(Arrays.asList(nums[i]));
        res.addAll(p1);
        res.addAll(p3);
        return res;
    }

}
