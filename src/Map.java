
import static Test.image;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Map {
    int[] pixel= Noises.PerlinNoise(700, 700, 4, 70, 2, 0.5F,3);
    int[] pixels= Noises.fademaskease(700, 700, pixel);
    Color sea=new Color(10,155,190);
    Color lsea=new Color(65,200,225);
    Color sand=new Color(250,235,190);
    Color grass=new Color(60,210,60);
    Color hgrass=new Color(25,160,25);
    Color hill=new Color(140,90,35);
    Color snow=new Color(230,240,240);
    for(int i=0; i<700*700; i++){
        if((pixels[i]&255)<=115){pixels[i]=sea.getRGB();}
        else if((pixels[i]&255)>115 && (pixels[i]&255)<= 120){pixels[i]=lsea.getRGB();}
        else if((pixels[i]&255)>120 && (pixels[i]&255)<= 125){pixels[i]=sand.getRGB();}
        else if((pixels[i]&255)>125 && (pixels[i]&255)<= 135){pixels[i]=grass.getRGB();}
        else if((pixels[i]&255)>135 && (pixels[i]&255)<= 142){pixels[i]=hgrass.getRGB();}
        else if((pixels[i]&255)>142 && (pixels[i]&255)<= 155){pixels[i]=hill.getRGB();}
        else if((pixels[i]&255)>155 && (pixels[i]&255)<= 255){pixels[i]=snow.getRGB();}
    }
    BufferedImage image=new BufferedImage(700,700,BufferedImage.TYPE_INT_RGB);
    image.setRGB(0, 0, image.getHeight(), image.getWidth(), pixels, 0, image.getWidth());
    File noise=new File("heightmap1.png");
    try {
        ImageIO.write(image, "png", noise);
    } 
    catch (IOException ex) {
        ex.printStackTrace();
    }
}
