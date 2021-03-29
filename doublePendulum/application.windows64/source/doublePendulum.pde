import peasy.*;
PeasyCam cam;
float r1 = 125;
float r2 = 125;
float m1 = 1;
float m2 = 1;
float a1 = -1;
float a2 = -1;
float a1_v = 0;
float a2_v = 0;
float g = 1;
float dt = 0.5;

float px2 = -1;
float py2 = -1;
float cx, cy;

int count = 0;
boolean save = true;

ArrayList<PVector> pointNew = new ArrayList<PVector>();

void setup(){
  frameRate(80);
  size(1000,800,P3D);
  a1 = PI / 2 - 0.1;
  a2 = PI / 2 - 0.1;
  cx = width / 2;
  cy = 50;
  colorMode(HSB);
  cam = new PeasyCam(this,400);
  pixelDensity(1);
}
void draw(){
  background(0);
  translate(-500,-100);
  stroke(255);
  float hu = 0;

  float num1 = -g * (2 * m1 + m2) * sin(a1);
  float num2 = -m2 * g * sin(a1 - 2 * a2);
  float num3 = -2 * sin(a1 - a2) * m2;
  float num4 = a2_v * a2_v * r2 + a1_v * a1_v * r1 * cos(a1 - a2);
  float den = r1 * (2 * m1 + m2 - m2 * cos(2 * a1 - 2 * a2));
  float a1_a = (num1 + num2 + num3 * num4) / den;

  num1 = 2 * sin(a1 - a2);
  num2 = (a1_v * a1_v * r1 * (m1 + m2));
  num3 = g * (m1 + m2) * cos(a1);
  num4 = a2_v * a2_v * r2 * m2 * cos(a1 - a2);
  den = r2 * (2 * m1 + m2 - m2 * cos(2 * a1 - 2 * a2));
  float a2_a = (num1 * (num2 + num3 + num4)) / den;

  translate(cx, cy);

  float x1 = r1 * sin(a1);
  float y1 = r1 * cos(a1);

  float x2 = x1 + r2 * sin(a2);
  float y2 = y1 + r2 * cos(a2);

  a1_v += a1_a*dt;
  a2_v += a2_a*dt;
  a1 += a1_v*dt;
  a2 += a2_v*dt;
  pointNew.add(new PVector(x2,y2));
  
  strokeWeight(4);
  noFill();
  //stroke(hu,255,255);
  beginShape();
  for(int i=0;i<pointNew.size();i++){
    //stroke(0,255,255);
    stroke(hu%255,255,255);
    count++;
    if (count==5){
    hu++;
    count = 0;
    }
    vertex(pointNew.get(i).x,pointNew.get(i).y);
  //for(PVector pn: pointNew){
    //vertex(pn.x,pn.y);
  }
  endShape();
  if(pointNew.size()>500 && save){
    saveFrame("output/Exec3.png");
    save = false;
  }
  if(pointNew.size()>4000) pointNew.remove(0);
  stroke(255);
  strokeWeight(2);
  line(0, 0, x1, y1);
  fill(0);
  ellipse(x1, y1, 10+m1, 10+m1);

  line(x1, y1, x2, y2);
  fill(0);
  ellipse(x2, y2, 10+m2, 10+m2);
  
  px2 = x2;
  py2 = y2;
}
