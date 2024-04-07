package florinczi.projects.chessgame;

public class Coordinates {
 
    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + getX();
        result = prime * result + getY();
        if (vector != null){
            result = prime * result + vector.getX();
            result = prime * result + vector.getY();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinates other = (Coordinates) obj;
        if (getX() != other.getX() || getY() != other.getY())
            return false;
        if (vector == null) {
            if (other.vector != null)
                return false;
        } 
        else if (vector.getX() != other.vector.getX() || vector.getY() != other.vector.getY())
            return false;
        return true;
       
    }
  

    private static final int MIN_X = 0;
    private static final int MAX_X = 7;
    private static final int MIN_Y = 0;
    private static final int MAX_Y = 7;

    private int x;
    private int y;
    @SuppressWarnings("unused")
    private Vector vector;
    

    public Coordinates(int x, int y) {
        setX(x);
        setY(y);
    }
   

    public Coordinates (Coordinates coordinates){
        setX(coordinates.getX());
        setY(coordinates.getY());
    }

    public Coordinates (Coordinates coordinates, Vector vector, boolean isCapture){
        setX(coordinates.getX());
        setY(coordinates.getY());
        this.vector = new Vector(vector.getX(), vector.getY(), isCapture);
        
    }

    public Coordinates (Coordinates coordinates, int x, int y, boolean isCapture){
        setX(coordinates.getX());
        setY(coordinates.getY());
        this.vector = new Vector(x, y, isCapture);
        
    }

    public Coordinates (Coordinates coordinates, int x, int y){
        setX(coordinates.getX());
        setY(coordinates.getY());
        this.vector = new Vector(x, y);
        
    }
   
    public boolean isValidVector(Vector probe){
        int probeX = this.getX() + probe.getX();
        int probeY = this.getY() + probe.getY();
        return (probeX >= MIN_X || probeX <= MAX_X && probeY >= MIN_Y || probeY <= MIN_Y);
    }


    public void setX(int x) {
        if (x < MIN_X || x > MAX_X) throw new IllegalArgumentException("x coordinate out of bound.");
        this.x = x;
    }

    public void setY(int y) {
        if (y < MIN_Y || y > MAX_Y) throw new IllegalArgumentException("y coordinate out of bound.");
        this.y = y;
    }

    public int getX() {
        return x;
    }
   
    public int getY() {
        return y;
    }
    public void addVector(Vector vector) {
                   
        if (!isValidVector(vector)) throw new IllegalArgumentException("Vector coordinates out of bound.");
        this.y = this.y + vector.getY();
        this.x = this.x + vector.getX();
    }
    
    public class Vector {

        private int x;
        private int y;
        private boolean isCapture;       

        public Vector(int x, int y, boolean isCapture) {
            this.x = x;
            this.y = y;
            this.isCapture = isCapture;            
        }

        public Vector(int x, int y) {
            this.x = x;
            this.y = y;           
        }

        public boolean getIsCapture() {
            return isCapture;
        }
        public void setIsCapture(boolean isCapture) {
            this.isCapture = isCapture;
        }
        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }
        public void set(int x, int y){
            this.x = x;
            this.y = y;
        }


    }
    

    
}
