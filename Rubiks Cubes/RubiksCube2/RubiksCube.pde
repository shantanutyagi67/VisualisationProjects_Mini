import peasy.*;

PeasyCam cam;
PImage img;
Pieces cube[][][] = new Pieces[3][3][3];
float len =50;
float scale = 0;
void setup(){
  size(1920,1080,P3D);
  cam = new PeasyCam(this,400);
  img = loadImage("ccd6.png");
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
  background(100);
  stroke(0);
  strokeWeight(6);
  fill(255);
  textSize(15);
  text("@geekSA67",120,150);
  scale += 0.003;
  rotateX(4*scale);
  rotateY(-scale);
  rotateZ(2*scale);
  for(int i=0;i<3;i++){
    for(int j=0;j<3;j++){
      for(int k=0;k<3;k++){
        //translate(len*i-len,len*j-len,len*k-len);
        //box(len);
        cube[i][j][k].showPiece();
        //translate(-len*i+len,-len*j+len,-len*k+len);
      }
    }
  }
  
}
