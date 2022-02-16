package numberlink.entity;


import numberlink.control.Direction;

public class Grid {

	private static int nbLines;
	private static int nbColumns;
	private static int nbTags;
	private static Cell[][] grid;
	private static int[][] starts =new int[][] { { 0, 0 }, { 1, 4 }, { 1, 2 }, { 0, 2 }, { 0, 4 } };
	private static int[][] ends = new int[][] { { 4, 1 }, { 4, 3 }, { 4, 2 }, { 3, 1 }, { 3, 3 } };


	public Grid(int nbTags, int nbLines, int nbColumns, int[][] starts , int [][] ends) {
		
		
		Grid.nbTags = nbTags;
		Grid.nbLines = nbLines;
		Grid.nbColumns = nbColumns;
		Grid.starts = starts;
		Grid.ends = ends; 
		
		
		grid = new Cell[nbLines][nbColumns];
		for (int line = 0; line < nbLines; line++) {
			for (int column = 0; column < nbColumns; column++) {
				grid[line][column] = new Cell(line, column, false, null);
			}
		}
		for (int end = 0; end < nbTags; end++) {
			Tag currTag = new Tag();
			int line1 = starts[end][0];
			int column1 = starts[end][1];
			int line2 = ends[end][0];
			int column2 = ends[end][1];
			grid[line1][column1].setEnd(new End(currTag));
			grid[line2][column2].setEnd(new End(currTag));
		}
	}

	public Path createNewPath(int ligne, int colonne) {
		Cell cell = grid[ligne][colonne];
		if (!cell.isEnd())
			return null;
		if (!isStart(ligne, colonne))
			swapStartEnd(ligne, colonne);
		return cell.createNewPath();
	}

	public static int[][] getE() {
		return ends;
	}

	public static int getNbColumns() {
		return nbColumns;
	}

	public static int getNbLines() {
		return nbLines;
	}

	public static int getNbTags() {
		return nbTags;
	}

	public Cell getNeighbor(Cell cell, Direction dir) {
		int line = cell.getLine();
		int column = cell.getColumn();
		try {
			return switch (dir) {
			case DOWN -> grid[line + 1][column];
			case UP -> grid[line - 1][column];
			case RIGHT -> grid[line][column + 1];
			case LEFT -> grid[line][column - 1];
			default -> null;
			};

		} catch (Exception e) {
			System.out.println("Vous dépassez les limites de la grille !");
			return null;
		}
	}

	public static int[][] getS() {
		return starts;
	}

	public boolean isFinished() {

		for (int line = 0; line < nbLines; line++) {
			for (int column = 0; column < nbColumns; column++) {
				if (!grid[line][column].hasPath()) {
					return false;
				}

			}
		}
		return true;
	}

	private boolean isStart(int ligne, int colonne) {

		for (int[] element : starts) {
			if (element[0] == ligne && element[1] == colonne)
				return true;
		}
		return false;
	}

	private void swapStartEnd(int ligne, int colonne) {

		for (int i = 0; i < ends.length; i++) {
			if (ends[i][0] == ligne && ends[i][1] == colonne) {
				int[] tmp = starts[i];
				Cell firstCell = grid[tmp[0]][tmp[1]];
				firstCell.setFull(false);
				starts[i] = ends[i];
				ends[i] = tmp;
			}
		}
	}

}
