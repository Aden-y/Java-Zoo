/* 
 * Author: Jordan Angle
 * Version 1
 * 4/14/2019
 * COP 2552.0m1
 */
package terrains;

public class Mountain extends Terrain{
	
	private double height;

	public Mountain(String name, String climate, String topography, double height) {
		super(name, climate, topography);
		this.setHeight(height);
	}

	
	@Override
	public String toString() {
		String output = "Mountain "+getName()+" with climate "+getClimate()+ " and topography "
				+ getTopography()+" has height "+ getHeight()+" meters\n";
		return output;
	}


	// if all attributes are equal objects are equal and 0 returned, otherwise not and -1 returned
	@Override
	public int compareTo(Object o) {
		Mountain m = (Mountain) o;
		
		if (getName().equals(m.getName()) && getClimate().equals(m.getClimate()) &&
				getTopography().equals(m.getTopography()) && getHeight() == m.getHeight()) {
			return 0;
		}else {
			return -1;
		}
	}


	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}

}
