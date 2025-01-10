import java.util.Scanner;

public class Main {
	static final long MOD = 1000000007;
	static final int END = 200000;
	static int N;
	static long[] arr;
	static long[] tree;
	static long[] cntTree;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new long[N];
		tree = new long[END*4];
		cntTree = new long[END*4];
		
		long ans = 1;
		for(int i = 0; i < N; i++) {
			long x = sc.nextLong();
			long left = cntQuery(0,x-1,1,0,END)*x - query(0,x-1,1,0,END);
			long right = query(x+1,END,1,0,END) - cntQuery(x+1,END,1,0,END)*x;
			if(i!=0)
				ans = (left+right)%MOD * ans%MOD;
			update(x,x,1,0,END);
			cntUpdate(x,1,1,0,END);
		}
		System.out.println(ans);
		sc.close();
	}
	
	static long query(long s, long e, int node, int ns, int ne) {
		if(ne < s || ns > e) return 0;
		if(s <= ns && ne <= e) return tree[node];
		int mid = (ns+ne)/2;
		return query(s,e,node*2,ns,mid)+query(s,e,node*2+1,mid+1,ne);
	}
	
	static long cntQuery(long s, long e, int node, int ns, int ne) {
		if(ne < s || ns > e) return 0;
		if(s <= ns && ne <= e) return cntTree[node];
		int mid = (ns+ne)/2;
		return cntQuery(s,e,node*2,ns,mid)+cntQuery(s,e,node*2+1,mid+1,ne);
	}
	
	static long update(long i, long val, int node, int s, int e) {
		if(i < s || i > e) return tree[node];
		if(s==e) return tree[node]+=val;
		int mid= (s+e)/2;
		return tree[node] = update(i,val,node*2,s,mid)+update(i,val,node*2+1,mid+1,e);
	}
	
	static long cntUpdate(long i, long val, int node, int s, int e) {
		if(i < s || i > e) return cntTree[node];
		if(s==e) return cntTree[node]+=val;
		int mid= (s+e)/2;
		return cntTree[node] = cntUpdate(i,val,node*2,s,mid)+cntUpdate(i,val,node*2+1,mid+1,e);
	}
}