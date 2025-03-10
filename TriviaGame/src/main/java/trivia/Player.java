package trivia;

public class Player {
    private String name;
    private int score;
    private int position;
    private boolean isInJail;
    private int streak;

    Player(String name) {
        this.name = name;
        this.score = 0;
        this.position = 1;
        this.isInJail = false;
        this.streak = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isInJail() {
        return isInJail;
    }

    public void setInJail(boolean inJail) {
        isInJail = inJail;
    }
    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }
}
