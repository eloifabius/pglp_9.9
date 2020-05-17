package fr.uvsq.pglp;;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DrawingTUI {
    /**
     * Constructeur.
     */
    public DrawingTUI() {
    }
    /**
     * Lecture d'une commande pour créer un cercle.
     * @param split La ligne de commande
     * @return Le cercle a creer
     */
    private Forme cercleCommand(final String[] split) {
        final int trois = 3;
        String[] split2 = split[1].split("Cercle");
        if (!split2[0].equals("")) {
            System.err.println("Commande invalide");
        }
        if (split2[1].startsWith("(") && split2[1].endsWith(")")) {
            split2[1] = split2[1].substring(1,
                    split2[1].length() - 1);
            String[] split3 = split2[1].split(",");
            if (split3.length == trois) {
                String position = split3[0].concat(",")
                        .concat(split3[1]);
                try {
                    Position p = new Position(position);
                    int rayon = Integer.parseInt(split3[2]);
                    Cercle c = new Cercle(split[0], p,
                            rayon);
                    return c;
                } catch (Exception e) {
                    System.err.println("Argument(s) invalide(s)");
                    System.err.println("Commande invalide");
                }
            } else {
                System.err.println("Nombre d'arguments invalide");
                System.err.println("Commande invalide");
            }
        } else {
            System.err.println("Parenthèse(s) manquante(s)");
             System.err.println("Commande invalide");
         }
        return null;
    }
    /**
     * Lecture d'une commande pour créer un carré.
     * @param split La ligne de commande
     * @return Le carre a créer
     */
    private Forme carreCommand(final String[] split) {
        final int trois = 3;
        String[] split2 = split[1].split("Carre");
        if (!split2[0].equals("")) {
             System.err.println("Commande invalide");
         }
         if (split2[1].startsWith("(") && split2[1].endsWith(")")) {
             split2[1] = split2[1].substring(1,
                     split2[1].length() - 1);
             String[] split3 = split2[1].split(",");
             if (split3.length == trois) {
                 String position = split3[0].concat(",")
                         .concat(split3[1]);
                 try {
                    Position p = new Position(position);
                    int longueur = Integer
                            .parseInt(split3[2]);
                    Carre c = new Carre(split[0], p,
                            longueur);
                    return c;
                } catch (Exception e) {
                    System.err.println("Argument(s) invalide(s)");
                    System.err.println("Commande invalide");
                }
             } else {
                 System.err.println("Nombre d'arguments invalide");
                 System.err.println("Commande invalide");
             }
         } else {
             System.err.println("Parenthèse(s) manquante(s)");
             System.err.println("Commande invalide");
         }
         return null;
    }
    /**
     * Lecture d'une commande pour créer un rectangle.
     * @param split La ligne de commande
     * @return Le rectangle a créer
     */
    private Forme rectangleCommand(final String[] split) {
        final int trois = 3;
        final int quatre = 4;
        String[] split2 = split[1].split("Rectangle");
        if (!split2[0].equals("")) {
             System.err.println("Commande invalide");
         }
         if (split2[1].startsWith("(") && split2[1].endsWith(")")) {
             split2[1] = split2[1].substring(1,
                     split2[1].length() - 1);
             String[] split3 = split2[1].split(",");
             if (split3.length == quatre) {
                 String position = split3[0].concat(",")
                         .concat(split3[1]);
                 try {
                    Position p = new Position(position);
                    int longueur = Integer
                            .parseInt(split3[2]);
                    int hauteur = Integer
                            .parseInt(split3[trois]);
                    Rectangle c = new Rectangle(split[0],
                            p, longueur, hauteur);
                    return c;
                } catch (Exception e) {
                    System.err.println("Argument(s) invalide(s)");
                    System.err.println("Commande invalide");
                }
             } else {
                 System.err.println("Nombre d'arguments invalide");
                 System.err.println("Commande invalide");
             }
         } else {
             System.err.println("Parenthèse(s) manquante(s)");
             System.err.println("Commande invalide");
         }
         return null;
    }
    /**
     * Lecture d'une commande pour créer un triangle.
     * @param split La ligne de commande
     * @return Le triangle a créer
     */
    private Forme triangleCommand(final String[] split) {
        final int trois = 3;
        final int quatre = 4;
        final int cinq = 5;
        final int six = 6;
        String[] split2 = split[1].split("Triangle");
        if (!split2[0].equals("")) {
             System.err.println("Commande invalide");
         }
         if (split2[1].startsWith("(") && split2[1].endsWith(")")) {
             split2[1] = split2[1].substring(1,
                     split2[1].length() - 1);
             String[] split3 = split2[1].split(",");
             if (split3.length == six) {
                 String position = split3[0].concat(",")
                         .concat(split3[1]);
                 String position2 = split3[2].concat(",")
                         .concat(split3[trois]);
                 String position3 = split3[quatre].concat(",")
                         .concat(split3[cinq]);
                 try {
                    Position p = new Position(position);
                    Position p2 = new Position(position2);
                    Position p3 = new Position(position3);
                    Triangle t = new Triangle(split[0],
                            p, p2, p3);
                    return t;
                } catch (Exception e) {
                    System.err.println("Argument(s) invalide(s)");
                    System.err.println("Commande invalide");
                }
             } else {
                 System.err.println("Nombre d'arguments invalide");
                 System.err.println("Commande invalide");
             }
         } else {
             System.err.println("Parenthèse(s) manquante(s)");
             System.err.println("Commande invalide");
         }
         return null;
    }
    /**
     * Recherche une forme parmis tout les types
     * a partir du nom de la variable.
     * @param nom La variable dont on cherche la forme
     * @return La forme de la variable
     */
    private Forme findAll(final String nom) {
        Connection c = DataBase.createBase();
        FactoryDaoJDBC fdj = new FactoryDaoJDBC(c);
        CercleDaoJDBC cercledj = (CercleDaoJDBC) fdj.getCercleDao();
        CarreDaoJDBC carredj = (CarreDaoJDBC) fdj.getCarreDao();
        RectangleDaoJDBC rdj = (RectangleDaoJDBC) fdj.getRectangleDao();
        TriangleDaoJDBC tdj = (TriangleDaoJDBC) fdj.getTriangleDao();
        GroupeDaoJDBC gdj = (GroupeDaoJDBC) fdj.getGroupeDao();
        Forme form = cercledj.find(nom);
        if (form == null) {
            form = carredj.find(nom);
        }
        if (form == null) {
            form = rdj.find(nom);
        }
        if (form == null) {
            form = tdj.find(nom);
        }
        if (form == null) {
            form = gdj.find(nom);
        }
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return form;
    }
    /**
     * Lecture d'une commande pour créer un groupe.
     * @param split La ligne de commande
     * @return Le groupe a créer
     */
    private Forme groupeCommand(final String[] split) {
        String[] split2 = split[1].split("Groupe");
        if (!split2[0].equals("")) {
             System.err.println("Commande invalide");
         }
         if (split2[1].startsWith("(")
                 && split2[1].endsWith(")")) {
             split2[1] = split2[1].substring(1,
                     split2[1].length() - 1);
             String[] sousGroupe = split2[1].split(",");
             Groupe g = new Groupe(split[0]);
             for (int i = 0; i < sousGroupe.length; i++) {
                 Forme f = findAll(sousGroupe[i]);
                 if (f != null) {
                     g.add(f);
                 } else {
                     System.err.println("Nom de "
                              + "variable inconnue");
                     System.err.println("Commande "
                             + "invalide");
                     return null;
                 }
             }
             return g;
         } else {
             System.err.println("Parenthèse(s) manquante(s)");
             System.err.println("Commande invalide");
         }
         return null;
    }
    /**
     * Lecture d'une commande pour deplacer une forme.
     * @param comm La ligne de commande
     * @return La command pour déplacer une forme
     */
    private Command moveCommand(final String comm) {
        final int trois = 3;
        String command = comm;
        command = command.replace(" ", "");
        String[] split = command.split("move");
        if (!split[0].equals("")) {
             System.err.println("Commande invalide");
        }
        if (split[1].startsWith("(") && split[1].endsWith(")")) {
            split[1] = split[1].substring(1, split[1].length() - 1);
            String[] split2 = split[1].split(",");
            if (split2.length == trois) {
                String position = split2[1].concat(",")
                        .concat(split2[2]);
                try {
                    Position p = new Position(position);
                    String nom = split2[0];
                    Forme f = findAll(nom);
                    if (f != null) {
                        return new MoveCommand(f,
                                p.getX(), p.getY());
                    } else {
                        System.err.println("Nom de "
                                 + "variable inconnue");
                        System.err.println("Commande "
                                + "invalide");
                    }
                    } catch (Exception e) {
                        System.err.println("Position "
                                 + "invalide");
                        System.err.println("Commande "
                                + "invalide");
                    }
            } else {
                System.err.println("Nombre d'arguments invalide");
                System.err.println("Commande invalide");
            }
        } else {
            System.err.println("Parenthèse(s) manquante(s)");
            System.err.println("Commande invalide");
        }
        return null;
    }
    /**
     * Lecture d'une commande pour supprimer une forme.
     * @param comm La ligne de commande
     * @return La commande pour supprimer une forme
     */
    private Command deleteCommand(final String comm) {
        String command = comm;
        command = command.replace(" ", "");
        String[] split = command.split("delete");
        if (!split[0].equals("")) {
             System.err.println("Commande invalide");
         }
         if (split[1].startsWith("(") && split[1].endsWith(")")) {
             split[1] = split[1].substring(
                     1, split[1].length() - 1);
             String[] nom = split[1].split(",");
             ArrayList<Forme> lf = new ArrayList<Forme>();
             for (String n : nom) {
                 Forme f = findAll(n);
                 if (f != null) {
                     lf.add(f);
                 } else {
                     System.err.println("Nom de "
                             + "variable inconnue");
                     System.err.println("Commande "
                             + "invalide");
                     return null;
                 }
             }
             return new DeleteCommand(lf);
         } else {
             System.err.println("Parenthèse(s) manquante(s)");
             System.err.println("Commande invalide");
         }
         return null;
    }
    /**
     * Dessiner toutes les formes.
     */
    public void drawAllCommand() {
         Connection c = DataBase.createBase();
         FactoryDaoJDBC fdj = new FactoryDaoJDBC(c);
         GroupeDaoJDBC groupe = (GroupeDaoJDBC)
                 fdj.getGroupeDao();
         CercleDaoJDBC cercle = (CercleDaoJDBC)
                 fdj.getCercleDao();
         CarreDaoJDBC carre = (CarreDaoJDBC)
                 fdj.getCarreDao();
         RectangleDaoJDBC rectangle = (RectangleDaoJDBC)
                 fdj.getRectangleDao();
         TriangleDaoJDBC triangle = (TriangleDaoJDBC)
                 fdj.getTriangleDao();
         ArrayList<Forme> f = new ArrayList<Forme>();
         f.addAll(groupe.findAll());
         f.addAll(cercle.findAll());
         f.addAll(carre.findAll());
         f.addAll(rectangle.findAll());
         f.addAll(triangle.findAll());
         System.out.println("Dessin :");
         for (Forme f2 : f) {
             if (!GroupeFormeDaoJDBC.checkFormeInGroupe(c, f2.getNom())) {
                 f2.draw();
             }
         }
         try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Lecture d'une commande.
     * @param command La ligne de commandes rentrée par l'utilisateur
     * @return La commande a executer
     */
    public Command nextCommand(final String command) {
        if (command.contains("=")) {
            String[] split = command.split("=");
            split[0] = split[0].trim();
            if (split[0].contains(" ")) {
                System.err.println("Nom de variable invalide "
                        + "car contenant des espaces");
                return null;
            } else {
                split[1] = split[1].replace(" ", "");
                Forme f = null;
                if (split[1].contains("Cercle")) {
                    f = cercleCommand(split);
                } else if (split[1].contains("Carre")) {
                    f = carreCommand(split);
                } else if (split[1].contains("Rectangle")) {
                    f = rectangleCommand(split);
                } else if (split[1].contains("Triangle")) {
                    f = triangleCommand(split);
                } else if (split[1].contains("Groupe")) {
                    f = groupeCommand(split);
                }
                if (f != null) {
                    return new CreateCommand(f);
                }
            }
        } else if (command.contains("move")) {
            return moveCommand(command);
        } else if (command.contains("delete")) {
            return deleteCommand(command);
        } else if (!command.equals("exit")) {
            System.err.println("Commande invalide");
        }
        return null;
    }
}
