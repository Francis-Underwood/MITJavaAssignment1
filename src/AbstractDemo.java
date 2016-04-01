
public class AbstractDemo {

	public static void main(String[] args) {
		Polyhedron polyObj1 = new Tetrahedron("purple", 2);
		Polyhedron polyObj2 = new Parallelepiped(106, 103, 271, 30, 30, 30);
		displayPolyhedron(polyObj1);
		displayPolyhedron(polyObj2);
	}

	public static void displayPolyhedron(Polyhedron polyObj) {
		System.out.println("");
		System.out.println("An object of " + polyObj.getClass().toString());
		System.out.println("With color as: " + polyObj.getColor());
		System.out.println("and Euler Number as: " + polyObj.getEulerNumber());
		System.out.println("and volume as: " + polyObj.getVolume());
	}
}
