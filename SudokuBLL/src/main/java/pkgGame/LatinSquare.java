package pkgGame;

public class LatinSquare {
	
	private int[][] square;
	
	/*
	 * Default Constructor
	 */
	public LatinSquare() {
		super();
		this.square = new int[][] {{1,2,3},{2,3,1},{3,1,2}};
	}
	
	/*
	 * Constructor for specific Latin square
	 */
	public LatinSquare(int [][] Square) {
		this.square = Square;
	}
	
	/*
	 * Getter method
	 */
	public int[][] getLatinSquare() {
		return square;
	}
	
	/*
	 * Setter method
	 */
	public void setLatinSquare(int[][] square) {
		this.square = square;
	}
	
	/*
	 * get specific row
	 */
	public int[] getRow(int index) {
		return this.square[index];
	}
	
	/*
	 * get specific column
	 */
	public int[] getColumn(int index) {
		int [] rtnArray = new int[this.square[index].length];
		for(int i = 0; i < this.square.length; i++) {
			rtnArray[i] = this.square[index][i];
		}
		return rtnArray;
	}
	
	/*
	 * Checks if latin square
	 */
	public boolean isLatinSquare() {
		
		// Check if any row has duplicates
		
		for(int i = 0; i < this.square.length; i++) {
			if(hasDuplicates(this.getRow(i))) {
				return false;
			}
		}
		
		// Check if any col has duplicates
		for(int i = 0; i < this.square.length; i++) {
			if(hasDuplicates(this.getColumn(i))) {
				return false;
			}
		}
		
		// Check if all values in first row are in all others
		for(int i = 1; i < this.square.length; i++) {
			if(! hasAllValues(this.getRow(0), this.getRow(i))) {
				return false;
			}
		}
		
		// Check if all values in first col are in all others
		for(int i = 1; i < this.square.length; i++) {
			if(! hasAllValues(this.getColumn(0), this.getColumn(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	/*
	 * Checks if square has a zero
	 */
	public boolean ContainsZero() {
		
		for(int [] row:this.square) {
			for (int item: row) {
				if (item == 0) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	// Static Methods
	
	public static boolean hasDuplicates(int [] row) {
		for(int a = 0; a < row.length; a++) {
			for(int b = a + 1; b < row.length; b++) {
				if(row[a] == row[b]) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean hasAllValues(int[] a, int [] b) {
		for(int itemA: a) {
			if(! doesElementExist(b, itemA)){
				return false;
			}
		}
		return true; // TODO
	}
	
	public static boolean doesElementExist(int [] row, int target) {
		for(int item : row) {
			if (item == target) {
				return true;
			}
		}
		return false; 
	}
	
	
	
}
