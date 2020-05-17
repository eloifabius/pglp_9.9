package fr.uvsq.pglp;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateCommand implements Command {
    /**
     * Forme a créer.
     */
    private Forme form;
    /**
     * Constructeur.
     * @param f La forme a créer
     */
    public CreateCommand(final Forme f) {
        this.form = f;
    }
    /**
     * Execute la commande de création.
     */
    public void execute() {
        Connection c = DataBase.createBase();
        FactoryDaoJDBC fdj = new FactoryDaoJDBC(c);
        Forme f = null;
        if (form.getClass() == Cercle.class) {
            CercleDaoJDBC cercle = (CercleDaoJDBC)
                    fdj.getCercleDao();
            f = cercle.create((Cercle) form);
        } else if (form.getClass() == Carre.class) {
            CarreDaoJDBC carre = (CarreDaoJDBC) fdj.getCarreDao();
            f = carre.create((Carre) form);
        } else if (form.getClass() == Rectangle.class) {
            RectangleDaoJDBC rectangle = (RectangleDaoJDBC)
                    fdj.getRectangleDao();
            f = rectangle.create((Rectangle) form);
        } else if (form.getClass() == Triangle.class) {
            TriangleDaoJDBC triangle = (TriangleDaoJDBC)
                    fdj.getTriangleDao();
            f = triangle.create((Triangle) form);
        } else if (form.getClass() == Groupe.class) {
            GroupeDaoJDBC groupe = (GroupeDaoJDBC)
                    fdj.getGroupeDao();
            f = groupe.create((Groupe) form);
        }
        if (f == null) {
            System.err.println("Nom existe déjà");
        }
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
