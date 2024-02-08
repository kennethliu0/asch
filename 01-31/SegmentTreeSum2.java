/*
 * Segment tree for partial sum between two positions of an array.
 *  (Range sum)
 */
public class SegmentTreeSum2 {
	int[] tree;   //array to store segment tree
	int height;   //height of segment tree
	int maxSize;  //size of the total (full complete binary) segment tree
	int STARTINDEX = 0;   //the smallest left end of any range
	int ENDINDEX;         //the biggest right end of any range
	
	SegmentTreeSum2(int n) { //n is the length of original array
		height = (int) Math.ceil(Math.log(n)/Math.log(2));
		maxSize = (int) Math.pow(2, height+1) - 1;  //1 << (height+1) - 1;
		tree = new int[maxSize];
		ENDINDEX = n-1;
	}

	//build segment tree using the original array A
	public void buildSegTree(int[] A) {
		buildSegTreeRec(0, STARTINDEX, ENDINDEX, A);
	}

	//find the partial sum between A[x...y], inclusive (0-indexed)
	public int getSum(int x, int y) {
		if(x<0 || ENDINDEX<y)
			return Integer.MIN_VALUE;

		return getSumRec(0, STARTINDEX, ENDINDEX, x, y);
	}

	//add "val" at position "pos": A[pos] += val
	public void addValue(int pos, int val) {
		addValueRec(0, STARTINDEX, ENDINDEX, pos, val);
	}

	/*====== Helper functions  ====== */

	//p: node ID in the segment tree
	//[low, high]: range controlled by node p
	//A: original array
	private int buildSegTreeRec(int p, int low, int high, int[] A) {
        if(low == high) {
            tree[p] = A[low];
            return tree[p];
        }

        int mid = (low + high)/2;
        tree[p] = buildSegTreeRec(leftChild(p), low, mid, A)
        		  + buildSegTreeRec(rightChild(p), mid+1, high, A);

        return tree[p];
	}
	
	//p: node ID in the segment tree
	//[low, high]: range of node p
	//[x, y]: query range
	private int getSumRec(int p, int low, int high, int x, int y) {
		if(x<=low && high<=y)  //[x, y] contains [low, high]
			return tree[p];

		if(y<low || high<x)   //[x, y] doesn't overlap [low, high]
			return 0;

		int mid = (low + high) / 2;
		return getSumRec(leftChild(p), low, mid, x, y)
			  +getSumRec(rightChild(p), mid+1, high, x, y);
	}
	
	//p: node ID in the segment tree
	//[low, high]: range of p
	//pos: position in original array to be modified
	//val: value to be added to pos
	private void addValueRec(int p, int low, int high, int pos, int val) {
		if(low<=pos  && pos<=high) {
			tree[p] = tree[p] + val;

			if(low != high) {
				int mid = (low + high) / 2;
				addValueRec(leftChild(p), low, mid, pos, val);
				addValueRec(rightChild(p), mid+1, high, pos, val);
			}
		}
	}
	
	public int leftChild(int k) {return 2*k+1;}
	public int rightChild(int k) {return 2*k+2;}
}