package fr.uvsq.test;

import static org.junit.Assert.*;

import fr.uvsq.forms.Cercle;
import fr.uvsq.forms.Groupe;
import fr.uvsq.position.Position;
import org.junit.Test;

class GroupeTest {

	@Test
	public void testConstructeur() {
		Groupe g = new Groupe("g1");
		assertTrue(g.getNom().equals("g1") && g.iterator().hasNext() == false);
	}
	
	@Test
	public void testAdd() throws Exception {
		Position p1 = new Position(15,20);
		Cercle c = new Cercle("c1", p1, 10);
		Groupe g = new Groupe("g1");
		g.add(c);
		assertTrue(g.getNom().equals("g1") && g.iterator().next().equals(c));
	}
	
	@Test
	public void testRemove() throws Exception {
		Position p1 = new Position(15,20);
		Cercle c = new Cercle("c1", p1, 10);
		Groupe g = new Groupe("g1");
		g.add(c);
		g.remove(c);
		assertTrue(g.getNom().equals("g1") && g.iterator().hasNext() == false);
	}
	
	@Test
	public void testMove() throws Exception {
		Position p1 = new Position(15,20);
		Cercle c = new Cercle("c1", p1, 10);
		Groupe g = new Groupe("g1");
		g.add(c);
		g.move(10, 20);
		p1.setX(p1.getX() + 10);
		p1.setY(p1.getY() + 20);
		assertTrue(g.getNom().equals("g1") && c.getCentre().getX() == p1.getX() 
				&& c.getCentre().getY() == p1.getY() && c.getRayon() == 10);
	}
	
	@Test
	public void testDraw() throws Exception {
		Position p1 = new Position(15,20);
		Cercle c = new Cercle("c1", p1, 10);
		Groupe g = new Groupe("g1");
		g.add(c);
		g.draw();
	}
}
