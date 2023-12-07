package com.example.demo4;
//
import javafx.scene.effect.Light;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.scene.image.WritableImage;

import static java.lang.Math.sqrt;

public class Sphere {
    private Vector cs;
    double r;
    Vector c ;

    Sphere(Vector cs, double r, Vector c) {
        this.cs = cs;
        this.r = r;
        this.c = c;
    }


    public double disc(Vector o, Vector d){
        double a, b, c;
        Vector v=o.sub(cs);
        a=d.dot(d);
        b=2*(v.dot(d));
        c=v.dot(v)-r*r;
        double disc = b*b-4*a*c;
        return disc;
    }

    public double sphereIntersection(Vector o, Vector d){

        double a, b;
        Vector v= o.sub(cs);
        a = d.dot(d);
        b= 2*v.dot(d);
        double c=v.dot(v)-r*r;
        double disc = b*b-4*a*c;
        double t = (-b-sqrt(disc))/(2*a);
        if (t<0){
            t = (b-sqrt(disc))/(2*a);
            return t;
        }
        else{
            return t;
        }
    }
    public double shading(Vector o, Vector d, double t, Vector Light){
        Vector p = o.add(d.mul(t));
        Vector Lv = Light.sub(p);
        Lv.normalise();
        Vector n = p.sub(cs);
        n.normalise();
        double dp = Lv.dot(n);
        return dp;
    }
    public Vector getCol(){
        return c;
    }
}
