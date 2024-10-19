package org.example;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine().toLowerCase();
        StringBuilder stringBuilder =new StringBuilder();
        // 移除所以非字母和数字的字符
        for ( int i = 0 ; i < s.length(); i++ ){
            if(s.charAt(i)>='a'&&s.charAt(i)<='z' || (s.charAt(i)>='0'&&s.charAt(i)<='9')){
                stringBuilder.append(s.charAt(i));
            }
        }
        System.out.println(p(stringBuilder.toString())?1:0);
    }


    public static boolean p(String s ){
        // 判断字符串是否是回文
        for(int i = 0 ;  i < s.length() ; i++){
            if(s.charAt(i)!=s.charAt(s.length()-1-i)){
                return false;
            }
        }
        return true;
    }
}