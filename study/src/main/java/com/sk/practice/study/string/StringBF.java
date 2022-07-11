package com.sk.practice.study.string;

public class StringBF {

    public static void main(String[] args) {
        int i = indexBF("abcdef", "cde");
        System.out.println(i);
    }

    /**
     * 串的暴力破解算法
     *
     * @param s    主串
     * @param subs 子串
     * @return 下标
     */
    public static int indexBF(String s, String subs) {
        int i = 0;
        int j = 0;
        char[] sChars = s.toCharArray();
        char[] subChars = subs.toCharArray();
        while (i <= s.length() - 1 && j <= subs.length() - 1) {
            if (sChars[i] == subChars[j]) {
                // 如果匹配上，主串和子串依次匹配下一个字符
                i++;
                j++;
            } else {
                // 主串、子串指针回溯重新下一次匹配
                i = i - j + 2;
                j = 1;
            }
        }
        // 跳出循环一共两种情况，一种是第1个条件不满足，即主串比完了
        // 另一种是第2个条件不满足，即子串比完了，也就是子串全部匹配成功

        if (j >= subs.length()) {
            return i - subs.length();
        } else {
            return 0;
        }

    }
}
