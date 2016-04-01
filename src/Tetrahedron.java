public class Tetrahedron extends Polyhedron {
	private double edgeLength;
	public Tetrahedron(double edgeLength) {
		super(4, 6, 4);
		this.edgeLength = edgeLength;
	}
	public Tetrahedron(String color, double edgeLength) {
		super(color, 4, 6, 4);
		this.edgeLength = edgeLength;
	}
	public double getEdgeLength() {
		return edgeLength;
	}
	public void setEdgeLength(double edgeLength) {
		this.edgeLength = edgeLength;
	}
	@Override
	public double getVolume() {
		return edgeLength * edgeLength * edgeLength / (6 * Math.sqrt(2));
	}
}
