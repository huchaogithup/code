package org.example;

import java.util.ArrayList;
import java.util.List;

public class Feeee {
    public static void main(String[] args) {
        System.out.println(new Feeee().countCombinations(new String[]{"bishop"}, new int[][]{{8, 5}}));
        System.out.println(new Feeee().countCombinations(new String[]{"rook"}, new int[][]{ {7, 7}}));
        System.out.println(new Feeee().countCombinations(new String[]{"rook", "bishop"}, new int[][]{{7, 7},{8, 5} }));
    }

    String[] prices;

    public int countCombinations(String[] pieces, int[][] positions) {
        this.prices = pieces;
        int res = 0;
        for (int i = 0; i < positions.length; i++) {
            positions[i][0] -= 1;
            positions[i][1] -= 1;
        }
        res += p(positions, 0);
        return res;
    }


    private List<int[][]> p1(String[] prices, int i, int[][] ints) {
        ArrayList<int[][]> res = new ArrayList<>();
        if (i == prices.length) {
            int[][] c = ints.clone();
            for (int i1 = 0; i1 < c.length; i1++) {
                c[i1] = c[i1].clone();
            }
            res.add(c);
            return res;
        }

        int[] p1 = new int[]{-1, 0, 1, 0, -1};
        if (prices[i].equals("rook")) {
            for (int j = 0; j < p1.length - 1; j++) {
                ints[i][0] = p1[j];
                ints[i][1] = p1[j + 1];
                res.addAll(p1(prices, i + 1, ints));
            }
            return res;
        }

        int[] p2 = new int[]{-1, 1, 1, -1, -1};
        if (prices[i].equals("bishop")) {
            for (int j = 0; j < p2.length - 1; j++) {
                ints[i][0] = p2[j];
                ints[i][1] = p2[j + 1];
                res.addAll(p1(prices, i + 1, ints));
            }
            return res;
        }

        if (prices[i].equals("queen")) {
            for (int j = 0; j < p1.length - 1; j++) {
                ints[i][0] = p1[j];
                ints[i][1] = p1[j + 1];
                res.addAll(p1(prices, i + 1, ints));
            }

            for (int j = 0; j < p2.length - 1; j++) {
                ints[i][0] = p2[j];
                ints[i][1] = p2[j + 1];
                res.addAll(p1(prices, i + 1, ints));
            }
            return res;
        }
        return res;
    }

    private int p(int[][] positions, int i) {
        if (i == positions.length) {
            return 1;
        }
        int res = 0;

        int r = positions[i][0];
        int c = positions[i][1];
        List<int[][]> list = p1(new String[]{prices[i]}, 0, new int[1][2]);
        res += p(positions, i + 1);
        for (int j = 0; j < list.size(); j++) {
            int[] dir = list.get(j)[0];
            for (; hasNext(positions[i], dir); ) {
                positions[i][0] += dir[0];
                positions[i][1] += dir[1];
//                if (hasBlock(positions, i, positions[i][0], positions[i][1])) {
//                    break;
//                }
                res += p(positions, i + 1);
            }
            positions[i][0] = r;
            positions[i][1] = c;
        }

        return res;
    }

    private boolean hasBlock(int[][] positions, int i, int c, int r) {
        for (int j = 0; j < positions.length; j++) {
            if (j == i) {
                continue;
            }
            if (positions[j][0] == c && positions[j][1] == r) {
                return true;
            }
        }
        return false;
    }

    private boolean hasNext(int[] position, int[] dir) {
        int r = position[0] + dir[0];
        int c = position[1] + dir[1];
        if (r < 0 || r >= 8 || c < 0 || c >= 8) {
            return false;
        }
        return true;
    }


}
