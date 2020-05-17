package fr.uvsq.pglp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RectangleDaoJDBC extends AbstractDao<Rectangle> {
    /**
     * Constructeur.
     * @param c Le connecteur
     */
    public RectangleDaoJDBC(final Connection c) {
        this.connect = c;
    }
    /**
     * Ajoute un Rectangle.
     * @param r Le Rectangle a ajouter
     */
    @Override
    public Rectangle create(final Rectangle r) {
        try {
            final int un = 1;
            final int deux = 2;
            final int trois = 3;
            final int quatre = 4;
            final int cinq = 5;
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO Forme (Nom)"
                    + "VALUES (?)");
            prepare.setString(un, r.getNom());
            int result = prepare.executeUpdate();
            prepare = connect.prepareStatement(
                    "INSERT INTO Rectangle (Nom,Centre_X,Centre_Y,"
                    + "Longueur, Hauteur)"
                    + "VALUES (?,?,?,?,?)");
            prepare.setString(un, r.getNom());
            prepare.setInt(deux, r.getCentre().getX());
            prepare.setInt(trois, r.getCentre().getY());
            prepare.setInt(quatre, r.getLongueur());
            prepare.setInt(cinq, r.getHauteur());
            result = prepare.executeUpdate();
            assert result == un;
            System.out.println("Rectangle créé");
        } catch (SQLException e) {
            return null;
        }
        return r;
    }
    /**
     * Retourne le Rectangle recherché.
     * @param nom Le nom du Rectangle
     * @return Le Rectangle trouvé
     */
    @Override
    public Rectangle find(final String nom) {
        Rectangle r = null;
        try {
            final int un = 1;
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM Rectangle WHERE Nom = ?");
            prepare.setString(un, nom);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                try {
                    r = new Rectangle(
                        result.getString("Nom"),
                        new Position(result.getInt("Centre_X"),
                        result.getInt("Centre_Y")),
                        result.getInt("Longueur"),
                        result.getInt("Hauteur")
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
        return r;
    }
    /**
     * Retourne tous les Rectangles.
     * @return Les Rectangles trouvés
     */
    @Override
    public ArrayList<Rectangle> findAll() {
        ArrayList<Rectangle> r = new ArrayList<Rectangle>();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT Nom FROM Rectangle");
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                r.add(find(result.getString("Nom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Rectangle>();
        }
        return r;
    }
    /**
     * Modifie un Rectangle.
     * @param r Le Rectangle a modifier
     */
    @Override
    public Rectangle update(final Rectangle r) {
        Rectangle r2 = this.find(r.getNom());
        if (r2 != null) {
            try {
                final int un = 1;
                final int deux = 2;
                final int trois = 3;
                final int quatre = 4;
                final int cinq = 5;
                PreparedStatement prepare = connect.prepareStatement(
                        "UPDATE Rectangle SET Centre_X = ?,"
                        + "Centre_Y = ?, Longueur = ?, Hauteur = ?"
                        + " WHERE Nom = ?");
                prepare.setString(cinq, r.getNom());
                prepare.setInt(un, r.getCentre().getX());
                prepare.setInt(deux, r.getCentre().getY());
                prepare.setInt(trois, r.getLongueur());
                prepare.setInt(quatre, r.getHauteur());
                int result = prepare.executeUpdate();
                assert result == 1;
                System.out.println("Rectangle deplacé");
            } catch (SQLException e) {
                e.printStackTrace();
                return r2;
            }
        } else {
            return r2;
        }
        return r;
    }
    /**
     * Retire un Rectangle.
     * @param r Le Rectangle a retirer
     */
    @Override
    public void delete(final Rectangle r) {
        final int un = 1;
        try {
            GroupeFormeDaoJDBC.deleteFormeGroupe(connect, r.getNom());
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE FROM Rectangle WHERE Nom = ?");
            prepare.setString(1, r.getNom());
            int result = prepare.executeUpdate();
            prepare = connect.prepareStatement(
                    "DELETE FROM Forme WHERE Nom = ?");
            prepare.setString(1, r.getNom());
            result = prepare.executeUpdate();
            assert result == un;
            System.out.println("Triangle supprimé");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
