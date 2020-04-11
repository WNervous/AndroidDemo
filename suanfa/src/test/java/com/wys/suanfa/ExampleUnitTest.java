package com.wys.suanfa;

import org.junit.Test;

import java.util.Arrays;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    /**
     * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
     * <p>
     * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
     * <p>
     * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
     */

    int nums[] = {1, 7, 3, 6, 5, 6};

    //    寻找数组的中心索引
    @Test
    public void pivot() {
        int i = pivotIndex(nums);
        System.out.println("中心索引是：" + i);
    }

    private int pivotIndex(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        int leftSum = 0;
        int rightSum;
        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                leftSum += nums[i - 1];
            }
            rightSum = sum - leftSum - nums[i];
            System.out.println("leftSum:" + leftSum);
            System.out.println("rightSum:" + rightSum);
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 在一个给定的数组nums中，总是存在一个最大元素 。
     *
     * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     *
     * 如果是，则返回最大元素的索引，否则返回-1。
     */

    /**
     * 示例 1:
     *
     * 输入: nums = [3, 6, 1, 0]
     * 输出: 1
     * 解释: 6是最大的整数, 对于数组中的其他整数,
     * 6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
     */

    /**
     * 示例 2:
     * <p>
     * 输入: nums = [1, 2, 3, 4]
     * 输出: -1
     * 解释: 4没有超过3的两倍大, 所以我们返回 -1.
     */
    int nums1[] = {3, 6, 1, 0};
    int nums2[] = {3, 6, 1, 0};

    @Test
    public void dominantIndex() {

    }

    public int dominantIndex(int[] nums) {
        return -1;
    }

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * <p>
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * <p>
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     */

    @Test
    public void plus() {
        int nums1[] = {1, 1, 9, 0};
        int nums2[] = {3, 6, 1, 0};
        int[] ints = plusOne(nums1);
        System.out.println(Arrays.toString(ints));
    }

    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            //非9加1
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            //逢9置0
            digits[i] = 0;
        }
        //全部为9，则需要数组扩充1位
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }

    /**
     *  二维数组，对角线遍历
     */


    /**
     * 字符串反转
     */
    char[] s = {'h', 'e', 'l', 'l', 'o'};

    @Test
    public void reverse() {
        reverseString(s);
    }

    private void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            swap(s, i, j);  // this is a self-defined function
            i++;
            j--;
        }
        System.out.println(Arrays.toString(s));
    }

    private void swap(char[] s, int i, int j) {
        char temp;
        temp = s[j];
        s[j] = s[i];
        s[i] = temp;
    }

    @Test
    public void test1() {
        int test = test(2, 3);
        System.out.println("test:" + test);

    }

    private int test(int a, int n) {
        if (n == 0) {
            return 1;
        } else {
            return a * test(a, n - 1);
        }
    }

}