import java.util.ArrayList;

public class Segment {
    private Point p1;
    private Point p2;
    private double rise;
    private double run;

    public Segment(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;

        this.rise = p2.getY()-p1.getY();
        this.run = p2.getX()-p1.getX();

        
    }

    
    public boolean checkSameSlope(Segment s){
        return ((s.getRise() == this.getRise()) && (s.getRun() == this.getRun())) || (s.getRise()/s.getRun() == this.getRise()/this.getRun());
    }
    public Segment checkAllSlope(ArrayList<Segment> sAr){
        for (Segment s : sAr) {
            if(this.checkSameSlope(s)){
                return s;
            }
        }
        return null;
    }
    public xy getLineIntersection(Segment seg2) {
        Point p0 = this.getp1();
        Point p1 = this.getp2();
        Point p2 = seg2.getp1();
        Point p3 = seg2.getp2();

        double s1_x = p1.getX() - p0.getX();
        double s1_y = p1.getY() - p0.getY();
        double s2_x = p3.getX() - p2.getX();
        double s2_y = p3.getY() - p2.getY();

        double s = (-s1_y * (p0.getX() - p2.getX()) + s1_x * (p0.getY() - p2.getY())) / (-s2_x * s1_y + s1_x * s2_y);
        double t = (s2_x * (p0.getY() - p2.getY()) - s2_y * (p0.getX() - p2.getX())) / (-s2_x * s1_y + s1_x * s2_y);

        if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
            // Collision detected
            double i_x = p0.getX() + (t * s1_x);
            double i_y = p0.getY() + (t * s1_y);
            return new xy(i_x, i_y);
        }

        return null; // No collision
    }


    public Point getp1(){
        return this.p1;
    }

    public Point getp2(){
        return this.p2;
    }

    public double getRise(){
        return this.rise;
    }

    public double getRun(){
        return this.run;
    }

    public boolean countains(xy p1){
        if(p1.sameCords(this.getp1())){
            return true;
        }
        else if(p1.sameCords(this.getp2())){
            return true;
        }
        return false;
    }

    public boolean countains(xy p1, xy p2){
        if(countains(p1) && countains(p2)){
            return true;
        }
        return false;
    }

    public Point connecting(Segment s){
        if(countains(s.getp1())){
            return s.getp1();
        }
        if(countains(s.getp2())){
            return s.getp2();
        }
        return new Point(true);
    }


    public double getSlope(){
        return this.rise/this.run;
    }

    public double getInverseSlope(){
        return this.run/this.rise;
    }

    public double getLength(){
        return Math.sqrt(Math.pow(this.run,2)+Math.pow(this.rise, 2));
    }

    public Point otherPoint(Point p){
        return p.sameCords(this.getp1()) ? this.getp2() : this.getp1();
    }


    public String toString(){
        return "From " + this.p1 + " to " + this.p2 + " Rise, Run: " + String.valueOf(getRise()) + ", " + String.valueOf(getRun());
    }
}
