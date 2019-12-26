package leetcode;

public class TrapRainWater_42 {
    public int trap(int[] height) {
        if (height.length==0) return 0;
        int N = height.length;
        int[] leftMax = new int[N], rightMax = new int[N];
        int area = 0;

        leftMax[0] = height[0];
        for(int i=1; i<N; i++){
            leftMax[i] = Math.max(height[i], leftMax[i-1]);
        }

        rightMax[N-1] = height[N-1];
        for(int i=N-2; i>=0; i--){
            rightMax[i] = Math.max(height[i], rightMax[i+1]);
        }

        for(int i=1; i<N-1; i++){
            area += (Math.min(leftMax[i], rightMax[i]) - height[i]);
        }

        return area;
    }
}
