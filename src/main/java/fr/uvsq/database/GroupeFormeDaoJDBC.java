package fr.uvsq.database;

import fr.uvsq.database.*;
import fr.uvsq.forms.Forme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe pour les associations.
 */
public abstract class GroupeFormeDaoJDBC {
    /**
     * Crée l'association GroupeCercle entre un Groupe et une Forme.
     * @param c Le connecteur a la bd
     * @param nom Le nom du groupe
     * @param nom2 Le nom de la forme
     */
    public static void createGroupeForme(final Connection c,
            final String nom, final String nom2) {
        try {
            final int un = 1;
            final int deux = 2;
            PreparedStatement prepare = c.prepareStatement(
                    "INSERT INTO GroupeForme (NomGroupe, NomForme)"
                    + "VALUES (?,?)");
            prepare.setString(un, nom);
            prepare.setString(deux, nom2);
            int result = prepare.executeUpdate();
            assert result == un;
        } catch (SQLException e) {
        }
    }
    /**
     * Supprime les associations d'une Forme vers des groupes.
     * @param c Le connecteur
     * @param nom Le nom de la Forme
     */
    public static void deleteFormeGroupe(final Connection c,
            final String nom) {
        final int un = 1;
        try {
            PreparedStatement prepare = c.prepareStatement(
                    "DELETE FROM GroupeForme WHERE NomForme = ?");
            prepare.setString(1, nom);
            int result = prepare.executeUpdate();
            assert result == un;
        } catch (SQLException e) {
        }
    }
    /**
     * Supprime toutes les associations d'un groupe vers des Formes.
     * @param c Le connecteur
     * @param nom Le nom du groupe
     */
    public static void deleteGroupeForme(final Connection c,
            final String nom) {
        final int un = 1;
        try {
            PreparedStatement prepare = c.prepareStatement(
                    "DELETE FROM GroupeForme WHERE NomGroupe = ?");
            prepare.setString(1, nom);
            int result = prepare.executeUpdate();
            assert result == un;
        } catch (SQLException e) {
        }
    }
    /**
     * Cherche toutes les associations GroupeForme d'un même groupe.
     * @param connect Le connecteur
     * @param nom Le nom du groupe
     * @return La liste des formes d'un type
     */
    public static ArrayList<Forme> findGroupeForme(
            final Connection connect, final String nom) {
        ArrayList<Forme> f = new ArrayList<Forme>();
        try {
            final int un = 1;
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT NomForme FROM GroupeForme WHERE NomGroupe = ?");
            prepare.setString(un, nom);
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                FactoryDaoJDBC fdj = new FactoryDaoJDBC(connect);
                CercleDaoJDBC cercledj = (CercleDaoJDBC) fdj.getCercleDao();
                CarreDaoJDBC carredj = (CarreDaoJDBC) fdj.getCarreDao();
                RectangleDaoJDBC rdj = (RectangleDaoJDBC) fdj.getRectangleDao();
                TriangleDaoJDBC tdj = (TriangleDaoJDBC) fdj.getTriangleDao();
                GroupeDaoJDBC gdj = (GroupeDaoJDBC) fdj.getGroupeDao();
                Forme form = cercledj.find(result.getString("NomForme"));
                if (form == null) {
                    form = carredj.find(result.getString("NomForme"));
                }
                if (form == null) {
                    form = rdj.find(result.getString("NomForme"));
                }
                if (form == null) {
                    form = tdj.find(result.getString("NomForme"));
                }
                if (form == null) {
                    form = gdj.find(result.getString("NomForme"));
                }
                f.add(form);
            }
        } catch (SQLException e) {
            return new ArrayList<Forme>();
        }
        return f;
    }
    /**
     * Verifie qu'une forme est contenu dans un groupe.
     * @param c Le connecteur a la base de données
     * @param nom La forme a vérifié
     * @return Vrai si la forme est contenu dans un groupe
     */
    public static boolean checkFormeInGroupe(final Connection c,
            final String nom) {
        try {
            PreparedStatement prepare = c.prepareStatement(
                    "SELECT * FROM GroupeForme WHERE NomForme = ?");
            prepare.setString(1, nom);
            ResultSet result = prepare.executeQuery();
            return result.next();
        } catch (SQLException e) {
            return false;
        }
    }
}
