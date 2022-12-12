package sudoku;
public class SudokuSolver {
	
	private static final int SIZE_OF_GRID=9;
	
	public static void main(String[] args) {
		int[][] sudokuBoard = {
				{7, 0, 2, 0, 5, 0, 6, 0, 0},
				{0, 0, 0, 0, 0, 3, 0, 0, 0},
				{1, 0, 0, 0, 0, 9, 5, 0, 0},
				{8, 0, 0, 0, 0, 0, 0, 9, 0},
				{0, 4, 3, 0, 0, 0, 7, 5, 0},
				{0, 9, 0, 0, 0, 0, 0, 0, 8}, 
				{0, 0, 9, 7, 0, 0, 0, 0, 5},
				{0, 0, 0, 2, 0, 0, 0, 0, 0},
				{0, 0, 7, 0, 4, 0, 2, 0, 3}
				};
		if(solveBoard(sudokuBoard))
		{
			System.out.println("Solved");
			for(int i = 0; i < SIZE_OF_GRID; i++)
				for(int j = 0; j < SIZE_OF_GRID; j++) {
					if(j < SIZE_OF_GRID -1)
						System.out.print(sudokuBoard[i][j] + ", ");
					else System.out.print(sudokuBoard[i][j]+"\n");
				}
					
		}
		else {
			System.out.println("There is a problem with the board values");
			for(int i = 0; i < SIZE_OF_GRID; i++)
				for(int j = 0; j < SIZE_OF_GRID; j++) {
					if(j < SIZE_OF_GRID -1)
						System.out.print(sudokuBoard[i][j] + ", ");
					else System.out.print(sudokuBoard[i][j]+"\n");
				}
		}

	}
	
	private static boolean isNumberInRow(int [][] board, int number,int row)
	{
		for(int i=0;i<SIZE_OF_GRID;i++)
		{
			if(board[row][i] == number) {
				return true;
			}
				
		}
		
		return false;
	}
	
	private static boolean isNumberInColumn(int [][] board, int number,int column)
	{
		for(int i=0;i<SIZE_OF_GRID;i++)
		{
			if(board[i][column] == number) {
				return true;
			}
		}
		
		return false;
	}

	private static boolean isNumberInBox(int [][] board, int number,int row,int column)
	{
		int localBoxRow = row -  row % 3; //check this later row % 3
		int localBoxColumn = column - column % 3;
		

		for(int i = 0; i < 3 ; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if(board[i+localBoxRow][j+localBoxColumn] == number) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private static boolean isPositionValid(int [][] board, int number, int row, int column) {
		return !isNumberInRow(board, number, row) && 
				!isNumberInColumn(board, number, column) &&
				!isNumberInBox(board, number, row, column);
	}
	
	private static boolean solveBoard(int [][] board) {
		for(int row = 0;  row < SIZE_OF_GRID; row++) {
			for (int column = 0; column < SIZE_OF_GRID; column++) {
				if(board[row][column]==0) {
					for(int guess = 1; guess <= SIZE_OF_GRID; guess++) {
						if(isPositionValid(board,guess,row,column)) {
							board[row][column] = guess;
							if(solveBoard(board)) {
								return true;
							}
							else {
								board[row][column] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;		
	}

}
