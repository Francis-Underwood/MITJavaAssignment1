package interfaceDemo;

interface IPolyhedron {
	public abstract String getColor();
	public abstract void setColor(String color);
	public abstract int getFace();
	public abstract int getEdge();
	public abstract int getVertex();
	public abstract int getEulerNumber();
	public abstract double getVolume();
}
