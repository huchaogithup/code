package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Azz {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] array = IntStream.generate(() -> new Random().nextInt(10)).limit(10).toArray();
            for (int j = 0; j < 10; j++) {
                int index = new Random().nextInt(array.length);
                int nums = new Random().nextInt(20);
                if (baoli(array, index, nums) != p(array, index, nums)) {
                    System.out.println(Arrays.toString(array));
                    System.out.println("index:" + index + "," + "nums:" + nums);
                    System.out.println(baoli(array, index, nums));
                    System.out.println(p(array, index, nums));
                    throw new RuntimeException("asd");
                }
            }
        }

    }

    public static int baoli(int[] nums, int i, int num) {
        for (int j = i; j < nums.length; j++) {
            if (nums[j] > num) {
                return j;
            }
        }
        return -1;
    }


    public static int p(int[] nums, int i, int num) {
        if (i == nums.length) {
            return -1;
        }

        if (nums[i] > num) {
            return i;
        }
        return p(nums, i + 1, num);
    }
}
