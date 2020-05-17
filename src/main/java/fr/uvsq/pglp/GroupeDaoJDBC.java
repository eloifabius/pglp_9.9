package fr.uvsq.pglp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class GroupeDaoJDBC extends AbstractDao<Groupe> {
    /**
     * Constructeur.
     * @param c Le connecteur
     */
    public GroupeDaoJDBC(final Connection c) {
        this.connect = c;
    }
    /**
     * Ajoute un Groupe.
     * @param g Le Groupe a ajouter
     */
    @Override
    public Groupe create(final Groupe g) {
        try {
            final int un = 1;
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO Forme (Nom)"
                    + "VALUES (?)");
            prepare.setString(un, g.getNom());
            int result = prepare.executeUpdate();
            prepare = connect.prepareStatement(
                    "INSERT INTO Groupe (Nom)"
                    + "VALUES (?)");
            prepare.setString(un, g.getNom());
            result = prepare.executeUpdate();
            assert result == un;
            Iterator<Forme> it = g.iterator();
            FactoryDaoJDBC fdj = new FactoryDaoJDBC(connect);
            while (it.hasNext()) {
                Forme f = it.next();
                if (f.getClass() == Cercle.class) {
                    Cercle c = (Cercle) f;
                    CercleDaoJDBC cdj = (CercleDaoJDBC) fdj.getCercleDao();
                    cdj.create(c);
                } else if (f.getClass() == Carre.class) {
                    Carre c = (Carre) f;
                    CarreDaoJDBC cdj =
                            (CarreDaoJDBC) fdj.getCarreDao();
                    cdj.create(c);
                } else if (f.getClass() == Rectangle.class) {
                    Rectangle r = (Rectangle) f;
                    RectangleDaoJDBC rdj =
                            (RectangleDaoJDBC) fdj.getRectangleDao();
                    rdj.create(r);
                } else if (f.getClass() == Triangle.class) {
                    Triangle t = (Triangle) f;
                    TriangleDaoJDBC tdj =
                            (TriangleDaoJDBC) fdj.getTriangleDao();
                    tdj.create(t);
                } else {
                    Groupe g2 = (Groupe) f;
                    create(g2);
                }
                GroupeFormeDaoJDBC.createGroupeForme(connect,
                        g.getNom(), f.getNom());
            }
            System.out.println("Groupe créé");
        } catch (SQLException e) {
            return null;
        }
        return g;
    }
    /**
     * Retourne le Groupe recherché.
     * @param nom Le nom du Groupe
     * @return Le Groupe trouvé
     */
    @Override
    public Groupe find(final String nom) {
        Groupe g = null;
        try {
            final int un = 1;
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM Groupe WHERE Nom = ?");
            prepare.setString(un, nom);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                g = new Groupe(nom);
            }
            ArrayList<Forme> lf =
                    GroupeFormeDaoJDBC.findGroupeForme(connect, nom);
            for (Forme f : lf) {
                g.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return g;
    }
    /**
     * Retourne tous les Groupes.
     * @return Les Groupes trouvés
     */
    @Override
    public ArrayList<Groupe> findAll() {
        ArrayList<Groupe> g = new ArrayList<Groupe>();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT Nom FROM Groupe");
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                g.add(find(result.getString("Nom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Groupe>();
        }
        return g;
    }
    /**
     * Modifie un Groupe.
     * @param g Le Groupe a modifier
     * @return Le groupe modifié
     */
    @Override
    public Groupe update(final Groupe g) {
        Groupe g2 = this.find(g.getNom());
        if (g2 != null) {
            delete(g2);
            if (create(g) == null) {
                delete(g);
                create(g2);
                return g2;
            }
        } else {
            return g2;
        }
        return g;
    }
    /**
     * Retire un Groupe.
     * @param g Le Groupe a retirer
     */
    @Override
    public void delete(final Groupe g) {
        final int un = 1;
        try {
            GroupeFormeDaoJDBC.deleteGroupeForme(connect, g.getNom());
            GroupeFormeDaoJDBC.deleteFormeGroupe(connect, g.getNom());
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE FROM Groupe WHERE Nom = ?");
            prepare.setString(1, g.getNom());
            int result = prepare.executeUpdate();
            prepare = connect.prepareStatement(
                    "DELETE FROM Forme WHERE Nom = ?");
            prepare.setString(1, g.getNom());
            result = prepare.executeUpdate();
            assert result == un;
            System.out.println("Groupe supprimé");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
