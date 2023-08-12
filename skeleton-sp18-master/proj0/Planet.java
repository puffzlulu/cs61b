public class Planet{
	double xxPos;/** its current x position */
	double yyPos;/** its current y position */
	double xxVel;/** its current velocity in the x direction */
	double yyVel;/** its current velocity in the y direction */
	double mass;/** its mass */
	String imgFileName;/** the name of the file that corresponds to the image that depicts the planet */

	public Planet(double xP,double yP,double xV,double yV,double m,String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}

	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}

	public double calcDistance(Planet p){
		double distance=(p.xxPos-xxPos)*(p.xxPos-xxPos)+(p.yyPos-yyPos)*(p.yyPos-yyPos);
		return Math.sqrt(distance);
	}

	public double calcForceExertedBy(Planet p){
		double G=6.67e-11;
		double dr=calcDistance(p)*calcDistance(p);
		return G*mass*p.mass/dr;
	}

	public double calcForceExertedByX(Planet p){
		double dx=p.xxPos-xxPos;
		return calcForceExertedBy(p)*dx/calcDistance(p);
	}

	public double calcForceExertedByY(Planet p){
		double dy=p.yyPos-yyPos;
		return calcForceExertedBy(p)*dy/calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] allPlanets){
		double nx=0;
		for(int i=0;i<allPlanets.length;i+=1){
			if(this.equals(allPlanets[i])){
				continue;
			}
			nx=nx+calcForceExertedByX(allPlanets[i]);
		}
		return nx;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets){
		double ny=0;
		for(int i=0;i<allPlanets.length;i+=1){
			if(this.equals(allPlanets[i])){
				continue;
			}
			ny=ny+calcForceExertedByY(allPlanets[i]);
		}
		return ny;
	}

	public void update(double dt,double fX,double fY){
		double ax=fX/mass;
		double ay=fY/mass;
		xxVel=xxVel+dt*ax;
		yyVel=yyVel+dt*ay;
		xxPos=xxPos+dt*xxVel;
		yyPos=yyPos+dt*yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
	}
}
