public class PerlinNoise{
    private static float interpolate(float a1, float a2, float w){
        if(w<0){return a1;}
        else if(w>1){return a2;}
        else{
            return (float)((a2 -a1)*((w *(w *6.0 -15.0) +10.0) *w *w *w) + a1);
        }
    }
    class vec2{
        float x,y;
        vec2(){
            x=0;
            y=0;
        }
        vec2(float xin, float yin){
            x=xin;
            y=yin;
        }
    }
    static int[] p=new int[]{
      151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 
      103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 
      26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 
      87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 
      77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 
      46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 
      187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 
      198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 
      255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 
      170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 
      172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 
      104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 
      241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 
      157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 
      93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180
    };
    
    private static int perm[]=new int[512];
    static{
        for(int i=0;i<512;i++)
            perm[i]=p[i&255];
    }
    
    static int[][] grad2=new int[][]{
        {0,1},{1,0},{0,-1},{-1,0},{-1,-1},{1,1},{-1,1},{1,-1}
    };
    
    private static float dot(int[]g, float ox, float oy){
        return (g[0]*ox)+(g[1]*oy);
    }
    
    public static float noise(float xin, float yin){
        int x0=(int)xin;
        int x1=x0+1;
        int y0=(int)yin;
        int y1=y0+1;
        //the four corners are (x0,y0),(x0,y1),(x1,y1),(x1,y0)
        
        float ox0=xin-x0;
        float oy0=yin-y0;
        float ox1=ox0-1;
        float oy1=oy0-1;
        
        int x00= x0&255;
        int y00= y0&255;
        
        int g1= perm[x00+perm[y00]]%8;
        int g2= perm[x00+perm[y00+1]]%8;
        int g3= perm[x00+1+perm[y00+1]]%8;
        int g4= perm[x00+1+perm[y00]]%8;
        
        float d1=dot(grad2[g1],ox0,oy0);
        float d2=dot(grad2[g4],ox1,oy0);
        float n1=interpolate(d1,d2,ox0);
        
        d1=dot(grad2[g2],ox0,oy1);
        d2=dot(grad2[g3],ox1,oy1);
        float n2=interpolate(d1,d2,ox0);
        
        float nf=interpolate(n1,n2,oy0);
        nf=nf/1.9488549F;
        return nf;
    }
}