package com.example.demo4;//package com.example.demo4;
////CS-255 Getting started code for the assignment
////I do not give you permission to post this code online
////Do not post your solution online
////Do not copy code
////Do not use JavaFX functions or other libraries to do the main parts of the assignment (i.e. ray tracing steps 1-7)
////All of those functions must be written by yourself
////You may use libraries to achieve a better GUI
////*/
/** Done the assignment in pairs: Bader Shalata (2221254 - ME), Mohamed Morabit (2221244). */
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {
  int Width = 640;
  int Height = 640;

  int green_col = 255; //just for the test example
  int green_col2 = 55; //just for the test example
  int green_col3 = 0;
  int blue_col = 100;
  int blue_col2 = 230;
  int blue_col3 = 0;
  int red_col = 150;
  int red_col2 = 255;
  int red_col3 = 255;
  int x_axis = 55;
  int x_axis1 = 55;
  int x_axis2 = 160;
  int y_axis = -55;
  int y_axis1 = -255;
  int y_axis2 = -255;
  int z_axis = 255;
  int z_axis1 = 255;
  int z_axis2 = 255;
  double camera_x;
  int camera_y = 255;
  int camera_z = 255;
  Vector VRP = new Vector(0,0,0);
  boolean sphere1pressed = false;
  boolean sphere2pressed = false;
  boolean sphere3pressed = false;

  @Override
  public void start(Stage stage) throws FileNotFoundException {
    stage.setTitle("Ray Tracing");
    //We need 3 things to see an image
    //1. We create an image we can write to
    WritableImage image = new WritableImage(Width, Height);
    //2. We create a view of that image
    ImageView view = new ImageView(image);
    //3. Add to the pane (below)
    //Create the simple GUI
    // (min,max,default)
    Slider g_slider = new Slider(0, 255, 0);
    // (Width,height)
    g_slider.setMaxSize(750,0);
    Label label1 = new Label();
    label1.setText("R");
    Slider r_slider = new Slider(0,255,0);
    r_slider.setMaxSize(750,0);
    Label label2 = new Label();
    label2.setText("G");
    Slider b_slider = new Slider(0,255,0);
    b_slider.setMaxSize(750,0);
    Label label3  = new Label();
    label3.setText("B");
    Slider x_slider = new Slider(-255,255,0);
    Label label4 = new Label();
    label4.setText("X-Axis");
    Slider y_slider = new Slider(-255,255,0);
    Label label5 = new Label();
    label5.setText("Y-Axis");
    Slider z_slider = new Slider(-255,255,0);
    Label label6 = new Label();
    label6.setText("Z-Axis");
    /** buttons*/
    Button button1 = new Button("Sphere 1");
    Button button2 = new Button("Sphere 2");
    Button button3 = new Button("Sphere 3");


    /** Camera Sliders */
    Slider camerax_slider = new Slider(-18600,18600,0);
    camerax_slider.setMaxSize(550,0);
    Slider cameray_slider = new Slider(-13600,13600,0);
    cameray_slider.setMaxSize(550,0);


      /** ticks */
      g_slider.setShowTickLabels(true);
      r_slider.setShowTickLabels(true);
      b_slider.setShowTickLabels(true);
      x_slider.setShowTickLabels(true);
      y_slider.setShowTickLabels(true);
      z_slider.setShowTickLabels(true);
      camerax_slider.setShowTickLabels(true);
      cameray_slider.setShowTickLabels(true);


    GridPane root = new GridPane();
    root.add(button1,2,3);
    root.add(button2, 2, 4);
    root.add(button3,2,5);

    /** Buttons Actions */
    button1.setOnAction(event -> {
      sphere1pressed = true;
      sphere2pressed = false;
     sphere3pressed = false;
    });
    button2.setOnAction(event -> {
      sphere1pressed = false;
      sphere2pressed = true;
      sphere3pressed = false;
    });
      button3.setOnAction(event -> {
          sphere1pressed = false;
          sphere2pressed = false;
          sphere3pressed = true;
      });
    //Add all the event handlers
    g_slider.valueProperty().addListener(
            new ChangeListener < Number > () {
              public void changed(ObservableValue < ? extends Number >
                                          observable, Number oldValue, Number newValue) {
                  if (sphere1pressed){
                      green_col = newValue.intValue();
                      Render(image);
                  } else if (sphere2pressed){
                      green_col2 = newValue.intValue();
                      Render(image);
                  } else if (sphere3pressed)
                  {
                      green_col3 = newValue.intValue();
                      Render(image);
                  }
              }
            });
    r_slider.valueProperty().addListener(
            new ChangeListener < Number > () {
              public void changed(ObservableValue < ? extends Number >
                                          observable, Number oldValue, Number newValue) {
                  if (sphere1pressed){
                      red_col = newValue.intValue();
                      Render(image);
                  } else if (sphere2pressed){
                      red_col2 = newValue.intValue();
                      Render(image);
                  } else if (sphere3pressed)
                  {
                      red_col3 = newValue.intValue();
                      Render(image);
                  }
              }
            });
    b_slider.valueProperty().addListener(
            new ChangeListener < Number > () {
              public void changed(ObservableValue < ? extends Number >
                                          observable, Number oldValue, Number newValue) {
                if (sphere1pressed){
                  blue_col = newValue.intValue();
                  Render(image);
                } else if (sphere2pressed){
                  blue_col2 = newValue.intValue();
                  Render(image);
                } else if (sphere3pressed)
                {
                    blue_col3 = newValue.intValue();
                    Render(image);
                }
              }
            });


    x_slider.valueProperty().addListener(
            new ChangeListener < Number > () {
              public void changed(ObservableValue < ? extends Number >
                                          observable, Number oldValue, Number newValue) {
                  if (sphere1pressed){
                      x_axis = newValue.intValue();
                      sphere2pressed = false;
                      sphere3pressed = false;
                      Render(image);
                  } else if (sphere2pressed){
                      x_axis1 = newValue.intValue();
                      sphere1pressed = false;
                      sphere3pressed = false;
                      Render(image);
                  }
                  else if(sphere3pressed)
                  {
                      x_axis2 = newValue.intValue();
                      sphere2pressed = false;
                      sphere1pressed = false;
                      Render(image);
                  }

              }
            });
    y_slider.valueProperty().addListener(
            new ChangeListener < Number > () {
              public void changed(ObservableValue < ? extends Number >
                                          observable, Number oldValue, Number newValue) {
                if (sphere1pressed){
                  y_axis = newValue.intValue();
                  Render(image);
                } else if (sphere2pressed){
                  y_axis1 = newValue.intValue();
                  Render(image);
                }
                else if(sphere3pressed)
                {
                    y_axis2 = newValue.intValue();
                    Render(image);
                }

              }
            });
    z_slider.valueProperty().addListener(
            new ChangeListener < Number > () {
              public void changed(ObservableValue < ? extends Number >
                                          observable, Number oldValue, Number newValue) {
                  if (sphere1pressed){
                      z_axis = newValue.intValue();
                      Render(image);
                  } else if (sphere2pressed){
                      z_axis1 = newValue.intValue();
                      Render(image);
                  }
                  else if(sphere3pressed)
                  {
                      z_axis2 = newValue.intValue();
                      Render(image);
                  }

              }
            });

    camerax_slider.valueProperty().addListener(
            new ChangeListener < Number > () {
              public void changed(ObservableValue < ? extends Number >
                                          observable, Number oldValue, Number newValue) {
           camera_x= newValue.intValue();
            Render(image);
              }
            });

    cameray_slider.valueProperty().addListener(
            new ChangeListener < Number > () {
              public void changed(ObservableValue < ? extends Number >
                                          observable, Number oldValue, Number newValue) {
                camera_y = newValue.intValue();
                Render(image);
              }
            });

    //The following is in case you want to interact with the image in any way
    //e.g., for user interaction, or you can find out the pixel position for debugging
    view.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
      System.out.println(event.getX() + " " + event.getY());
      event.consume();
    });

    Render(image);
    root.setVgap(12);
    root.setHgap(12);

    //3. (referring to the 3 things we need to display an image)
    //we need to add it to the pane
    root.add(view, 0, 0);
    // (column, row).
    /** Add RGB and XYZ for Spheres */
    root.add(g_slider, 0, 1);
    root.add(r_slider, 0, 2);
    root.add(b_slider, 0, 3);
    root.add(x_slider, 0, 4);
    root.add(y_slider, 0, 5);
    root.add(z_slider, 0, 6);

    /** Add Camera Sliders */
    root.add(camerax_slider,3,4);
    root.add(cameray_slider,4,4);

    /** Add Labels */
    root.add(label1,1,1);
    root.add(label2,1,2);
    root.add(label3,1,3);
    root.add(label4,1,4);
    root.add(label5,1,5);
    root.add(label6,1,6);
    //Display to user
    Scene scene = new Scene(root, 1024, 768);
    stage.setScene(scene);
    stage.show();

  }

  public void Render(WritableImage image) {
    //Get image dimensions, and declare loop variables
    int w = (int) image.getWidth(), h = (int) image.getHeight(), i, j;
    PixelWriter image_writer = image.getPixelWriter();
    /** sphere color converter  RANGE 0-1 */
    double g = green_col / 255.0;
    double g2 = green_col2 / 255.0;
    double g3 = green_col3 / 255.0;
    double redcol = red_col / 255.0;
    double redcol2 = red_col2 / 255.0;
    double redcol3 = red_col3 / 255.0;
    double blue = blue_col / 255.0;
    double blue2 = blue_col2 / 255.0;
    double blue3 = blue_col3 / 255.0;

    // origin of a ray
    Vector o;
    // direction of the ray
    Vector d;
    // center of sphere
    /** Center of Spheres that accept Variables of sliders */
    Vector cs1 = new Vector(x_axis,y_axis,z_axis);
    Vector cs2 = new Vector(x_axis1,y_axis1,z_axis1);
    Vector cs3 = new Vector(x_axis2,y_axis2,z_axis2);
    /** Colors of Spheres that accept Variables of sliders */
    Vector _c = new Vector(redcol, g, blue);
    Vector _c1= new Vector(redcol2, g2, blue2);
    Vector _c2= new Vector(redcol3, g3, blue3);
    Vector background_col = new Vector(0.5,0.5,0.5);
    /** Radius */
    double r = 50;
    double r1 = 20;
    double r2 = 55;
    // we want to solve this variable
    double t;
    /** Sphere objects */
    Sphere s1 = new Sphere(cs1, r, _c);
    Sphere s2 = new Sphere(cs2, r1, _c1);
    Sphere s3 = new Sphere(cs3,r2,_c2);
    /** Light Vector */
    Vector Light = new Vector(-320,320,-200);
    /** ArrayList contains the spheres */
    ArrayList<Sphere> circles = new ArrayList<Sphere>();
    circles.add(s1);
    circles.add(s2);
    circles.add(s3);
    /** view reference point VRP LOOKAT VRV VUV */
    Vector VRP = new Vector(camera_x,camera_y,-400);
    Vector LookAt = new Vector(0,0,0);
      /** view right vector */
    Vector VRV = new Vector(0,0,0);
    /** view plane normal */
    Vector VUV = new Vector(0,1,0);
    /** Camera Object */
    Camera c1 = new Camera(VRP,LookAt,VRV,VUV);
    for (j = 0; j < h; j++) {
      for (i = 0; i < w; i++) {
        double closestT = 1000000;
        Vector closestColor = background_col;
          int u = i-w/2;
          int v1 = j-h/2;
          //pixel world screen
          o = c1.origin(u,v1);
          d = c1.returnVPN();
        for(Sphere m: circles){
          double disc = m.disc(o, d);
          if (disc<0) continue;
          t = m.sphereIntersection(o, d);
          if (t<0){
            continue;
          }
          if (t > closestT){
            continue;
          }
          closestT = t;
          closestColor = m.getCol();
          double dp = m.shading(o, d, t, Light);
          if (dp < 0 ){
            closestColor = new Vector (0,0,0);
          }
          else {
            closestColor = closestColor.mul(dp);
          }
        }
        image_writer.setColor(i, j, Color.color(closestColor.x,closestColor.y,closestColor.z,1.0));
      }
    }
  }

  public static void main(String[] args) {
    launch();
  }

}
//hac