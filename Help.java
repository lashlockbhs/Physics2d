import java.util.ArrayList;

public class Help {
    public static int removeDuplicatePoint(ArrayList<xy> ar){
        if(ar.size() == 0){
            return 0;
        }
        int n = 0;
        for (int i = 0; i < ar.size(); i++) {
            xy p1 = ar.get(i);
            for (int j = i+1; j < ar.size(); j++) {
                xy p2 = ar.get(j);
                if(p1.sameCords(p2)){
                    ar.remove(j);
                    j--;
                    i--;
                    n++;
                }
            }
        }
        return n;
    }


    public static int removeDuplicateTri(ArrayList<TriFunc> ar){
        if(ar.size() == 0){
            return 0;
        }
        int n = 0;
        
        for (int i = 0; i < ar.size(); i++) {
            TriFunc tri1 = ar.get(i);

            ArrayList<Point> points = tri1.getPoints();
            Point p11 = points.get(0);
            Point p12 = points.get(1);
            Point p13 = points.get(2);
            
            
            for (int j = i+1; j < ar.size(); j++) {
                TriFunc tri2 = ar.get(j);
                ArrayList<Point> points2 = tri2.getPoints();
                Point p21 = points2.get(0);
                Point p22 = points2.get(1);
                Point p23 = points2.get(2);
                
                if((p11.sameCords(p21) && p12.sameCords(p22)) && p13.sameCords(p23)){
                    ar.remove(j);
                    j--;
                    i--;
                    n++;
                }
            }
        }
        return n;
    }


    
    public static double angleBetweenSegment(Segment s1, Segment s2){
        Double s1L = s1.getLength();//b
        Double s2L = s2.getLength();//c
        Point commonPoint = s1.connecting(s2);
        
        Segment s3 = new Segment(s1.otherPoint(commonPoint), s2.otherPoint(commonPoint));//a
        double s3L = s3.getLength();

        return Math.acos((Math.pow(s1L, 2) + Math.pow(s2L, 2) - Math.pow(s3L, 2))/(2*s1L*s2L));//
    }
}
