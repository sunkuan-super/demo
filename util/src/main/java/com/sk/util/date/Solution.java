package com.sk.util.date;

import java.util.Arrays;

public class Solution {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        /**
         * 如果第二个数组为空，第一个数组则为它本身
         * 如果第
         *
         */
        if (n == 0) {
            // 如果第二个数组为空，第一个数组则为它本身
            nums1 = nums1;
        } else if (m == 0) {
            // 如果第一个数组为空，第一个数组则为第二个元素
            nums1 = nums2;
        } else {
            int j = 0;
            for (int i = m - n; i < nums1.length; i++) {
                nums1[i] = nums2[j];
                j++;
            }

            Arrays.sort(nums1);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[4];
        nums1[0] = 6;
        nums1[1] = 0;
        nums1[2] = 0;
        nums1[3] = 0;

        int[] nums2 = new int[3];
        nums2[0] = 1;
        nums2[1] = 2;
        nums2[2] = 3;

        merge(nums1, 4, nums2, 3);

        for (int i : nums1) {
            System.out.println(i);
        }



    }
}
