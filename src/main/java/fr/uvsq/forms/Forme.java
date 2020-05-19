package fr.uvsq.forms;

public abstract class Forme {
    /**
     * Le nom de la forme.
     */
    private String nom;
    /**
     * Getter de nom.
     * @return Le nom de la forme
     */
    public String getNom() {
        return nom;
    }
    /**
     * Setter de nom.
     * @param n Le nouveau nom
     */
    public void setNom(final String n) {
        this.nom = n;
    }
    /**
     * Fonction de déplacement.
     * @param x Ajout en abscisse par rapport a la position initiale
     * @param y Ajout en ordonnée par rapport a la position initiale
     */
    public abstract void move(int x, int y);
    /**
     * Fonction d'affichage.
     */
    public abstract void draw();
}
