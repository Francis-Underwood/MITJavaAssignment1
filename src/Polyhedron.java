abstract class Polyhedron {
	private String color = "white";
	private int face;
	private int edge;
	private int vertex;
	protected Polyhedron(String color, int face, int edge, int vertex) {
		this.color = color;
		this.face = face;
		this.edge = edge;
		this.vertex = vertex;
	}
	protected Polyhedron(int face, int edge, int vertex) {
		this.face = face;
		this.edge = edge;
		this.vertex = vertex;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getFace() {
		return face;
	}
	public int getEdge() {
		return edge;
	}
	public int getVertex() {
		return vertex;
	}
	public int getEulerNumber() {
		return vertex - edge + face;
	}
	public abstract double getVolume();
}
