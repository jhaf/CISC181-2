package pkgHelper;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import pkgGame.LatinSquare;

public class LatinSquareTest {

	@Test
	public void doesElementExist_Test1() {
		boolean bActual = LatinSquare.doesElementExist(new int[] {0,1,2,3}, 2);
		boolean bExpected = true;
		
		assertEquals(bExpected, bActual);
	}
	
	@Test
	public void doesElementExist_Test2() {
		boolean bActual = LatinSquare.doesElementExist(new int[] {0,1,2,3}, 8);
		boolean bExpected = false;
		
		assertEquals(bExpected, bActual);
	}
	
	@Test
	public void getColumn_Test1() {
		LatinSquare S = new LatinSquare();
		int [] iActual = S.getColumn(0);
		int [] iExpected = new int[] {1,2,3};
		
		assertEquals(Arrays.compare(iExpected, iActual), 0);
		
	}
	
	@Test
	public void getRow_Test1() {
		LatinSquare S = new LatinSquare();
		int [] iActual = S.getRow(0);
		int [] iExpected = new int[] {1,2,3};
		
		assertEquals(Arrays.compare(iExpected, iActual), 0);
	}
	
	
	@Test
	public void hasAllValues_Test1() {
		boolean bActual = LatinSquare.hasAllValues(new int [] {1,2,3}, new int [] {3,1,2});
		boolean bExpected = true;
		
		assertEquals(bExpected, bActual);
	}
	
	@Test
	public void hasAllValues_Test2() {
		boolean bActual = LatinSquare.hasAllValues(new int [] {1,2,3}, new int [] {1,1,2});
		boolean bExpected = false;
		
		assertEquals(bExpected, bActual);
	}
	
	@Test
	public void hasDuplicates_test1() {
		boolean bActual = LatinSquare.hasDuplicates(new int [] {1,2,3});
		boolean bExpected = false;
		
		assertEquals(bExpected, bActual);
	}
	
	@Test
	public void hasDuplicates_test2() {
		boolean bActual = LatinSquare.hasDuplicates(new int [] {1,1,3});
		boolean bExpected = true;
		
		assertEquals(bExpected, bActual);
	}
	
	@Test
	public void isLatinSquare_Test1() {
		LatinSquare S = new LatinSquare();
		boolean bActual = S.isLatinSquare();
		boolean bExpected = true;
		
		assertEquals(bExpected, bActual);
	}
	
	@Test
	public void isLatinSquare_Test2() {
		LatinSquare S = new LatinSquare(new int[][] {{1,1,2},{2,2,3},{3,2,1}});
		boolean bActual = S.isLatinSquare();
		boolean bExpected = false;
		
		assertEquals(bExpected, bActual);
	}
}
