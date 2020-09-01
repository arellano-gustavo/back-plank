package mx.qbits.plank.estudio;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public static void main(String...argv) {
        int[] a = {3,1,4,1,5};
        System.out.println(findPairs(a, 2));
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int [] iArr = addAll(nums1, nums2, len);
        Arrays.sort(iArr);
        if(len %2 == 0) {
            int mid = len/2;
            return (iArr[mid-1] + iArr[mid])/2.0;
        }
        return iArr[len/2];
    }
    private static int[] addAll(int[] nums1, int[] nums2, int len) {
        int[] result = new int[len];
        for(int i=0; i< nums1.length; i++) {
            result[i] = nums1[i];
        }
        for(int i=0; i< nums2.length; i++) {
            result[i+nums1.length] = nums2[i];
        }
        return result;
    }
    
    
    private static Set<String> store = new HashSet<>();
    public static int findPairs(int[] nums, int k) {
        int counter = 0;
        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                if (abs(nums[i]-nums[j])==k && !stored(nums[i],nums[j])) {
                    counter ++;
                    store.add(nums[i]+"_"+nums[j]);
                }
            }
        }
        return counter;
    }
    private static int abs(int num) {
        return (num>0)?num:-num;
    }
    private static boolean stored(int i, int j) {
        return store.contains(i+"_"+j);
    }

}
