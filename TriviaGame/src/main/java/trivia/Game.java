package trivia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

// TODO REFACTOR ME
public class Game implements IGame {
   public static final int MAX_PLAYER_COUNT = 6;
   public static final int LENGHT_MAP = 12;

   public ArrayList<Player> players = new ArrayList<>();
   Map<Categorie, LinkedList<String>> categoriesQuestions = new HashMap<>();

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   boolean gameInProgress = false;

   Categorie actualCategory;
   boolean inSecondChance = true;

   public Game() {
      for (Categorie categorie : Categorie.values()) {
         categoriesQuestions.put(categorie, new LinkedList<>());
         File file = new File("ressources/"+categorie.getFileName());

         try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
               categoriesQuestions.get(categorie).add(line);
            }
         } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier " + actualCategory.getFileName());
            e.printStackTrace();
         }
      }
   }

   public boolean add(String playerName) {
      //Nombre max de joueurs atteint
      if (players.size() == MAX_PLAYER_COUNT) {
         return false;
      }
      //Joueurs ayant le même nom
      for (Player player : players) {
         if (Objects.equals(player.getName(), playerName)) {
            return false;
         }
      }
      //Partie en cours
      if (isGameInProgress()) {
         return false;
      }
      
      players.add(new Player(playerName));

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + numberOfPlayer());
      return true;
   }

   public int numberOfPlayer() {
      return players.size();
   }

   public void roll(int roll) throws Exception {
      gameInProgress = true;

      if (!isGameValid()) {
         throw new Exception("Le jeu ne peut pas être lancé");
      }

      Player p = players.get(currentPlayer);
      System.out.println(p.getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (p.isInJail()) {
         isGettingOutOfPenaltyBox = roll % 2 != 0;
         if (isGettingOutOfPenaltyBox) {
            System.out.println(p.getName() + " is getting out of the penalty box");

            executePlayerTurn(roll);

         } else {
            System.out.println(p.getName() + " is not getting out of the penalty box");
         }

      } else {
         executePlayerTurn(roll);
      }
   }

   private void executePlayerTurn(int roll) {
      movePlayer(roll);
      Player p = players.get(currentPlayer);
      System.out.println(p.getName() + "'s new location is " + p.getPosition());
      System.out.println("The category is " + currentCategory());

      actualCategory = currentCategory();
      askQuestion();
   }

   private void movePlayer(int roll) {
      Player p = players.get(currentPlayer);
      int pos = p.getPosition() + roll;
      if(pos > LENGHT_MAP){
         pos = pos % LENGHT_MAP;
      }
      p.setPosition(pos);
   }

   private void askQuestion() {
      System.out.println(categoriesQuestions.get(currentCategory()).removeFirst());
   }

   private Categorie currentCategory() {
      int playerPos = players.get(currentPlayer).getPosition();
      for (int i = 0; i < Categorie.values().length; i++) {
         if ((playerPos - i) % Categorie.values().length == 0) {
            return Categorie.values()[i];
         }
      }
      return null;
   }

   public boolean handleCorrectAnswer() {
      Player p = players.get(currentPlayer);
      if (p.isInJail()) {
         if (isGettingOutOfPenaltyBox) {
            return correctAnswer();

         } else {
            nextPlayer();
            return true;
         }
      } else {
         return correctAnswer();
      }
   }

   private boolean correctAnswer() {
      Player player = players.get(currentPlayer);
      System.out.println("Answer was correct");
      if (player.getScore() >= 3) {
         player.setScore(player.getScore()+1);
      }
      player.setScore(player.getScore() + 1);
      player.setStreak(player.getStreak() + 1);
      System.out.println(players.get(currentPlayer).getName() + " now has " + player.getScore()+ " Gold Coins. And " + player.getStreak() + " in streak");

      boolean win = didPlayerWin();
      nextPlayer();
      return win;
   }

   public boolean wrongAnswer() {
      Player player = players.get(currentPlayer);
      System.out.println("Question was incorrectly answered");

      if (inSecondChance) {
         System.out.println("Seconde chance");
         askQuestion();
         inSecondChance = false;
         return true;
      }

      if (player.getStreak() > 0) {
         player.setStreak(0);
         System.out.println(players.get(currentPlayer).getName() + " a son streak réinitialisé");
      }
      else {
         player.setInJail(true);
         System.out.println(players.get(currentPlayer).getName() + " was sent to the penalty box");
      }
      nextPlayer();
      return true;
   }

   @Override
   public boolean isGameInProgress() {
      return gameInProgress;
   }

   @Override
   public boolean isGameValid() {
      return players.size() >= 2;
   }

   private void nextPlayer() {
      currentPlayer++;
      if (currentPlayer == numberOfPlayer()) currentPlayer = 0;
      inSecondChance = true;
   }

   private boolean didPlayerWin() {
      return players.get(currentPlayer).getScore() < MAX_PLAYER_COUNT;
   }
}
