import java.util.*;

/*Grid class definition:
	Grid class is used to design the n x n grid for play area with n rows and n columns. It places some x number of Bombs within the n x n
	grid.

	For example the default play area is 10x10 that is, 10 rows and 10 columns. So it gives 100 cells to the user and within this 100 cells
	system randomly keeps 25 (which is default) bombs.

	NOTE: number of rows and columns are flexible, user can create any number of rows and columns while creating grid object.
*/
public class Grid {
	private boolean[][] bombGrid;
	private int[][] countGrid;
	private int numColumns;
	private int numRows;
	private int numBombs;

	//The defult constructor that creates 10x10 grid with 25vbomb
	public Grid() {
		this.numRows = 10;
		this.numColumns = 10;
		this.numBombs = 25;
		createBombGrid();
		createCountGrid();
	}

	//Parametrized constructor that creates nxm grid with 25 bombs
	public Grid(int rows, int columns) {
		this.numRows = rows;
		this.numColumns = columns;
		this.numBombs = 25;
		createBombGrid();
		createCountGrid();
	}
	public Grid(int rows, int columns, int numBombs) {
		this.numRows = rows;
		this.numColumns = columns;
		this.numBombs = numBombs;
		createBombGrid();
		createCountGrid();
	}
	//Accesor  that returns number of rows
	public int getNumRows() {
		return numRows;
	}
	//Accessor that returns number of columns
	public int getNumColumns() {
		return numColumns;
	}
	//Accsor method that gives number of bombs
	public int getNumBombs() {
		return numBombs;
	}
	//Method that returns copy of 2x2 matrix as Bomb Grid
	public boolean[][] getBombGrid() {
		boolean[][] temp = new boolean[numRows][];
		int i;
		for(i = 0; i < temp.length; i++) {
			temp[i] = Arrays.copyOf(bombGrid[i], bombGrid[i].length);
		}
		return temp;
	}
	//Method that return copy of a 2x2 matrix as count grid
	public int[][] getCountGrid() {
		int[][] temp = new int[numRows][];
		int i;
		//creates a copy of 2x2 matrix
		for(i = 0; i < temp.length; i++) {
			temp[i] = Arrays.copyOf(countGrid[i], countGrid[i].length);
		}
		return temp;
	}
	//Verifies if the cell has a bomb or not
	public boolean isBombAtLocation(int row, int column) {
		return bombGrid[row][column];
	}

	//Returns the count (count of bombs in the adjustance cells including the current) value of the cell
	public int getCountAtLocation(int row, int column) {
		return countGrid[row][column];
	}
	// This method created the grid and it is being called inside the constructor. This method is private and hence
	// it is not allowed to be accessed outside the class
	private void createBombGrid() {

		//Counter for loop
		int i, j;
		int rRow, rCol;
		//Random object to generate random positions
		Random rand = new Random();

		//Creating bombGrid
		bombGrid = new boolean[numRows][numColumns];

		//populating all bombGrid cells with false
		for(i = 0; i < numRows; i++) {
			for(j = 0; j<numColumns; j++) {
				bombGrid[i][j] = false;
			}	
		}

		//Generating and placing bombs randomly on bombCount
		i = 0;
		while(i < numBombs) {
			rRow = rand.nextInt(numRows-1);
			rCol = rand.nextInt(numColumns-1);
			if(bombGrid[rRow][rCol] != true) {
				bombGrid[rRow][rCol] = true;
				i++;
			}
		}
	}
	private void createCountGrid() {
		int i, j;
		int count;
		countGrid = new int[numRows][numColumns];
		//Calculating bombs for a cell to make a counter grid
		for(i = 0; i < numRows; i++) {
			for(j = 0; j<numColumns; j++) {
				count=0;
				if(bombGrid[i][j]) count++;
				//Checks if column is first 
				if(j == 0) {
					//maps with all rows, first, last and any other
					if(i == 0) {
						if(bombGrid[i][j+1]) count++;
						if(bombGrid[i+1][j+1]) count++;
						if(bombGrid[i+1][j]) count++;
					}
					else if(i == (numRows-1)) {
						if(bombGrid[i-1][j]) count++;
						if(bombGrid[i-1][j+1]) count++;
						if(bombGrid[i][j+1]) count++;
					}
					else {
						if(bombGrid[i-1][j]) count++;
						if(bombGrid[i-1][j+1]) count++;
						if(bombGrid[i][j+1]) count++;
						if(bombGrid[i+1][j+1]) count++;
						if(bombGrid[i+1][j]) count++;
					}
				}
				else if(j == (numColumns-1)) {	//If the column is last
					if(i == 0) {
						if(bombGrid[i][j-1]) count++;
						if(bombGrid[i+1][j-1]) count++;
						if(bombGrid[i+1][j]) count++;
					}
					else if(i == (numRows-1)) {
						if(bombGrid[i-1][j-1]) count++;
						if(bombGrid[i-1][j]) count++;
						if(bombGrid[i][j-1]) count++;
					}
					else {
						if(bombGrid[i][j-1]) count++;
						if(bombGrid[i+1][j-1]) count++;
						if(bombGrid[i+1][j]) count++;

						if(bombGrid[i-1][j]) count++;
						if(bombGrid[i-1][j-1]) count++;
					}
				}
				else {		For any other columns
					if(i == 0) {
						if(bombGrid[i][j-1]) count++;
						if(bombGrid[i+1][j-1]) count++;
						if(bombGrid[i+1][j]) count++;
						if(bombGrid[i+1][j+1]) count++;
						if(bombGrid[i][j+1]) count++;
					}
					else if(i == (numRows-1)) {
						if(bombGrid[i][j-1]) count++;
						if(bombGrid[i-1][j-1]) count++;
						if(bombGrid[i-1][j]) count++;
						if(bombGrid[i-1][j+1]) count++;
						if(bombGrid[i][j+1]) count++;
					}
					else {
						if(bombGrid[i][j-1]) count++;
						if(bombGrid[i-1][j-1]) count++;
						if(bombGrid[i-1][j]) count++;

						if(bombGrid[i-1][j+1]) count++;
						if(bombGrid[i][j+1]) count++;

						if(bombGrid[i+1][j-1]) count++;
						if(bombGrid[i+1][j]) count++;
						if(bombGrid[i+1][j+1]) count++;
					}
				}
				countGrid[i][j] = count;
			}	
		}
	}
}