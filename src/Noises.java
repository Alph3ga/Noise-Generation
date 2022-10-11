
/**
 *This is the class that combines different Noise algorithm to produce an usable output
 * @author AKASHNEELRC
 */
public class Noises {
    static int[] PerlinNoise(int width, int height, int octaves, float scale, float lacunarity, float persistence, float offset){
        float map[][]=new float[height][width];
        float amp=1;
        if(scale==0){scale=0.00003F;}
        float max=0;
        for(int o=0; o<octaves; o++){
            for(int y=0; y<height; y++){
                for(int x=0; x<width; x++){
                    map[y][x]+=(PerlinNoise.noise((x/scale)+offset, (y/scale)+offset))*amp;
                }
            }
            scale=scale/lacunarity;
            max+=amp;
            amp*=persistence;
        }
        int[] pixels=new int[width*height];
        int rgb;
        for(int y=0; y<height; y++){
                for(int x=0; x<width; x++){
                    map[y][x]=(map[y][x]+max)/(2*max);
                    rgb=(int)(255*map[y][x]);
                    pixels[y*width+x]=(256*256*rgb+256*rgb+rgb);
                }
                }
        return pixels;
    }
    
    public static int[] fademaskease(int width, int height, int[] picture){
        int[] pixels=new int[width*height];
        float fd;
        float d;
        int rgb;
        float dn= (float)Math.sqrt(width*width+height*height)/2;
        for(int i=0; i<width*height; i++){
            d=(float)(Math.sqrt(((int)(i/width)-(height/2))*((int)(i/width)-(height/2))+((i%width)-(width/2))*((i%width)-(width/2)))/dn);
            fd=1-(float)((d *(d *6.0 -15.0F) +10.0F) *d *d *d);
            rgb=(int)((picture[i]&255)*fd);
            pixels[i]=(256*256*rgb+256*rgb+rgb);
    }
        return pixels;
    }
    
    public static int[] fademaskeaseWider(int width, int height, int[] picture){ //this one is taylor series for ((10^x)-1)/9
        int[] pixels=new int[width*height];
        float fd;
        float d;
        int rgb;
        float dn= (float)Math.sqrt(width*width+height*height)/2;
        for(int i=0; i<width*height; i++){
            d=(float)(Math.sqrt(((int)(i/width)-(height/2))*((int)(i/width)-(height/2))+((i%width)-(width/2))*((i%width)-(width/2)))/dn);
            fd=1-(float)(d*(0.25584F+d*(0.29455F+d*(0.22608F+d*0.13014F))));
            rgb=(int)((picture[i]&255)*fd);
            pixels[i]=(256*256*rgb+256*rgb+rgb);
    }
        return pixels;
    }
    
        public static int[] newfademaskease(int width, int height, int[] picture){ //this one is a polynomial i found
        int[] pixels=new int[width*height];
        float fd;
        float d;
        int rgb;
        float dn= (float)Math.sqrt(width*width+height*height)/2;
        for(int i=0; i<width*height; i++){
            d=(float)(Math.sqrt(((int)(i/width)-(height/2))*((int)(i/width)-(height/2))+((i%width)-(width/2))*((i%width)-(width/2)))/dn);
            d=1-d;
            fd=(float)(d*(d*d*(d-2)+2));
            rgb=(int)((picture[i]&255)*fd);
            pixels[i]=(256*256*rgb+256*rgb+rgb);
    }
        return pixels;
    }
    
    public static int[] combineBW(int h, int w, int[] pixels1, int[] pixels2){
        int[] pixels=new int[h*w];
        float r1, r2, rf;
        int rgb;
        for(int i=0; i<h*w; i++){
            r1=(pixels1[i]*2)-1.0F;
            r2=(pixels2[i]*2)-1.0F;
            rf=(((r1+r2)/2)+1.0F)/2;
            rgb=(int)(255*rf);
            pixels[i]=(256*256*rgb+256*rgb+rgb);
        }
        return pixels;
    }
}
