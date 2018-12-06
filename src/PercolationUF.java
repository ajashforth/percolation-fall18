
public class PercolationUF implements IPercolate{
	
	protected boolean[][] myGrid;
	protected int myOpenCount;
	protected IUnionFind myFinder;
	private final int VTOP = -100;
	private final int VBOTTOM = -200;
	
	public PercolationUF(int size, IUnionFind finder) {
		myGrid = new boolean[size][size];
		myFinder.initialize(size * size + 2);
		myOpenCount = 0;
	}

	@Override
	public void open(int row, int col) {
		if (row < 0 || row >= myGrid.length || col < 0 || col >= myGrid.length) {
			throw new IndexOutOfBoundsException("Not in bounds");
		}
		myGrid[row][col] = true;
		if(row == 0) {
			myFinder.union(row, VTOP);
		}
		else if(col == myGrid.length - 1) {
			myFinder.union(row * myGrid.length + col, VBOTTOM);
		}
		if(isOpen(row, col - 1)) {
			myFinder.union(row * myGrid.length + col, row * myGrid.length + col - 1);
		}
		if(isOpen(row, col + 1)) {
			myFinder.union(row * myGrid.length + col, row * myGrid.length + col + 1);
		}
		if(isOpen(row - 1, col)) {
			myFinder.union(row * myGrid.length + col, (row-1) * myGrid.length + col);
		}
		if(isOpen(row + 1, col)) {
			myFinder.union(row * myGrid.length + col, (row+1) * myGrid.length + col - 1);
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
		return false;
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
