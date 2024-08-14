import java.util.ArrayList;

public class TriFunc {
    private Point p1;
    private Point p2;
    private Point p3;
    private Segment s1;
    private Segment s2;
    private Segment s3;

    private ArrayList<Segment> segments = new ArrayList<Segment>();
    private ArrayList<Point> points = new ArrayList<Point>();

    private boolean invalidTri;

    
    public TriFunc(Point p1, Point p2, Point p3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.s1 = new Segment(p1, p2);
        this.s2 = new Segment(p2, p3);
        this.s3 = new Segment(p3, p1);

        points.add(p1);
        points.add(p2);
        points.add(p3);

        segments.add(s1);
        segments.add(s2);
        segments.add(s3);


    }
    public TriFunc(boolean invalid){
        this.invalidTri = true;
    }
    public xy triangleCenter(){
        return new xy((p1.getX()+p2.getX()+p3.getX())/3, (p1.getY()+p2.getY()+p3.getY())/3);
    }
    public  double triArea(){
        return Math.abs(p1.getX()*(p2.getY() - p3.getY()) + p2.getX()*(p3.getY() - p1.getY()) + p3.getX()*(p1.getY() - p2.getY()))/2;
    }
    public ArrayList<Point> getPoints(){
        return this.points;
    }
    public ArrayList<Segment> getSegments(){
        return this.segments;
    }

    public void invalid(){
        this.invalidTri = true;
    }
    public boolean isInvalid(){
        return this.invalidTri;
    }

    public String toString(){
        return "{{{" + p1 + ", " + p2 + ", " + p3 + " Segments: " + s1 + ", " + s2 + ", " + s3 + "}}}";
    }
}
