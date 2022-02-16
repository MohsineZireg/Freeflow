package numberlink.control;

import java.util.ArrayList;

import numberlink.entity.Grid;
import numberlink.entity.Path;

/**
 * Contrôleur bouchon pour tester l'IHM du jeu Numberlink
 *
 * Cette classe ne sert qu'à tester que l'IHM est fonctionnellle en remplaçant
 * le modèle métier par un bouchon qui retourne des réponses pré-définies.
 *
 * @author Mohsine Zireg
 * @version 1.2
 */
public class ControllerStub implements IController {

	private Grid currGrid; 
	private Path currentPath;
	private ArrayList<ArrayList<Direction>> listDirs;

	public ControllerStub(int nbTags, int nbLines, int nbColumns, int[][] tmpS, int[][] tmpE) {
		super();
		this.currGrid = new Grid(nbTags, nbLines, nbColumns,tmpS,tmpE);
		setListDirs();
	}

	@Override
	public boolean action(Direction direction) {
		System.out.println("flèche " + direction.name());
		boolean progress_made = currentPath.advance(currGrid, direction);
		if (progress_made) {
			int id = currentPath.getTagId();
			listDirs.get(id).add(direction); // Demeter Law !!
			return currGrid.isFinished();
		}
		return false;
	}

	@Override
	public boolean clickCell(int ligne, int colonne) {
		currentPath = currGrid.createNewPath(ligne, colonne);
		System.out.println("clic en l" + ligne + "c" + colonne);
		return currentPath != null;
	}

	@Override
	public ArrayList<Direction> getDirections(int tag) {
		return listDirs.get(tag);

	}

	@Override
	public int getNbColumns() {
		return Grid.getNbColumns();
	}

	@Override
	public int getNbLines() {
		return Grid.getNbLines();
	}

	@Override
	public int getNbTags() {
		return Grid.getNbTags();

	}

	@Override
	public int[] getSecondEndPosition(int tag) {
		return Grid.getE()[tag];
	}

	@Override
	public int[] getStartPathPosition(int tag) {
		return Grid.getS()[tag];
	}

	@Override
	public void resetGrid() {
		int [][] starts = Grid.getS();
		int [][] ends = Grid.getE();
		setListDirs();
		currGrid = new Grid(getNbTags(), getNbLines(),getNbColumns(),starts,ends);
		System.out.println("Grid reset !");
	}

	private void setListDirs() {
		listDirs = new ArrayList<>();
		for (int i = 0; i < getNbTags(); i++) {
			listDirs.add(new ArrayList<Direction>());
		}
	}

}
