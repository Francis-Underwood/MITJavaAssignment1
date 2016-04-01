public class Parallelepiped extends Polyhedron {
	private double edgeLengthA;
	private double edgeLengthB;
	private double edgeLengthC;
	private double angleAlpha;	// in degree
	private double angleBeta;	// in degree
	private double angleGamma;	// in degree
	public Parallelepiped(double edgeLengthA, double edgeLengthB,
							double edgeLengthC, double angleAlpha,
							double angleBeta, double angleGamma) {
		super(6, 12, 8);
		this.edgeLengthA = edgeLengthA;
		this.edgeLengthB = edgeLengthB;
		this.edgeLengthC = edgeLengthC;
		this.angleAlpha = angleAlpha;
		this.angleBeta = angleBeta;
		this.angleGamma = angleGamma;
	}
	public Parallelepiped(String color, double edgeLengthA, double edgeLengthB,
			double edgeLengthC, double angleAlpha,
			double angleBeta, double angleGamma) {
		super(color, 6, 12, 8);
		this.edgeLengthA = edgeLengthA;
		this.edgeLengthB = edgeLengthB;
		this.edgeLengthC = edgeLengthC;
		this.angleAlpha = angleAlpha;
		this.angleBeta = angleBeta;
		this.angleGamma = angleGamma;
	}
	public double getEdgeLengthA() {
		return edgeLengthA;
	}
	public void setEdgeLengthA(double edgeLengthA) {
		this.edgeLengthA = edgeLengthA;
	}
	public double getEdgeLengthB() {
		return edgeLengthB;
	}
	public void setEdgeLengthB(double edgeLengthB) {
		this.edgeLengthB = edgeLengthB;
	}
	public double getEdgeLengthC() {
		return edgeLengthC;
	}
	public void setEdgeLengthC(double edgeLengthC) {
		this.edgeLengthC = edgeLengthC;
	}
	public double getAngleAlpha() {
		return angleAlpha;
	}
	public void setAngleAlpha(double angleAlpha) {
		this.angleAlpha = angleAlpha;
	}
	public double getAngleBeta() {
		return angleBeta;
	}
	public void setAngleBeta(double angleBeta) {
		this.angleBeta = angleBeta;
	}
	public double getAngleGamma() {
		return angleGamma;
	}
	public void setAngleGamma(double angleGamma) {
		this.angleGamma = angleGamma;
	}
	@Override
	public double getVolume() {
		double cosAngAlpha = Math.cos(Math.toRadians(angleAlpha));
		double cosAngBeta = Math.cos(Math.toRadians(angleBeta));
		double cosAngGamma = Math.cos(Math.toRadians(angleGamma));
		return edgeLengthA * edgeLengthB * edgeLengthC * 
				Math.sqrt(1 + 2 * cosAngAlpha * cosAngBeta * cosAngGamma - 
						cosAngAlpha*cosAngAlpha - cosAngBeta * cosAngBeta - cosAngGamma * cosAngGamma);
	}
}
