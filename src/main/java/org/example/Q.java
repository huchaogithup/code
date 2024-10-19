package org.example;

import java.util.Arrays;
import java.util.Random;

public class Q {
    public static void main(String[] args) {


        for (int i = 0; i < 100000; i++) {
            int[] ints = new int[10];
            for (int j = 0; j < ints.length; j++) {
                ints[j] = new Random().nextInt(10);
            }
            if (new Q().way1(ints) != new Q().way2(ints)) {
                System.out.println(Arrays.toString(ints));
                System.out.println(new Q().way1(ints));
                System.out.println(new Q().way2(ints));
                throw new RuntimeException("Asd");
            }
        }

    }

    public int way2(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int k = 0; k < 31; k++) {
                if (((nums[i] >> k) & 1) == 1) {
                    res = Math.max(res, p2(nums, i + 1, k) + 1);
                }
            }
        }
        return res;
    }

    private int p2(int[] nums, int i, int j) {
        if (i == nums.length) {
            return 0;
        }
        int res = 0;
        res = p2(nums, i + 1, j);
        if (((nums[i] >> j) & 1) == 1) {
            for (int k = 0; k < 31; k++) {
                if (((nums[i] >> k) & 1) == 1) {
                    res = Math.max(res, p2(nums, i + 1, k) + 1);
                }
            }
        }
        return res;
    }

    public int way1(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, p1(nums, i));
        }
        return res;
    }

    public int p1(int[] nums, int i) {
        if (i == nums.length) {
            return 0;
        }
        int res = 0;
        for (int j = i + 1; j < nums.length; j++) {
            if ((nums[i] & nums[j]) != 0) {
                res = Math.max(res, p1(nums, j));
            }
        }
        return res + 1;
    }
}
