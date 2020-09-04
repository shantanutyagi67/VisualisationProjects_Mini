import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import peasy.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class chaos extends PApplet {

float x=-15,y=-16,z=30;
float a=10,b=28,c=8.000f/3.000f;
float dt = 0.01f;
ArrayList<PVector> points= new ArrayList<PVector>();
PeasyCam cam;
public void setup(){
  
  translate(width/2,height/2);
  colorMode(HSB);
  cam = new PeasyCam(this,600);
}

public void draw(){
  background(0);

  stroke(255);
  //Euler approximation of differencial equation
  x = x + (a * (y - x))*dt;
  y = y + (x * (b - z) - y)*dt;
  z = z + (x * y - c * z)*dt;
  points.add(new PVector(x,y,z));
  scale(5);
  strokeWeight(0.5f);
  noFill();
  float hu=0;
  beginShape();
  for(PVector p: points){
    stroke(hu,255,255);
    rotateX(0.0025f);
    rotateZ(-0.0025f);
    rotateY(0.0025f);
    hu += 1;
    hu %= 255;
    vertex(p.x,p.y,p.z);
    //println(x,y,z);
  }
  endShape();
  if(points.size()>10000) points.remove(0);
}
  public void settings() {  size(800,800,P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "chaos" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
