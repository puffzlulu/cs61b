public class forfindmax{
	public static int formax(int[] nums){
		int max=0;
		for(int i=0;i<nums.length;i=i+1){
			if(nums[i]>max){
				max=nums[i];
			}
		}
		return max;
	}

	public static void main(String[] args){
		int[] nums=new int[]{9,2,15,2,22,10,6};
		int m=formax(nums);
		System.out.println(m);
	}
}
