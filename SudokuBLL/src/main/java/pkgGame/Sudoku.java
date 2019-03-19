package pkgGame;

import java.util.Random;

import pkgEnum.ePuzzleViolation;
import pkgHelper.LatinSquare;
import pkgHelper.PuzzleViolation;

/**
 * Sudoku - This class extends LatinSquare, adding methods, constructor to
 * handle Sudoku logic
 * 
 * @version 1.2
 * @since Lab #2
 * @author Bert.Gibbons
 *
 */
public class Sudoku extends LatinSquare {

	/**
	 * 
	 * iSize - the length of the width/height of the Sudoku puzzle.
	 * 
	 * @version 1.2
	 * @since Lab #2
	 */
	private int iSize;

	/**
	 * iSqrtSize - SquareRoot of the iSize. If the iSize is 9, iSqrtSize will be
	 * calculated as 3
	 * 
	 * @version 1.2
	 * @since Lab #2
	 */

	private int iSqrtSize;

	/**
	 * Sudoku - for Lab #2... do the following:
	 * 
	 * set iSize If SquareRoot(iSize) is an integer, set iSqrtSize, otherwise throw
	 * exception
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param iSize-
	 *            length of the width/height of the puzzle
	 * @throws Exception
	 *             if the iSize given doesn't have a whole number square root
	 */
	public Sudoku(int iSize) throws Exception {
		this.iSize = iSize;

		double SQRT = Math.sqrt(iSize);
		if ((SQRT == Math.floor(SQRT)) && !Double.isInfinite(SQRT)) {
			this.iSqrtSize = (int) SQRT;
		} else {
			throw new Exception("Invalid size");
		}
	}

	/**
	 * Sudoku - pass in a given two-dimensional array puzzle, create an instance.
	 * Set iSize and iSqrtSize
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param puzzle
	 *            - given (working) Sudoku puzzle. Use for testing
	 * @throws Exception will be thrown if the length of the puzzle do not have a whole number square root
	 */
	public Sudoku(int[][] puzzle) throws Exception {
		super(puzzle);
		this.iSize = puzzle.length;
		double SQRT = Math.sqrt(iSize);
		if ((SQRT == Math.floor(SQRT)) && !Double.isInfinite(SQRT)) {
			this.iSqrtSize = (int) SQRT;
		} else {
			throw new Exception("Invalid size");
		}

	}

	/**
	 * getPuzzle - return the Sudoku puzzle
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @return - returns the LatinSquare instance
	 */
	public int[][] getPuzzle() {
		return super.getLatinSquare();
	}

	/**
	 * getRegion - figure out what region you're in based on iCol and iRow and call
	 * getRegion(int)<br>
	 * 
	 * Example, the following Puzzle:
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 * 
	 * getRegion(0,3) would call getRegion(1) and return [2],[3],[3],[4]
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param iCol
	 *            given column
	 * @param iRow
	 *            given row
	 * @return - returns a one-dimensional array from a given region of the puzzle
	 */
	public int[] getRegion(int iCol, int iRow) {

		int i = (iCol / iSqrtSize) + ((iRow / iSqrtSize) * iSqrtSize);

		return getRegion(i);
	}

	/**
	 * getRegion - pass in a given region, get back a one-dimensional array of the
	 * region's content<br>
	 * 
	 * Example, the following Puzzle:
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 * 
	 * getRegion(2) and return [3],[4],[4],[1]
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param r
	 *            given region
	 * @return - returns a one-dimensional array from a given region of the puzzle
	 */

	public int[] getRegion(int r) {

		int[] reg = new int[super.getLatinSquare().length];


		int i = (r / iSqrtSize) * iSqrtSize;
		int j = (r % iSqrtSize) * iSqrtSize;		
		int jMax = j + iSqrtSize;
		int iMax = i + iSqrtSize;
		int iCnt = 0;

		for (; i < iMax; i++) {
			for (j = (r % iSqrtSize) * iSqrtSize; j < jMax; j++) {
				reg[iCnt++] = super.getLatinSquare()[i][j];
			}
		}

		return reg;
	}
	
 
	
	@Override
	public boolean hasDuplicates()
	{
		if (super.hasDuplicates())
			return true;
		
		for (int k = 0; k < this.getPuzzle().length; k++) {
			if (super.hasDuplicates(getRegion(k))) {
				super.AddPuzzleViolation(new PuzzleViolation(ePuzzleViolation.DupRegion,k));
			}
		}
	
		return (super.getPV().size() > 0);
	}

