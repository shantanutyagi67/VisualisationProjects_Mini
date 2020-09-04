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
        if(x==0&&y==0&&z==0){
          rotateX(PI/2);
          translate(-0.5*len+2.5,2*len+7.5,1.51*len);
          //image(img, 0, -2.5*len);
          translate(0.5*len-2.5,-2*len-7.5,-1.51*len);
          rotateX(-PI/2);
        }
    translate(-x,-y,-z);
  }
}
PeasyCam cam;
PImage img,img2;
Pieces cube[][][] = new Pieces[3][3][3];
float len =50;
float scale = 0.0001, scale1 = 0;
void setup(){
  size(1920,1080,P3D);
  cam = new PeasyCam(this,1000);
  img = loadImage("ccd1.png");
  //translate(width/2,height/2);
  for(int i=0;i<3;i++){
    for(int j=0;j<3;j++){
      for(int k=0;k<3;k++){
        cube[i][j][k] = new Pieces(len*i-len,len*j-len,len*k-len,len);
      }
    }
  }
}
void draw(){
  background(255);
  stroke(0);
  strokeWeight(2);
  //fill(255);
  //textSize(15);
  //text("@geekSA67",120,150);
  //translate(0,0,-5*len);
  img2 = loadImage("logo1.png");
  //translate(0,0,5*len);
  if(scale<=3.14&&scale!=0)
    scale += 0.003;
   else{
     scale1+=0.05;
     scale=0;
   }    
  image(img2, -385, -320);
  translate(0,-60,120);
  rotateX(scale1);
  rotateY(scale1);
  rotateZ(scale1);
  for(int i=0;i<3;i++){
    for(int j=0;j<3;j++){
      for(int k=0;k<3;k++){
          rotateX(scale);
          rotateY(scale);
          rotateZ(scale);
        //translate(len*i-len,len*j-len,len*k-len);
        //box(len);
        cube[i][j][k].showPiece();
        //translate(-len*i+len,-len*j+len,-len*k+len);
      }
    }
  }
  //saveFrame("output/movie_#####.png");
}
