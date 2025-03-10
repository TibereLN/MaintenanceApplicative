package trivia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Helper {
    public static void main(String[] args) throws Exception {
        //Création des questions
        for (Categorie categorie : Categorie.values()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("ressources/" + categorie.getFileName()))) {
                for (int i = 0; i < 50; i++) {
                    writer.write(categorie.toString() + " Question " + i);
                    writer.newLine(); // Passer à la ligne suivante
                }
                System.out.println("Fichier créé : " + categorie.getFileName());
            } catch (IOException e) {
                System.err.println("Erreur lors de l'écriture du fichier " + categorie.getFileName());
                e.printStackTrace();
            }
        }
    }
}
