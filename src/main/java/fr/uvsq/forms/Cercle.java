package fr.uvsq.forms;

import fr.uvsq.position.Position;

public class Cercle extends Forme {
    /**
     * La position du centre.
     */
    private Position centre;
    /**
     * Le rayon du cercle.
     */
    private int rayon;
    /**
     * Getter du rayon.
     * @return Le rayon
     */
    public int getRayon() {
        return rayon;
    }
    /**
     * Setter du rayon.
     * @param r Le nouveau rayon
     * @throws Exception Rayon négatif
     */
    public void setRayon(final int r) throws Exception {
        if (r >= 0) {
            this.rayon = r;
        } else {
            System.err.println("Rayon négatif");
            throw new Exception();
        }
    }
    /**
     * Getter du centre.
     * @return Le centre
     */
    public Position getCentre() {
        return centre.clone();
    }
    /**
     * Setter du centre.
     * @param c La nouvelle Position du centre
     */
    public void setCentre(final Position c) {
        this.centre = c.clone();
    }
    /**
     * Constructeur.
     * @param n Le nom
     * @param p La position initiale
     * @param r Le rayon
     * @throws Exception Rayon negatif
     */
    public Cercle(final String n, final Position p, final int r)
            throws Exception {
        setNom(n);
        this.centre = p.clone();
        setRayon(r);
    }
    /**
     * Fonction de déplacement.
     * @param x Ajout en abscisse par rapport a la position initiale
     * @param y Ajout en ordonnée par rapport a la position initiale
     */
    @Override
    public void move(final int x, final int y) {
        centre.move(x, y);
    }
    /**
     * Fonction d'affichage.
     */
    @Override
    public void draw() {
        System.out.println("Cercle(centre=(" + centre.getX()
        + "," + centre.getY() + "),rayon=" + rayon + ")");
    }
}
