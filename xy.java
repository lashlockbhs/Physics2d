import java.util.ArrayList;

public class xy {
    private double x;
    private double y;
    private boolean invalid;
    public xy(double x, double y){
        this.x = x;
        this.y = y;
    }
    public xy(boolean invalid){
        this.invalid = true;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }
    
    public boolean cordsInArray(ArrayList<xy> ar){
        for (xy p : ar) {
            if(this.sameCords(p)){
                return true;
            }
        }
        return false;
    }
    public boolean sameCords(xy p1){
        if((this.getX()==p1.getX())&&(this.getY()==p1.getY())){
            return true;
        }
        return false;
    }
    public boolean isInvalid(){
        return this.invalid;
    }
    public String toString(){
        return "( " + String.valueOf(getX()) + ", " + String.valueOf(getY()) + " )";
    }
}