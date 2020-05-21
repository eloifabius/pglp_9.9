package fr.uvsq.test;

import static org.junit.Assert.*;

import fr.uvsq.position.Position;
import org.junit.Test;

class PositionTest {

	@Test
	public void testConstructeurDefaut() {
		Position p = new Position();
		assertTrue(p.getX() == 0 && p.getY() == 0);
	}
	
	@Test
	public void testConstructeur() {
		Position p = new Position(10,20);
		assertTrue(p.getX() == 10 && p.getY() == 20);
	}
	
	@Test
	public void testConstructeur2() throws Exception {
		Position p = new Position("(10,20)");
		assertTrue(p.getX() == 10 && p.getY() == 20);
	}
	
	@Test (expected = Exception.class)
	public void testConstructeurException() throws Exception {
		@SuppressWarnings("unused")
		Position p = new Position("10,20");
	}
	
	@Test (expected = Exception.class)
	public void testConstructeurException2() throws Exception {
		@SuppressWarnings("unused")
		Position p = new Position("(10,vingt)");
	}
	
	@Test (expected = Exception.class)
	public void testConstructeurException3() throws Exception {
		@SuppressWarnings("unused")
		Position p = new Position("(10,)");
	}
	
	@Test
	public void testSetter() {
		Position p = new Position(10,20);
		p.setX(30);
		p.setY(30);
		assertTrue(p.getX() == 30 && p.getY() == 30);
	}
	
	@Test
	public void testMove() {
		Position p = new Position(10,20);
		p.move(20,10);
		assertTrue(p.getX() == 30 && p.getY() == 30);
	}
	
}
