# -Minesweeper
<h1>The game of Minesweeper</h1> 
<p>In this game of Minesweeper, a player searches for hidden bombs on a rectangular grid. The game board is represented by a grid of booleans marking bomb locations. A grid value is true if there is a bomb at that location, false otherwise. A user can click on any cell they choose. The game is lost when the user clicks on a cell containing a bomb. The game is won when all cells not containing bombs have been opened and the only remaining cells are those containing bombs.</p>

<p>Given such a grid of bomb locations, the method createCountGrid() constructs a new grid of integers storing the count of bombs in each neighborhood. The neighborhood for a location includes the location itself and its eight adjacent locations. In the returned grid, each value will be a number from 0 to 9.</p> 

<p>If passed the boolean grid on the left, createCountGrid() returns the grid of int values on the right:</p>

<p>Here are the example grids:</p>
<img width="609" alt="Screen Shot 2022-12-21 at 12 00 20 AM" src="https://user-images.githubusercontent.com/95479134/208825329-aec97a9a-b07d-47b6-bbdf-7f8b1c15c080.png">

<h2> The examples below demonstrate how to compute the countGrid from the bombGrid.</>
<p>A. In “Example A” one can see the cell [0][0] has a count of 1 because the only adjacent cell containing a bomb is [1][1]. </p>

<p>B. In “Example B” one can see the cell [1][2] has a count of 0 because there are no adjacent cells containing a bomb.</p>

<p>C. In “Example C” one can see the cell [1][1] has a count of 4 because there are 4 adjacent cells containing a bomb. [0][0] , [0][2], [2][0], [2][1]</p>

<p>D. In “Example D” one can see the cell [1][1] has a count of 3 because there are 3 adjacent cells containing a bomb (including the cell itself). [1][1] , [2][0], [2][2]</p>
<img width="570" alt="Screen Shot 2022-12-20 at 11 58 55 PM" src="https://user-images.githubusercontent.com/95479134/208825159-5327871a-bf5d-4996-abcf-67cfe7e81d00.png">

