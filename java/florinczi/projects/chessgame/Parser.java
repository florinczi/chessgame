package florinczi.projects.chessgame;

public final class Parser {

        private static final char[] LETTERS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        
   
    public static Coordinates convertToCoordinates (String str){
        char file;
        char row;
        int intFile = 0 ;//a bit of a hotfix I guess
        int intRow;

        if (str.length() != 2) //is the lenght right?
            return null;
        file = str.charAt(0);
        row = str.charAt(1);

        file = Character.toLowerCase(file); //sanitase first char
        boolean isValid = false;
        for (int i = 0; i < 8; i++){
            if (file == LETTERS[i]){
                isValid = true;
                intFile = i + 1; //conver to int, and intFile ready
                break;
            }
    }
    if (!isValid)
            return null; //looks like first char is alright
    
    if (!Character.isDigit(row) || row < '1' || row > '8') //you can do that thanks to ASCII and Unicode standards 
            return null; // second

        intRow = Character.getNumericValue(row);
        return new Coordinates(intFile, intRow);
    }

}