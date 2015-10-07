package module1;

public class VectorMethods {
	public static void main(String[] args){
		
		VectorMethods vm = new VectorMethods();
		final double setOneAngle = vm.angle(2,4,1,5,3,1);
		final double setTwoAngle = vm.angle(2,4,1,0,0,0); //Output NaN (Not a Number) due to division by zero in angle method.

		System.out.println("The angle between the vectors (2,4,1) and (5,3,1) is: " + setOneAngle);
		System.out.println("The angle between the vectors (2,4,1) and (0,0,0) is: " + setTwoAngle);
	}
	
	private double dotProduct(
			final double x1, final double y1, final double z1, 
			final double x2, final double y2, final double z2)
	{
		return x1*x2 + y1*y2 + z1*z2;
	}
	
	private double magnitude(final double x, final double y, final double z)
	{
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	private double angle(
			final double x1, final double y1, final double z1, 
			final double x2, final double y2, final double z2)
	{
		return Math.acos(dotProduct(x1, y1, z1, x2, y2, z2) / (magnitude(x1,y1,z1)*magnitude(x2,y2,z2)));
	}
	
}
