package fr.uvsq.main;

import fr.uvsq.commands.Command;
import fr.uvsq.commands.DrawingTUI;
import fr.uvsq.database.DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Classe DrawingApp.
 *
 */
public class DrawingAPP {
    /**
     * Scanner.
     */
    private Scanner scanner;
    /**
     * DrawingTUI.
     */
    private DrawingTUI dt;
    /**
     * Constructeur.
     */
    public DrawingAPP() {
        this.scanner = new Scanner(System.in);
        dt = new DrawingTUI();
    }
    /**
     * Lance l'execution du programme.
     * @param args
     * @throws SQLException
     */
    public static void main(final String[] args) throws SQLException {
        Connection connect = DataBase.createBase();
        DataBase.deleteAllTables(connect);
        DataBase.createAllTables(connect);
        connect.close();
        DrawingAPP da = new DrawingAPP();
        da.run();
    }
    /**
     * Execute le programme.
     */
    public void run() {
        System.out.println("L O G I C I E L   D E   D E S S I N :\n\n"
                + "------------------------------\n\n"
                + "Liste de commandes :\n"
                + "Creation : \n"
                + "c1 = Cercle((0, 0), 50)\n"
                + "c2 = Carre((0, 0), 30)\n"
                + "r1 = Rectangle((0, 0), 40, 10)\n"
                + "t1 = Triangle((0, 0), (2, 2), (4, 0))\n"
                + "g1 = Groupe(c1, c2, r1, t1)\n"
                + "Déplacement : move(c1, (10, 20))\n"
                + "Suppression : delete(r1, t1)\n"
                + "Quitter : exit\n\n"
                + "------------------------------\n\n"
                + "En attente de commande :\n");
        String command = scanner.nextLine();
        Command c;
        while (!command.equals("exit")) {
            c = dt.nextCommand(command);
            if (c != null) {
                c.execute();
                dt.drawAllCommand();
            }
            System.out.println("\nEn attente de commande :");
            command = scanner.nextLine();
        }
    }
}