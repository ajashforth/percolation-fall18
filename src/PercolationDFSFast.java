
public class PercolationDFSFast extends PercolationDFS {
	public PercolationDFSFast(int k) {
		super(k);
	}
	
	@Override
	protected void updateOnOpen(int row, int col) {
		boolean above = inBounds(row, col - 1) && isFull(row, col - 1);
		boolean below = inBounds(row, col + 1) && isFull(row, col + 1);
		boolean left = inBounds(row - 1, col) && isFull(row - 1, col);
		boolean right = inBounds(row + 1, col) && isFull(row + 1, col);
		if(row == 0 || below || above || left || right) {
			dfs(row, col);
		}
	}
}
