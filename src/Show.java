
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Show {
    Render.vec2d[] points;
    public static void main(String[] args){
        float[][] mesh=new float[][]{{1,0,0},{0,0,0},{0,0,1}};
    }
    class Frame extends JFrame{
        Frame(){
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Tryout");
            this.setVisible(true);
    }
    class Panel extends JPanel{
        Panel(){
            this.setPreferredSize(new Dimension(600,600));
            this.setBackground(Color.black);
        }
        public void paint(Graphics g){
            super.paint(g);
            drawit(g);
        }
        public static void drawit(g){
            
        }
    }
}
}