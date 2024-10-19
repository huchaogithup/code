package org.example;

import java.util.Scanner;

public class Ffffff {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int res=p(str,0,'(',false);
        System.out.println(res);
    }

    public static int p(String str,int index,char ch , boolean f){
        int res=0;
        int c = 0;
        for(int i = index ; i < str.length();i++){
            if(str.charAt(i)==ch){
                c++;
            }else{
                c--;
                if(c>=0){
                    res+=2;
                }
            }
            if(c<0){
                res=Math.max(res,p(str,i+1,ch,f));
                return res;
            }
        }

        if(c==0){
            return res;
        }

        if(f){
            return  res;
        }
        return p(new StringBuilder(str.substring(index)).reverse().toString(),0,')',true);
    }
}