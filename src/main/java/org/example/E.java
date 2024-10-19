package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class E {
    public static void main(String[] args) {
        p("123".toCharArray(), 0, new ArrayList<>(), false, false);
        System.out.println(res.size());
        System.out.println(res);
    }

    static List<String> res = new ArrayList<>();

    public static void p(char[] max, int i, List<Character> nums, boolean free, boolean has) {
        if (i == max.length) {
            if (has) {
                res.add(nums.stream().map(Character::toChars).map(String::new).collect(Collectors.joining()));
            }
            return;
        }
//        if (i == max.length - 1) {
//            if (!free) {
//                if (!has) {
//                    for (int j = 1; j <= (max[i] - '0'); j++) {
//                        nums.add((char) (j + '0'));
//                        res.add(nums.stream().map(Character::toChars).map(String::new).collect(Collectors.joining("")));
//                        nums.removeLast();
//                    }
//                } else {
//                    for (int j = 0; j <= (max[i] - '0'); j++) {
//                        nums.add((char) (j + '0'));
//                        res.add(nums.stream().map(Character::toChars).map(String::new).collect(Collectors.joining("")));
//                        nums.removeLast();
//                    }
//                }
//            } else {
//                if (!has) {
//                    for (int j = 1; j <= 9; j++) {
//                        nums.add((char) (j + '0'));
//                        res.add(nums.stream().map(Character::toChars).map(String::new).collect(Collectors.joining("")));
//                        nums.removeLast();
//                    }
//                } else {
//                    for (int j = 0; j <= 9; j++) {
//                        nums.add((char) (j + '0'));
//                        res.add(nums.stream().map(Character::toChars).map(String::new).collect(Collectors.joining("")));
//                        nums.removeLast();
//                    }
//                }
//            }
//            return;
//        }
        if (!free) {
            if (!has) {
                p(max, i + 1, nums, true, has);
                for (int j = 1; j <= (max[i] - '0'); j++) {
                    nums.add((char) (j + '0'));
                    p(max, i + 1, nums, j < (max[i] - '0'), true);
                    nums.removeLast();
                }
            } else {
                for (int j = 0; j <= (max[i] - '0'); j++) {
                    nums.add((char) (j + '0'));
                    p(max, i + 1, nums, j < (max[i] - '0'), true);
                    nums.removeLast();
                }
            }
        } else {
            if (!has) {
                p(max, i + 1, nums, true, has);
                for (int j = 1; j <= 9; j++) {
                    nums.add((char) (j + '0'));
                    p(max, i + 1, nums, true, true);
                    nums.removeLast();
                }
            } else {
                for (int j = 0; j <= 9; j++) {
                    nums.add((char) (j + '0'));
                    p(max, i + 1, nums, true, true);
                    nums.removeLast();
                }
            }
        }
    }
}
