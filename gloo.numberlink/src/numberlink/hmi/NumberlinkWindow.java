package numberlink.hmi;

import java.awt.Dimension;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import numberlink.control.ControllerStub;
import numberlink.control.Direction;
import numberlink.control.IController;

/**
 * Fenêtre de l'IHM pour le jeu Numberlink
 * 
 * @author Dominique Marcadet
 * @version 1.2
 *
 */
@SuppressWarnings("serial")
public class NumberlinkWindow extends JFrame implements KeyListener {

	private static final int WINDOW_SIZE = 500;
    private static final int WINDOW_TITLE_HEIGHT = 20;
    private IController controller;

    /**
     * Initialise la fenêtre du jeu.
     * La fenêtre délègue à un panneau l'affichage de l'état du jeu.
     * Elle est responsable des dimensions globales de la fenêtre au départ et
     * de la transmission au contrôleur des appuis sur les flèches du clavier.
     * 
     * @param controller le contrôleur qu'il faut informer des appuis par
     *                   l'utilisateur des flèches du clavier
     */
    public NumberlinkWindow() {
    	@SuppressWarnings("resource")
		Scanner myObj = new Scanner(System.in);
		System.out.println("Voulez-vous jouer avec une grille prédéfinie ? ");
		String answer = myObj.next();
		if (answer.equals("Oui")) this.controller = new ControllerStub(5, 5, 5, new int[][] { { 0, 0 }, { 1, 4 }, { 1, 2 }, { 0, 2 }, { 0, 4 } }, new int[][] { { 4, 1 }, { 4, 3 }, { 4, 2 }, { 3, 1 }, { 3, 3 } });
		else 
		{
			System.out.println("Veuillez entrer le nombre d'étiquettes : ");
			int nbTags = myObj.nextInt();
			System.out.println("Veuillez entrer le nombre de lignes et colonnes de la grille : ");
			int nbLines = myObj.nextInt();
			int nbColumns = myObj.nextInt();
			int [][] tmpS = new int[nbTags][2];
			int [][] tmpE = new int[nbTags][2];
			for (int i = 0; i<nbTags ; i++) {
				System.out.println("Veuillez entrer la coordonnée du 1er point de la "+ (i+1)  + "(ere/eme) étiquette suivant l'axe horizontal (x) - La première position est 0" );
				int x1 = myObj.nextInt();
				tmpS [i][0] = x1;
				System.out.println("Veuillez entrer la coordonnée du 1er point de la "+(i+1)  + "(ere/eme) étiquette suivant l'axe vertical (y) - La première position est 0" );
				int y1 = myObj.nextInt();
				tmpS [i][1] = y1;
				System.out.println("Veuillez entrer la coordonnée du 2eme point de la "+ (i+1) + "(ere/eme) étiquette suivant l'axe horizontal (x) - La première position est 0" );
				int x2 = myObj.nextInt();
				tmpE [i][0] = x2;
				System.out.println("Veuillez entrer la coordonnée du 2eme point de la "+ (i+1) + "(ere/eme) étiquette suivant l'axe vertical (y) - La première position est 0" );
				int y2 = myObj.nextInt();
				tmpE [i][1] = y2;
			}
			this.controller = new ControllerStub(nbTags, nbLines, nbColumns, tmpS, tmpE);

		}
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setPreferredSize( new Dimension( WINDOW_SIZE, WINDOW_SIZE + WINDOW_TITLE_HEIGHT ));
        this.setTitle( "Numberlink" );

        this.add( new NumberlinkPanel( controller ));
        this.addKeyListener( this );

        this.pack();
        this.setVisible( true );

    }

    @Override
    public void keyTyped( KeyEvent e ) {
        // nothing
    }

    @Override
    public void keyPressed( KeyEvent e ) {
        Direction direction = switch( e.getKeyCode() ) {
            case KeyEvent.VK_UP    -> Direction.UP;
            case KeyEvent.VK_DOWN  -> Direction.DOWN;
            case KeyEvent.VK_LEFT  -> Direction.LEFT;
            case KeyEvent.VK_RIGHT -> Direction.RIGHT;
            default                -> null;
        };
        if( direction == null ) return;
        if( controller.action( direction )) {
            repaint();
            JOptionPane.showMessageDialog( this, "Vous avez gagné !" );
            System.exit( 0 );
        }
        repaint();
    }

    @Override
    public void keyReleased( KeyEvent e ) {
        // nothing
    }

}
