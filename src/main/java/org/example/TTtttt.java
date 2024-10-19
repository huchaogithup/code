package org.example;

public class TTtttt {





    //3144. 分割字符频率相等的最少子字符串
    //中等
    //相关标签
    //相关企业
    //提示
    //给你一个字符串 s ，你需要将它分割成一个或者更多的 平衡 子字符串。比方说，s == "ababcc" 那么 ("abab", "c", "c") ，
    // ("ab", "abc", "c") 和 ("ababcc") 都是合法分割，但是 ("a", "bab", "cc") ，("aba", "bc", "c") 和 ("ab", "abcc") 不是，不平衡的子字符串用粗体表示。
    //
    //请你返回 s 最少 能分割成多少个平衡子字符串。
    //
    //注意：一个 平衡 字符串指的是字符串中所有字符出现的次数都相同。
    //
    //
    //
    //示例 1：
    //
    //输入：s = "fabccddg"
    //
    //输出：3
    //
    //解释：
    //
    //我们可以将 s 分割成 3 个子字符串：("fab, "ccdd", "g") 或者 ("fabc", "cd", "dg") 。
    //
    //示例 2：
    //
    //输入：s = "abababaccddb"
    //
    //输出：2
    //
    //解释：
    //
    //我们可以将 s 分割成 2 个子字符串：("abab", "abaccddb") 。
    //
    //
    //
    //提示：
    //
    //1 <= s.length <= 1000
    //s 只包含小写英文字母。
    public static void main(String[] args) {
        System.out.println(new TTtttt().minimumSubstringsInPartition("abababaccddb"));
    }

    int[][] counts;
    int[] cache;

    public int minimumSubstringsInPartition(String s) {
        counts = new int[s.length()][26];
        counts[0][s.charAt(0) - 'a'] = 1;
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                counts[i][j] = counts[i - 1][j];
            }
            counts[i][s.charAt(i) - 'a']++;
        }
        cache = new int[s.length()];
        return p(s, 0);
    }

    private int p(String s, int i) {
        if (i == s.length()) {
            return 0;
        }
        if (cache[i] != 0) {
            return cache[i];
        }
        int res = s.length();
        out:
        for (int j = i; j < s.length(); j++) {
            int count = -1;
            if (i == 0) {
                for (int k = 0; k < 26; k++) {
                    if (counts[j][k] == 0) {
                        continue;
                    }
                    if (count == -1) {
                        count = counts[j][k];
                        continue;
                    }
                    if (count != counts[j][k]) {
                        continue out;
                    }
                }
                res = Math.min(res, p(s, j + 1) + 1);

            } else {
                for (int k = 0; k < 26; k++) {
                    if (counts[j][k] - counts[i - 1][k] == 0) {
                        continue;
                    }
                    if (count == -1) {
                        count = counts[j][k] - counts[i - 1][k];
                        continue;
                    }
                    if (count != counts[j][k] - counts[i - 1][k]) {
                        continue out;
                    }
                }
                res = Math.min(res, p(s, j + 1) + 1);
            }
        }
        cache[i] = res;
        return res;
    }
}
