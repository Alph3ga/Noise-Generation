public class Render {
    float aspectr, zq, fov, znear;
    
    Render(int screenheight, int screenwidth, float fovAngle, float Znear, float Zfar){
        aspectr=screenwidth/screenheight;
        fov=1/((float)Math.tan(fovAngle/2));
        zq=Zfar/(Zfar-Znear);
        znear=Znear;
    }
    
    public class vec2d{
        float x,y;
        vec2d(){
            
        }
        vec2d(float xin, float yin){
            x=xin;
            y=yin;
        }
    }
    
    public void transform(float x, float y, float z, float[] out){
        out[0]=aspectr*fov*x/z;
        out[1]=fov*y/z;
        out[2]=(z-znear)*zq;
        out[3]=z;
    }
    
    public void transform(Mesh.vec3D inp, vec2d out){
        out.x=aspectr*fov*inp.x/inp.z;
        out.y=fov*inp.y/inp.z;
    }
    
}
