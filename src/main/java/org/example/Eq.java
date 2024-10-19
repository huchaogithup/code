package org.example;

import com.alibaba.fastjson2.JSON;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Eq {
    public static void main(String[] args) throws IOException {
        byte[] abs = Files.readAllBytes(Path.of("ab"));
        int[][] ints = JSON.parseObject(abs, int[][].class);
        System.out.println(new Eq().numBusesToDestination(ints, 1, 203));
    }

    //字母序连续字符串 是由字母表中连续字母组成的字符串。换句话说，字符串 "abcdefghijklmnopqrstuvwxyz" 的任意子字符串都是 字母序连续字符串 。
    //
    //例如，"abc" 是一个字母序连续字符串，而 "acb" 和 "za" 不是。
    //给你一个仅由小写英文字母组成的字符串 s ，返回其 最长 的 字母序连续子字符串 的长度。
    //
    //
    //
    //示例 1：
    //
    //输入：s = "abacaba"
    //输出：2
    //解释：共有 4 个不同的字母序连续子字符串 "a"、"b"、"c" 和 "ab" 。
    //"ab" 是最长的字母序连续子字符串。
    //示例 2：
    //
    //输入：s = "abcde"
    //输出：5
    //解释："abcde" 是最长的字母序连续子字符串。
    //
    //
    //提示：
    //
    //1 <= s.length <= 105
    //s 由小写英文字母组成
    public int longestContinuousSubstring(String s) {
        int res = 0;
        int L = 0;
        int R = 0;
        for (; R < s.length(); R++) {
            if (R - L + 1 <= 1) {
                res = Math.max(res, R - L + 1);
                if (res == 3) {
                    System.out.println(1);
                }
                continue;
            }

            if ((s.charAt(R - 1) + 1 - 'a') == s.charAt(R) - 'a') {
                res = Math.max(res, R - L + 1);
                if (res == 3) {
                    System.out.println(1);
                }
                continue;
            }
            L = R;
        }
        return res;
    }

    //给你一个下标从 0 开始长度为 n 的整数数组 buses ，其中 buses[i] 表示第 i 辆公交车的出发时间。同时给你一个下标从 0 开始长度为 m 的整数数组 passengers ，其中 passengers[j] 表示第 j 位乘客的到达时间。所有公交车出发的时间互不相同，所有乘客到达的时间也互不相同。
    //
    //给你一个整数 capacity ，表示每辆公交车 最多 能容纳的乘客数目。
    //
    //每位乘客都会搭乘下一辆有座位的公交车。如果你在 y 时刻到达，公交在 x 时刻出发，满足 y <= x  且公交没有满，那么你可以搭乘这一辆公交。最早 到达的乘客优先上车。
    //
    //返回你可以搭乘公交车的最晚到达公交站时间。你 不能 跟别的乘客同时刻到达。
    //
    //注意：数组 buses 和 passengers 不一定是有序的。
    //
    //
    //
    //示例 1：
    //
    //输入：buses = [10,20], passengers = [2,17,18,19], capacity = 2
    //输出：16
    //解释：
    //第 1 辆公交车载着第 1 位乘客。
    //第 2 辆公交车载着你和第 2 位乘客。
    //注意你不能跟其他乘客同一时间到达，所以你必须在第二位乘客之前到达。
    //示例 2：
    //
    //输入：buses = [20,30,10], passengers = [19,13,26,4,25,11,21], capacity = 2
    //输出：20
    //解释：
    //第 1 辆公交车载着第 4 位乘客。
    //第 2 辆公交车载着第 6 位和第 2 位乘客。
    //第 3 辆公交车载着第 1 位乘客和你。
    //
    //
    //提示：
    //
    //n == buses.length
    //m == passengers.length
    //1 <= n, m, capacity <= 105
    //2 <= buses[i], passengers[i] <= 109
    //buses 中的元素 互不相同 。
    //passengers 中的元素 互不相同 。
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int pIndex = 0;
        int start = 0;
        int end = 0;
        int busIndex = 0;
        for (int i = 0; i < buses.length; i++) {
            int cur = buses[i];
            busIndex = i;
            start = pIndex;
            for (int j = 0; j < capacity; j++) {
                if (pIndex == passengers.length) {
                    break;
                }
                if (passengers[pIndex] > cur) {
                    break;
                }
                pIndex++;
            }
            end = pIndex;
        }
        int res;
        if (end - start < capacity) {
            res = buses[busIndex];
        } else {
            res = passengers[end - 1];
        }
        for (int i = end - 1; i >= 0; i--) {
            if (res > passengers[i]) {
                break;
            }
            if (res == passengers[i]) {
                res--;
            }
        }
        return res;
    }

    //给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
    //
    //例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
    //现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
    //
    //求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
    //
    //
    //
    //示例 1：
    //
    //输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
    //输出：2
    //解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
    //示例 2：
    //
    //输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
    //输出：-1
    //
    //
    //提示：
    //
    //1 <= routes.length <= 500.
    //1 <= routes[i].length <= 105
    //routes[i] 中的所有值 互不相同
    //sum(routes[i].length) <= 105
    //0 <= routes[i][j] < 106
    //0 <= source, target < 106
    public int numBusesToDestination(int[][] routes, int source, int target) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                max = Math.max(max, routes[i][j]);
                map.computeIfAbsent(routes[i][j], k -> new HashMap<>()).put(i, j);
            }
        }
        PriorityQueue<Info> priorityQueue = new PriorityQueue<>((s1, s2) -> {
            return s1.wight - s2.wight;
        });
        boolean[][] visit = new boolean[max + 1][routes.length];
        int res = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> busEntry : map.get(source).entrySet()) {
            Info info = new Info();
            info.source = source;
            info.wight = 1;
            info.bus = busEntry.getKey();
            info.nextIndex = (busEntry.getValue() + 1) % routes[busEntry.getKey()].length;
            priorityQueue.add(info);
        }
        for (; !priorityQueue.isEmpty(); ) {
            Info poll = priorityQueue.poll();
            if (visit[poll.source][poll.bus]) {
                continue;
            }
            visit[poll.source][poll.bus] = true;

            if (poll.source == target) {
                res = Math.min(res, poll.wight);
            }

            Info info = new Info();
            info.source = routes[poll.bus][poll.nextIndex];
            info.wight = poll.wight;
            info.bus = poll.bus;
            info.nextIndex = (poll.nextIndex + 1) % routes[poll.bus].length;
            if (!visit[info.source][info.bus]) {
                priorityQueue.add(info);
            }
            for (Map.Entry<Integer, Integer> busEntry : map.getOrDefault(poll.source, new HashMap<>()).entrySet()) {
                if (poll.bus != busEntry.getKey()) {
                    Info i = new Info();
                    i.source = poll.source;
                    i.wight = poll.wight + 1;
                    i.bus = busEntry.getKey();
                    i.nextIndex = (busEntry.getValue() + 1) % routes[busEntry.getKey()].length;
                    if (!visit[i.source][i.bus]) {
                        priorityQueue.add(i);
                    }
                }
            }
        }

        if (res == Integer.MAX_VALUE) {
            res = -1;
        }
        if (source == target) {
            res = 0;
        }
        return res;
    }

    public static class Info {
        int bus;
        int source;
        int wight;
        int nextIndex;
    }
}
