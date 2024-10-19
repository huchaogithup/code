package org.example;

import java.math.BigInteger;
import java.util.*;

public class Aaab {

    public static void main(String[] args) {
        System.out.println(new Aaab().treeOfInfiniteSouls(new int[]{27793,48468,77531,33084,45675,84239,95151,1,1}, 39217, 30704));
//        Aaab x = new Aaab();
//        x.p = (int) 1e9+7;
//        System.out.println(x.p3(new int[]{1,1,1}, 0, 0));
//        System.out.println(x.i);
    }

    int p;
    int target;

    public int treeOfInfiniteSouls(int[] gem, int p, int target) {
        this.p = p;
        this.target = target;
        return p2(gem, 0, p, target);
    }


    public int p2(int[] nums, int i, int p, int target) {
        if (i == nums.length) {
            Map<Info, Integer> infoIntegerMap = p3(nums, 0, nums.length - 1);
            Info info = new Info();
            info.n = target;
            return infoIntegerMap.getOrDefault(info, 0);
        }

        int res = 0;

        for (int i1 = i; i1 < nums.length; i1++) {
            int tmp = nums[i];
            nums[i] = nums[i1];
            nums[i1] = tmp;

            res = res + p2(nums, i + 1, p, target);

            int tmp1 = nums[i];
            nums[i] = nums[i1];
            nums[i1] = tmp1;
        }

        return res;

    }

    public static class Info {
        int len;
        long n;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return n == info.n;
        }

        @Override
        public int hashCode() {
            return Objects.hash(n);
        }
    }

    int i;
    //11391299
    public Map<Info, Integer> p3(int[] nums, int start, int j) {
        i++;
        if (j == 0) {
            String s = "1" + nums[start] + (start == nums.length - 1 ? "9" : "");
            long p = Long.parseLong(s);
            Map<Info, Integer> set = new HashMap<>();
            Info info = new Info();
            info.n = (int) (p % this.p);
            info.len = s.length();
            set.put(info, 1);
            return set;
        }
        Map<Info, Integer> res = new HashMap<>();
        for (int k = 0; k < j; k++) {
            Map<Info, Integer> p1 = p3(nums, start, k);
            Map<Info, Integer> p2 = p3(nums, start + k + 1, j - k - 1);
            for (Map.Entry<Info, Integer> pp1 : p1.entrySet()) {
                for (Map.Entry<Info, Integer> pp2 : p2.entrySet()) {
                    Info info = new Info();
                    info.n = ((pp2.getKey().n * quickMod(2)) % p + 9 + 9 * quickMod(pp2.getKey().len + 1 + 1) % p + pp1.getKey().n * quickMod(pp2.getKey().len + 1 + 1 + 1) % p + quickMod(pp2.getKey().len + pp1.getKey().len + 1 + 2) % p);
                    info.n = info.n % p;
                    info.len = pp1.getKey().len + pp2.getKey().len + 3;
                    res.merge(info, pp1.getValue() * pp2.getValue(), Integer::sum);
                }
            }
        }
        return res;
    }

    Map<Integer, Integer> map = new HashMap<>();

    public int quickMod(int n) {
        if (n == 1) {
            return 1;
        }

        if (map.containsKey(n)) {
            return map.get(n);
        }

        int res = (quickMod(n - 1) * 10) % p;
        map.put(n, res);
        return res;
    }


    public List<String> p(int[] nums, int start, int j) {
        if (j == 0) {
            return Arrays.asList("1" + nums[start] + (start == nums.length - 1 ? "9" : ""));
        }
        List<String> res = new ArrayList<>();
        for (int k = 0; k < j; k++) {
            List<String> p1 = p(nums, start, k);
            for (int i = 0; i < p1.size(); i++) {
                p1.set(i, "1" + p1.get(i) + "9");
            }
            List<String> p2 = p(nums, start + k + 1, j - k - 1);
            for (int i = 0; i < p2.size(); i++) {
                p2.set(i, p2.get(i) + "9");
            }
            for (int i = 0; i < p1.size(); i++) {
                for (int i1 = 0; i1 < p2.size(); i1++) {
                    res.add(p1.get(i) + p2.get(i1));
                }
            }
        }
        return res;
    }

}
