package com.mycalendar;

import com.mycalendar.event.eventType.*;
import com.mycalendar.user.User;
import com.mycalendar.user.Users;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;

public class Application {
    int numEvent = 0;
    CalendarManager calendar = new CalendarManager();
    Users users = new Users();
    User currentUser = null;
    boolean continuer = true;
    Scanner scanner = new Scanner(System.in);
    List<Runnable> commandesMenuConnexion = new ArrayList<>();
    List<Runnable> commandesMenuGlobal = new ArrayList<>();
    List<Runnable> commandesMenuAfficherEvenements = new ArrayList<>();
    List<Runnable> commandesMenuAjouterEvenements = new ArrayList<>();

    public void main() {
        commandesMenuConnexion.addLast(this::connexion);
        commandesMenuConnexion.addLast(this::inscription);

        commandesMenuGlobal.addLast(this::menuAfficherEvenements);
        commandesMenuGlobal.addLast(this::menuAjouterEvenement);
        commandesMenuGlobal.addLast(this::deconnexion);

        commandesMenuAfficherEvenements.addLast(this::afficherTousEvenements);
        commandesMenuAfficherEvenements.addLast(this::afficherEvenementsMois);
        commandesMenuAfficherEvenements.addLast(this::afficherEvenementsSemaine);
        commandesMenuAfficherEvenements.addLast(this::afficherEvenementsJour);
        commandesMenuAfficherEvenements.addLast(this::afficherEvenementsPeriode);

        commandesMenuAjouterEvenements.addLast(this::ajouterEvenementRDV);
        commandesMenuAjouterEvenements.addLast(this::ajouterEvenementReunion);
        commandesMenuAjouterEvenements.addLast(this::ajouterEvenementPeriodique);
        commandesMenuAjouterEvenements.addLast(this::ajouterEvenementJour);
        commandesMenuAjouterEvenements.addLast(this::supprimerEvenement);

        //Utilisateurs déjà existants
        users.addUser(new User("Roger", "Chat"));
        users.addUser(new User("Pierre", "KiRouhl"));

        while (continuer) {
            if (currentUser == null) {
                afficherLogo();
                System.out.println("1 - Se connecter");
                System.out.println("2 - Créer un compte");
                System.out.println("Votre choix : ");
                commandesMenuConnexion.get(Integer.parseInt(scanner.nextLine())-1).run();
            }
            while (currentUser != null) {
                System.out.println("\nBonjour, " + currentUser.getName());
                System.out.println("=== Menu Gestionnaire d'Événements ===");
                System.out.println("1 - Voir les événements");
                System.out.println("2 - Ajouter un évènement (ou supprimer)");
                System.out.println("3 - Se déconnecter");
                System.out.print("Votre choix : ");
                commandesMenuGlobal.get(Integer.parseInt(scanner.nextLine())-1).run();
            }
        }
    }

    private void inscription() {
        System.out.print("Nom d'utilisateur: "); String name = scanner.nextLine();
        System.out.print("Mot de passe: "); String mdp = scanner.nextLine();
        System.out.print("Répéter mot de passe: ");
        if (scanner.nextLine().equals(mdp)) {
            users.addUser(new User(name, mdp));
            currentUser = users.getUser(name);
            System.out.println("Inscription effectuée !");
        }
        else System.out.println("Les mots de passes ne correspondent pas...");
    }

    private void connexion() {
        System.out.print("Nom d'utilisateur: ");  String name = scanner.nextLine();
        if (users.containUser(name)) {
            System.out.print("Mot de passe: ");
            String mdp = scanner.nextLine();
            if (users.validMDP(name, mdp)) {
                currentUser = users.getUser(name);
                System.out.println("Connexion efffectuée !");
            }
            else System.out.println("Mot de passe invalide");
        }
        else System.out.println("Aucun utilisateur a ce nom");
    }

    private void deconnexion() {
        System.out.println("Déconnexion ! Voulez-vous continuer ? (O/N)"); continuer = scanner.nextLine().trim().equalsIgnoreCase("O");
        currentUser = null;
    }

    private void menuAfficherEvenements() {
        System.out.println("\n=== Menu de visualisation d'Événements ===");
        System.out.println("1 - Afficher TOUS les événements");
        System.out.println("2 - Afficher les événements d'un MOIS précis");
        System.out.println("3 - Afficher les événements d'une SEMAINE précise");
        System.out.println("4 - Afficher les événements d'un JOUR précis");
        System.out.println("5 - Afficher les événements sur une période donnée");
        System.out.println("Autres - Retour");
        System.out.print("Votre choix : ");
        commandesMenuAfficherEvenements.get(Integer.parseInt(scanner.nextLine())-1).run();
    }

    private void afficherTousEvenements() {
        LocalDateTime debut = LocalDateTime.MIN;
        LocalDateTime fin = LocalDateTime.MAX;
        afficherEvenements(debut, fin);
    }

    private void afficherEvenementsMois() {
        System.out.print("Entrez l'année (AAAA) : "); int anneeMois = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : "); int mois = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
        LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

        afficherEvenements(debutMois, finMois);
    }


