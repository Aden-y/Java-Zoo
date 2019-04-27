/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo;

import java.util.ArrayList;
import terrains.Terrain;

/**
 *
 * @author User
 */
public class Animal {
    private String name;
    private ArrayList<String> food;
    private Terrain terrain;
    
    public Animal(){
        food = new ArrayList();
        name = "";
    }
    
    public Animal(String name){
        food = new ArrayList();
        this.name = name;
       // this.terrain = terrain;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public ArrayList<String> getFood() {
		return food;
	}

	public void setFood(ArrayList<String> food) {
		this.food = food;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public void addFood(String food){
        this.food.add(food);
    }
    
    public ArrayList getFoods(){
        return food;
    }
    
    public String toString(){
        String thisAnimal = "Name: "+name+"\n";
        thisAnimal+="Food: [";
        int size = food.size() - 1;
        for(int i = 0; i < (size); i++){
            thisAnimal+=(String)food.get(i);
            if(i == food.size())
                break;
            thisAnimal+=",";
        }
        if(food.size() > 0)
            thisAnimal+= (String)food.get(size);
        thisAnimal+="]\n";
        thisAnimal+="Terrain: "+terrain.toString();
        return thisAnimal;
    }
    
}
