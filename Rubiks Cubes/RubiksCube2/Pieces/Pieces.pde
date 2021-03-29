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
