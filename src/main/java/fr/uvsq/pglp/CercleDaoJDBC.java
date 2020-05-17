package fr.uvsq.pglp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CercleDaoJDBC extends AbstractDao<Cercle> {
    /**
     * Constructeur.
     * @param c Le connecteur
     */
    public CercleDaoJDBC(final Connection c) {
        this.connect = c;
    }
    /**
     * Ajoute un Cercle.
     * @param c Le Cercle a ajouter
     */
    @Override
    public Cercle create(final Cercle c) {
        try {
            final int un = 1;
            final int deux = 2;
            final int trois = 3;
            final int quatre = 4;
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO Forme (Nom)"
                    + "VALUES (?)");
            prepare.setString(un, c.getNom());
            int result = prepare.executeUpdate();
            prepare = connect.prepareStatement(
                    "INSERT INTO Cercle (Nom,Centre_X,Centre_Y,"
                    + "Rayon)"
                    + "VALUES (?,?,?,?)");
            prepare.setString(un, c.getNom());
            prepare.setInt(deux, c.getCentre().getX());
            prepare.setInt(trois, c.getCentre().getY());
            prepare.setInt(quatre, c.getRayon());
            result = prepare.executeUpdate();
            assert result == un;
            System.out.println("Cercle créé");
        } catch (SQLException e) {
            return null;
        }
        return c;
    }
    /**
     * Retourne le cercle recherché.
     * @param nom Le nom du cercle
     * @return Le cercle trouvé
     */
    @Override
    public Cercle find(final String nom) {
        Cercle c = null;
        try {
            final int un = 1;
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM Cercle WHERE Nom = ?");
            prepare.setString(un, nom);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                try {
                    c = new Cercle(
                        result.getString("Nom"),
                        new Position(result.getInt("Centre_X"),
                        result.getInt("Centre_Y")),
                        result.getInt("Rayon")
                        );
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return c;
    }
    /**
     * Retourne tous les Cercles.
     * @return Les cercles trouvés
     */
    @Override
    public ArrayList<Cercle> findAll() {
        ArrayList<Cercle> c = new ArrayList<Cercle>();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT Nom FROM Cercle");
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                c.add(find(result.getString("Nom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Cercle>();
        }
        return c;
    }
    /**
     * Modifie un cercle.
     * @param c Le cercle a modifier
     */
    @Override
    public Cercle update(final Cercle c) {
        Cercle c2 = this.find(c.getNom());
        if (c2 != null) {
            try {
                final int un = 1;
                final int deux = 2;
                final int trois = 3;
                final int quatre = 4;
                PreparedStatement prepare = connect.prepareStatement(
                        "UPDATE Cercle SET Centre_X = ?,"
                        + "Centre_Y = ?, Rayon = ? WHERE Nom = ?");
                prepare.setString(quatre, c.getNom());
                prepare.setInt(un, c.getCentre().getX());
                prepare.setInt(deux, c.getCentre().getY());
                prepare.setInt(trois, c.getRayon());
                int result = prepare.executeUpdate();
                assert result == 1;
                System.out.println("Cercle deplacé");
            } catch (SQLException e) {
                e.printStackTrace();
                return c2;
            }
        } else {
            return c2;
        }
        return c;
    }
    /**
     * Retire un cercle.
     * @param c Le cercle a retirer
     */
    @Override
    public void delete(final Cercle c) {
        final int un = 1;
        try {
            GroupeFormeDaoJDBC.deleteFormeGroupe(connect, c.getNom());
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE FROM Cercle WHERE Nom = ?");
            prepare.setString(1, c.getNom());
            int result = prepare.executeUpdate();
            prepare = connect.prepareStatement(
                    "DELETE FROM Forme WHERE Nom = ?");
            prepare.setString(1, c.getNom());
            result = prepare.executeUpdate();
            assert result == un;
            System.out.println("Cercle supprimé");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
