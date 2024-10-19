package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Eee {


    public static void main(String[] args) {
        //System.out.println(Arrays.toString(new Eee().shortestDistanceAfterQueries(5,new int[][]{{2,4},{0,2},{0,4}})));
        int n = 6;
        int[] arr = new int[n];
        Arrays.fill(arr, 1);
        SegmentTree segmentTree = new SegmentTree(n);
        //[0-1-1, 0-0-0, 1-2-1, 2-3-0, 0-3-1, 1-3-0]
//        segmentTree.update(0, 1, 1);
//        segmentTree.update(0, 0, 0);
//        segmentTree.update(1, 2, 1);
//        segmentTree.update(2, 3, 0);
//        segmentTree.update(0, 3, 1);
//        segmentTree.update(1, 3, 0);
//        System.out.println(segmentTree.query());
        // 1 0 0 0
        List<String> ops = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int start = new Random().nextInt(arr.length - 1);
            int end = new Random().nextInt(arr.length - start) + start;
            int v = new Random().nextInt(2);
            Arrays.fill(arr, start, end + 1, v);
            ops.add(start + "-" + end + "-" + v);
            segmentTree.update(start, end, v);
            if (Arrays.stream(arr).sum() != segmentTree.query()) {
                System.out.println(ops);
                throw new RuntimeException("asd");
            }
        }
    }


    int n;

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        SegmentTree segmentTree = new SegmentTree(n);
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int from = queries[i][0];
            int to = queries[i][1];
            if (from + 1 <= to - 1) {
                segmentTree.update(from + 1, to - 1, 0);
            }
            res[i] = segmentTree.query() - 1;
        }
        return res;
    }


    public static class SegmentTree {
        int[] data;
        String[] helper;
        boolean[] cache;
        int size;

        public SegmentTree(int n) {
            data = new int[n << 2];
            cache = new boolean[n << 2];
            helper = new String[n << 2];
            size = n;
            build(0, size - 1, 1);
        }

        private void build(int start, int end, int index) {
            if (helper[index] == null) {
                helper[index] = start + "" + end;
            }
            if (start == end) {
                data[index] = 1;
                return;
            }
            int mid = (start + end) / 2;
            build(start, mid, index * 2);
            build(mid + 1, end, index * 2 + 1);
            data[index] = data[index * 2] + data[index * 2 + 1];
        }

        public void update(int s, int e, int v) {
            update(s, e, v, 0, size - 1, 1);
        }

        private void update(int s, int e, int v, int START, int END, int index) {
            if (s == START && e == END) {
                data[index] = (e - s + 1) * v;
                if (START != END) {
                    cache[index] = true;
                } else {
                    cache[index] = false;
                }
                return;
            }
            downCache(START, END, index);
            int MID = (START + END) / 2;
            if (e <= MID) {
                update(s, e, v, START, MID, index * 2);
            } else if (s > MID) {
                update(s, e, v, MID + 1, END, index * 2 + 1);
            } else {
                update(s, MID, v, START, MID, index * 2);
                update(MID + 1, e, v, MID + 1, END, index * 2 + 1);
            }
            data[index] = data[index * 2] + data[index * 2 + 1];
        }

        private void downCache(int START, int END, int index) {
            if (!cache[index]) {
                return;
            }
            cache[index * 2] = cache[index];
            cache[index * 2 + 1] = cache[index];
            int num = END - START + 1;
            data[index * 2] = data[index] / num * ((num+1) / 2);
            data[index * 2 + 1] = data[index] / num * (num - (num+1) / 2);
            cache[index] = false;
        }


        public int query() {
            return data[1];
        }


    }


}
