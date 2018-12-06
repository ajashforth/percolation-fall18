
public class PercolationUF implements IPercolate{
	
	protected boolean[][] myGrid;
	protected int myOpenCount;
	protected IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;
	
	public PercolationUF(int size, IUnionFind finder) {
		myGrid = new boolean[size][size];
		finder.initialize(size * size + 2);
		myFinder = finder;
		myOpenCount = 0;
		VTOP = size * size;
		VBOTTOM = size * size + 1;
	}

	@Override
	public void open(int row, int col) {
		if (row < 0 || row >= myGrid.length || col < 0 || col >= myGrid.length) {
			throw new IndexOutOfBoundsException("Not in bounds");
		}
		if(myGrid[row][col] != true) {
			myGrid[row][col] = true;
			myOpenCount = myOpenCount + 1;
			if(row == 0) {
				myFinder.union(col, VTOP);
			}
			else if(row == myGrid.length - 1) {
				myFinder.union(row * myGrid.length + col, VBOTTOM);
			}
			if(col - 1 >= 0 && isOpen(row, col - 1)) {
				myFinder.union(row * myGrid.length + col, row * myGrid.length + col - 1);
			}
			if(col + 1 < myGrid.length && isOpen(row, col + 1)) {
				myFinder.union(row * myGrid.length + col, row * myGrid.length + col + 1);
			}
			if(row - 1 >= 0 && isOpen(row - 1, col)) {
				myFinder.union(row * myGrid.length + col, (row-1) * myGrid.length + col);
			}
			if(row + 1 < myGrid.length && isOpen(row + 1, col)) {
				myFinder.union(row * myGrid.length + col, (row+1) * myGrid.length + col - 1);
			}
		}
	}

	@Override
	public boolean isOpen(int row, int col) {
		if (row < 0 || row >= myGrid.length || col < 0 || col >= myGrid.length) {
			throw new IndexOutOfBoundsException("Not in bounds");
		}
		if (myGrid[row][col] == true) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isFull(int row, int col) {
		if (row < 0 || row >= myGrid.length || col < 0 || col >= myGrid.length) {
			throw new IndexOutOfBoundsException("Not in bounds");
		}
		if(myFinder.connected(row * myGrid.length + col, VTOP)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean percolates() {
		if(myFinder.connected(VTOP,VBOTTOM)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}
}
