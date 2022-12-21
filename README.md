# -Minesweeper
<h1>The game of Minesweeper</h1> 
<p>In this game of Minesweeper, a player searches for hidden bombs on a rectangular grid. The game board is represented by a grid of booleans marking bomb locations. A grid value is true if there is a bomb at that location, false otherwise. A user can click on any cell they choose. The game is lost when the user clicks on a cell containing a bomb. The game is won when all cells not containing bombs have been opened and the only remaining cells are those containing bombs.</p>

<p>Given such a grid of bomb locations, the method createCountGrid() constructs a new grid of integers storing the count of bombs in each neighborhood. The neighborhood for a location includes the location itself and its eight adjacent locations. In the returned grid, each value will be a number from 0 to 9.</p> 

<p>If passed the boolean grid on the left, createCountGrid() returns the grid of int values on the right:</p>

<p>Here are the example grids:</p>
