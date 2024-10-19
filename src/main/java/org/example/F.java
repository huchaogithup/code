package org.example;

import java.util.ArrayDeque;

public class F {

    public static void main(String[] args) {
        System.out.println(new F().minValidStrings(new String[]{"abc", "aaaaa", "bcdef"}, "aabcdabc"));
    }


    TNode[] root = new TNode[26];

    public int minValidStrings(String[] words, String target) {
        root = new TNode[26];
        build(words);
        cache1 = new int[target.length()];
        int p = p(target, 0);
        return p == Integer.MAX_VALUE ? -1 : p;
    }

    int[] cache1;

    private int p(String target, int i) {
        if (i == target.length()) {
            return 0;
        }


        if (cache1[i] != 0) {
            return cache1[i];
        }


        int res = Integer.MAX_VALUE;

        TNode[] node = root;
        for (int j = i; j < target.length(); j++) {
            int index = target.charAt(j) - 'a';
            if (node[index] == null) {
                res = p(target, j);
                break;
            }
            node = node[index].next;
//            if (p2 != Integer.MAX_VALUE) {
//                res = Math.min(res, p2 + 1);
//            }

        }
        cache1[i] = res;
        return res;
    }

    private void build(String[] words) {
        for (int i = 0; i < words.length; i++) {
            TNode[] node = root;
            for (int c = 0; c < words[i].length(); c++) {
                int index = words[i].charAt(c) - 'a';
                if (node[index] == null) {
                    node[index] = new TNode();
                }
                node = node[index].next;
            }
        }
    }

    public static class TNode {
        TNode[] next = new TNode[26];
    }

//
////    public static int p(String str, int index) {
////        if (index == str.length() - 1) {
////            return str.length();
////        }
////
////        if (index == str.length()) {
////            return str.length();
////        }
////
////        int p = p(str, index + 1);
////        for (; ; ) {
////            if (str.charAt(index) == str.charAt(p - 1)) {
////                return p - 1;
////            } else {
////                if (p == str.length()) {
////                    return p;
////                }
////                p = p(str, p);
////            }
////        }
////    }
////
////    public static void main(String[] args) {
////        System.out.println(F.p("abcabab", 0));
////    }
//
//    public static void main(String[] args) {
//        System.out.println(new F().minValidStrings(new String[]{"abababab","ab"}, "ababaababa"));
//    }
//
//    //
////    public static void main(String[] args) {
////        String str = "789";
////        long res = 0;
////        for (int i = 0; i < str.length(); i++) {
////            res = (res * 10 + str.charAt(i) - '0');
////        }
////        System.out.println(res);
////    }
////
////    public int[] getSneakyNumbers(int[] nums) {
////        int n = nums.length - 1;
////        for (int i = 0; i < nums.length - 2; ) {
////            if (nums[i] == i) {
////                i++;
////                continue;
////            }
////
////            if (nums[i] == nums[nums[i]]) {
////                swap(nums, i, n);
////                n--;
////            }
////
////
////            swap(nums, nums[i], i);
////        }
////        return new int[]{nums[nums.length - 1], nums[nums.length - 2]};
////    }
////
////    private static void swap(int[] nums, int a, int b) {
////        int tmp = nums[a];
////        nums[a] = nums[b];
////        nums[b] = tmp;
////    }
////
////    long[][] cache;
////
////    public long maxScore(int[] a, int[] b) {
////        cache = new long[b.length][a.length + 1];
////        return p(a, b, 0, a.length);
////    }
////
////    private long p(int[] a, int[] b, int i, int k) {
////        if (k == 0) {
////            return 0;
////        }
////
////        if (b.length == i) {
////            return Integer.MIN_VALUE;
////        }
////
////        if (cache[i][k] != 0) {
////            return cache[i][k];
////        }
////
////        long res = Integer.MIN_VALUE;
////        long p1 = p(a, b, i + 1, k);
////        if (p1 != Integer.MIN_VALUE) {
////            res = p1;
////        }
////        long p2 = p(a, b, i + 1, k - 1);
////        if (p2 != Integer.MIN_VALUE) {
////            res = Math.max(res, p2 + (long) (a[a.length - k]) * b[i]);
////        }
////        cache[i][k] = res;
////        return res;
////    }
////
////
//    TNode root = new TNode();
//    static int index;
//
//    public int minValidStrings(String[] words, String target) {
//        root = new TNode();
//        index = 0;
//        build(words);
//        cache1 = new int[index+1][target.length()];
//        int p = p(target, root, 0);
//        return p == Integer.MAX_VALUE ? -1 : p;
//    }
//
//    int[][] cache1;
//
//    private int p(String target, TNode node, int index) {
//        if (index == target.length()) {
//            return 1;
//        }
//
//        int res = Integer.MAX_VALUE;
//
//        if (node == null) {
//            return res;
//        }
//        if (cache1[node.i][index] != 0) {
//            return cache1[node.i][index];
//        }
//
//        int cIndex = target.charAt(index) - 'a';
//        if (node.next[cIndex] != null) {
//            res = p(target, node.next[cIndex], index + 1);
//        }
//
//        int p2 = p(target, node.fail, index);
//        if (p2 != Integer.MAX_VALUE) {
//            res = Math.min(res, p2 + 1);
//        }
//
////        for (int i = index; i < target.length(); i++) {
////            int cIndex = target.charAt(i) - 'a';
////            if (node.next[cIndex] == null) {
////                for (; ; ) {
////                    node = node.fail;
////                    if (node == null) {
////                        return res;
////                    }
////                    if (node.next[cIndex] != null) {
////                        break;
////                    }
////                }
////            }
////            node = node.next[cIndex];
////            int p1 = p(target, i + 1);
////            if (p1 != Integer.MAX_VALUE) {
////                res = Math.min(res, p1 + 1);
////            }
////        }
//        cache1[node.i][index]=res;
//        return res;
//    }
//
//    private void build(String[] words) {
//        for (int i = 0; i < words.length; i++) {
//            TNode[] node = root.next;
//            for (int c = 0; c < words[i].length(); c++) {
//                int index = words[i].charAt(c) - 'a';
//                if (node[index] == null) {
//                    node[index] = new TNode();
//                }
//                node = node[index].next;
//            }
//        }
//
//        ArrayDeque<TNode> arrayDeque = new ArrayDeque<>();
//        arrayDeque.add(root);
//        for (; !arrayDeque.isEmpty(); ) {
//            TNode poll = arrayDeque.poll();
//            for (int i = 0; i < poll.next.length; i++) {
//                if (poll.next[i] != null) {
//                    arrayDeque.add(poll.next[i]);
//                    poll.next[i].fail = getFail(poll.fail, i);
//                }
//            }
//        }
//
//    }
//
//    private TNode getFail(TNode fail, int i) {
//        if (fail == null) {
//            return root;
//        }
//        if (fail.next[i] != null) {
//            return fail.next[i];
//        }
//        return getFail(fail.fail, i);
//    }
//
//    public static class TNode {
//        TNode[] next = new TNode[26];
//        TNode fail = null;
//        int i = index++;
//    }
}