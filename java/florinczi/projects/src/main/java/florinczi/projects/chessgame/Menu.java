package florinczi.projects.chessgame;

import static florinczi.projects.chessgame.pieces.PlayerColor.BLACK;
import static florinczi.projects.chessgame.pieces.PlayerColor.WHITE;

import java.util.Scanner;

import florinczi.projects.chessgame.util.Coordinates;
import florinczi.projects.chessgame.util.MoveCandidate;
import florinczi.projects.chessgame.util.Parser;

public class Menu {
    //added testing scenarios
    private Scanner scanner;
    public Scanner getScanner() {
        return scanner;
    }
    private boolean exit;
    protected Engine engine;
    
    public Menu (){
        scanner = new Scanner(System.in);
        exit = false;
        engine = new Engine(this);
    }

    public void mainMenu(){
        while(!exit){
         System.out.println("Let's play some chess!");
         System.out.println("1: New game");
         System.out.println("2: Pawn capture and promotion test");
         System.out.println("3: Castling test");
         System.out.println("4: En passant test");
         System.out.println("5: Checkmate test");
         System.out.println("6: Game against black AI");
         System.out.println("7: AI test 1");
         System.out.println("0: Exit");
         int choice = scanner.nextInt();
         scanner.nextLine();
        
        if (choice == 0)
            exit = true;
        if (choice > 0 && choice <=7)
            game(choice);
        else
            System.out.println("Wrong choice,try again");
         
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

    public void game(int choice){
        
        boolean nextRound = true;
        engine.newGame(choice);
        while(nextRound){
        printBoard(engine.getMainBoard());
        System.out.printf("%nEvaluation: %.2f %n", Evaluator.evaluate(engine.getMainBoard()));
        System.out.printf("Black move count: %d%nWhite move count %d%n", engine.getMainBoard().getMoveList(BLACK).size(), engine.getMainBoard().getMoveList(WHITE).size());
        nextRound = engine.nextTurn();
        }
        if (engine.isCheckmate())
            System.out.println("Checkmate! Game Over!");
        if (engine.isStalemate())
            System.out.println("Stalemate! Game Over!");
        System.out.printf("%n%n*********************%n%n");
        
}


    public MoveCandidate getPlayerMove (){

        String input;
        
        System.out.printf("Now playing: %s. What is your move?%nEnter 0 to exit%n" , engine.getMainBoard().getNowPlaying());
        System.out.println("From which square do you want to move?");

        input = scanner.nextLine();

        if (input.equals("0"))
            return null;
        Coordinates from = Parser.convertToCoordinates(input);
        while (from == null){
            System.out.println("Sorry, wrong coordinates, please try again");
            from = Parser.convertToCoordinates(scanner.nextLine());
        
        }
        System.out.println("To which square do you want to move?");
        input = scanner.nextLine();
        if (input.equals("0"))
            return null;
        Coordinates to = Parser.convertToCoordinates(input);
        while (to == null){
            System.out.println("Wrong coordinates, please try again");
            to = Parser.convertToCoordinates(scanner.nextLine());
            }
        
        
        return new MoveCandidate(from, to);

    }
}
