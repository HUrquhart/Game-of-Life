package life;

public class Main {
    private final UniverseController controller;

    public Main(int size){
        this.controller = new UniverseController(new Universe(size),new UniverseView());
    }

    public static void main(String[] args) {
        Main game = new Main(60);
    }
}

