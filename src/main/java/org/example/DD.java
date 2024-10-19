package org.example;

import io.lettuce.core.cluster.RedisClusterClient;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DD {


    public static void main(String[] args) {





        List<Integer> arr = Stream.generate(() -> new Random().nextInt(10)).limit(10).collect(Collectors.toList());
        arr.addFirst(Integer.MIN_VALUE);
        arr.addLast(Integer.MAX_VALUE);
        int[] stack = new int[arr.size()];
        System.out.println(arr);
        stack[0] = 0;
        int stackIndex = 1;
        Map<Integer, Info> res = new HashMap<>();
        for (int i = 1; i < arr.size(); i++) {
            for (; stackIndex - 1 >= 1 && arr.get(stack[stackIndex - 1]) <= arr.get(i); ) {
                res.put(stack[stackIndex - 1], new Info(stack[stackIndex - 2], i));
                stackIndex--;
            }
            stack[stackIndex] = i;
            stackIndex++;
        }
        for (int i = 1; i < arr.size()-1; i++) {
            res.get(i).right = p(arr, res, i);
        }
        System.out.println(res);


    }

    private static int p(List<Integer> arr, Map<Integer, Info> res, int i) {
        if (!Objects.equals(arr.get(i), arr.get(res.get(i).right))) {
            return res.get(i).right;
        }
        res.get(i).right=p(arr,res,res.get(i).right);
        return res.get(i).right;
    }


    public static class Info {
        int left;
        int right;

        public Info(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return left == info.left && right == info.right;
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, right);
        }

        @Override
        public String toString() {
            return "Info{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }


}