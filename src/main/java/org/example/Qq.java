package org.example;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.*;
import java.util.stream.IntStream;

public class Qq {
    public static void main(String[] args) {

        VelocityEngine velocityEngine = new VelocityEngine();
        Properties props = new Properties();
        props.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        props.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init(props);
        // 加载模板
        Template template = velocityEngine.getTemplate("a.vm");
        // 创建 VelocityContext 并添加数据
        VelocityContext context = new VelocityContext();
        context.put("name", "Alice");
        context.put("age", 30);

        // 渲染模板
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        // 输出渲染结果
        System.out.println(writer);

        System.out.println(new Qq().maximizeWin(new int[]{2, 3, 5, 7, 7}, 1));

        for (int i = 0; i < 1000000; i++) {
            int[] array = IntStream.generate(() -> new Random().nextInt(10)).limit(5).toArray();
            Arrays.sort(array);
            int k = new Random().nextInt(array[0] + 1);
            if (new Qq().maximizeWin(array, k) != new Qq().baili(array, k)) {
                System.out.println(Arrays.toString(array));
                System.out.println(k);
                System.out.println(new Qq().maximizeWin(array, k));
                System.out.println(new Qq().baili(array, k));
                throw new RuntimeException("Asd");
            }
        }

    }

    public int maximizeWin(int[] prizePositions, int k) {
        int[] preNums = new int[prizePositions.length];
        int[] preEndNums = new int[prizePositions.length];
        int L = 0;
        int R = 0;
        for (; L < preNums.length; ) {
            if (R < preNums.length) {
                for (; prizePositions[L] + k < prizePositions[R]; ) {
                    preNums[L] = R - L;
                    preEndNums[L] = R;
                    L++;
                }
                R++;
            } else {
                preNums[L] = R - L;
                preEndNums[L] = R;
                L++;
            }
        }
        int[] leftMaxNums = new int[prizePositions.length];
        leftMaxNums[prizePositions.length - 1] = 1;
        for (int i = prizePositions.length - 2; i >= 0; i--) {
            leftMaxNums[i] = Math.max(leftMaxNums[i + 1], preNums[i]);
        }

        int res = 0;
        for (int i = 0; i < prizePositions.length; i++) {
            if (preEndNums[i] == prizePositions.length) {
                res = Math.max(res, preNums[i]);
            }
            if (preEndNums[i] < prizePositions.length) {
                res = Math.max(res, preNums[i] + leftMaxNums[preEndNums[i]]);
            }
        }
        return res;
    }

    public int baili(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, p1(nums, 0, i + 1, k) + p1(nums, i + 1, nums.length, k));
        }
        return res;
    }

    private int p1(int[] nums, int start, int end, int k) {
        int res = 0;
        for (int i = start; i < end; i++) {
            for (int j = start; j < end; j++) {
                if (nums[j] <= nums[i] + k) {
                    res = Math.max(res, j - i + 1);
                    continue;
                }
                break;
            }
        }
        return res;
    }


    public String removeStars(String s) {
        char[] stack = new char[s.length()];
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                index--;
                continue;
            }
            stack[index] = s.charAt(i);
            index++;
        }
        return new String(stack, 0, index);
    }

}
