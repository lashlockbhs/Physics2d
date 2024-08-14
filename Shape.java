import java.util.ArrayList;

public class Shape {
    private double x;
    private double y;
    private ArrayList<Point> points;
    private ArrayList<Segment> segments = new ArrayList<Segment>();
    private ArrayList<Segment> bulidSegments = new ArrayList<Segment>();

    private double area;
    private xy center;

    public Shape(ArrayList<Point> points) {
        this.points = points;
        this.CreateSegments();
        this.pointsToCenterMass();

    }

    public ArrayList<Point> getPoints() {
        return this.points;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    private void pointsToCenterMass() {
        ArrayList<Segment> totalSegments = new ArrayList<Segment>();
        totalSegments.addAll(segments);
        xy center = new xy(true);
        double area = 0;
        for (int i = 0; i < this.points.size(); i++) {
            for (int j = 0; j < this.points.size(); j++) {

                Point p1 = points.get(i);
                Point p2 = points.get(j);
                if (!checkSameSegment(p1, p2, totalSegments) && !p1.sameCords(p2)) {

                    Segment seg = new Segment(p1, p2);
                    Vect baseVect = new Vect(p1, new Point(seg.getRun(), seg.getRise(), null, null));

                    int rayVectL = rayCast(baseVect.GetRotated(0.0001));
                    int rayVectR = rayCast(baseVect.GetRotated(-0.0001));

                    boolean outsideS = (rayVectL % 2 == 0) || (rayVectR % 2 == 0);

                    boolean onTopOfSeg = (rayVectL % 2 == 0) != (rayVectR % 2 == 0);
                    if (!checkSegmentCollisons(seg, totalSegments) && !outsideS && !onTopOfSeg) {
                        ArrayList<TriFunc> tris = createTri(p1, p2, totalSegments);

                        if (tris.size() > 0) {
                            totalSegments.add(seg);

                            if (center.isInvalid()) {

                                center = tris.get(0).triangleCenter();
                                area = tris.get(0).triArea();

                                for (int k = 1; k < tris.size(); k++) {
                                    TriFunc tri2 = tris.get(k);
                                    center = twoAreaCenterCalc(center, tri2.triangleCenter(), area, tri2.triArea());
                                    area += tri2.triArea();
                                }
                            } else {
                                for (int k = 0; k < tris.size(); k++) {
                                    TriFunc tri2 = tris.get(k);
                                    center = twoAreaCenterCalc(center, tri2.triangleCenter(), area, tri2.triArea());
                                    area += tri2.triArea();
                                }
                            }
                        }
                    }
                }
            }
        }
        this.bulidSegments = totalSegments;
        this.area = area;
        this.center = center;
    }

    public ArrayList<Segment> getBulidSegments() {
        return this.bulidSegments;
    }

    public double getArea() {
        return this.area;
    }

    public xy getCenter() {
        return this.center;
    }

    private ArrayList<TriFunc> createTri(Point p1, Point p2, ArrayList<Segment> segments) { 
        ArrayList<TriFunc> tris = new ArrayList<TriFunc>();

        if (checkSameSegment(p1, p2, segments) || p1.sameCords(p2)) {
            return tris;
        }
        ArrayList<Point> ptS = commonPointSAR(p1, p2, segments);
        for (Point p3 : ptS) {
            tris.add(new TriFunc(p1, p2, p3));
        }
        return tris;
    }

    private boolean checkSameSegment(Point p1, Point p2, ArrayList<Segment> segments) {
        for (Segment s : segments) {
            if (s.countains(p1, p2)) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Point> commonPointSAR(Point p1, Point p2, ArrayList<Segment> segments) {
        ArrayList<Point> points = new ArrayList<Point>();
        ArrayList<Segment> p1S = segsOfPoint(p1, segments);
        ArrayList<Segment> p2S = segsOfPoint(p2, segments);

        for (Segment s1 : p1S) {
            for (Segment s2 : p2S) {
                Point p3 = s1.connecting(s2);
                if (!s1.connecting(s2).isInvalid()) {
                    points.add(p3);
                }
            }
        }
        return points;
    }

    private ArrayList<Segment> segsOfPoint(Point p1, ArrayList<Segment> segments) {
        ArrayList<Segment> finalS = new ArrayList<Segment>();
        for (Segment s : segments) {
            if (s.countains(p1)) {
                finalS.add(s);
            }
        }
        return finalS;
    }

    public boolean checkSegmentCollisons(Segment s1, ArrayList<Segment> segments) {// tested
        for (int i = 0; i < segments.size(); i++) {
            Segment s2 = segments.get(i);
            xy interP = s1.getLineIntersection(s2);

            if (((interP != null) && !(s1.equals(s2)))
                    && !((interP.sameCords(s1.getp1()) || interP.sameCords(s1.getp2())))) {
                return true;
            }
        }
        return false;
    }

    private xy twoAreaCenterCalc(xy center1, xy center2, double area1, double area2) {
        xy center = new xy((area1 * center1.getX() + area2 * center2.getX()) / (area1 + area2),
                (area1 * center1.getY() + area2 * center2.getY()) / (area1 + area2));
        return center;
    }

    public int rayCast(Vect vect) { // tested
        ArrayList<xy> collisons = new ArrayList<xy>();
        ArrayList<xy> invalidPoints = new ArrayList<xy>();

        for (Segment s : segments) {
            xy col = vect.vectSegmentIntersect(s, segments, invalidPoints);
            if (col != null) {
                collisons.add(col);
            }
        }
        Help.removeDuplicatePoint(collisons);
        collisons.removeIf(e -> e.cordsInArray(invalidPoints));// remove if point is invalid point
        return collisons.size();
    }

    private void CreateSegments() {
        for (int i = 0; i < this.points.size(); i++) {

            Point p1 = this.points.get(i);
            this.segments.add(new Segment(p1, p1.getN2()));
        }
    }

}
