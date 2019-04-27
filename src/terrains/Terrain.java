/* 
 * Author: Jordan Angle
 * Version 1
 * 4/14/2019
 * COP 2552.0m1
 */
package terrains;

public abstract class Terrain<T> implements Comparable<T>{
	private String name;
	private String climate;
	private String topography;
	
	public Terrain(String name, String climate, String topography) {
		super();
		this.name = name;
		this.climate = climate;
		this.topography = topography;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTopography() {
		return topography;
	}

	public void setTopography(String topography) {
		this.topography = topography;
	}
	
}
