import java.util.*;

public class PercolationBFS extends PercolationDFSFast{
	public PercolationBFS(int k) {
		super(k);
	}
	
	/**
	 * Private helper method to mark all cells that are open and reachable from
	 * (row,col).
	 * 
	 * @param row
	 *            is the row coordinate of the cell being checked/marked
	 * @param col
	 *            is the col coordinate of the cell being checked/marked
	 */
	@Override
	protected void dfs(int row, int col) {
		Queue<Integer> myQueue = new LinkedList<>();
		
		// out of bounds?
		if (! inBounds(row,col)) return;
		
		// full or NOT open, don't process
		if (isFull(row, col) || !isOpen(row, col))
			return;
		
		myGrid[row][col] = FULL;
		myQueue.add(row * myGrid.length + col);
		
		while (myQueue.size() != 0) {
			Integer i = myQueue.remove();
			Integer rowNext = i / myGrid.length;
			Integer colNext = i % myGrid.length;
			int[] above = {rowNext - 1, colNext};
			int[] below = {rowNext + 1, colNext};
			int[] left = {rowNext, colNext - 1};
			int[] right = {rowNext, colNext + 1};
			if(inBounds(above[0],above[1]) && isOpen(above[0],above[1]) && !isFull(above[0],above[1])) {
				myGrid[above[0]][above[1]] = FULL;
				myQueue.add(above[0] * myGrid.length + above[1]);
			}
			if(inBounds(below[0],below[1]) && isOpen(below[0],below[1]) && !isFull(below[0],below[1])) {
				myGrid[below[0]][below[1]] = FULL;
				myQueue.add(below[0] * myGrid.length + below[1]);
			}
			if(inBounds(left[0],left[1]) && isOpen(left[0],left[1]) && !isFull(left[0],left[1])) {
				myGrid[left[0]][left[1]] = FULL;
				myQueue.add(left[0] * myGrid.length + left[1]);
			}
			if(inBounds(right[0],right[1]) && isOpen(right[0],right[1]) && !isFull(right[0],right[1])) {
				myGrid[right[0]][right[1]] = FULL;
				myQueue.add(right[0] * myGrid.length + right[1]);
			}
		}
	}
}
