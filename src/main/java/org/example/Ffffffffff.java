package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Ffffffffff {
    //3153. 所有数对中数位不同之和
    //已解答
    //中等
    //相关标签
    //相关企业
    //提示
    //你有一个数组 nums ，它只包含 正 整数，所有正整数的数位长度都 相同 。
    //
    //两个整数的 数位不同 指的是两个整数 相同 位置上不同数字的数目。
    //
    //请你返回 nums 中 所有 整数对里，数位不同之和。
    //
    //
    //
    //示例 1：
    //
    //输入：nums = [13,23,12]
    //
    //输出：4
    //
    //解释：
    //计算过程如下：
    //- 13 和 23 的数位不同为 1 。
    //- 13 和 12 的数位不同为 1 。
    //- 23 和 12 的数位不同为 2 。
    //所以所有整数数对的数位不同之和为 1 + 1 + 2 = 4 。
    //
    //示例 2：
    //
    //输入：nums = [10,10,10,10]
    //
    //输出：0
    //
    //解释：
    //数组中所有整数都相同，所以所有整数数对的数位不同之和为 0 。
    //
    //
    //
    //提示：
    //
    //2 <= nums.length <= 105
    //1 <= nums[i] < 109
    //nums 中的整数都有相同的数位长度。
    public static void main(String[] args) {
        System.out.println(new Ffffffffff().sumDigitDifferences(new int[]{10, 10, 10, 10}));
    }

    public long sumDigitDifferences(int[] nums) {
        List<Integer> nums1 = getNums(nums[0]);
        int[][] numsCount = new int[nums1.size()][10];
        for (int i = 0; i < nums1.size(); i++) {
            numsCount[i][nums1.get(i)]++;
        }
        long res = ((long) nums.length * (nums.length - 1) / 2) * nums1.size();
        for (int i = 1; i < nums.length; i++) {
            List<Integer> list = getNums(nums[i]);
            for (int j = 0; j < list.size(); j++) {
                res -= numsCount[j][list.get(j)];
            }
            for (int j = 0; j < list.size(); j++) {
                numsCount[j][list.get(j)]++;
            }
        }
        return res;
    }

    private List<Integer> getNums(int num) {
        List<Integer> res = new ArrayList<>();
        for (; num > 0; ) {
            res.add(num % 10);
            num /= 10;
        }
        return res;
    }
}
