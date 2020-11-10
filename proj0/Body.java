/**
 * @author Sean Mairs
 */
public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;

    public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /**Calculates and returns r using, r^2 = dx^2 + dy^2 */
    public double calcDistance(Body b) {
        double dx = Math.abs(xxPos - b.xxPos);
        double dy = Math.abs(yyPos - b.yyPos);
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**Calculates and returns F, using F = (G*m1*m2)/r^2 */
    public double calcForceExertedBy(Body b) {
        return (Body.G * mass * b.mass)/Math.pow(calcDistance(b), 2);
    }

    /**Calculates and return Fx, using Fx = (F*dx)/r */
    public double calcForceExertedByX(Body b) {
        double dx = b.xxPos - xxPos;
        return (calcForceExertedBy(b)*dx)/calcDistance(b);
    }

    /**Calculates and return Fy, using Fy = (F*dy)/r */
    public double calcForceExertedByY(Body b) {
        double dy = b.yyPos - yyPos;
        return (calcForceExertedBy(b)*dy)/calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] bodies){
        double x_force = 0;
        for (int i=0;i<bodies.length;i++){
            if (this != bodies[i]){
                x_force = x_force + this.calcForceExertedByX(bodies[i]);
            }
        }
        return x_force;
    }

    public double calcNetForceExertedByY(Body[] bodies){
        double y_force = 0;
        for (int i=0;i<bodies.length;i++){
            if (this != bodies[i]){
                y_force = y_force + this.calcForceExertedByY(bodies[i]);
            }
        }
        return y_force;
    }

    public void update(double time, double fx, double fy) {
        double acc_fx = fx/mass;
        double acc_fy = fy/mass;
        xxVel = xxVel + time*acc_fx;
        yyVel = yyVel + time*acc_fy;
        xxPos = xxPos + time*xxVel;
        yyPos = yyPos + time*yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}