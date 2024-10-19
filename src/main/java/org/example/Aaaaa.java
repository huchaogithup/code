package org.example;

import com.alibaba.fastjson2.JSON;

import java.lang.reflect.Type;
import java.util.*;

public class Aaaaa {
    public static void main(String[] args) {
        Integer a = 9999;
        Integer b = 9999;
        System.out.println(a == b);
//        List<List<Integer>> lists = JSON.parseArray("[[1,2,3],[4,3,2],[1,1,1]]", (Type) List.class);
//        System.out.println(new Aaaaa().maxScore(lists));
        System.out.println(Arrays.toString(new Aaaaa().maximumSubarrayXor(new int[]{0, 7, 3, 2, 8, 5, 1}, new int[][]{{5, 6}})));
        System.out.println(new Aaaaa().maxConsecutiveAnswers("TFFT", 1));

    }


    public static class Info {
        int days;
        int damage;
        double p;
    }

    public long minDamage(int power, int[] damage, int[] health) {
        // day1   day2
        // d1     d2
        // d1day1+(day1+day2)d2
        // d2day2+(day1+day2)d1
        // day1d2 < day2d1
        // d2/day2<d1/day1
        // d1/day1>d2/day2

        Info[] infos = new Info[damage.length];
        for (int i = 0; i < damage.length; i++) {
            Info info = new Info();
            info.damage = damage[i];
            info.days = (health[i] + power - 1) / power;
            info.p = ((double) info.damage) / info.days;
            infos[i] = info;
        }
        Arrays.sort(infos, (s1, s2) -> Double.compare(s2.p, s1.p));
        int cur = 0;
        long res = 0;
        for (int i = 0; i < infos.length; i++) {
            res += (long) (cur + infos[i].days) * infos[i].damage;
            cur += infos[i].days;
        }
        return res;
    }


    public int maxScore(List<List<Integer>> grid) {
        int max = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int i1 = 0; i1 < grid.get(i).size(); i1++) {
                max = Math.max(max, grid.get(i).get(i1));
            }
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < grid.size(); i++) {
            for (int i1 = 0; i1 < grid.get(i).size(); i1++) {
                map.computeIfAbsent(grid.get(i).get(i1), k -> new HashSet<>()).add(i);
            }
        }
        cache.clear();
        return p(0, 0, max + 1, map);
    }

    Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();

    private int p(int i, int set, int max, Map<Integer, Set<Integer>> map) {
        if (i == max) {
            return 0;
        }
        if (cache.computeIfAbsent(i, k -> new HashMap<>()).containsKey(set)) {
            return cache.get(i).get(set);
        }
        int res = 0;
        res = p(i + 1, set, max, map);

        for (Integer index : map.getOrDefault(i, new HashSet<>())) {
            if (((set >> index) & 1) == 1) {
                continue;
            }
            int newSet = set;
            newSet |= 1 << index;
            res = Math.max(res, p(i + 1, newSet, max, map) + i);
        }
        cache.get(i).put(set, res);
        return res;
    }


    int[][] p1Cache;

    int[][] p2Cache;

    public int[] maximumSubarrayXor(int[] nums, int[][] queries) {
        p1Cache = new int[nums.length][nums.length];
        p2Cache = new int[nums.length][nums.length];
        p1(nums, 0, nums.length - 1);
        p2(0, nums.length - 1);
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = p2Cache[queries[i][0]][queries[i][1]];
        }
        return res;
    }
    // 1 2 3 4 5

    private int p2(int start, int end) {
        if (p2Cache[start][end] != 0) {
            return p2Cache[start][end];
        }
        if (start == end) {
            int res = p1Cache[start][end];
            p2Cache[start][end] = res;
            return res;
        }
        int res = p1Cache[start][end];
        res = Math.max(res, p2(start + 1, end));
        res = Math.max(res, p2(start, end - 1));
        p2Cache[start][end] = res;
        return res;
    }

    private int p1(int[] nums, int start, int end) {
        if (p1Cache[start][end] != 0) {
            return p1Cache[start][end];
        }
        if (start == end) {
            p1Cache[start][end] = nums[start];
            return nums[start];
        }
        int res = p1(nums, start + 1, end) ^ p1(nums, start, end - 1);
        p1Cache[start][end] = res;
        return res;
    }


    public int maxConsecutiveAnswers(String answerKey, int k) {
        int L = 0;
        int R = 0;
        int ks=k;
        int max = 0;
        // true
        for (L = 0, R = 0; R < answerKey.length(); R++) {
            if (answerKey.charAt(R) != 'T') {
                k--;
            }
            for (; k < 0; ) {
                if (answerKey.charAt(L) != 'T') {
                    k++;
                }
                L++;
            }
            max = Math.max(max, R - L + 1);
        }

        k=ks;
        for (L = 0, R = 0; R < answerKey.length(); R++) {
            if (answerKey.charAt(R) != 'F') {
                k--;
            }
            for (; k < 0; ) {
                if (answerKey.charAt(L) != 'F') {
                    k++;
                }
                L++;
            }
            max = Math.max(max, R - L + 1);
        }
        return max;
    }


    public int maxConsecutiveAnswers11(String answerKey, int k) {
        int res = 0;
        for (int i = 0; i < answerKey.length(); i++) {
            res = Math.max(res, p(answerKey, i, 'T', k));
            res = Math.max(res, p(answerKey, i, 'F', k));
        }
        return res;
    }

    private int p(String str, int i, char c, int k) {
        if (i == str.length()) {
            return 0;
        }
        int res = 0;
        if (str.charAt(i) == c) {
            res = p(str, i + 1, c, k);
        } else if (k > 0) {
            res = p(str, i + 1, c, k - 1);
        } else {
            return res;
        }
        return res + 1;
    }


}
