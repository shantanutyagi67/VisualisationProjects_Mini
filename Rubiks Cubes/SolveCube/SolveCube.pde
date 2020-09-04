import peasy.*;
class Pieces{
  float len,x,y,z;
  Pieces(float x,float y,float z,float len){
    this.x=x;
    this.y=y;
    this.z=z;
    this.len=len;
  }
  void showPiece(){
    float r= len/2;
    translate(x,y,z);
        beginShape();
        fill(#008000);//green//F
        vertex(-r,-r, r);
        vertex(-r, r, r);
        vertex( r, r, r);
        vertex( r,-r, r);
        endShape();
        beginShape();
        fill(#0000FF);//blue//B
        vertex(-r,-r,-r);
        vertex(-r, r,-r);
        vertex( r, r,-r);
        vertex( r,-r,-r);
        vertex(-r,-r,-r);//idk why it was not working for blue only
        endShape();
        beginShape();
        fill(#FF0000);//red//R
        vertex( r,-r,-r);
        vertex( r,-r, r);
        vertex( r, r, r);
        vertex( r, r,-r);
        endShape();
        beginShape();
        fill(#FFA500);//orange//L
        vertex(-r,-r,-r);
        vertex(-r,-r, r);
        vertex(-r, r, r);
        vertex(-r, r,-r);
        endShape();
        beginShape();
        fill(#FFFF00);//yellow//D
        vertex(-r, r,-r);
        vertex(-r, r, r);
        vertex( r, r, r);
        vertex( r, r,-r);
        endShape();
        beginShape();
        fill(#FFFFFF);//white//U
        vertex(-r,-r,-r);
        vertex(-r,-r, r);
        vertex( r,-r, r);
        vertex( r,-r,-r);
        endShape();
    translate(-x,-y,-z);
  }
}
PeasyCam cam;
int n =3;
Pieces cube[][][] = new Pieces[n][n][n];
float len =50;
float scale = 0.0005,scale2=0;

void setup(){
  size(1920,1080,P3D);
  cam = new PeasyCam(this,400);
  for(int i=0;i<n;i++){
    for(int j=0;j<n;j++){
      for(int k=0;k<n;k++){
        cube[i][j][k] = new Pieces(len*i-len/2.00*(n-1),len*j-len/2.00*(n-1),len*k-len/2.00*(n-1),len);  //make it generalised for n sided cube
      }
    }
  }
}
void draw(){
  background(255);
  stroke(0);
  strokeWeight(6);
  if(scale<=PI&&scale!=0)
    scale += 0.002;
   else{
     scale2+=0.005;
     scale=0;
   }    
  //if(scale>1){
  //  n--;
  //  scale=0;
  //}
  //for(int i=0;i<n;i++){
  //  for(int j=0;j<n;j++){
  //    for(int k=0;k<n;k++){
  //      cube[i][j][k] = new Pieces(len*i-len/2.00*(n-1),len*j-len/2.00*(n-1),len*k-len/2.00*(n-1),len);  //make it generalised for n sided cube
  //    }
  
  
  //  }
  //}
  rotateX(4*scale2);
  rotateY(-scale2);
  rotateZ(2*scale2);
  for(int i=0;i<n;i++){
    for(int j=0;j<n;j++){
      for(int k=0;k<n;k++){
        //translate(len*i-len,len*j-len,len*k-len);
        //box(len);
          rotateX(scale);
          rotateY(scale);
          rotateZ(scale);
        cube[i][j][k].showPiece();
        //translate(-len*i+len,-len*j+len,-len*k+len);
      }
    }
  }
}
