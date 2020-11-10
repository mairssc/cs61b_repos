public class NBody {

    /*Next thing to do is draw the background*/
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Body[] b_array = NBody.readBodies(filename);
        StdDraw.enableDoubleBuffering();
        for (double time=0;time<=T;time=time+dt){
            Double[] xForces = new Double[b_array.length];
            Double[] yForces = new Double[b_array.length];

            for (int i=0;i<b_array.length;i++) {
                xForces[i] = b_array[i].calcNetForceExertedByX(b_array);
                yForces[i] = b_array[i].calcNetForceExertedByY(b_array);
            }

            for (int i=0;i<b_array.length;i++) {
                b_array[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body b : b_array){
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", b_array.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < b_array.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            b_array[i].xxPos, b_array[i].yyPos, b_array[i].xxVel,
            b_array[i].yyVel, b_array[i].mass, b_array[i].imgFileName); 
        }
        
        
    }

    /*This is static because there is no instance, instead it can be referenced from the class NBody*/
    public static double readRadius(String file){
        In f = new In(file);
        double radius = 0;
        for (int i=0;i<2;i++){
            radius = f.readDouble();
        }
        return radius;
    }

    /*6 values per row*/
    public static Body[] readBodies(String file){
        In f = new In(file);
        int planets = f.readInt();
        f.readDouble();
        Body[] b_array = new Body[planets];
        for (int i=0;i<planets;i++){
            b_array[i] = new Body(f.readDouble(), f.readDouble(), f.readDouble(), f.readDouble(), f.readDouble(), f.readString());
        }
        return b_array;
    }
}
