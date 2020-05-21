package fr.uvsq.test;

import static org.junit.Assert.*;

import fr.uvsq.forms.Triangle;
import fr.uvsq.position.Position;
import org.junit.Test;

class TriangleTest {

	@Test
	public void testConstructeur() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(10,10);
		Position p3 = new Position(20,0);
		Triangle t = new Triangle("t1", p1, p2, p3);
		assertTrue(t.getNom().equals("t1") && t.getHaut().getX() == p1.getX() 
				&& t.getHaut().getY() == p1.getY() && t.getGauche().getX() == p2.getX() 
				&& t.getGauche().getY() == p2.getY() && t.getDroite().getX() == p3.getX() 
				&& t.getDroite().getY() == p3.getY());
	}
	
	@Test
	public void testSetter() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(10,10);
		Position p3 = new Position(20,0);
		Triangle t = new Triangle("t1", p1, p2, p3);
		p1.setX(p1.getX() + 10);
		p1.setY(p1.getY() + 20);
		t.setHaut(p1);
		p2.setX(p2.getX() + 10);
		p2.setY(p2.getY() + 20);
		t.setGauche(p2);
		p3.setX(p3.getX() + 10);
		p3.setY(p3.getY() + 20);
		t.setDroite(p3);
		assertTrue(t.getNom().equals("t1") && t.getHaut().getX() == p1.getX() 
				&& t.getHaut().getY() == p1.getY() && t.getGauche().getX() == p2.getX() 
				&& t.getGauche().getY() == p2.getY() && t.getDroite().getX() == p3.getX() 
				&& t.getDroite().getY() == p3.getY());
	}
	
	@Test
	public void testMove() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(10,10);
		Position p3 = new Position(20,0);
		Triangle t = new Triangle("t1", p1, p2, p3);
		t.move(10, 20);
		p1.setX(p1.getX() + 10);
		p1.setY(p1.getY() + 20);
		p2.setX(p2.getX() + 10);
		p2.setY(p2.getY() + 20);
		p3.setX(p3.getX() + 10);
		p3.setY(p3.getY() + 20);
		assertTrue(t.getNom().equals("t1") && t.getHaut().getX() == p1.getX() 
				&& t.getHaut().getY() == p1.getY() && t.getGauche().getX() == p2.getX() 
				&& t.getGauche().getY() == p2.getY() && t.getDroite().getX() == p3.getX() 
				&& t.getDroite().getY() == p3.getY());
	}
	
	@Test
	public void testDraw() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(10,10);
		Position p3 = new Position(20,0);
		Triangle t = new Triangle("t1", p1, p2, p3);
		t.draw();
	}

}
