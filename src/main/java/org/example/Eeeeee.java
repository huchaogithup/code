package org.example;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

import java.util.*;
import java.util.stream.IntStream;

public class Eeeeee {

    public static void main(String[] args) {
        System.out.println(new Eeeeee().p("aa", "cf"));
    }

    boolean use = false;
    int pos = 0;
    char tmp = '#';
    Set<String> cache = new HashSet<>();

    public boolean p(String s1, String s2) {
        String key = s1 + "->" + s2;
        System.out.println(key);
        if (cache.contains(key)) {
            return false;
        }
        cache.add(key);
        if (s1.equals(s2)) {
            return true;
        }
        if (use) {
            int i = s1.indexOf(s2.charAt(pos));
            s1 = s1.replaceAll(s1.charAt(i) + "", s2.charAt(i) + "");
            s1 = s1.replaceAll(tmp + "", s2.charAt(pos) + "");
            return p(s1, s2);
        } else {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == s2.charAt(i)) {
                    continue;
                }
                if (s1.indexOf(s2.charAt(i)) != -1) {
                    use = true;
                    pos = i;
                    s1 = s1.replaceAll(s1.charAt(i) + "", tmp + "");
                    return p(s1, s2);
                } else {
                    s1 = s1.replaceAll(s1.charAt(i) + "", s2.charAt(i) + "");
                    return p(s1, s2);
                }
            }
        }
        return false;
    }


}
