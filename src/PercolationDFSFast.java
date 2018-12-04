
public class PercolationDFSFast extends PercolationDFS {
	public PercolationDFSFast(int k) {
		super(k);
	}
	
	@Override
	protected void updateOnOpen(int row, int col) {
		if(row == 0 || isFull(row, col + 1) || isFull(row, col - 1) || isFull(row + 1, col) || isFull(row - 1, col)) {
			dfs(row, col);
		}
	}
}
