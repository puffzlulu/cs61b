public class SLList{
	public static class IntNode{
		public int item;
		public IntNode next;

		public IntNode(int i,IntNode n){
			item=i;
			next=n;
		}

		/**private static int size(IntNode p){
			if(p.next==null){
				return 1;
			}
			return 1+size(p.next);
		}*/
	}

	private IntNode sentinel;
	private int size;

	public SLList(){
		sentinel=new IntNode(63,null);
		size=0;
	}

	public SLList(int x){
		size=1;
		sentinel=new IntNode(63,null);
		sentinel.next=new IntNode(x,null);
	}

	public void addFirst(int x){
		size+=1;
		sentinel.next=new IntNode(x,sentinel.next);
	}

	public int getFirst(){
		return sentinel.next.item;
	}

	public void addLast(int x){
		size+=1;
		IntNode p=sentinel;
		while(p.next!=null){
			p=p.next;
		}
		p.next=new IntNode(x,null);
	}

	public int size(){
		return size;
	}

	public static void main(String[] args){
		SLList s1=new SLList();
		System.out.println(s1.size);
	}
}
