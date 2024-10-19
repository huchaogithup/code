package org.example;

public class Aa {
    public static void main(String[] args) {
        System.out.println(new Aa().checkRecord(2));
    }


    public String clearDigits(String s) {
        char[] queue = new char[s.length()];
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                index--;
            } else {
                queue[index] = s.charAt(i);
                index++;
            }
        }
        return new String(queue, 0, index);
    }

    int mod = (int) (1e9 + 7);
    int n;
    int[][][] cache;

    public int checkRecord(int n) {
        this.n = n;
        cache = new int[n][2][3];
        return p(0, 1, 0);
    }

    private int p(int i, int k, int c) {
        if (i == n) {
            return 1;
        }
        if (cache[i][k][c] != 0) {
            return cache[i][k][c];
        }
        int res = 0;
        if (k > 0) {
            res += p(i + 1, k - 1, 0);
            res %= mod;
        }
        if (c < 2) {
            res += p(i + 1, k, c + 1);
            res %= mod;
        }
        res += p(i + 1, k, 0);
        res %= mod;
        cache[i][k][c] = res;
        return res;
    }


    int[] sub = new int[]{'a', 'e', 'i', 'o', 'u'};
    int[][] ops = new int[][]{{1}, {0, 2}, {0, 1, 3, 4}, {2, 4}, {0}};
    int[][] cache1;

    public int countVowelPermutation(int n) {
        this.n = n;
        int res = 0;
        cache1 = new int[n][sub.length];
        for (int i = 0; i < sub.length; i++) {
            res += p(1, i);
            res %= mod;
        }
        return res;
    }

    private int p(int i, int c) {
        if (i == n) {
            return 1;
        }
        if (cache1[i][c] != 0) {
            return cache1[i][c];
        }
        int res = 0;
        for (int i1 = 0; i1 < ops[c].length; i1++) {
            res += p(i + 1, ops[c][i1]);
            res %= mod;
        }
        cache1[i][c] = res;
        return res;
    }


    public int countCombinations(String[] pieces, int[][] positions) {
        for (int i = 0; i < positions.length; i++) {
            positions[i][0]--;
            positions[i][1]--;
        }
        return p(pieces, 0, positions, new int[positions.length], new int[positions.length][2]);
    }

    private int p(String[] pieces, int i, int[][] positions, int[] step, int[][] dir) {
        if (i == pieces.length) {
            if (check(positions, step, dir)) {
                return 1;
            }
            return 0;
        }
        int res = 0;
        int[] dirs = getDirs(pieces[i]);
        for (int i1 = 0; i1 < dirs.length - 1; i1++) {
            dir[i][0] = dirs[i1];
            dir[i][1] = dirs[i1 + 1];
            for (int j = 1; j < 8; j++) {
                int r = positions[i][0] + j * dir[i][0];
                int c = positions[i][1] + j * dir[i][1];
                if (r < 0 || r >= 8 || c < 0 || c >= 8) {
                    continue;
                }
                step[i] = j;
                res += p(pieces, i + 1, positions, step, dir);
            }
        }
        step[i] = 0;
        res += p(pieces, i + 1, positions, step, dir);

        return res;
    }

    private int[] getDirs(String piece) {
        if (piece.equals("rook")) {
            return new int[]{-1, 0, 1, 0, -1};
        }
        if (piece.equals("bishop")) {
            return new int[]{-1, -1, 1, 1, -1};
        }
        if (piece.equals("queen")) {
            return new int[]{-1, -1, 1, 1, -1, 0, 1, 0, -1};
        }
        return null;
    }

    private boolean check(int[][] positions, int[] step, int[][] dir) {
        for (int i = 0; i < 8; i++) {
            int[][] pos = new int[8][8];
            for (int i1 = 0; i1 < positions.length; i1++) {
                int r = positions[i1][0];
                int c = positions[i1][1];
                int s = Math.min(i, step[i1]);
                r = s * dir[i1][0] + r;
                c = s * dir[i1][1] + c;
                if (pos[r][c] != 0) {
                    return false;
                }
                pos[r][c] = 1;
            }
        }
        return true;
    }


}