    private void afficherEvenementsSemaine() {
        System.out.print("Entrez l'année (AAAA) : "); int anneeSemaine = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le numéro de semaine (1-52) : "); int semaine = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutSemaine = LocalDateTime.now()
                .withYear(anneeSemaine)
                .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                .withHour(0).withMinute(0);
        LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);

        afficherEvenements(debutSemaine, finSemaine);
    }

    private void afficherEvenementsJour() {
        System.out.print("Entrez l'année (AAAA) : "); int anneeJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : "); int moisJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le jour (1-31) : "); int jour = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
        LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

        afficherEvenements(debutJour, finJour);
    }

    private void afficherEvenementsPeriode() {
        System.out.print("Entrez l'année début (AAAA) : "); int anneeJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois début (1-12) : "); int moisJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le jour début (1-31) : "); int jour = Integer.parseInt(scanner.nextLine());

        System.out.print("Entrez l'année fin (AAAA) : "); int anneeJour2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois fin (1-12) : "); int moisJour2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le jour fin (1-31) : "); int jour2 = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
        LocalDateTime finJour = LocalDateTime.of(anneeJour2, moisJour2, jour2, 0, 0);

        afficherEvenements(debutJour, finJour);
    }

    private void menuAjouterEvenement() {
        System.out.println("=== Menu Création d'Événements ===");
        System.out.println("1 - Ajouter un rendez-vous perso");
        System.out.println("2 - Ajouter une réunion");
        System.out.println("3 - Ajouter un évènement périodique");
        System.out.println("4 - Ajouter un jour important");
        System.out.println("5 - Supprimer un évènement");
        System.out.print("Votre choix : ");

        commandesMenuAjouterEvenements.get(Integer.parseInt(scanner.nextLine())-1).run();
    }

    private void ajouterEvenementRDV() {
        System.out.print("Titre de l'événement : ");String titre = scanner.nextLine();
        System.out.print("Année (AAAA) : ");int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");int moisRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");int jourRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");int minute = Integer.parseInt(scanner.nextLine());
        System.out.print("Durée (en minutes) : ");int duree = Integer.parseInt(scanner.nextLine());
        this.calendar.ajouterEvent(new EventRDVPerso(numEvent, titre, this.currentUser, LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute), duree));
        numEvent++;
    }

    private void ajouterEvenementReunion() {
        System.out.print("Titre de l'événement : ");String titre = scanner.nextLine();
        System.out.print("Année (AAAA) : ");int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");int moisRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");int jourRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");int minute = Integer.parseInt(scanner.nextLine());
        System.out.print("Durée (en minutes) : ");int duree = Integer.parseInt(scanner.nextLine());
        System.out.println("Lieu :"); String lieu = scanner.nextLine();
        List<String> participants = new ArrayList<>();
        participants.add(this.currentUser.getName());
        System.out.println("Ajouter un participant ? (oui / non)");
        while (scanner.nextLine().equals("oui")) {
            System.out.print("Participants : " + participants);  participants.add(scanner.nextLine());
        }
        this.calendar.ajouterEvent(new EventReunion(numEvent, titre, this.currentUser, LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute), duree,
                lieu, participants));
        numEvent++;
    }

    private void ajouterEvenementPeriodique() {
        System.out.print("Titre de l'événement : ");String titre = scanner.nextLine();
        System.out.print("Année (AAAA) : ");int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");int moisRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");int jourRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");int minute = Integer.parseInt(scanner.nextLine());
        System.out.print("Durée (en minutes) : ");int duree = Integer.parseInt(scanner.nextLine());
        System.out.print("Frequence (en jours) : "); int frequence = Integer.parseInt(scanner.nextLine());
        this.calendar.ajouterEvent(new EventPeriodique(numEvent, titre, this.currentUser, LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute), duree,
                frequence));
        numEvent++;
    }

    private void ajouterEvenementJour() {
        System.out.print("Titre de l'événement : ");String titre = scanner.nextLine();
        System.out.print("Année (AAAA) : ");int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");int moisRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");int jourRdv = Integer.parseInt(scanner.nextLine());
        this.calendar.ajouterEvent(new EventJour(numEvent, titre, this.currentUser, LocalDateTime.of(annee, moisRdv, jourRdv, 0, 0), 24*60));
        numEvent++;
    }

    private void supprimerEvenement() {
        System.out.print("ID de l'événement : ");int id = Integer.parseInt(scanner.nextLine());
        this.calendar.supprimerEvenement(id);
    }

    private void afficherEvenements(LocalDateTime debut, LocalDateTime fin) {
        List<Event> listEvents = this.calendar.getEvents().eventsDansPeriode(debut, fin);
        if (listEvents.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");
            for (Event event : listEvents) {
                System.out.println("- " + event.description());
            }
        }
    }

    private static void afficherLogo() {
        System.out.println("  _____         _                   _                __  __");
        System.out.println(" / ____|       | |                 | |              |  \\/  |");
        System.out.println(
                "| |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __");
        System.out.println(
                "| |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|");
        System.out.println(
                "| |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | || (_| || | | || (_| || (_| ||  __/| |");
        System.out.println(
                " \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|");
        System.out.println(
                "                                                                                   __/ |");
        System.out.println(
                "                                                                                  |___/");

    }
}
