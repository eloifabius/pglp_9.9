package fr.uvsq.database;

import fr.uvsq.forms.Carre;
import fr.uvsq.position.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarreDaoJDBC extends AbstractDao<Carre> {
    /**
     * Constructeur.
     * @param c Le connecteur
     */
    public CarreDaoJDBC(final Connection c) {
        this.connect = c;
    }
    /**
     * Ajoute un Carre.
     * @param c Le Carre a ajouter
     */
    @Override
    public Carre create(final Carre c) {
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
                    "INSERT INTO Carre (Nom,Centre_X,Centre_Y,"
                    + "Longueur)"
                    + "VALUES (?,?,?,?)");
            prepare.setString(un, c.getNom());
            prepare.setInt(deux, c.getCentre().getX());
            prepare.setInt(trois, c.getCentre().getY());
            prepare.setInt(quatre, c.getLongueur());
            result = prepare.executeUpdate();
            assert result == un;
            System.out.println("Carre créé");
        } catch (SQLException e) {
            return null;
        }
        return c;
    }
    /**
     * Retourne le Carre recherché.
     * @param nom Le nom du Carre
     * @return Le Carre trouvé
     */
    @Override
    public Carre find(final String nom) {
        Carre c = null;
        try {
            final int un = 1;
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM Carre WHERE Nom = ?");
            prepare.setString(un, nom);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                try {
                    c = new Carre(
                        result.getString("Nom"),
                        new Position(result.getInt("Centre_X"),
                        result.getInt("Centre_Y")),
                        result.getInt("Longueur")
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
     * Retourne tous les Carres.
     * @return Les Carres trouvé
     */
    @Override
    public ArrayList<Carre> findAll() {
        ArrayList<Carre> c = new ArrayList<Carre>();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT Nom FROM Carre");
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                c.add(find(result.getString("Nom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Carre>();
        }
        return c;
    }
    /**
     * Modifie un Carre.
     * @param c Le Carre a modifier
     */
    @Override
    public Carre update(final Carre c) {
        Carre c2 = this.find(c.getNom());
        if (c2 != null) {
            try {
                final int un = 1;
                final int deux = 2;
                final int trois = 3;
                final int quatre = 4;
                PreparedStatement prepare = connect.prepareStatement(
                        "UPDATE Carre SET Centre_X = ?,"
                        + "Centre_Y = ?, Longueur = ? WHERE Nom = ?");
                prepare.setString(quatre, c.getNom());
                prepare.setInt(un, c.getCentre().getX());
                prepare.setInt(deux, c.getCentre().getY());
                prepare.setInt(trois, c.getLongueur());
                int result = prepare.executeUpdate();
                assert result == 1;
                System.out.println("Carre deplacé");
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
     * Retire un Carre.
     * @param c Le Carre a retirer
     */
    @Override
    public void delete(final Carre c) {
        final int un = 1;
        try {
            GroupeFormeDaoJDBC.deleteFormeGroupe(connect, c.getNom());
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE FROM Carre WHERE Nom = ?");
            prepare.setString(1, c.getNom());
            int result = prepare.executeUpdate();
            prepare = connect.prepareStatement(
                    "DELETE FROM Forme WHERE Nom = ?");
            prepare.setString(1, c.getNom());
            result = prepare.executeUpdate();
            assert result == un;
            System.out.println("Carré supprimé");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
