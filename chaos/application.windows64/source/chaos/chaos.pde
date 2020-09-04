import peasy.*;

float x=-15,y=-16,z=30;
float a=10,b=28,c=8.000/3.000;
float dt = 0.01;
ArrayList<PVector> points= new ArrayList<PVector>();
PeasyCam cam;
void setup(){
  size(800,800,P3D);
  translate(width/2,height/2);
  colorMode(HSB);
  cam = new PeasyCam(this,600);
}

void draw(){
  background(0);

  stroke(255);
  //Euler approximation of differencial equation
  x = x + (a * (y - x))*dt;
  y = y + (x * (b - z) - y)*dt;
  z = z + (x * y - c * z)*dt;
  points.add(new PVector(x,y,z));
  scale(5);
  strokeWeight(0.5);
  noFill();
  float hu=0;
  beginShape();
  for(PVector p: points){
    stroke(hu,255,255);
    rotateX(0.0025);
    rotateZ(-0.0025);
    rotateY(0.0025);
    hu += 1;
    hu %= 255;
    vertex(p.x,p.y,p.z);
    //println(x,y,z);
  }
  endShape();
  if(points.size()>10000) points.remove(0);
}
