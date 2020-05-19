package fr.uvsq.forms;

import fr.uvsq.position.Position;

public class Triangle extends Forme {
    /**
     * Sommet 1.
     */
    private Position haut;
    /**
     * Sommet 2.
     */
    private Position gauche;
    /**
     * Sommet 3.
     */
    private Position droite;
    /**
     * Getter du sommet 1.
     * @return La position du sommet 1
     */
    public Position getHaut() {
        return haut.clone();
    }
    /**
     * Setter du sommet 1.
     * @param h La nouvelle position
     */
    public void setHaut(final Position h) {
        this.haut = h.clone();
    }
    /**
     * Getter du sommet 2.
     * @return La position du sommet 2
     */
    public Position getGauche() {
        return gauche.clone();
    }
    /**
     * Setter du sommet 2.
     * @param g La nouvelle position
     */
    public void setGauche(final Position g) {
        this.gauche = g.clone();
    }
    /**
     * Getter du sommet 3.
     * @return La position du sommet 3
     */
    public Position getDroite() {
        return droite.clone();
    }
    /**
     * Setter du sommet 3.
     * @param d La nouvelle position
     */
    public void setDroite(final Position d) {
        this.droite = d.clone();
    }
    /**
     * Constructeur.
     * @param n Le nom
     * @param p1 La position du sommet 1
     * @param p2 La position du sommet 2
     * @param p3 La position du sommet 3
     */
    public Triangle(final String n, final Position p1,
            final Position p2, final Position p3) {
        setNom(n);
        this.haut = p1.clone();
        this.gauche = p2.clone();
        this.droite = p3.clone();
    }
    /**
     * Fonction de déplacement.
     * @param x Ajout en abscisse par rapport a la position initiale
     * @param y Ajout en ordonnée par rapport a la position initiale
     */
    @Override
    public void move(final int x, final int y) {
        haut.move(x, y);
        gauche.move(x, y);
        droite.move(x, y);
    }
    /**
     * Fonction d'affichage.
     */
    @Override
    public void draw() {
        System.out.println("Triangle(sommet1=(" + haut.getX()
        + "," + haut.getY() + "),sommet2=(" + gauche.getX()
        + "," + gauche.getY() + "),sommet3=(" + droite.getX()
        + "," + droite.getY() + "))");
    }
}
