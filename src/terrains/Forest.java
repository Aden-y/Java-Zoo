/* 
 * Author: Jordan Angle
 * Version 1
 * 4/14/2019
 * COP 2552.0m1
 */
package terrains;

public class Forest extends Terrain{
	private double surface;

	public Forest(String name, String climate, String topography, double surface) {
		super(name, climate, topography);
		this.setSurface(surface);
	}

	// if all attributes are equal objects are equal and 0 returned, otherwise not and -1 returned
	@Override
	public int compareTo(Object o) {
		Forest f = (Forest) o;
		
		if (getName().equals(f.getName()) && getClimate().equals(f.getClimate()) &&
				getTopography().equals(f.getTopography()) && getSurface() == f.getSurface()) {
			return 0;
		}else {
			return -1;
		}
	}
	
	
	@Override
	public String toString() {
		String output = "Forest "+getName()+" with climate "+getClimate()+ " and topography "
				+ getTopography()+" is on surface area of "+ getSurface()+" square kilometers\n";
		return output;
	}


	public double getSurface() {
		return surface;
	}

	public void setSurface(double surface) {
		this.surface = surface;
	}

}
