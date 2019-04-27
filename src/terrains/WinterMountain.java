/* 
 * Author: Jordan Angle
 * Version 1
 * 4/14/2019
 * COP 2552.0m1
 */
package terrains;

public class WinterMountain extends Mountain{
	
	private double temperature;

	public WinterMountain(String name, String climate, String topography, double height, double temperature) {
		super(name, climate, topography, height);
		this.setTemperature(temperature);
	}
	
	// if all attributes are equal objects are equal and 0 returned, otherwise not and -1 returned
	@Override
	public int compareTo(Object o) {
		WinterMountain wm = (WinterMountain) o;
		
		if (getName().equals(wm.getName()) && getClimate().equals(wm.getClimate()) &&
				getTopography().equals(wm.getTopography()) && getHeight() == wm.getHeight()
				&& getTemperature() == wm.getTemperature()) {
			return 0;
		}else {
			return -1;
		}
	}
	
	
	@Override
	public String toString() {
		String output = "WinterMountain "+getName()+" with climate "+getClimate()+ " and topography "
				+ getTopography()+" has height "+ getHeight()+ " meters and temperature "+ getTemperature()+" celsius\n";
		return output;
	}


	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}


}
