
import javax.swing.JFrame;

public class mainRender{

    public static void main(String[] args) {
        JFrame jf = new JFrame("Physics");
        Enviromain main = new Enviromain();
        jf.setSize(600, 600);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(main);


    }
}