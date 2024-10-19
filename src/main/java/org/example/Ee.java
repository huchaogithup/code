package org.example;

import java.util.HashMap;
import java.util.Map;

public class Ee {

    public static void main(String[] args) {
        System.out.println(new Ee().countQuadruplets(new int[]{1, 3, 5, 2, 4}));
        System.out.println(new Ee().maximizeWin(new int[]{1,1,2,2,3,3,5}, 2));
    }


    public int maximizeWin(int[] prizePositions, int k) {
        k++;
        int max = 0;
        int maxL = 0;
        int maxR = 0;
        int second = 0;
        int L = 0;
        int R = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        for (; R < prizePositions.length; R++) {
            Integer i = counts.get(prizePositions[R]);
            if (i == null) {
                counts.put(prizePositions[R], 1);
            } else {
                counts.put(prizePositions[R], i + 1);
            }

            for (; counts.size() > k; ) {
                Integer i1 = counts.get(prizePositions[L]);
                if (i1 - 1 == 0) {
                    counts.remove(prizePositions[L]);
                } else {
                    counts.put(prizePositions[L], i1 - 1);
                }
                L++;
            }
            if (max < R - L + 1) {
                max = R - L + 1;
                maxR = R;
                maxL = L;
            } else if (second < R - L + 1 && (R > maxR || R < maxL) && (L > maxR || L < maxL)) {
                second = R - L + 1;
            }
        }


        return max + second;
    }

    int[][] great;
    int[][] less;

    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        great = new int[n][n + 1];
        p(nums, 0);
        less = new int[n][n + 1];
        p1(nums, nums.length - 1);

        long res = 0;
        for (int j = 0; j < nums.length; j++) {
            for (int k = j + 1; k < nums.length; k++) {
                if (nums[j] > nums[k]) {
                    res += great[k][nums[j]] * less[j][nums[k]];
                }
            }
        }

        return res;

    }

    private void p1(int[] nums, int i) {
        if (i == 0) {
            return;
        }
        p1(nums, i - 1);
        int[] p1 = less[i - 1];
        for (int j = nums.length; j > 0; j--) {
            less[i][j] = p1[j];
            if (j > nums[i - 1]) {
                less[i][j]++;
            }
        }
    }

    private void p(int[] nums, int i) {
        if (i == nums.length - 1) {
            return;
        }
        p(nums, i + 1);
        int[] p1 = great[i + 1];
        for (int i1 = 0; i1 < nums.length; i1++) {
            great[i][i1] = p1[i1];
            if (i1 < nums[i + 1]) {
                great[i][i1]++;
            }
        }
    }


}