	/**
	 * isPartialSudoku - return 'true' if...
	 * 
	 * It's a LatinSquare If each region doesn't have duplicates If each element in
	 * the first row of the puzzle is in each region of the puzzle At least one of
	 * the elemnts is a zero
	 * 
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @return true if the given puzzle is a partial sudoku
	 */
	public boolean isPartialSudoku() {

		super.setbIgnoreZero(true);
		
		super.ClearPuzzleViolation();
		
		if (hasDuplicates())
			return false;

		if (!ContainsZero()) {
			super.AddPuzzleViolation(new PuzzleViolation(ePuzzleViolation.MissingZero, -1));
			return false;
		}
		return true;

	}

	/**
	 * isSudoku - return 'true' if...
	 * 
	 * Is a partialSudoku Each element doesn't a zero
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @return - returns 'true' if it's a partialSudoku, element match (row versus column) and no zeros
	 */
	public boolean isSudoku() {

		this.setbIgnoreZero(false);
		
		super.ClearPuzzleViolation();
		
		if (hasDuplicates())
			return false;
		
		if (!super.isLatinSquare())
			return false;
		
		for (int i = 1; i < super.getLatinSquare().length; i++) {

			if (!hasAllValues(getRow(0), getRegion(i))) {
				return false;
			}
		}

		if (ContainsZero()) {
			return false;
		}

		return true;
	}

	/**
	 * isValidValue - test to see if a given value would 'work' for a given column /
	 * row
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param iCol
	 *            puzzle column
	 * @param iRow
	 *            puzzle row
	 * @param iValue
	 *            given value
	 * @return - returns 'true' if the proposed value is valid for the row and column
	 */
	public boolean isValidValue(int iCol, int iRow, int iValue) {
		
		if (doesElementExist(super.getRow(iRow),iValue))
		{
			return false;
		}
		if (doesElementExist(super.getColumn(iCol),iValue))
		{
			return false;
		}
		if (doesElementExist(this.getRegion(iCol, iRow),iValue))
		{
			return false;
		}
		
		return true;
	}
	
	
	
	/**
	 * Returns the region number of a specified row and col
	 */
	public int getRegionNbr(int row, int col) {
		
		return (row / this.iSqrtSize) * this.iSqrtSize + (col % this.iSqrtSize);
		
	}
	
	/**
	 * 
	 */
	public void printPuzzle() {
		
		for(int r = 0; r < this.iSize; r++) {
			for(int c = 0; c < this.iSize; c++) {
				System.out.print(this.getPuzzle()[r][c] + " ");
			}
			System.out.println();
		}
		
	}
	
	/**
	 * Sets the diagonal regions to random values from 1 to length
	 */
	public void FillDiagonalRegions() {
		
		for(int r = 0; r < this.iSize; r += this.iSqrtSize + 1) {
			this.SetRegion(r);
			this.ShuffleRegion(r);
		}
		
		
		// Is this all that is needed? or should the other diagonal be filled also?
	}
	
	/**
	 * Fills in a region with values of 1 to the length
	 */
	public void SetRegion(int r) {
		
		int down = (r / this.iSqrtSize) * this.iSqrtSize;
		int over = (r % this.iSqrtSize) * this.iSqrtSize;
		
		for(int i = 0; i < this.iSize; i++) {
			this.getPuzzle()[down + i / this.iSqrtSize][over + i % this.iSqrtSize] = (i + 1);
		}
	}
	
	/**
	 * Shuffles a region of the puzzle
	 */
	public void ShuffleRegion(int r) {
		
		int [] shuffled = this.getRegion(r);
		this.shuffleArray(shuffled);
		
		int down = (r / this.iSqrtSize) * this.iSqrtSize;
		int over = (r % this.iSqrtSize) * this.iSqrtSize;
		
		for(int i = 0; i < this.iSize; i++) {
			this.getPuzzle()[down + i / this.iSqrtSize][over + i % this.iSqrtSize] = shuffled[i];
		}
	}
	
	/**
	 * Shuffles the given array;
	 */
	public void shuffleArray(int [] a) {
		// Generating a new array
		int[] b = new int[a.length];
		for(int i = 0; i < b.length; i++) {
			b[i] = 0;
		}
		
		// shuffling array a into b
		for(int i = 0; i < a.length; i++) {
			boolean bloop = true;
			while(bloop) {
				Random rng = new Random();	// Change Random to Secure random?
				int iRng = rng.nextInt(a.length);
				if(b[iRng] == 0) {
					b[iRng] = a[i]; 
					bloop = false;
				}
			}
		}
		// setting a to b
		for(int i = 0; i < a.length; i++) {
			a[i] = b[i];
		}
	}
}
