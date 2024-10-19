package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Eeeeeeee {


    public static void main(String[] args) {
        System.out.println(new Eeeeeeee().numDupDigitsAtMostN(12));
    }




    public int numDupDigitsAtMostN(int n) {
        return p((n + "").toCharArray(), 0, false, false, 0, false, new ArrayList<Integer>());
    }

    private int p(char[] str, int i, boolean start, boolean free, int len, boolean ok, List<Integer> set) {
        if (str.length == i) {
            if (start && ok) {
                return 1;
            }
            return 0;
        }
        int res = 0;
        if (!start) {
            res += p(str, i + 1, false, true, 0, ok, null);
            if (!free) {
                for (int j = 1; j < str[i] - '0'; j++) {
                    res += p(str, i + 1, true, true, 1, false, null);
                }
                set.add(str[i] - '0');
                res += p(str, i + 1, true, false, 1, false, set);
            } else {
                res += 9 * p(str, i + 1, true, true, 1, false, null);
            }
        } else {
            if (!free) {
                if (ok) {
                    for (int j = 0; j < str[i] - '0'; j++) {
                        res += p(str, i + 1, true, true, len + 1, true, set);
                    }
                    res += p(str, i + 1, true, false, len + 1, true, set);
                } else {
                    for (int j = 0; j < (str[i] - '0'); j++) {
                        if (!set.contains(j)) {
                            res += p(str, i + 1, true, true, len + 1, false, set);
                        } else {
                            res += p(str, i + 1, true, true, len + 1, true, set);
                        }
                    }
                    if (set.contains(str[i] - '0')) {
                        set.add(str[i] - '0');
                        res += p(str, i + 1, true, false, len + 1, true, set);
                    } else {
                        set.add(str[i] - '0');
                        res += p(str, i + 1, true, false, len + 1, false, set);
                    }
                    set.removeLast();
                }

            } else {
                if (ok) {
                    res += 10 * p(str, i + 1, true, true, len + 1, true, null);
                } else {
                    res += (10 - len) * p(str, i + 1, true, true, len + 1, false, null);
                    res += len * p(str, i + 1, true, true, len + 1, true, null);
                }
            }
        }
        return res;
    }


}
