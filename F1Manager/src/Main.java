import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static GameEngine gameEngine;

    public static void main(String[] args) {

        gameEngine = new GameEngine();
        gameEngine.start();
        Scanner restartInput = new Scanner(System.in);
        System.out.println("Do you want to Start a new Race (Input: Yes / No?");
        String restart = restartInput.nextLine();

        if (restart.equals("Yes")){
            gameEngine = new GameEngine();
            gameEngine.start();
        }
    }
}