/*
 * Segment tree for the minimum value between two positions of an array.
 * (Range sum)
 */
import static java.lang.Math.*;
public class SegmentTreeSum {
	int n; //length of underlying array (0-indexed)
	int tree[]; //array for the segment tree (0-indexed)

	SegmentTreeSum(int _n) {
		n = _n;
		tree = new int[4*n];
	}
	
	//get partial sum of the original array a[x...y]
	int query(int x, int y) {
		return query(x, y, 1, 0, n-1);
	}
	
	int query(int x, int y, int p, int low, int high) {
		if(x>y) return 0;
		if(low>=x && high<=y) return tree[p];
		
		int mid = (low+high)/2;
		int left = query(x, min(y, mid), p*2, low, mid);
		int right = query(max(x, mid+1), y, p*2+1, mid+1, high);
		return left + right;
	}
	
	//add v at position x
	void addValue(int x, int v) {
		addValue(x, v, 1, 0, n-1);
	}
	
	void addValue(int x, int v, int p, int low, int high) {
		if(low==high) {
			tree[p]+=v; 
			return; 
		}
		
		int mid = (low+high)/2;
		if(x<=mid) 
			addValue(x, v, p*2, low, mid);
		else 
			addValue(x, v, p*2+1, mid+1, high);
	
		tree[p] = tree[p*2] + tree[p*2+1];
	}
	
	void buildSegTree(int[] A) {
		for(int j=0; j<min(n, A.length); j++)
			addValue(j, A[j]);
	}
}
