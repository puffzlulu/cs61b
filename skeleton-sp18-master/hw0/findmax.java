public class findmax{
	public static int max(int[] nums){
		int n=nums.length,max=0,x=0;
		while(x<n){
			if(nums[x]>max){
				max=nums[x];
			}
			x=x+1;
		}
		return max;
	}

	public static void main(String[] args){
		int[] nums=new int[]{9,2,15,2,22,10,6};
		int m=max(nums);
		System.out.println(m);
	}
}
