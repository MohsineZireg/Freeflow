package numberlink.entity;

import java.util.ArrayList;
import java.util.List;

import numberlink.control.Direction;

public class Path {

	private List<Cell> path = new ArrayList<>();
	private Tag tag;

	public Path(Tag tag) {
		this.tag = tag;
	}

	public void addCell(Cell cell) {
		path.add(cell);
		cell.setFull(true);

	}

	public boolean advance(Grid currGrid, Direction dir) {
		Cell current_cell = getLastCell();
		Cell next_cell = current_cell.getNeighbor(currGrid, dir);
		if (next_cell == null)
			return false;
		return next_cell.acceptPath(this);

	}

	public Cell getLastCell() {
		return path.get(path.size() - 1);
	}

	public Tag getTag() {
		return tag;
	}

	public int getTagId() {
		return tag.getId();
	}

}
