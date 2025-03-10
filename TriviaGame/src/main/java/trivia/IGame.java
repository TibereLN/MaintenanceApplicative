package trivia;

public interface IGame {

	boolean add(String playerName);

	void roll(int roll) throws Exception;

	boolean handleCorrectAnswer();

	boolean wrongAnswer();

	boolean isGameInProgress();

	boolean isGameValid();
}