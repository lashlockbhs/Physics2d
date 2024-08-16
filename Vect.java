import java.util.ArrayList;

public class Vect {
    private Point p1;
    private Point direction;

    public Vect(Point p1, Point direction) {
        this.p1 = p1;
        this.direction = direction;
    }

    public boolean checkParalell(Segment s){
        return s.getRise() / s.getRun() == this.direction.getX() / this.direction.getY();
    }

    public xy vectSegmentIntersect(Segment segment, ArrayList<Segment> sAr,  ArrayList<xy> invalid) {

        Point p0 = this.getP1();
        Point p1 = new Point(p0.getX() + this.getDirection().getX(), p0.getY() + this.getDirection().getY(), null,null);
        Point p2 = segment.getp1();
        Point p3 = segment.getp2();

        if (segment.countains(p0)) {
            return null; // No intersection if the vector starts on the segment
        }
        if (checkParalell(segment)) {
            invalid.add(p0);
            return null;
        }

        double s1_x = p1.getX() - p0.getX();
        double s1_y = p1.getY() - p0.getY();
        double s2_x = p3.getX() - p2.getX();
        double s2_y = p3.getY() - p2.getY();

        double s = (-s1_y * (p0.getX() - p2.getX()) + s1_x * (p0.getY() - p2.getY())) / (-s2_x * s1_y + s1_x * s2_y);
        double t = (s2_x * (p0.getY() - p2.getY()) - s2_y * (p0.getX() - p2.getX())) / (-s2_x * s1_y + s1_x * s2_y);

        if (s >= 0 && s <= 1 && t >= 0) { // Removed upper limit check for t since it's a vector

            // Collision detected
            double i_x = p0.getX() + (t * s1_x);
            double i_y = p0.getY() + (t * s1_y);
            xy colP = new xy(i_x, i_y);
            if (!colP.sameCords(p0)) {
                return new xy(i_x, i_y);
            }

        }

        return null; // No collision
    }

    public Vect GetRotated(double angle) {
        double rad = Math.toRadians(angle); // Convert angle to radians
        double cosTheta = Math.cos(rad);
        double sinTheta = Math.sin(rad);

        double x_d = this.direction.getX();
        double y_d = this.direction.getY();

        double x_d_new = x_d * cosTheta - y_d * sinTheta;
        double y_d_new = x_d * sinTheta + y_d * cosTheta;

        Point newDir = new Point(x_d_new, y_d_new, null, null);

        return new Vect(p1, newDir);
    }

    public Point getP1() {
        return this.p1;
    }

    public Point getDirection() {
        return this.direction;
    }

    public String toString() {
        return "Start: " + getP1() + " Dir: " + getDirection();
    }
}
