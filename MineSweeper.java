import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;

//Start up class with main() method
class MineSweeper {
	
	//Start up method
	public static void main(String args[]) {
		//Creates a grid that has both bomb grid and counter grid
		Grid g = new Grid();
		//Creating an object to make a GUI (Graphical usder Interface) bomb-grid
		BombGrid b = new BombGrid(g);
	}
}

//Class to make GUI bomb grid it implements ActionListener in order to make buttons clickable

class BombGrid implements ActionListener {
	private Grid g;
	//Temorary local variables for bomb grid and counter grid
	private boolean[][] bombGrid;
	private int[][] countGrid;

	//Button grid holds references of GUI button created to JButton class
	private JButton[][] buttonGrid;
	
	//The frame object reference that points to a JFrame object that holds the entire GUI game play area
	JFrame gridContainer;
	//Total bombs and total successful clicks
	int totalNonBombs=0, clickCount=0;

	//Constructor that creates GUI jButton play area with reference to n x m bomb grid supplied to it
	public BombGrid(Grid g) {
		this.g = g;
		bombGrid = g.getBombGrid();
		countGrid = g.getCountGrid();

		//Creating button grid object matrix
		buttonGrid = new JButton[g.getNumRows()][g.getNumColumns()];
		totalNonBombs = (g.getNumRows() + g.getNumColumns()) - g.getNumBombs();
		int i, j;

		//Creating Frame to hold them all
		gridContainer = new JFrame();

		//Temporary references
		JButton btn;

		//Play area title text and location configuration in pixel 
		gridContainer.setTitle("My Game 1.0");
		gridContainer.setLocation(300,100);
		
		//Creating JButtons and associating ActionListerner with each button to make them clicable
		for(i = 0; i < bombGrid.length; i++) {
			for(j = 0; j < bombGrid[i].length; j++) {
				btn = new JButton();
				buttonGrid[i][j] = btn;
				//Adding ActionListerner
				//NOTE: The class itself implements ActionListener, therefore actionPerformed() method is over-rided 
				//in this class and hence "this" reference is passed as paramerter to addActionListener() method
				btn.addActionListener(this);
				gridContainer.add(btn);	
			}
		}
		gridContainer.setLayout(new GridLayout(g.getNumRows(), g.getNumColumns()));

		//Setting grid GUI size in pixel 
		gridContainer.setSize(500,500);
		gridContainer.setVisible(true);   
	}
	//This must over-ridable method is originally declared in ActionListener and here it is publicly over-rided
	//This method makes the buttons respond accoring to user click, if check if bomb is there then finishes the game with a LOST message
	// If no bomb then it make you proceed
	public void actionPerformed(ActionEvent e) {
		int i, j;
		//Finds the reference of the clicked button
		JButton source = (JButton)e.getSource();
		for(i = 0; i < bombGrid.length; i++) {
			for(j = 0; j < bombGrid[i].length; j++) {
				//Maps with the exact cell in bomb grid
				if(buttonGrid[i][j] == source) {
					//Checks if bomb found
					if(bombGrid[i][j] == true) {
						//Makes everything reveal out
						revealAll();
					//JOptionPane dialog displays the Lost message and asks the user to restart the game or to terminate
			int result = JOptionPane.showConfirmDialog (null, "You Lost this Game!\nDo you want to play it again?","Oops!", JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.YES_OPTION) {
							//Restart with new settings
							gridContainer.dispose();
							new BombGrid(new Grid());
						}
						else {
							//Terminates the Game
							System.exit(0);
						}
					}
					else {		//If bomb not found
						if(countGrid[i][j] != 0) {
							//Reveals the cell 
							source.setText(countGrid[i][j]+"");
							source.setEnabled(false);
						}
						else {
							source.setText("");
							source.setEnabled(false);
						}
						//Increments click count
						clickCount++;
						if(clickCount == totalNonBombs) {
							//Finally if all non-bombs reveals then acknowledge that you WON
							JOptionPane.showInternalMessageDialog(null, "You Won!","Congratulation", JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
					}
				}
			}
		}
	}

	//Method that revelas all cells (bomb or non-bomb)
	private void revealAll() {
		int i, j;
		for(i = 0; i < bombGrid.length; i++) {
			for(j = 0; j < bombGrid[i].length; j++) {
				if(bombGrid[i][j] == true) {

					//If a bomb is found
					buttonGrid[i][j].setText("*");
					buttonGrid[i][j].setFont(new Font("Arial", Font.BOLD, 20));

					//Sets color of the bomb to disabled and RED
					buttonGrid[i][j].setUI(new MetalButtonUI() {
    						protected Color getDisabledTextColor() {
        							return Color.RED;
    						}
					});
				}
				else { // All non bomb cell (I have used different color to show different count value on the cell
					if(countGrid[i][j] != 0) buttonGrid[i][j].setText(countGrid[i][j]+"");
					if(countGrid[i][j] == 1) {
						buttonGrid[i][j].setUI(new MetalButtonUI() {
    							protected Color getDisabledTextColor() {
        							return Color.BLUE;
    							}
						});
					}
					if(countGrid[i][j] == 2) {
						buttonGrid[i][j].setUI(new MetalButtonUI() {
    							protected Color getDisabledTextColor() {
        							return Color.PINK;
    							}
						});
					}
					if(countGrid[i][j] == 3) {
						buttonGrid[i][j].setUI(new MetalButtonUI() {
    							protected Color getDisabledTextColor() {
        							return Color.GREEN;
    							}
						});
					}
					if(countGrid[i][j] > 3) {
						buttonGrid[i][j].setUI(new MetalButtonUI() {
    							protected Color getDisabledTextColor() {
        							return Color.MAGENTA;
    							}
						});
					}
				}
				buttonGrid[i][j].setEnabled(false);
			}
		}
	}
}