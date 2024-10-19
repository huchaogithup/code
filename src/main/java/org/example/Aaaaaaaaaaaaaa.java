package org.example;

import org.apache.logging.log4j.message.Message;

import java.util.*;

public class Aaaaaaaaaaaaaa {


    public static void main(String[] args) {
        System.out.println(new Aaaaaaaaaaaaaa().numberOfPermutations(3, new int[][]{{2, 2}, {0, 0}}));
    }


    public int numberOfPermutations(int n, int[][] requirements) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < requirements.length; i++) {
            map.put(requirements[i][0], requirements[i][1]);
        }
        this.map = map;
        this.n = n;
        cache = new int[n + 1][400+2];
        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[i].length; j++) {
                cache[i][j] = -1;
            }
        }
        return p(n - 1, -1);
    }

    int MOD = (int) (1e9 + 7);
    Map<Integer, Integer> map;
    int n;
    int[][] cache;

    private int p(int i, int total) {
        if (i == -1) {
            if (total == 0 || total == -1) {
                return 1;
            }
            return 0;
        }
        if (cache[i + 1][total + 1] != -1) {
            return cache[i + 1][total + 1];
        }

        int res = 0;
        Integer limit = map.getOrDefault(i, -1);
        if (total == -1 && limit == -1) {
            res = res + (i + 1) * p(i - 1, -1);
            res = res % MOD;
        }


        if (total == -1 && limit != -1) {
            for (int j = 0; j <= i && limit - j >= 0; j++) {
                res = res + p(i - 1, limit - j);
                res = res % MOD;
            }
        }

        if (total != -1 && limit == -1) {
            for (int j = 0; j <= i && total - j >= 0; j++) {
                res = res + p(i - 1, total - j);
                res = res % MOD;
            }
        }

        if (total == limit) {
            for (int j = 0; j <= i && total - j >= 0; j++) {
                res = res + p(i - 1, total - j);
                res = res % MOD;
            }
        }

        cache[i + 1][total + 1] = res;
        return res;
    }

}
