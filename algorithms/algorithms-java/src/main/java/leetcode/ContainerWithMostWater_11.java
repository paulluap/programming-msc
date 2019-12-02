package leetcode;

public class ContainerWithMostWater_11 {

    public static void main(String[] args) {
        int result = new ContainerWithMostWater_11().maxArea(new int[]{1,8,6,2,5,4,8,3,7});
        System.out.println(result == 49);
        result= new ContainerWithMostWater_11().maxArea(new int[]{2,3,10,5,7,8,9});
        System.out.println(result == 36);
    }

    //[1,8,6,2,5,4,8,3,7] -> 49 (min(a[1], a[8])*(8-1)
    public int maxArea(int[] height) {
        int maxArea = -1;
        int i = 0, j = height.length - 1;
        while(i<j){
            int area = Math.min(height[i], height[j]) * (j - i);
            if (area > maxArea) {
                maxArea = area;
            }
            if (height[i] < height[j]){
                i++;
            }else{
                j--;
            }
        }
        return maxArea;
    }


}
