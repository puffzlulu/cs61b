public class functriangle{
	public static void drawTriangle(int n){
		int x=1,i;
		while(x<=n){
			i=0;
			while(i<x){
				i=i+1;
				System.out.print("*");
			}
			x=x+1;
			System.out.println("");
		}
	}
	
	public static void main(String[] args){
		int n=10;
		drawTriangle(n);
	}
}
