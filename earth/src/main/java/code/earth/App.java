package code.earth;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.transform.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.*;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.beans.property.*;

@SuppressWarnings("restriction")
public class App extends Application
{
    private static final int HEIGHT = 800;
    private static final int WIDTH = 1200;
    private double ancorX,ancorY,ancorAngleX=0,ancorAngleY=0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
	
    public static void main( String[] args )
    {
    	launch(args);
    }
	
	@Override 
	public void start(Stage primaryStage) throws Exception {
		
		Smartworld world = new Smartworld();
		world.getChildren().add(prepareEarth());
		Camera camera = new PerspectiveCamera(true);
		Scene scene = new Scene(world, WIDTH, HEIGHT);
		scene.setFill(Color.BLACK);
		scene.setCamera(camera);
		camera.translateXProperty().set(0);
		camera.translateYProperty().set(0);
		camera.translateZProperty().set(-500);
		camera.setNearClip(1);
		camera.setFarClip(1000);
		
		initMouseControl(world,scene);
		
		primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			switch(event.getCode()) {
			case UP:
				camera.translateZProperty().set(camera.getTranslateZ()+10);
				break;
			case DOWN:
				camera.translateZProperty().set(camera.getTranslateZ()-10);
				break;
			case X:
				world.rotateX(10);
				break;
			case Y:
				world.rotateY(10);
				break;
			default:
				break;
			}
		});
		primaryStage.setTitle("Welcome to Earth");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	private Node prepareEarth() {
		PhongMaterial earthMaterial = new PhongMaterial();
		earthMaterial.setBumpMap(new Image(getClass().getResourceAsStream("C:\\Users\\geekSA67\\OneDrive\\Pictures\\fotu01.png")));
		Sphere sphere = new Sphere(50);
		sphere.setMaterial(earthMaterial);
		return sphere;
	}
	private void initMouseControl(Smartworld world, Scene scene) {
	}
	
	class Smartworld extends Group{
		Rotate r;
		Transform t = new Rotate();
		void rotateX(double ang) {
			r = new Rotate(ang, Rotate.X_AXIS);
			t=t.createConcatenation(r);
			this.getTransforms().clear();
			this.getTransforms().addAll(t);
		}
		void rotateY(double ang) {
			r = new Rotate(ang, Rotate.Y_AXIS);
			t=t.createConcatenation(r);
			this.getTransforms().clear();
			this.getTransforms().addAll(t);
		}
	}
}
