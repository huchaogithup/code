package org.example;

import com.alibaba.fastjson2.JSON;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Eeee {

    public static void main(String[] args) throws IOException {
        String a = Files.readString(Path.of("a"));
        System.out.println(countRangeSum(JSON.parseObject(a, int[].class), -1, 0));
    }


    public static int countRangeSum(int[] nums, int lower, int upper) {
        long[] helper = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            helper[i + 1] = helper[i] + nums[i];
        }
        return q(helper, 0, helper.length - 1, lower, upper);
    }

    private static int q(long[] helper, int start, int end, int lower, int upper) {
        if (start == end) {
            return 0;
        }
        int res = 0;
        int mid = (start + end) / 2;
        res += q(helper, start, mid, lower, upper);
        res += q(helper, mid + 1, end, lower, upper);

        // helper[j]-helper[i]<=upper ==> helper[i]>=helper[j]-upper
        // helper[j]-helper[i]>=lower ==> helper[i]<=helper[j]-lower
        int j1 = mid + 1;
        int j2 = j1;
        for (int i = start; i <= mid; i++) {

            for (; j1 <= end && helper[i] >= helper[j1] - upper; j1++) {
            }
            for (; j2 <= end && helper[i] > helper[j2] - lower; j2++) {

            }
            if (j1 > j2) {
                res += j1 - j2;
            }

        }
        Arrays.sort(helper, start, end + 1);
        return res;
    }

    private static void merge(long[] helper, int start, int mid, int end) {
        long[] res = new long[end - start + 1];
        int index = 0;
        int index1 = start;
        int index2 = mid + 1;
        for (; ; ) {
            if (index1 == mid + 1 && index2 == end + 1) {
                break;
            }
            if (index1 == mid + 1) {
                res[index] = helper[index2];
                index++;
                index2++;
                continue;
            }

            if (index2 == end + 1) {
                res[index] = helper[index1];
                index++;
                index1++;
                continue;
            }

            if (helper[index1] > helper[index2]) {
                res[index] = helper[index2];
                index++;
                index2++;
            } else {
                res[index] = helper[index1];
                index++;
                index1++;
            }

        }

        System.arraycopy(res, 0, helper, start, end - start + 1);
    }


}

