package interfaceDemo;

public class InterfaceDemo {

	public static void main(String[] args) {
		IPolyhedron polyObj1 = new Tetrahedron("purple", 2);
		IPolyhedron polyObj2 = new Parallelepiped(106, 103, 271, 30, 30, 30);
		displayPolyhedron(polyObj1);
		displayPolyhedron(polyObj2);
	}
	
	public static void displayPolyhedron(IPolyhedron polyObj) {
		System.out.println("");
		System.out.println("An object of " + polyObj.getClass().toString());
		System.out.println("With color as: " + polyObj.getColor());
		System.out.println("and Euler Number as: " + polyObj.getEulerNumber());
		System.out.println("and volume as: " + polyObj.getVolume());
	}
}
