package com.mycalendar.commande;

import com.mycalendar.Main;

public class CommandeAfficherEvenements implements Commande {
    @Override
    public void executer() {
        Main.afficherEvenements();
    }
}
