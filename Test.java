public class Test {

    public static class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Point{" + "x=" + x + ", y=" + y + '}';
        }
    }

    public static class Segment {
        private Point start;
        private Point end;

        public Segment(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        public Point getStart() {
            return start;
        }

        public Point getEnd() {
            return end;
        }

        @Override
        public String toString() {
            return "Segment{" + "start=" + start + ", end=" + end + '}';
        }
    }

    public static class Vector {
        private Point start;
        private Point direction;

        public Vector(Point start, Point direction) {
            this.start = start;
            this.direction = direction;
        }

        public Point getStart() {
            return start;
        }

        public Point getDirection() {
            return direction;
        }

        public XY vectSegmentIntersect(Segment segment) {
            Point p0 = this.getStart();
            Point p1 = new Point(p0.getX() + this.getDirection().getX(), p0.getY() + this.getDirection().getY());
            Point p2 = segment.getStart();
            Point p3 = segment.getEnd();

            double s1_x = p1.getX() - p0.getX();
            double s1_y = p1.getY() - p0.getY();
            double s2_x = p3.getX() - p2.getX();
            double s2_y = p3.getY() - p2.getY();

            double s = (-s1_y * (p0.getX() - p2.getX()) + s1_x * (p0.getY() - p2.getY())) / (-s2_x * s1_y + s1_x * s2_y);
            double t = (s2_x * (p0.getY() - p2.getY()) - s2_y * (p0.getX() - p2.getX())) / (-s2_x * s1_y + s1_x * s2_y);

            if (s >= 0 && s <= 1 && t >= 0) {
                // Collision detected
                double i_x = p0.getX() + (t * s1_x);
                double i_y = p0.getY() + (t * s1_y);
                return new XY(i_x, i_y);
            }

            return null; // No collision
        }

        @Override
        public String toString() {
            return "Vector{" + "start=" + start + ", direction=" + direction + '}';
        }
    }

    public static class XY {
        private double x;
        private double y;

        public XY(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        @Override
        public String toString() {
            return "XY{" + "x=" + x + ", y=" + y + '}';
        }
    }
}