package fr.uvsq.test;

import static org.junit.Assert.*;

import fr.uvsq.forms.Carre;
import fr.uvsq.position.Position;
import org.junit.Test;

class CarreTest {

	@Test
	public void testConstructeur() throws Exception {
		Position p1 = new Position(15,20);
		Carre c = new Carre("c1", p1, 10);
		assertTrue(c.getNom().equals("c1") && c.getCentre().getX() == p1.getX() 
				&& c.getCentre().getY() == p1.getY() && c.getLongueur() == 10);
	}
	
	@Test
	public void testSetter() throws Exception {
		Position p1 = new Position(15,20);
		Carre c = new Carre("c1", p1, 10);
		p1.setX(p1.getX() + 10);
		p1.setY(p1.getY() + 20);
		c.setCentre(p1);
		c.setLongueur(20);
		assertTrue(c.getNom().equals("c1") && c.getCentre().getX() == p1.getX() 
				&& c.getCentre().getY() == p1.getY() && c.getLongueur() == 20);
	}
	
	@Test
	public void testMove() throws Exception {
		Position p1 = new Position(15,20);
		Carre c = new Carre("c1", p1, 10);
		c.move(10, 20);
		p1.setX(p1.getX() + 10);
		p1.setY(p1.getY() + 20);
		assertTrue(c.getNom().equals("c1") && c.getCentre().getX() == p1.getX() 
				&& c.getCentre().getY() == p1.getY() && c.getLongueur() == 10);
	}
	
	@Test
	public void testDraw() throws Exception {
		Position p1 = new Position(15,20);
		Carre c = new Carre("c1", p1, 10);
		c.draw();
	}
}
