
public class Point extends xy{
    private Point n1;
    private Point n2;
    public Point(double x, double y, Point n1, Point n2){
        super(x, y);
        this.n2 = n2; //connecting points
        this.n1 = n1;
    }
    public Point(boolean invalid){
        super(invalid);
    }
    public void setN1(Point p){
        this.n1 = p;
    }
    public void setN2(Point p){
        this.n2 = p;
    }
    public Point getN1(){
        return this.n1;
    }
    public Point getN2(){
        return this.n2;
    }

    public String toString(){
        return "( " + String.valueOf(this.getX()) + ", " + String.valueOf(this.getY()) + " )";
    }
}
