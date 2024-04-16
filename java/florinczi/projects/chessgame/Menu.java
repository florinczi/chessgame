package florinczi.projects.chessgame;

import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private boolean exit;
    protected Engine engine;
    
    public Menu (){
        scanner = new Scanner(System.in);
        exit = false;
        engine = new Engine();
    }

    public void mainMenu(){
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
                    game();
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

    
    public static void printBoard (Board mainBoard) {

        System.out.flush();
        System.out.println("     A B C D E F G H ");
        System.out.println();
        for (int y = 8; y >= 1; y--)
        {   
            
            System.out.printf(" %d   ", (y));
            for (int x = 1; x <= 8; x++){
                System.out.print(mainBoard.printSquare(x, y) + " ");
            }
            System.out.printf("  %d", (y));
            System.out.print("\n");
        }
        System.out.println();
        System.out.println("     A B C D E F G H ");
        System.out.println();
               
    }    

    public void game(){
        
        boolean nextRound = true;
        engine.newGame();
        while(nextRound){
        printBoard(engine.getMainBoard());
        nextRound = getPlayerMove();



        }

    }
    public boolean getPlayerMove (){

        String input;

        System.out.printf("Now playing: %s. What is your move?%nEnter 0 to exit%n" , engine.getMainBoard().getNowPlaying());
        System.out.println("From which square do you want to move?");

        input = scanner.nextLine();
        if (input.equals("0"))
            return false;
        Coordinates from = Parser.convertToCoordinates(input);
        while (from == null){
             System.out.println("Sorry, wrong coordinates, please try again");
            from = Parser.convertToCoordinates(scanner.nextLine());
        
        }
        System.out.println("To which square do you want to move?");
        input = scanner.nextLine();
        if (input.equals("0"))
            return false;
        Coordinates to = Parser.convertToCoordinates(input);
        while (to == null){
            System.out.println("Wrong coordinates, please try again");
            to = Parser.convertToCoordinates(scanner.nextLine());
            }
        MoveCandidate moveCandidate = new MoveCandidate(from, to);
        
        engine.movePiece(moveCandidate);
        return true;

    }

    

  


}
