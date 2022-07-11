package com.sk.practice.study.string;

public class StringKMP {


    public int getIndexOf(String str, String subStr) {
        // 不合法的情况
        if (str == null || subStr == null || str.length() < 1 || subStr.length() > str.length()) {
            return -1;
        }


        char[] strCharArray = str.toCharArray();
        char[] subCharArray = subStr.toCharArray();
        int[] next = getNextArray(subCharArray);
        int i = 0;
        int j = 0;
        while (i < strCharArray.length && j < subCharArray.length) {
            if (strCharArray[i] == subCharArray[j]) {  // 位置相等，则往下一个比
                i++;
                j++;
            } else if (j == 0) {  //  或者next[j] == -1   i和j位置相比，不相等。此时j已经到了第一个字符，则主串从i位置向后边挪一个
                i++;
            } else {
                j = next[j];     // next数组代表的是最长的前缀和后缀的相等的长度。
                // i和j位置相比，不相等。此时移动j的位置，将j移动到最长的前后缀长度的下一位直接比较
            }

        }

        return j == subCharArray.length ? i - j : -1;
    }

    public int[] getNextArray(char[] chars) {
        if (chars.length == 1) {
            return new int[]{-1};
        }

        int[] next = new int[chars.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;   // 数组的位置
        int cn = 0;  // 哪个位置的数和 i-1 位置的数据进行比较,也代表 i -1 位置的最大前后缀

        while (i < next.length) {
            if (chars[cn] == chars[i - 1]) {
                // 当 cn 位置的元素与 i- 1 位置的元素相等时，最大的前后缀等于i-1的前后缀cn再加上1
                next[i] = cn + 1;

                // i - 1位置的元素处理过了，要继续往后处理了
                i++;

                // cn还代表着哪一个元素和 i - 1的元素进行比较。
                // i-1 处理过了，继续往后要处理i了， 这cn个元素也要往后处理了，加1
                cn++;
            } else if (cn > 0) {
                // 当前跳到cn位置的字符，和 i - 1的字符配不上
                cn = next[cn];
            } else {
                // 比较到第一个元素了，还是和i-1位置的元素匹配不上
                next[i] = 0;
                i++;
            }
        }


        return new int[]{1};
    }
}
