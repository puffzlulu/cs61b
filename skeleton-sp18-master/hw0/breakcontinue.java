public class breakcontinue{
	public static void windowPosSum(int[] a,int n){
		for(int i=0;i<a.length;i=i+1){
			int sum=0;
			if(a[i]<0){
				continue;
			}
			for(int j=0;j<=n;j=j+1){
				if((i+j)>=a.length){
					break;
				}
				sum=sum+a[i+j];
			}
			a[i]=sum;
		}
	}

	public static void main(String[] args){
		int[] a={1,2,-3,4,5,4};
		int n=3;
		windowPosSum(a,n);
		System.out.println(java.util.Arrays.toString(a));
	}
}
