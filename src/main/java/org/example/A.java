package org.example;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class A {

    public static class MyClass {
        void MyMethod() throws Exception {
            int unusedVar = 10;
            String str = null;
            System.out.println(str.length());
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int i = 0; i < list.size(); i++) {
            }
            for (int i = 0; i < list.size(); i++) {
                new Object();
            }
            try {
            } catch (Exception e) {
            }
            int status = 0;
            if (status == 1) {

            }
            FileInputStream fis = new FileInputStream("file.txt");
        }
    }
}
