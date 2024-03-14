package efs.task.syntax;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
public class GuessNumberGame {

    //Do not modify main method
    private final int secretNumber;
    private final int M; //max range
    private final int L; //amount of guesses
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) throws IllegalArgumentException {
        //TODO: Implement the constructor
        try {
            this.M = Integer.parseInt(argument);
        } catch (IllegalArgumentException e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException("Invalid argument", e);
        }
        if(M < 1 || M > UsefulConstants.MAX_UPPER_BOUND) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            System.out.println("Poza zakresem <1," + M +">");
            throw new IllegalArgumentException();
        }
        this.secretNumber = new Random().nextInt(M) + 1;
        this.L = (int)(Math.abs(Math.floor(Math.log(M) / Math.log(2))) + 1);
    }

    public void play() {
        //TODO: Implement the method that executes the game session
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1," + M +">");

        //Initialization of variables needed:
        int counter = 0;
        int attemptInt;
        String attempt;
        String[] progressBar = new String[L];
        Arrays.fill(progressBar,".");

        while (true) {
            if (counter == L) {
                System.out.println(UsefulConstants.UNFORTUNATELY + " Wykorzystałeś każdą możliwą szansę (" + L +")");
                System.out.println("Liczba to: " + secretNumber);
                break;
            }

            progressBar[counter] = "*";
            attemptsTab(progressBar);

            System.out.println(UsefulConstants.GIVE_ME + " liczbę:");
            attempt = scanner.next();

            try {
                attemptInt = Integer.parseInt(attempt);
            } catch (NumberFormatException e) {
                System.out.println("Wygląda na to, że: " + attempt + " to " + UsefulConstants.NOT_A_NUMBER);
                scanner.nextLine();
                counter++;
                continue;
            }

            if (attemptInt > secretNumber) {
                System.out.println(UsefulConstants.TO_MUCH);
            } else if (attemptInt < secretNumber) {
                System.out.println(UsefulConstants.TO_LESS);
            } else {
                System.out.println(UsefulConstants.YES + " to jest prawidłowa liczba!");
                System.out.println(UsefulConstants.CONGRATULATIONS + " Udało ci się zgadnąć.");
                break;
            }
            counter++;
        }
        scanner.close();
    }

    private void attemptsTab(String[] array) {
        System.out.print("Próby: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println("]");
    }
}
