
import java.awt.*;
import javax.swing.JFrame;


public class mainRender extends Canvas{

    public void paint(Graphics g){
        g.setColor(Color.RED);

        g.fillOval(0, 0, 10, 10);
    }
    public static void main(String[] args){
        JFrame jf = new JFrame("Physics");

        mainRender main = new mainRender();
        jf.setSize(600, 600);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(main);
         
    }
}