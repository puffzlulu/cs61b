public class NBody{
	public static double readRadius(String filename){
		In in=new In(filename);
		int n=in.readInt();
		double rad=in.readDouble();
		return rad;
	}

	public static Planet[] readPlanets(String filename){
		In in=new In(filename);
		int n=in.readInt();
		Planet[] planets=new Planet[n];
		double rad=in.readDouble();
		for(int i=0;i<n;i+=1){
			double initialx=in.readDouble();
			double initialy=in.readDouble();
			double initialvx=in.readDouble();
			double initialvy=in.readDouble();
			double mass=in.readDouble();
			String imgname=in.readString();
			planets[i]=new Planet(initialx,initialy,initialvx,initialvy,mass,imgname);
		}
		return planets;
	}

	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		Planet[] planets=readPlanets(filename);
		double rad=readRadius(filename);
		StdDraw.setXscale(-rad,rad);
		StdDraw.setYscale(-rad,rad);
		double t=0;
		int n=planets.length;
		while(t<T){
			StdDraw.enableDoubleBuffering();
			StdDraw.picture(0,0,"images/starfield.jpg");
			double[] xForces=new double[n];
			double[] yForces=new double[n];
			for(int i=0;i<n;i+=1){
				xForces[i]=planets[i].calcNetForceExertedByX(planets);
				yForces[i]=planets[i].calcNetForceExertedByY(planets);
			}
			for(int i=0;i<n;i+=1){
				planets[i].update(dt,xForces[i],yForces[i]);
                                planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t=t+dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", rad);
		for (int i = 0; i < planets.length; i++) {
    			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  	planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  	planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}
}
