package florinczi.projects.chessgame;

import java.util.Scanner;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);
    private static boolean exit = false;
    
    public static void mainMenu(){
        while(!exit){
         System.out.println("Let's play some chess!");
         System.out.println("1: New game");
         System.out.println("2: Save game");
         System.out.println("0: Load game");
         System.out.println("0: Exit");
         int choice = scanner.nextInt();
         scanner.nextLine();

            switch (choice) {
                case 1:
                    newGame();
                    break;
                case 2:
                    System.out.println("Not yet supported");
                    break;
                case 3:
                    System.out.println("Not yet supported");
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");


            }
        }

    }

    public static void newGame(){
        Board mainBoard = new Board();
        printBoard(mainBoard);




    }

    public static void printBoard (Board mainBoard) {

        System.out.flush();
        System.out.println("      1 2 3 4 5 6 7 8 ");
        System.out.println();
        for (int x = 7; x >= 0; x--)
        {   
            char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
            System.out.printf(" %c    ", letters[x]);
            for (int y = 0; y < 8; y++){
                System.out.print(mainBoard.printSquare(x, y) + " ");
            }
            System.out.printf("   %c", letters[x]);
            System.out.print("\n");
        }
        System.out.println();
        System.out.println("      1 2 3 4 5 6 7 8 ");
        System.out.println();
        System.out.printf("Now playing: %s. What is your move?%n", mainBoard.nowPlaying);
        //String choice = scanner.nextLine();
    }    


}
