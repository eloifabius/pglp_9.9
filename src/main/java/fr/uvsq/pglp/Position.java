package fr.uvsq.pglp;

public class Position {
    /**
     * Valeur en abscisse.
     */
    private int x;
    /**
     * Valeur en ordonnée.
     */
    private int y;
    /**
     * Getter de x.
     * @return La position en x
     */
    public int getX() {
        return x;
    }
    /**
     * Getter de y.
     * @return La position en y
     */
    public int getY() {
        return y;
    }
    /**
     * Setter de x.
     * @param a La nouvelle valeur de x
     */
    public void setX(final int a) {
        this.x = a;
    }
    /**
     * Setter de y.
     * @param a La nouvelle valeur de y
     */
    public void setY(final int a) {
        this.y = a;
    }
    /**
     * Constructeur par défaut.
     */
    public Position() {
        x = 0;
        y = 0;
    }
    /**
     * Constructeur.
     * @param absc Valeur en abscisse de la position
     * @param ord Valeur en ordonnée de la position
     */
    public Position(final int absc, final int ord) {
        this.x = absc;
        this.y = ord;
    }
    /**
     * Constructeur.
     * @param p La position en chaine de caractere
     * @throws Exception Position invalide
     */
    public Position(final String p) throws Exception {
        String position = p + "";
        if (position.startsWith("(") && position.endsWith(")")) {
            position = position.substring(1, position.length() - 1);
            String[] pos = position.split(",");
            if (pos.length == 2) {
                this.x = Integer.parseInt(pos[0]);
                this.y = Integer.parseInt(pos[1]);
            } else {
                System.err.println("Position invalide");
                throw new Exception();
            }
        } else {
            System.err.println("Position invalide");
            throw new Exception();
        }
    }
    /**
     * Fonction de déplacement d'une position.
     * @param abs Ajout en abscisse par rapport a la position initiale
     * @param ord Ajout en ordonnée par rapport a la position initiale
     */
    public void move(final int abs, final int ord) {
        this.x += abs;
        this.y += ord;
    }
    /**
     * Clone la position.
     * @return La position clonée
     */
    public Position clone() {
        return new Position(this.x, this.y);
    }
}
