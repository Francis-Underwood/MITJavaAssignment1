package interfaceDemo;

class Tetrahedron implements IPolyhedron {
	private final int FACE = 4;
	private final int EDGE = 6;
	private final int VERTEX = 4;
	private String color = "white";
	private double edgeLength;
	public Tetrahedron(double edgeLength) {
		this.edgeLength = edgeLength;
	}
	public Tetrahedron(String color, double edgeLength) {
		this.color = color;
		this.edgeLength = edgeLength;
	}
	@Override
	public String getColor() {
		return color;
	}
	@Override
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public int getFace() {
		return FACE;
	}
	@Override
	public int getEdge() {
		return EDGE;
	}
	@Override
	public int getVertex() {
		return VERTEX;
	}
	@Override
	public int getEulerNumber() {
		return VERTEX - EDGE + FACE;
	}
	@Override
	public double getVolume() {
		return edgeLength * edgeLength * edgeLength / (6 * Math.sqrt(2));
	}
	public double getEdgeLength() {
		return edgeLength;
	}
	public void setEdgeLength(double edgeLength) {
		this.edgeLength = edgeLength;
	}
}
