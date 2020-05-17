package fr.uvsq.pglp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DataBase {
    /**
     * Création de la base de données.
     * @return Le connecteur a la base
     * @throws SQLException
     */
    public static Connection createBase() {
        try {
            return DriverManager.getConnection(
                    "jdbc:derby:DataForme;create=true");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Supprime toutes les tables de la base de données.
     * @param connect Le connecteur a la base
     * @throws SQLException
     */
    public static void deleteAllTables(final Connection connect)
            throws SQLException {
        Statement s = null;
        s = connect.createStatement();
        try {
            s.execute("DROP TABLE GroupeForme");
        } catch (SQLException e) {
        }
        try {
            s.execute("DROP TABLE Cercle");
        } catch (SQLException e) {
        }
        try {
            s.execute("DROP TABLE Carre");
        } catch (SQLException e) {
        }
        try {
            s.execute("DROP TABLE Triangle");
        } catch (SQLException e) {
        }
        try {
            s.execute("DROP TABLE Rectangle");
        } catch (SQLException e) {
        }
        try {
            s.execute("DROP TABLE Groupe");
        } catch (SQLException e) {
        }
        try {
            s.execute("DROP TABLE Forme");
        } catch (SQLException e) {
        }
    }
    /**
     * Création de toutes les tables de la base de données.
     * @param connect Le connecteur a la base
     * @throws SQLException
     */
    public static void createAllTables(final Connection connect)
            throws SQLException {
        String forme = "CREATE TABLE Forme ("
                + "Nom varchar(30),"
                + "PRIMARY KEY (Nom)"
                + ")";
        String cercle = "CREATE TABLE Cercle ("
                + "Nom varchar(30),"
                + "Centre_X int,"
                + "Centre_Y int,"
                + "Rayon int,"
                + "PRIMARY KEY (Nom),"
                + "FOREIGN KEY (Nom) REFERENCES Forme (Nom)"
                + ")";
        String carre = "CREATE TABLE Carre ("
                + "Nom varchar(30),"
                + "Centre_X int,"
                + "Centre_Y int,"
                + "Longueur int,"
                + "PRIMARY KEY (Nom),"
                + "FOREIGN KEY (Nom) REFERENCES Forme (Nom)"
                + ")";
        String rectangle = "CREATE TABLE Rectangle ("
                + "Nom varchar(30),"
                + "Centre_X int,"
                + "Centre_Y int,"
                + "Longueur int,"
                + "Hauteur int,"
                + "PRIMARY KEY (Nom),"
                + "FOREIGN KEY (Nom) REFERENCES Forme (Nom)"
                + ")";
        String triangle = "CREATE TABLE Triangle ("
                + "Nom varchar(30),"
                + "Sommet1_X int,"
                + "Sommet1_Y int,"
                + "Sommet2_X int,"
                + "Sommet2_Y int,"
                + "Sommet3_X int,"
                + "Sommet3_Y int,"
                + "PRIMARY KEY (Nom),"
                + "FOREIGN KEY (Nom) REFERENCES Forme (Nom)"
                + ")";
        String groupe = "CREATE TABLE Groupe ("
                + "Nom varchar(30),"
                + "PRIMARY KEY (Nom),"
                + "FOREIGN KEY (Nom) REFERENCES Forme (Nom)"
                + ")";
        String groupeForme = "CREATE TABLE GroupeForme ("
                + "NomGroupe varchar(30),"
                + "NomForme varchar(30),"
                + "PRIMARY KEY (NomGroupe, NomForme),"
                + "FOREIGN KEY (NomGroupe) REFERENCES Groupe (Nom),"
                + "FOREIGN KEY (NomForme) REFERENCES Forme (Nom)"
                + ")";
        Statement s = connect.createStatement();
        s.execute(forme);
        s.execute(cercle);
        s.execute(carre);
        s.execute(rectangle);
        s.execute(triangle);
        s.execute(groupe);
        s.execute(groupeForme);
    }
}
