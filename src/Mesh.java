
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Mesh {

    /**Class for vec3Ds, a position vector on the 3D euclidean space**/
    public class vec3D{
        float x,y,z;
        /**creates a position vector from the origin to the given coordinates**/
        vec3D(float xcoor,float ycoor, float zcoor){
            x=xcoor;
            y=ycoor;
            z=zcoor;
        }
        
        /**scales the vec3D by the scalar b**/
        public void scaleBy(float b){
            this.x=x*b;
            this.y=y*b;
            this.z=z*b;
        }
        
        /**returns the dot product of the 2 vec3d a and b**/
        public float dotProduct(vec3D a, vec3D b){
            float dp= a.x*b.x + a.y*b.y + a.z*b.z;
            return dp;
        }
        
        /**returns a vec3D which is the cross product of the 2 provided vec3Ds**/
        public vec3D crossProduct(vec3D a, vec3D b){
            float xp= a.y*b.z - a.z*b.y;
            float yp= a.z*b.x - a.x*b.z;
            float zp= a.x*b.y - a.y*b.x;
            return new vec3D(xp, yp, zp);
        }
        
        /**returns a 3x1 Matrix object corresponding to the vec3D calling this method**/
        public Matrix getMatrix(){
            Matrix mtx=new Matrix(3,1);
            mtx.setElementx(0, 0, this.x);
            mtx.setElementx(1, 0, this.y);
            mtx.setElementx(2, 0, this.z);
            return mtx;
        }
        
        /**returns resultant of 2 vec3Ds**/
        public vec3D add(vec3D a, vec3D b){
            return new vec3D(a.x+b.x,a.y+b.y,a.z+b.z);
        }
        
        /**subtracts vec3D b from vec3D a "(a-b)" and returns the resultant vec3D**/
        public vec3D subtract(vec3D a, vec3D b){
            return new vec3D(a.x-b.x,a.y-b.y,a.z-b.z);
        }
        
        /**returns the length of the vec3D calling this method**/
        public float length(){
            float fl= this.x*this.x + this.y*this.y + this.z*this.z;
            return (float)Math.sqrt(fl);
        }
        
        /**returns the angle between the vec3D calling this method and the vec3D provided, in radians, between 0.0 to pi**/
        public float angleWith(vec3D b){
            float costheta= (dotProduct(this,b))/(this.length()*b.length());
            return (float)Math.acos(costheta);
        }
    }
    
    public class Triangle{
        /**vertices of the triangle, initialized upon creating an Triangle object**/
        vec3D[] points;
        /**initialize a triangle with 3 given points, clockwise**/
        Triangle(vec3D a, vec3D b, vec3D c){
            points= new vec3D[]{a,b,c};
        }
        /**initialize a triangle with all vertices at origin**/
        Triangle(){
            points= new vec3D[3];
        }
        /**replaces the vertex of the triangle at given serial number(1-3) with the given vec3D**/
        public void setPoints(vec3D pnt, int srnum){
            this.points[srnum-1]=pnt;
        }
        /**sets the vertices of the triangle to the given 3 vec3Ds in the array**/
        public void setPoints(vec3D[] vrtx){
            this.points=vrtx;
        }
        /**sets the vertices of the triangle to the given 3 vec3Ds, in the same order**/
        public void setPoints(vec3D a, vec3D b, vec3D c){
            this.points[0]=a;
            this.points[1]=b;
            this.points[2]=c;
        }
    }
    //Class Mesh starts here
    /**Synchronized List wrapper of ArrayList of all the triangles in the mesh**/
    private List mesh;
    
    /**Create a Mesh and initialize its arraylist of Triangles**/
    Mesh(){
        this.mesh = Collections.synchronizedList(new ArrayList<Triangle>());
    }
    /**Create a Mesh and initialize its arraylist of Triangles to the given ArrayList of Triangles**/
    Mesh(ArrayList<Triangle> list){
        this.mesh = Collections.synchronizedList(list);
    }
    
    /**adds the Triangle to the end of the Mesh arraylist**/
    public void addTriangle(Triangle trn){
        this.mesh.add(trn);
    }
    
    /**adds the Triangle to the given index of the Mesh arraylist**/
    public void addTriangle(Triangle trn, int n){
        this.mesh.add(n, mesh);
    }
    
    /**removes the Triangle at the index n**/
    public void removeTriangle(int n){
        this.mesh.remove(n);
    }
    
    /**removes the Triangle from the arraylist**/
    public void removeTriangle(Triangle trn){
        this.mesh.remove(trn);
    }
    
    /**Returns the synchronized list wrapper of the arraylist of the Mesh calling this method**/
    public List getMesh(){
        return this.mesh;
    }
}
