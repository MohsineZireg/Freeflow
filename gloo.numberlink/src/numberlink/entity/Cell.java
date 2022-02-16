package numberlink.entity;

import numberlink.control.Direction;

public class Cell {

	private int line;
	private int column;
	private boolean full;
	private End end;

	public Cell(int line, int column, boolean full, End end) {
		this.line = line;
		this.column = column;
		this.full = full;
		this.end = end;
	}

	public boolean acceptPath(Path path2) {
		if (isEnd()) {
			Tag currTag = path2.getTag();
			if (end.sameTag(currTag)) {
				path2.addCell(this);
				return true;
			}
			return false;
		}
		boolean isValid = !this.hasPath();
		if (isValid)
			path2.addCell(this);
		return isValid;

	}

	public Path createNewPath() {
		Path curr_path = end.createNewPath();
		curr_path.addCell(this);
		this.full = true;
		return curr_path;
	}

	public int getColumn() {
		return column;
	}

	public int getLine() {
		return line;
	}

	public Cell getNeighbor(Grid grid, Direction dir) {
		return grid.getNeighbor(this, dir);

	}

	public boolean isEnd() {
		return (end != null);
	}

	public boolean hasPath() {
		return full;
	}

	public void setEnd(End end) {
		this.end = end;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

}
