public class Matrix {
    int row, column;
    private float[][] matrix;
    
    Matrix(int r, int c){
        matrix=new float[r][c];
        row=r;
        column=c;
    }
    
    /**sets the matrix of the Matrix object to the 2D float array inp**/
    public void setMatrix(float[][] inp){
        matrix=inp;
    }
    
    /**sets the value of the given array to the matrix of the Matrix object**/
    public void getArray(float[][]arr){
        arr=matrix;
    }
    /**returns the matrix of the Matrix object as a 2D float array**/
    public float[][] returnArray(){
        return matrix;
    }
    
    /**checks if the matrix of the Matrix object is a square matrix**/
    public boolean isSquare(){
        if(this.row==this.column)
            return true;
        else
            return false;
    }
    
    /**Set the matrix of the Matrix object calling this function to an Identity matrix with order same as that of the Matrix object
    if not square matrix, throws exception**/ 
    
    public void IdentityMatrix() throws Exception{
        if(this.isSquare()){//check if square matrix
        for(int i=0;i<this.row;i++){
            for(int j=0;j<this.column;j++){
                if(i==j)
                    matrix[i][j]=1;//set diagonal elements to 1
            }
        }}
        else
        {
             throw new Exception("not a square matrix");
        }
    }
    
    /**Set element at specific position(at argument row r and column c)
     Uses standard matrix notation, i.e., the first entry is (1,1)**/
    public void setElement(int r, int c, float element){//sets specific element
        matrix[r-1][c-1]=element;
    }
    
    /**Set element at specific position(at argument row r and column c)
     Uses java array notation, i.e., the first entry is (0,0)**/
    public void setElementx(int r, int c, float element){//sets specific element
        matrix[r][c]=element;
    }
    
    /**returns a Matrix object with the matrix which is the transpose of the matrix of the Matrix object calling the function**/ 
    public Matrix getTranspose(){
        Matrix trans=new Matrix(this.column,this.row);
        for(int i=0; i<this.row;i++){
            for(int j=0; j<this.column;j++){
                trans.matrix[j][i]=this.matrix[i][j];
            }
        }
        return trans;
    }
    
    /**Multiply the matrix of the Matrix object calling it, by the matrix of the Matrix object in the arguments, in said order
     Make sure the column size of the first Matrix matches the row size of the second, otherwise exception will be thrown**/
    public Matrix multipliedBy(Matrix b) throws Exception{
        Matrix prod;
        if(this.column==b.row){
            prod= new Matrix(this.row,b.column);
            for(int i=0;i<prod.row; i++){
                for(int j=0;j<prod.column;j++){
                    float intd=0f;
                    for(int k=0; k<this.column; k++)
                    {
                        if(this.matrix[i][k]!=0&&b.matrix[k][j]!=0)
                        {intd+=this.matrix[i][k]*b.matrix[k][j];}
                    }
                    prod.matrix[i][j]=intd;
                }
            }
        }
        else{
            throw new Exception("invalid matrix multiplication");
        }
        return prod;
    }
    
    /**multiply the contents of the Matrix object calling it by the given scalar b**/
    public void multiplybyScalar(float b){
        for(int i=0;i<this.row;i++){
            for(int j=0;j<this.column;j++)
            {
                this.matrix[i][j]=(this.matrix[i][j])*b;
            }
        }
    }

    private Matrix minorMatrix(int r, int c){
        Matrix minor=new Matrix(this.row-1,this.column-1);
        int ii=0,jj=0;
        for(int i=0;i<this.row;i++){
            if(i==r){
                continue;
            }
            jj=0;
            for(int j=0;j<this.column;j++)
            {
                if(j==c){
                    continue;
                }
                minor.matrix[ii][jj]=this.matrix[i][j];
                jj++;
            }
            ii++;
        }
        return minor;
    }
    
    private float getDeterminant2(int i, int j){
        float det2=(this.matrix[0][0]*this.matrix[1][1])-(this.matrix[1][0]*this.matrix[0][1]);
        if((i+j)%2==0)
        return det2;
        else{
            det2=-det2;
            return det2;
        }
    }
    /**recursively generate the determinant of the given square matrix
     Calling it using a non-square matrix will throw exception**/
    public float getDeterminant() throws Exception{
        if(this.isSquare()){
            if(this.row==2){
                return this.getDeterminant2(0,0);
            }
            else if(this.row>2){
                float fl=0f;
                for(int i=0;i<this.column;i++){
                    fl+= this.matrix[0][i]*(this.minorMatrix(0, i).getDeterminant())*(Math.pow(-1, i));
                }
                return fl;
            }
            else{
                return 0f;
            }
        }
        else{
            throw new Exception("not a square matrix");
        }
    }
}
