package org.example;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Aaaaaaaaaaaaaaaaaaaaaaaaaaaa {

    public static void main(String[] args) {
//        for (int i = 0; i < 100000; i++) {
//            int[] ints = IntStream.generate(() -> new Random().nextInt(22)).limit(5).toArray();
//            Aaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaaa = new Aaaaaaaaaaaaaaaaaaaaaaaaaaaa();
//            if (aaaaaaaaaaaaaaaaaaaaaaaaaaaa.way(ints) != aaaaaaaaaaaaaaaaaaaaaaaaaaaa.way2(ints)) {
//                System.out.println(Arrays.toString(ints));
//                System.out.println(aaaaaaaaaaaaaaaaaaaaaaaaaaaa.way(ints));
//                System.out.println(aaaaaaaaaaaaaaaaaaaaaaaaaaaa.way2(ints));
//                throw new RuntimeException("asd");
//            }
//        }


//        for (int i = 0; i < 6; i++) {
//            int[] arr = new int[6];
//            arr[i] = 1;
//            Aaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaaa = new Aaaaaaaaaaaaaaaaaaaaaaaaaaaa();
//            aaaaaaaaaaaaaaaaaaaaaaaaaaaa.way2(arr);
//            aaaaaaaaaaaaaaaaaaaaaaaaaaaa.way(arr);
//            System.out.println(aaaaaaaaaaaaaaaaaaaaaaaaaaaa.cache);
//            System.out.println(aaaaaaaaaaaaaaaaaaaaaaaaaaaa.cache.get(i));
//            System.out.println(aaaaaaaaaaaaaaaaaaaaaaaaaaaa.cache2.get(Arrays.toString(arr)));
//            System.out.println(aaaaaaaaaaaaaaaaaaaaaaaaaaaa.cache2);
//            System.out.println();
//        }


        System.out.println(new Aaaaaaaaaaaaaaaaaaaaaaaaaaaa().countGoodIntegers(5, 6));


    }


    public int generateKey(int num1, int num2, int num3) {
        String num1Str = num1 + "";
        String num2Str = num2 + "";
        String num3Str = num3 + "";
        num1Str = "0".repeat(4 - num1Str.length()) + num1Str;
        num2Str = "0".repeat(4 - num2Str.length()) + num2Str;
        num3Str = "0".repeat(4 - num3Str.length()) + num3Str;
        char[] res = new char[4];
        for (int i = 0; i < 4; i++) {
            res[i] = '9';
            res[i] = (char) Math.min(res[i], num1Str.charAt(i));
            res[i] = (char) Math.min(res[i], num2Str.charAt(i));
            res[i] = (char) Math.min(res[i], num3Str.charAt(i));
        }
        return Integer.valueOf(new String(res));
    }


    public String stringHash(String s, int k) {
        int n = s.length() / k;
        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            int start = i * k;
            int end = k + i * k;
            int sum = 0;
            for (int j = start; j < end; j++) {
                sum += s.charAt(j) - 'a';
            }
            res[i] = (char) (sum % 26 + 'a');
        }
        return new String(res);
    }


    char[] path = new char[20];
    int index = 0;
    Set<String> res = new HashSet<>();

    public long countGoodIntegers(int n, int k) {
        res.clear();
        p(0, 0, n, k);
        long res = 0;
        for (String re : this.res) {
            res += getSum(re);
        }
        return res;
    }

    private long getSum(String re) {
        Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < re.length(); i++) {
            counts.merge(re.charAt(i), 1, Integer::sum);
        }
        long res = 0;
        if (counts.containsKey('0')) {
            for (Map.Entry<Character, Integer> characterIntegerEntry : counts.entrySet()) {
                if (characterIntegerEntry.getKey() == '0') {
                    continue;
                }
                long all = getJie(re.length() - 1);
                for (Map.Entry<Character, Integer> integerEntry : counts.entrySet()) {
                    if (integerEntry.getKey() == characterIntegerEntry.getKey()) {
                        all /= getJie(integerEntry.getValue() - 1);
                    } else {
                        all /= getJie(integerEntry.getValue());
                    }
                }
                res += all;
            }
            return res;
        }
        res = getJie(re.length());
        for (Map.Entry<Character, Integer> characterIntegerEntry : counts.entrySet()) {
            res /= getJie(characterIntegerEntry.getValue());
        }
        return res;
    }

    private long getJie(int n) {
        long res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    private void p(int i, int mod, int n, int k) {
        if (i == n / 2) {
            if (n % 2 == 0) {
                if (mod % k == 0) {
                    char[] clone = Arrays.copyOfRange(path, 0, index);
                    Arrays.sort(clone);
                    res.add(new String(clone));
                    return;
                }
            } else {
                for (int j = 0; j <= 9; j++) {
                    path[index] = (char) (j + '0');
                    index++;
                    long pow = (long) Math.pow(10, i);
                    if ((mod + pow * j) % k == 0) {
                        char[] clone = Arrays.copyOfRange(path, 0, index);
                        Arrays.sort(clone);
                        res.add(new String(clone));
                    }
                    index--;
                }
                return;
            }

            return;
        }
        if (i == 0) {
            for (int j = 1; j <= 9; j++) {
                long pow1 = (long) Math.pow(10, (n - i - 1));
                long pow2 = (long) Math.pow(10, i);
                int newMod = (int) ((pow1 * j + pow2 * j) % k);
                newMod += mod;
                path[index] = (char) (j + '0');
                index++;
                path[index] = (char) (j + '0');
                index++;
                p(i + 1, newMod, n, k);
                index--;
                index--;
            }
        } else {
            for (int j = 0; j <= 9; j++) {
                long pow1 = (long) Math.pow(10, (n - i - 1));
                long pow2 = (long) Math.pow(10, i);
                int newMod = (int) ((pow1 * j + pow2 * j + mod) % k);
                path[index] = (char) (j + '0');
                index++;
                path[index] = (char) (j + '0');
                index++;
                p(i + 1, newMod, n, k);
                index--;
                index--;
            }
        }
        return;
    }


    public long minDamage(int power, int[] damage, int[] health) {
        return 0;
    }


    private void p5(char[] s, int i, Set<String> detail) {
        if (i == s.length) {
            detail.add(new String(s));
            return;
        }
        for (int j = i; j < s.length; j++) {
            if (i == 0 && s[j] == '0') {
                continue;
            }
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            p5(s, i + 1, detail);
            tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
    }


    private boolean isOk(int i) {
        String str = i + "";
        int bit = 0;
        for (int j = 0; j < str.length(); j++) {
            bit ^= 1 << (str.charAt(j) - '0');
        }
        if (bit == 0 || (bit - (bit & (-bit))) == 0) {
            return true;
        }
        return false;
    }


//    public int p3(int i, int x, int j) {
//        if (i == n - 1) {
//            return 0;
//        }
//
//    }

    int n = 0;

    public int way2(int[] nums) {
        n = nums.length;
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            for (int n = 0; n < nums[i]; n++) {
                if (res == -1) {
                    res = p2(i);
                    continue;
                }
                res ^= p2(i);
            }
        }
        if (res == -1) {
            return 0;
        }
        return res;
    }

    Map<Integer, Integer> cache = new LinkedHashMap<>();

    private int p2(int i) {
        if (cache.containsKey(i)) {
            return cache.get(i);
        }
        if (i == n - 1) {
            cache.put(i, 0);
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int j = i + 1; j < n; j++) {
            for (int _n = j; _n < n; _n++) {
                set.add(p2(j) ^ p2(_n));
            }
        }
        for (int j = 0; ; j++) {
            if (set.contains(j)) {
                continue;
            }
            cache.put(i, j);
            return j;
        }
    }

    public int way(int[] nums) {
        aim = new int[nums.length];
        nums = nums.clone();
        nums[nums.length - 1] = 0;
        return p(nums);
    }


    int[] aim;
    Map<String, Integer> cache2 = new LinkedHashMap<>();

    public int p(int[] arr) {
        String key = Arrays.toString(arr);
        if (cache2.containsKey(key)) {
            return cache2.get(key);
        }
        if (Arrays.equals(arr, aim)) {
            cache2.put(key, 0);
            return 0;
        }

        Set<Integer> has = new HashSet<>();
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i] % 2;
            if (arr[i] == 0) {
                continue;
            }
            for (int j = i + 1; j < arr.length; j++) {
                for (int n = j; n < arr.length; n++) {
                    int[] newArr = arr.clone();
                    if (j != arr.length - 1) {
                        newArr[j]++;
                    }
                    if (n != arr.length - 1) {
                        newArr[n]++;
                    }
                    newArr[i]--;
                    has.add(p(newArr));
                }
            }
        }
        for (int i = 0; ; i++) {
            if (has.contains(i)) {
                continue;
            }
            cache2.put(key, i);
            return i;
        }
    }
}
