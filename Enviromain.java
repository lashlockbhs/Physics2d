import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.Timer;

public class Enviromain extends Canvas {
    private ArrayList<Shape> shapes;

    private ArrayList<Point> newShapeAr = new ArrayList<Point>();
    private final static int ONE_SECOND = 1000;
    private boolean newShape = false;
    public Enviromain() {
        this.shapes = new ArrayList<Shape>();

        addKeyListener(
                new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        keyPress(e);
                    }
                });

        addMouseListener(
                new MouseAdapter() {
                    public void mousePressed(MouseEvent evt) {
                        mouseClick(evt);
                    }
                });


        Timer timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(!newShape){
                    repaint();

                }
            }
        });

        timer.start();

    }

    public void paint(Graphics g) {
        
        for (Shape shape : shapes) {
            drawSegments(g, shape.getBulidSegments());
            drawShape(g, shape);

            g.setColor(Color.BLUE);
            g.drawOval((int)shape.getCenter().getX()-5, (int)shape.getCenter().getY()-5, 10, 10);

        }
    }

    public void mouseClick(MouseEvent evt) {
        if(evt.getButton() == MouseEvent.BUTTON1){
            leftClick(evt);
        }

    }
    public void keyPress(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            enterPress();
        }
    }

    public void leftClick(MouseEvent evt){
        Point newP;
        if(newShapeAr.size()>0){
            Point lastP = newShapeAr.get(newShapeAr.size()-1);
            newP = new Point(evt.getXOnScreen(), evt.getYOnScreen(), lastP, null);
            lastP.setN2(newP);
        }
        else{
            newP = new Point(evt.getXOnScreen(), evt.getYOnScreen(), null, null);
        }
        newShapeAr.add(newP);
    } 
    public void enterPress(){
        Point lastP = newShapeAr.get(newShapeAr.size()-1);
        Point firstP = newShapeAr.get(0);
        firstP.setN1(lastP);
        lastP.setN2(firstP);
        ArrayList<Point> newAr = new ArrayList<Point>();
        newAr.addAll(newShapeAr);


        shapes.add(new Shape(newAr));

        newShapeAr.clear();
    }

    public void drawSegments(Graphics g, ArrayList<Segment> segs){  
        for (Segment s : segs) {
            g.setColor(Color.PINK);
            g.drawLine((int)s.getp1().getX(), (int)s.getp1().getY(), (int)s.getp2().getX(), (int)s.getp2().getY());
        }
    }
    public void drawShape(Graphics g, Shape s){
        ArrayList<Point> points = s.getPoints();
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            g.setColor(Color.RED);
            g.drawOval((int)(p.getX()-1.5), (int)(p.getY()-1.5), 3, 3);
            g.setColor(Color.BLACK);

            if(i != points.size() - 1){

                Point p2 = points.get(i+1);
                g.drawLine((int)p.getX(), (int)p.getY(), (int)p2.getX(), (int)p2.getY());
            }
            else{

                Point p2 = points.get(0);
                g.drawLine((int)p.getX(), (int)p.getY(), (int)p2.getX(), (int)p2.getY());

            }
        }
    }

}
