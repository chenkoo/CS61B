import java.lang.Math;

public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel,
                  double mass, String imgFileName) {
                      this.xxPos = xxPos;
                      this.yyPos = yyPos;
                      this.xxVel = xxVel;
                      this.yyVel = yyVel;
                      this.mass = mass;
                      this.imgFileName = imgFileName;
                  }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double distance;
        distance = Math.sqrt(Math.pow((this.xxPos - p.xxPos), 2) +
                             Math.pow((this.yyPos - p.yyPos), 2));
        return distance;
    }

    public double calcForceExertedBy(Planet p) {
        double force;
        force = (G * this.mass * p.mass)/(this.calcDistance(p) * this.calcDistance(p));
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double forceX;
        forceX = this.calcForceExertedBy(p) * (p.xxPos - this.xxPos)/this.calcDistance(p);
        return forceX;
    }

    public double calcForceExertedByY(Planet p) {
        double forceY;
        forceY = this.calcForceExertedBy(p) * (p.yyPos - this.yyPos)/this.calcDistance(p);
        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceX = 0;
        for (Planet p: planets) {
            if (! this.equals(p)) {
                netForceX += this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceY = 0;
        for (Planet p: planets) {
            if (! this.equals(p)) {
                netForceY += this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double acceX = fX/this.mass;
        double acceY = fY/this.mass;
        this.xxVel += acceX * dt;
        this.yyVel += acceY * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
        StdDraw.show();
    }
}
