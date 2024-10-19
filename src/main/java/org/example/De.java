package org.example;

import com.alibaba.fastjson2.JSON;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

public class De {

    public static void main(String[] args) throws IOException {
        int[] ints = JSON.parseObject(Files.readAllBytes(Paths.get("a.json")), int[].class);
        System.out.println(new De().medianOfUniquenessArray(ints));
//        for (int i = 0; i < 100000; i++) {
//            int[] array = IntStream.generate(() -> new Random().nextInt(3)).limit(100).toArray();
//            new De().medianOfUniquenessArray(array);
//
////            if (new De().medianOfUniquenessArray(array) != new De().baoli(array)) {
////                System.out.println(Arrays.toString(array));
////                System.out.println(new De().medianOfUniquenessArray(array));
////                System.out.println(new De().baoli(array));
////                throw new RuntimeException("asd");
////            }
//        }
//        System.out.println(new De().medianOfUniquenessArray(new int[]{0, 1, 0, 1, 0}));
//        System.out.println(new De().baoli(new int[]{0, 1, 0, 1, 0}));
    }

    public int baoli(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int[] clone = nums.clone();
        for (int i = 0; i < clone.length; i++) {
            for (int j = i; j < clone.length; j++) {
                Map<Integer, Integer> map = new HashMap<>();
                for (int k = i; k <= j; k++) {
                    map.put(nums[k], 1);
                }
                res.add(map.size());
            }
        }
        res.sort((s1, s2) -> Integer.compare(s1, s2));
        return res.get((res.size() + 1) / 2 - 1);
    }


    public int medianOfUniquenessArray(int[] nums) {

        int L = 1;
        int R = nums.length ;
        long target = ((nums.length * (nums.length + 1L)) / 2 + 1) / 2;
        for (; ; ) {

            if (L == R) {
                return L;
            }

            int MID = (L + R) / 2;
            counts.clear();
            res = 0;
            long num1 = getNum(nums, MID);
            counts.clear();
            res = 0;
            long num2 = getNum(nums, MID + 1);
            if (num1 >= target) {
                R = MID;
            } else if (num2 <= target) {
                L = MID + 1;
            } else {
                return MID + 1;
            }
        }
    }

    Map<Integer, Long> counts = new HashMap<>();
    long res = 0;

    public long getNum(int[] nums, int target) {
        int L = 0;
        int R = 0;
        for (; R < nums.length; R++) {
            counts.merge(nums[R], 1l, Long::sum);
            for (; L <= R && counts.size() > target; ) {
                Long v = counts.merge(nums[L], -1l, Long::sum);
                if (v == 0) {
                    counts.remove(nums[L]);
                }
                L++;
            }
            res += R - L + 1;
        }
        return res;
    }


    public int findIntegers(int n) {
        String str = Integer.toBinaryString(n);
        cache.clear();
        int res = 0;
        res = p(str, 0, false, false);
        return res + 1;
    }

    Map<Integer, Map<Boolean, Map<Boolean, Integer>>> cache = new HashMap<>();

    private int p(String str, int i, boolean start, boolean free) {
        if (cache.computeIfAbsent(i, k -> new HashMap<>()).computeIfAbsent(start, k -> new HashMap<>()).containsKey(free)) {
            return cache.get(i).get(start).get(free);
        }
        if (str.length() <= i) {
            if (start) {
                return 1;
            }
            return 0;
        }
        int res = 0;
        if (!start) {
            res += p(str, i + 1, start, true);
            if (!free) {
                if (i + 1 < str.length() && str.charAt(i + 1) == '1') {
                    res += p(str, i + 2, true, true);
                } else {
                    res += p(str, i + 2, true, false);
                }
            } else {
                res += p(str, i + 2, true, true);
            }
        } else {
            if (!free) {
                if (str.charAt(i) == '0') {
                    res += p(str, i + 1, true, false);
                } else {
                    if (i + 1 < str.length() && str.charAt(i + 1) == '1') {
                        res += p(str, i + 2, true, true);
                    } else {
                        res += p(str, i + 2, true, false);
                    }
                    res += p(str, i + 1, true, true);
                }
            } else {
                res += p(str, i + 2, true, true);
                res += p(str, i + 1, true, true);
            }
        }
        cache.get(i).get(start).put(free, res);
        return res;
    }
}
