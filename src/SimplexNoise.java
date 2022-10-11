/**Class for creating simplex noise(based on what was developed by Perlin)
 * Uses 2D polytopes, ie, Triangles, instead of 2D hypercubes, ie, Squares
 * @author AKASHNEELRC
 */
public class SimplexNoise {    

//array of a random permutation of numbers from 0-255 
    private static int p[] = {151,160,137,91,90,15,
 131,13,201,95,96,53,194,233,7,225,140,36,103,30,69,142,8,99,37,240,21,10,23,
 190, 6,148,247,120,234,75,0,26,197,62,94,252,219,203,117,35,11,32,57,177,33,
 88,237,149,56,87,174,20,125,136,171,168, 68,175,74,165,71,134,139,48,27,166,
 77,146,158,231,83,111,229,122,60,211,133,230,220,105,92,41,55,46,245,40,244,
 102,143,54, 65,25,63,161, 1,216,80,73,209,76,132,187,208, 89,18,169,200,196,
 135,130,116,188,159,86,164,100,109,198,173,186, 3,64,52,217,226,250,124,123,
 5,202,38,147,118,126,255,82,85,212,207,206,59,227,47,16,58,17,182,189,28,42,
 223,183,170,213,119,248,152, 2,44,154,163, 70,221,153,101,155,167, 43,172,9,
 129,22,39,253, 19,98,108,110,79,113,224,232,178,185, 112,104,218,246,97,228,
 251,34,242,193,238,210,144,12,191,179,162,241, 81,51,145,235,249,14,239,107,
 49,192,214, 31,181,199,106,157,184, 84,204,176,115,121,50,45,127, 4,150,254,
 138,236,205,93,222,114,67,29,24,72,243,141,128,195,78,66,215,61,156,180};
    private static int perm[]=new int[512];
    static{
        for(int i=0;i<512;i++)
            perm[i]=p[i&255];
    }
    
    private static int ffloor(double n){
        return n>0 ? (int)n : (int)n-1; 
    }
    
    private static int[][]grad2= new int[][]{
        {0,1},{1,0},{0,-1},{-1,0},{-1,-1},{1,1},{-1,1},{1,-1}
    };
    
    /**sets the static pseudorandom array of permuations of 0-255 to the array provided
     *throws exception when the array is not 256 ints long
     */
    public static void setpseudorandom(int[] newperm) throws Exception{
        if(newperm.length==256){
            for(int i=0;i<512;i++)
            {perm[i]=p[i&255];}
        }
        else{
            throw new Exception("invalid permutation array length");
        }
    }
    
    private static double twoddot(int g[], double x, double y){
        return g[0]*x + g[1]*y;
    }
    
    public static double simNoise(double xin, double yin){
        /**Transform the input coords to place them on the desired lattice (coordinate skewing)
         * to find the desired cell origin coords
         */
        double Fs= 0.3660254038D*(xin+yin);
        int i1=ffloor(xin+Fs);
        int j1=ffloor(yin+Fs);
        //finding the unskewed origin coords, for Kernel summation(basically gradient calculation)
        double Gs=(i1+j1)*0.2113248654D;
        double xu=i1-Gs;
        double yu=j1-Gs;
        //array of offsets from the 3 corners
        double[][] offset=new double[3][2];
        /**the internal coordinates, ie, from the origin of the honeycomb cell
        *(rhombus made from 2 equilateral triangles(simplices)) are calculated now
        */
        offset[0][0]=xin-xu;
        offset[0][1]=yin-yu;
        //determine the offset of the middle corner(2) and last corner(3) of the simplex
        int x2=0,y2=0;
        if(xu>yu){
            x2=1;
            offset[1][0]=offset[0][0]-1.0D+0.3660254038D;
            offset[1][1]=offset[0][1]+0.3660254038D;
        }
        else{
            y2=1;
            offset[1][0]=offset[0][0]+0.3660254038D;
            offset[1][1]=offset[0][1]-1.0D+0.3660254038D;
        }
        offset[2][0]=offset[0][0]-1.0D+0.7320508076D;
        offset[2][1]=offset[0][1]-1.0D+0.7320508076D;
        //Work out the hashed gradient indices of the three simplex corners
        int x11= i1 &255;
        int y11= j1 &255;
        
        int[]gi=new int[3];
        gi[0]= perm[x11+perm[y11]] % 8;
        gi[1]= perm[x11+x2+perm[x11+y2]] % 8;
        gi[2]= perm[x11+1+perm[y11+1]] % 8;
        
        double ni=0,t;
        for(int a=0; a<3; a++){
            t= 0.5D- (offset[a][0]*offset[a][0])-(offset[a][1]*offset[a][1]);
            if(t>=0){
                t*=t;
                ni+=t*t*twoddot(grad2[gi[a]],offset[a][0],offset[a][1]);
            }
        }
        double res= (ni/0.0248619261D);
        if(res>1)
            return 1.0D;
        else if(res<-1)
            return -1.0D;
        else
            return res;
    }
}
