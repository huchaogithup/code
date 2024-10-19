package org.example;

import java.util.ArrayDeque;

public class Q1 {

    static TNode root = new TNode();

    public static void main(String[] args) {

        boolean a = false, b = false, c = true, d = false, e = false;
        var ff = a ? b ? d : e : c ;
        System.out.println(ff);

        System.exit(0);

        build(new String[]{"abc", "aaaaaa"});
        String target = "abcaaa";
        TNode node = root;
        int i = 0;
        for (; i < target.length(); i++) {
            node = node.next[target.charAt(i) - 'a'];
            if (node == root) {
                break;
            }
        }
        System.out.println(i == target.length());
    }


    public static void build(String[] words) {
        for (int i = 0; i < words.length; i++) {
            TNode node = root;
            String prefix = "";
            for (int j = 0; j < words[i].length(); j++) {
                prefix = prefix + words[i].charAt(j);
                int index = words[i].charAt(j) - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new TNode();
                    node.next[index].prefix = prefix;
                }
                node = node.next[index];
            }
            node.isEnd = true;
        }

        ArrayDeque<TNode> qq = new ArrayDeque<>();
        qq.add(root);
        for (; !qq.isEmpty(); ) {
            TNode node = qq.poll();
            for (int i = 0; i < node.next.length; i++) {
                if (node.fail == null) {
                    if (node.next[i] == null) {
                        node.next[i] = root;
                    } else {
                        node.next[i].fail = root;
                        qq.add(node.next[i]);
                    }
                    continue;
                }

                if (node.next[i] == null) {
                    node.next[i] = node.fail.next[i];
                } else {
                    node.next[i].fail = node.fail.next[i];
                    qq.add(node.next[i]);
                }
            }
        }
    }


    public static class TNode {
        String prefix = "";
        TNode[] next = new TNode[26];
        boolean isEnd = false;
        TNode fail;
    }
}
