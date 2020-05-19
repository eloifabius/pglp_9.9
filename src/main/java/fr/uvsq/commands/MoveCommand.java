package fr.uvsq.commands;

import fr.uvsq.database.CarreDaoJDBC;
import fr.uvsq.database.DataBase;
import fr.uvsq.database.RectangleDaoJDBC;
import fr.uvsq.database.TriangleDaoJDBC;
import fr.uvsq.forms.*;
import fr.uvsq.database.CercleDaoJDBC;
import fr.uvsq.database.FactoryDaoJDBC;
import fr.uvsq.forms.Groupe;
import fr.uvsq.database.GroupeDaoJDBC;

import java.sql.Connection;
import java.sql.SQLException;

public class MoveCommand implements Command {
    /**
     * Forme a deplacer.
     */
    private Forme form;
    /**
     * Vecteur de déplacement en abscisse.
     */
    private int vecteurX;
    /**
     * Vecteur de déplacement en ordonnée.
     */
    private int vecteurY;
    /**
     * Constructeur.
     * @param f La forme a deplacer
     * @param x Le vecteur de deplacement en abscisse
     * @param y Le vecteur de deplacement en ordonnee
     */
    public MoveCommand(final Forme f, final int x, final int y) {
        this.form = f;
        this.vecteurX = x;
        this.vecteurY = y;
    }
    /**
     * Execute la commande de déplacement.
     */
    public void execute() {
        form.move(vecteurX, vecteurY);
        Connection c = DataBase.createBase();
        FactoryDaoJDBC fdj = new FactoryDaoJDBC(c);
        if (form.getClass() == Cercle.class) {
            CercleDaoJDBC cercle = (CercleDaoJDBC)
                    fdj.getCercleDao();
            cercle.update((Cercle) form);
        } else if (form.getClass() == Carre.class) {
            CarreDaoJDBC carre = (CarreDaoJDBC) fdj.getCarreDao();
            carre.update((Carre) form);
        } else if (form.getClass() == Rectangle.class) {
            RectangleDaoJDBC rectangle = (RectangleDaoJDBC)
                    fdj.getRectangleDao();
            rectangle.update((Rectangle) form);
        } else if (form.getClass() == Triangle.class) {
            TriangleDaoJDBC triangle = (TriangleDaoJDBC)
                    fdj.getTriangleDao();
            triangle.update((Triangle) form);
        } else if (form.getClass() == Groupe.class) {
            GroupeDaoJDBC groupe = (GroupeDaoJDBC)
                    fdj.getGroupeDao();
            groupe.update((Groupe) form);
        }
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
