class Solution {
    public int removeDuplicates(int[] A) {
        //双指针解决

        //边界条件判断
        if (A == null || A.length == 0)
            return 0;
        int left = 0;
        for (int right = 1; right < A.length; right++)
            //如果左指针和右指针指向的值一样，说明有重复的，
            //这个时候，左指针不动，右指针继续往右移。如果他俩
            //指向的值不一样就把右指针指向的值往前挪
            if (A[left] != A[right])
                A[++left] = A[right];
        return ++left;

    }
}