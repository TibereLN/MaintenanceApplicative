package trivia;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class GameRefacto {

    @Test
    public void testMax6PLayer(){
        IGame game = new Game();
        for (int i = 0; i < 6; i++) {
            game.add(i + "");
        }
        boolean is_rejected = game.add("Joueur_de_trop");
        assertFalse(is_rejected, "Should have rejected adding a seventh player");
    }

    @Test
    public void testNewCategorie() {
        boolean categoriePresent = false;
        for (int i = 0; i < Categorie.values().length; i++) {
            if (Objects.equals(Categorie.values()[i].toString(), "Géographie")) {
                categoriePresent = true;
                break;
            }
        }
        assertTrue(categoriePresent, "Catégorie 'Géographie' non présente !");
    }

    @Test
    public void testMinPlayers() {
        IGame game = new Game();
        //Test à 0
        assertFalse(game.isGameValid());
        //Test à 1
        game.add("J1");
        assertFalse(game.isGameValid());
        //Test à 2
        game.add("J2");
        assertTrue(game.isGameValid());
    }

    @Test
    public void testPlayerEnterAfterGameStart() throws Exception {
        IGame game = new Game();
        game.add("JI");
        game.add("JO");
        assertFalse(game.isGameInProgress());
        game.roll(42);
        boolean result = game.add("JÜ");
        assertFalse(result);
        assertTrue(game.isGameInProgress());
    }

    @Test
    public void testTwoPlayerSameName() {
        IGame game = new Game();
        boolean result;
        result = game.add("JI");
        assertTrue(result);
        result = game.add("JI");
        assertFalse(result);
    }

    @Test
    public void testSecondeChance() throws Exception  {
        IGame game = new Game();
        game.add("JI");
        game.add("JO");
        game.roll(1);
        assertTrue(game.wrongAnswer());
        assertTrue(game.wrongAnswer());
        game.roll(2);
        assertTrue(game.wrongAnswer());
        assertTrue(game.handleCorrectAnswer());
    }

    @Test
    public void testStreak() throws Exception  {
        Game game = new Game();
        game.add("JI");
        game.add("JO");
        game.roll(1);
        game.handleCorrectAnswer();
        assertEquals(1, game.players.get(0).getScore());
        //PLAYER 1 STREAK = 1
        game.roll(1);
        game.handleCorrectAnswer();
        assertEquals(1, game.players.get(1).getStreak());
        //PLAYER 2 STREAK = 1
        game.roll(1);
        game.handleCorrectAnswer();
        assertEquals(2, game.players.get(0).getScore());
        //PLAYER 1 STREAK = 2
        game.roll(1);
        game.wrongAnswer();
        game.wrongAnswer();
        assertEquals(0, game.players.get(1).getStreak());
        //PLAYER 2 STREAK = 0
        game.roll(1);
        game.handleCorrectAnswer();
        assertEquals(3, game.players.get(0).getScore());
        //PLAYER 1 STREAK = 3
        game.roll(1);
        game.wrongAnswer();
        game.wrongAnswer();
        assertTrue(game.players.get(1).isInJail());
        //PLAYER 2 STREAK = 0 IN JAIL

        game.roll(1);
        game.handleCorrectAnswer();
        assertEquals(5, game.players.get(0).getScore());
        //PLAYER 1 STREAK = 4
        game.roll(1);
        game.handleCorrectAnswer();
    }
}
