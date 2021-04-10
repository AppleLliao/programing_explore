/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。

 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * @author xinrongLiao
 *
 */
public class DeArrayElement {

    public  static int removeDuplicates(int[] nums){
        //双指针解决问题
        //边界条件判断
        if(nums==null||nums.length==0){
            return 0;
        }
        int left=0;
        for(int right=0;right<nums.length;right++){
            //如果左指针和右指针指向的值一样，说明有重复的，
            //这个时候，左指针不动，右指针继续往右移。
            //如果他两指向的值不一样就把右指针指向的值往前挪
            if(nums[left]!=nums[right]){
                nums[++left]=nums[right];
            }
        }
        for(int a=0;a<left+1;a++)
            System.out.print(":"+nums[a]);
        return ++left;
    }

    public static void main(String[] args) {
        int[] a={1,1,2,3,3,5,5};
        int length=DeArrayElement.removeDuplicates(a);
        System.out.println("arrays"+"length:"+length);

    }

}
