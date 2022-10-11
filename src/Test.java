
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Test {
static int x=1024, y=1024;
static BufferedImage image;
    public static void main(String[] args) {
    Image bigCastle= new ImageIcon("src\\bigCastle.png").getImage();
    Image smallCastle= new ImageIcon("src\\smallCastle.png").getImage();
    Image hut= new ImageIcon("src\\hut.png").getImage();
    Random rnd=new Random();
    int[] pixel= Noises.PerlinNoise(x, y, 8, 200, 2, 0.65F,3.2f);
    int[] pixels= Noises.newfademaskease(x, y, pixel);
    ArrayList<Integer> landp= new ArrayList<>();
    ArrayList<Integer> beachp= new ArrayList<>();
    for(int i=0; i<x*y; i++){
        if((pixels[i]&255)>125 && (pixels[i]&255)<= 142){
            if((rnd.nextInt()&63)==10){landp.add(i);}
        }
        if((pixels[i]&255)>120 && (pixels[i]&255)<= 130){
            if((rnd.nextInt()&15)==10){beachp.add(i);}
        }
    }
    Color sea=new Color(10,155,190);
    Color lsea=new Color(65,200,225);
    Color sand=new Color(250,235,190);
    Color grass=new Color(60,210,60);
    Color hgrass=new Color(25,160,25);
    Color hill=new Color(140,90,35);
    Color snow=new Color(230,240,240);
    for(int i=0;i<x*y; i++){
        if((pixels[i]&255)<=115){pixels[i]=sea.getRGB();}
        else if((pixels[i]&255)>115 && (pixels[i]&255)<= 120){pixels[i]=lsea.getRGB();}
        else if((pixels[i]&255)>120 && (pixels[i]&255)<= 125){pixels[i]=sand.getRGB();}
        else if((pixels[i]&255)>125 && (pixels[i]&255)<= 135){pixels[i]=grass.getRGB();}
        else if((pixels[i]&255)>135 && (pixels[i]&255)<= 142){pixels[i]=hgrass.getRGB();}
        else if((pixels[i]&255)>142 && (pixels[i]&255)<= 155){pixels[i]=hill.getRGB();}
        else if((pixels[i]&255)>155 && (pixels[i]&255)<= 255){pixels[i]=snow.getRGB();}
    }
    image=new BufferedImage(x,y,BufferedImage.TYPE_INT_RGB);
    image.setRGB(0, 0, image.getHeight(), image.getWidth(), pixels, 0, image.getWidth());
    
    /**Graphics2D g= image.createGraphics();
    
    for(int i=0; i<2; i++){
        int j= landp.get(rnd.nextInt()&landp.size());
        int x,y;
        y=(int)(j/700);
        x=(int)(j%700);
        g.drawImage(bigCastle, x, y, null);
    }
    **/
    File noise=new File("newworldmap2.png");
    try {
        ImageIO.write(image, "png", noise);
    } catch (IOException ex) {
        ex.printStackTrace();
    
    }
}
}
