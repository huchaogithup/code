package org.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    /**
     * 异或运算满足以下几点特征:
     * 1. 交换律 a^b=b^a
     * 2. 结合律 a^(b^c)=(a^b)^c
     * 3. 任意相同两数异或都为0, a^a=0
     * 4. 0和任意数异或都是数值本身 0^a=a
     * <p>
     * 本题的的已知条件
     * 1. 原数组一定是奇数(很重要)
     * 2. 原数组是 n 个正整数（1,2,3,4,5,…,n-1,n 共n个正整数）的排列
     * 3. 加密过程是 encoded[i] = arr[i]^arr[i+1]
     * <p>
     * 求解原数组arr[i]
     * <p>
     *解题思路
     *  1. 利用交换律特性 将条件3转化成: arr[i]=encoded[i]^arr[i+1] 因此如果能求出原数组的最后一个arr[arr.length-1]的值,这题也就解了
     *  2. 因为原数组是1……n的不重复正整数,可以先计算原数组的整个异或值为all,利用异或的结合律, arr[arr.length-1]=(all)^(arr[1]^arr[2]...arr[arr.length-2]),
     *  问题就变成如何获取(arr[1]^arr[2]...arr[arr.length-2])值
     *  3. 题目给的是奇数因此切加密过程是 arr[i]^arr[i+1] ,假设原始长度为5的展开密文数组: [arr[0]^arr[1],arr[1]^arr[2],arr[2]^arr[3],arr[3]^arr[4]]
     *  提取新数组步长为2的话正好得[arr[0]^arr[1],arr[2]^arr[3]],得(arr[1]^arr[2]...arr[arr.length-2])值
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[]  arr = Arrays.stream(in.nextLine().split(",")).map(Integer::valueOf).mapToInt(Integer::intValue).toArray();
        // 计算1...n的整理异或值
        int all=0;
        for(int i = 0; i<=arr.length+1;i++){
            all^=i;
        }
        // 步长为2可以计算出(arr[1]^arr[2]...arr[arr.length-2])值
        int allRemoveLast =0;
        for(int i = 0 ; i < arr.length;i+=2){
            allRemoveLast^=arr[i];
        }
        // 公式:arr[arr.length-1]=(all)^(arr[1]^arr[2]...arr[arr.length-2])获取原数组最后一个值
        int lastNum = all^allRemoveLast;
        // 公式:arr[i]=encoded[i]^arr[i+1] 获取原数组
        int[] res = new int[arr.length+1];
        res[res.length-1]=lastNum;
        for(int i = res.length-2; i >= 0 ; i--){
            res[i]=res[i+1]^arr[i];
        }
        System.out.println(Arrays.stream(res).boxed().map(String::valueOf).collect(Collectors.joining(",")));
    }
}