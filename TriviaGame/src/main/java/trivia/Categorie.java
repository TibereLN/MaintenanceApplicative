package trivia;

public enum Categorie {
    ROCK("Rock", "rock.properties"),
    POP("Pop", "pop.properties"),
    SCIENCE("Science", "science.properties"),
    SPORT("Sports", "sport.properties"),
    GEOGRAPHIE("Géographie", "geographie.properties");

    private final String name;
    private final String fileName;

    Categorie(String name, String fileName){
        this.name = new String(name);
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getFileName() {
        return fileName;
    }
}
