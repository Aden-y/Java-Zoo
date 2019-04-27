/* 
 * Author: Jordan Angle
 * Version 1
 * 4/14/2019
 * COP 2552.0m1
 */
package zoo;

import com.sun.prism.paint.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import terrains.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Zoo extends Application {

	// Main display
	private static ArrayList<Animal> animals;
         public boolean validateInt(String input){
         try{
             int inputInt=Integer.parseInt(input);
             if(inputInt<0){
                 return false;
             }
             return true;
         }catch(NumberFormatException e){
              return false;
              
          }
         }
	private static boolean checkCorrectInput(String txt) {
		if (txt.trim().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("Error");
			alert.setContentText("All field must be filled in!");
			alert.show();
			return false;
		} else if (txt.length() > 50) {
			Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("Error");
			alert.setContentText("Field content can't be bigger then 50 characters!");
			alert.show();
			return false;
		}

		return true;
	}

	private void drawWindow() {
		VBox root = new VBox(10);

		Text textAnimalName = new Text("Enter Animal name:");
		TextField textFieldAnimalName = new TextField();
		textFieldAnimalName.setMaxWidth(100);

		Text textFood = new Text("Enter the food the animal consumes (each food separate with space): ");
		TextField textFieldFood = new TextField();
		textFieldFood.setMaxWidth(400);

		Text textTerrainType = new Text("Select Terrain type:");
		ToggleGroup toggleGroup = new ToggleGroup();
		RadioButton forest = new RadioButton("Forest");
		forest.setToggleGroup(toggleGroup);
		RadioButton mountain = new RadioButton("Mountain");
		mountain.setToggleGroup(toggleGroup);
		RadioButton winterMountain = new RadioButton("Winter Mountain");
		winterMountain.setToggleGroup(toggleGroup);

		Text textTerrainName = new Text("Enter Terrain name:");
		TextField textFieldTerrainName = new TextField();
		textFieldTerrainName.setMaxWidth(100);

		Text textTerrainClimate = new Text("Enter Terrain climate:");
		TextField textFieldTerrainClimate = new TextField();
		textFieldTerrainClimate.setMaxWidth(100);

		Text textTerrainTopography = new Text("Enter Terrain Topography:");
		TextField textFieldTerrainTopography = new TextField();
		textFieldTerrainTopography.setMaxWidth(100);

		Button button = new Button("Submit");

		Scene scene = new Scene(root, 500, 500);
		root.getChildren().add(textAnimalName);
		root.getChildren().add(textFieldAnimalName);
		root.getChildren().add(textFood);
		root.getChildren().add(textFieldFood);
		root.getChildren().add(textTerrainType);
		root.getChildren().addAll(forest, mountain, winterMountain);
		root.getChildren().add(textTerrainName);
		root.getChildren().add(textFieldTerrainName);
		root.getChildren().add(textTerrainClimate);
		root.getChildren().add(textFieldTerrainClimate);
		root.getChildren().add(textTerrainTopography);
		root.getChildren().add(textFieldTerrainTopography);

		root.getChildren().add(button);
		root.setPadding(new Insets(30));
		Stage stage1 = new Stage();
		stage1.setScene(scene);
		stage1.setTitle("Add new animal");
		stage1.show();

		button.setOnAction(e -> {
			JSONObject animalJSON = new JSONObject();
			String animalName = textFieldAnimalName.getText();
			if (checkCorrectInput(animalName) == false)
				return;
			animalJSON.put("name", animalName);
			JSONArray foodArray = new JSONArray();

			if (checkCorrectInput(textFieldFood.getText()) == false)
				return;
			String str[] = textFieldFood.getText().split(" ");
                        if (str.length<5){
                            //See if les than five food for an animal is added
                        Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("Error");
			alert.setContentText("Please enter a minimum of five foods!");
			alert.show();
                        return;
                        }
			for (int i = 0; i < str.length; i++)
				foodArray.add(str[i]);

			animalJSON.put("food", foodArray);

			String name = textFieldTerrainName.getText();
			if (checkCorrectInput(name) == false)
				return;
			String climate = textFieldTerrainClimate.getText();
			if (checkCorrectInput(climate) == false)
				return;
			String topography = textFieldTerrainTopography.getText();
			if (checkCorrectInput(topography) == false)
				return;

			JSONObject terrainJSON = new JSONObject();

			if (forest.isSelected()) {
				stage1.close();
				VBox root1 = new VBox(10);

				Text text1 = new Text("Enter forest surface: ");

				TextField input11 = new TextField();
				input11.setMaxWidth(100);

				Button button1 = new Button("Submit");

				Scene scene1 = new Scene(root1, 300, 300);
				root1.getChildren().add(text1);
				root1.getChildren().add(input11);
				root1.getChildren().add(button1);
				root1.setPadding(new Insets(30));
				Stage stage2 = new Stage();
				stage2.setScene(scene1);
				stage2.setTitle("Forest");
				stage2.show();

				button1.setOnAction(ee -> {
					try {
						double surface = Double.parseDouble(input11.getText());
                                                if(surface<=0.0){
                                                Alert alert = new Alert(AlertType.ERROR);

                                                alert.setTitle("Error");
                                                alert.setContentText("Surface area must be non negative and greater than zero!");
                                                alert.show();
                                                return;
                                                }
						terrainJSON.put("type", "forest");
						terrainJSON.put("name", name);
						terrainJSON.put("climate", climate);
						terrainJSON.put("topography", topography);
						terrainJSON.put("surface", surface);

						animalJSON.put("terrain", terrainJSON);
						Object obj = null;
						try {
							obj = new JSONParser().parse(new FileReader("animals.json"));
						} catch (FileNotFoundException e4) {

						} catch (IOException e3) {

						} catch (ParseException e2) {

						}

						try (FileWriter file = new FileWriter("animals.json")) {
							JSONArray animalArray;
							if (obj != null) {
								animalArray = (JSONArray) obj;
							} else
								animalArray = new JSONArray();

							animalArray.add(animalJSON);
							file.write(animalArray.toJSONString());
							file.flush();
							readJSONFile();

						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} catch (NumberFormatException ex) {
						Alert alert = new Alert(AlertType.ERROR);

						alert.setTitle("Error");
						alert.setContentText("Invalid type of input for surface!");
						alert.show();
						return;
					}
					stage2.close();

				});
			} else if (mountain.isSelected()) {

				stage1.close();
				VBox root1 = new VBox(10);

				Text text1 = new Text("Enter mountain height: ");

				TextField input11 = new TextField();
				input11.setMaxWidth(100);

				Button button1 = new Button("Submit");

				Scene scene1 = new Scene(root1, 300, 300);
				root1.getChildren().add(text1);
				root1.getChildren().add(input11);
				root1.getChildren().add(button1);
				root1.setPadding(new Insets(30));
				Stage stage2 = new Stage();
				stage2.setScene(scene1);
				stage2.setTitle("Mountain");
				stage2.show();

				button1.setOnAction(ee -> {
					try {
						double height = Double.parseDouble(input11.getText());
                                                if(height<=0.0){
                                                //Display an error if Zero or negative input is entered for height
                                                Alert alert = new Alert(AlertType.ERROR);

                                                alert.setTitle("Error");
                                                alert.setContentText("Please enter a height more than Zero!");
                                                alert.show();
                                                return;
                                                }
						terrainJSON.put("type", "mountain");
						terrainJSON.put("name", name);
						terrainJSON.put("climate", climate);
						terrainJSON.put("topography", topography);
						terrainJSON.put("height", height);

						animalJSON.put("terrain", terrainJSON);
						Object obj = null;
						try {
							obj = new JSONParser().parse(new FileReader("animals.json"));
						} catch (FileNotFoundException e4) {

						} catch (IOException e3) {

						} catch (ParseException e2) {

						}

						try (FileWriter file = new FileWriter("animals.json")) {
							JSONArray animalArray;
							if (obj != null) {
								animalArray = (JSONArray) obj;
							} else
								animalArray = new JSONArray();

							animalArray.add(animalJSON);
							file.write(animalArray.toJSONString());
							file.flush();
							readJSONFile();

						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} catch (NumberFormatException ex) {
						Alert alert = new Alert(AlertType.ERROR);

						alert.setTitle("Error");
						alert.setContentText("Invalid type of input for height!");
						alert.show();
						return;
					}
					stage2.close();

				});

			} else if (winterMountain.isSelected()) {

				stage1.close();
				VBox root1 = new VBox(10);

				Text text1 = new Text("Enter winter mountain height:  ");
				Text text2 = new Text("Enter winter mountain temperature: ");

				TextField input11 = new TextField();
				input11.setMaxWidth(100);

				TextField input22 = new TextField();
				input22.setMaxWidth(100);

				Button button1 = new Button("Submit");

				Scene scene1 = new Scene(root1, 300, 300);
				root1.getChildren().add(text1);
				root1.getChildren().add(input11);
				root1.getChildren().add(text2);
				root1.getChildren().add(input22);
				root1.getChildren().add(button1);
				root1.setPadding(new Insets(30));
				Stage stage2 = new Stage();
				stage2.setScene(scene1);
				stage2.setTitle("Winter Mountain");
				stage2.show();

				button1.setOnAction(ee -> {
					try {
						double height = Double.parseDouble(input11.getText());
						double temperature = Double.parseDouble(input22.getText());

						terrainJSON.put("type", "winter mountain");
						terrainJSON.put("name", name);
						terrainJSON.put("climate", climate);
						terrainJSON.put("topography", topography);
						terrainJSON.put("height", height);
						terrainJSON.put("temperature", temperature);

						animalJSON.put("terrain", terrainJSON);
						Object obj = null;
						try {
							obj = new JSONParser().parse(new FileReader("animals.json"));
						} catch (FileNotFoundException e4) {

						} catch (IOException e3) {

						} catch (ParseException e2) {

						}

						try (FileWriter file = new FileWriter("animals.json")) {
							JSONArray animalArray;
							if (obj != null) {
								animalArray = (JSONArray) obj;
							} else
								animalArray = new JSONArray();

							animalArray.add(animalJSON);
							file.write(animalArray.toJSONString());
							file.flush();
							readJSONFile();

						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} catch (NumberFormatException ex) {
						Alert alert = new Alert(AlertType.ERROR);

						alert.setTitle("Error");
						alert.setContentText("Invalid type of input for height or temperature!");
						alert.show();
						return;
					}
					stage2.close();

				});

			} else {
				Alert alert = new Alert(AlertType.ERROR);

				alert.setTitle("Error");
				alert.setContentText("Terrain type must be selected!");
				alert.show();
				return;
			}
                        
                        //

		});
                
                
                
	}

	public static void main(String[] args) {

		launch(args);

	}

	public void start(Stage stage1) throws Exception {

		animals = new ArrayList();
		boolean done = false;
		System.out.println("Welcome!");

		readJSONFile();

		VBox root1 = new VBox(20);

		Button button1 = new Button("Add a new animal");
                
                
                Button button3=new Button("View or Edit animal records");

		Scene scene1 = new Scene(root1, 300, 300);
		root1.getChildren().add(button1);
                root1.getChildren().add(button3);
		root1.setPadding(new Insets(50));
		stage1.setScene(scene1);
		stage1.setTitle("Menu");
		stage1.show();

		button1.setOnAction(e -> drawWindow());
                button3.setOnAction(e->edit());
	}

        public static void edit(){
            
            VBox root=new VBox(10);
            
            HBox hbox=new HBox();
            Text id=new Text("ID");
            id.setWrappingWidth(50);
            Text name=new Text("Name");
            Text foods=new Text("Foods");
            Text terrain=new Text("Terrain");
            Text edit=new Text("Edit");
            
            name.setWrappingWidth(150);
            foods.setWrappingWidth(150);
            terrain.setWrappingWidth(150);
            edit.setWrappingWidth(80);
            
            hbox.getChildren().add(id);
            hbox.getChildren().add(name);
            hbox.getChildren().add(foods);
            hbox.getChildren().add(terrain);
            hbox.getChildren().add(edit);
            hbox.setPadding(new Insets(30));
            hbox.setSpacing(20);
            //Scene
            root.getChildren().add(hbox);
            
            int index=1;
            for(Animal a: animals){
                
                HBox h=new HBox();
                Text id1=new Text(""+index);
                id1.setWrappingWidth(50);
                Text name1=new Text(a.getName());
                name1.setWrappingWidth(150);
                VBox foodsv=new VBox();
                foodsv.setMinWidth(150);
                for(int i=0; i<a.getFood().size(); i++){
                    Text food=new Text((String)a.getFoods().get(i));
                    foodsv.getChildren().add(food);
                    
                }
                VBox t=new VBox();
                t.setMinWidth(150);
                Text tName=new Text("Name  : "+a.getTerrain().getName());
                Text tClimate= new Text("Climate : "+a.getTerrain().getClimate());
                Text tTopology = new Text("Topology : "+a.getTerrain().getTopography());
                Button edit1=new Button("Edit");
                edit1.setMinWidth(80);
                t.getChildren().add(tName);
                t.getChildren().add(tClimate);
                t.getChildren().add(tTopology);
                h.getChildren().add(id1);
                h.getChildren().add(name1);
                h.getChildren().add(foodsv);
                h.getChildren().add(t);
                h.getChildren().add(edit1);
                edit1.setOnAction(e->editRecord());
                h.setPadding(new Insets(30));
                h.setSpacing(20);
                root.getChildren().add(h);
                index++;
            }
            Scene scene=new Scene(root,800,500);
            //Stage
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.setTitle("View / Edit Records");
            stage.show();
        }
        
        
        
        public static void editRecord(){
           
            VBox root = new VBox(10);
                Text id=new Text("Which ID would you like to edit *");
                TextField idF=new TextField();
		Text textAnimalName = new Text("Enter New Animal name:");
		TextField textFieldAnimalName = new TextField();
		textFieldAnimalName.setMaxWidth(100);

		Text textFood = new Text("Enter the food the animal consumes (each food separate with space): ");
		TextField textFieldFood = new TextField();
		textFieldFood.setMaxWidth(400);

		Text textTerrainType = new Text("Select Terrain type:");
		ToggleGroup toggleGroup = new ToggleGroup();
		RadioButton forest = new RadioButton("Forest");
		forest.setToggleGroup(toggleGroup);
		RadioButton mountain = new RadioButton("Mountain");
		mountain.setToggleGroup(toggleGroup);
		RadioButton winterMountain = new RadioButton("Winter Mountain");
		winterMountain.setToggleGroup(toggleGroup);

		Text textTerrainName = new Text("Enter New Terrain name:");
		TextField textFieldTerrainName = new TextField();
		textFieldTerrainName.setMaxWidth(100);

		Text textTerrainClimate = new Text("Enter Terrain climate:");
		TextField textFieldTerrainClimate = new TextField();
		textFieldTerrainClimate.setMaxWidth(100);

		Text textTerrainTopography = new Text("Enter Terrain Topography:");
		TextField textFieldTerrainTopography = new TextField();
		textFieldTerrainTopography.setMaxWidth(100);

		Button button = new Button("Submit");

		Scene scene = new Scene(root, 500, 600);
                root.getChildren().add(id);
                root.getChildren().add(idF);
		root.getChildren().add(textAnimalName);
		root.getChildren().add(textFieldAnimalName);
		root.getChildren().add(textFood);
		root.getChildren().add(textFieldFood);
		root.getChildren().add(textTerrainType);
		root.getChildren().addAll(forest, mountain, winterMountain);
		root.getChildren().add(textTerrainName);
		root.getChildren().add(textFieldTerrainName);
		root.getChildren().add(textTerrainClimate);
		root.getChildren().add(textFieldTerrainClimate);
		root.getChildren().add(textTerrainTopography);
		root.getChildren().add(textFieldTerrainTopography);

		root.getChildren().add(button);
		root.setPadding(new Insets(30));
		Stage stage1 = new Stage();
		stage1.setScene(scene);
		stage1.setTitle("Add new animal");
		stage1.show();

		button.setOnAction(e -> {
                    int index=-1;
                    //Verify 
                    try{
                         index=Integer.parseInt(idF.getText());
                         if(index>animals.size() || index<1){
                           Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("Error");
			alert.setContentText("Invalid ID!");
			alert.show();
                        return;  
                         }
                         index-=1;
                    }catch(NumberFormatException ex){
                       Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("Error");
			alert.setContentText("Invalid ID!");
			alert.show();
                        return;
                    }
			JSONObject animalJSON = new JSONObject();
			String animalName = textFieldAnimalName.getText();
			if (checkCorrectInput(animalName) == false)
				return;
			animalJSON.put("name", animalName);
			JSONArray foodArray = new JSONArray();

			if (checkCorrectInput(textFieldFood.getText()) == false)
				return;
			String str[] = textFieldFood.getText().split(" ");
                        if (str.length<5){
                            //See if les than five food for an animal is added
                        Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("Error");
			alert.setContentText("Please enter a minimum of five foods!");
			alert.show();
                        return;
                        }
			for (int i = 0; i < str.length; i++)
				foodArray.add(str[i]);

			animalJSON.put("food", foodArray);

			String name = textFieldTerrainName.getText();
			if (checkCorrectInput(name) == false)
				return;
			String climate = textFieldTerrainClimate.getText();
			if (checkCorrectInput(climate) == false)
				return;
			String topography = textFieldTerrainTopography.getText();
			if (checkCorrectInput(topography) == false)
				return;

			JSONObject terrainJSON = new JSONObject();

			if (forest.isSelected()) {
				stage1.close();
				VBox root1 = new VBox(10);

				Text text1 = new Text("Enter forest surface: ");

				TextField input11 = new TextField();
				input11.setMaxWidth(100);

				Button button1 = new Button("Submit");

				Scene scene1 = new Scene(root1, 300, 300);
				root1.getChildren().add(text1);
				root1.getChildren().add(input11);
				root1.getChildren().add(button1);
				root1.setPadding(new Insets(30));
				Stage stage2 = new Stage();
				stage2.setScene(scene1);
				stage2.setTitle("Forest");
				stage2.show();

				button1.setOnAction(ee -> {
					try {
						double surface = Double.parseDouble(input11.getText());
                                                if(surface<=0.0){
                                                Alert alert = new Alert(AlertType.ERROR);

                                                alert.setTitle("Error");
                                                alert.setContentText("Surface area must be non negative and greater than zero!");
                                                alert.show();
                                                return;
                                                }
						terrainJSON.put("type", "forest");
						terrainJSON.put("name", name);
						terrainJSON.put("climate", climate);
						terrainJSON.put("topography", topography);
						terrainJSON.put("surface", surface);

						animalJSON.put("terrain", terrainJSON);
						Object obj = null;
						try {
							obj = new JSONParser().parse(new FileReader("animals.json"));
						} catch (FileNotFoundException e4) {

						} catch (IOException e3) {

						} catch (ParseException e2) {

						}

						try (FileWriter file = new FileWriter("animals.json")) {
							JSONArray animalArray;
							if (obj != null) {
								animalArray = (JSONArray) obj;
							} else
								animalArray = new JSONArray();

							animalArray.add(animalJSON);
							file.write(animalArray.toJSONString());
							file.flush();
							readJSONFile();

						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} catch (NumberFormatException ex) {
						Alert alert = new Alert(AlertType.ERROR);

						alert.setTitle("Error");
						alert.setContentText("Invalid type of input for surface!");
						alert.show();
						return;
					}
					stage2.close();

				});
			} else if (mountain.isSelected()) {

				stage1.close();
				VBox root1 = new VBox(10);

				Text text1 = new Text("Enter mountain height: ");

				TextField input11 = new TextField();
				input11.setMaxWidth(100);

				Button button1 = new Button("Submit");

				Scene scene1 = new Scene(root1, 300, 300);
				root1.getChildren().add(text1);
				root1.getChildren().add(input11);
				root1.getChildren().add(button1);
				root1.setPadding(new Insets(30));
				Stage stage2 = new Stage();
				stage2.setScene(scene1);
				stage2.setTitle("Mountain");
				stage2.show();

				button1.setOnAction(ee -> {
					try {
                                            
						double height = Double.parseDouble(input11.getText());
                                                if(height<=0.0){
                                                //Display an error if Zero or negative input is entered for height
                                                Alert alert = new Alert(AlertType.ERROR);

                                                alert.setTitle("Error");
                                                alert.setContentText("Please enter a height more than Zero!");
                                                alert.show();
                                                return;
                                                }
						terrainJSON.put("type", "mountain");
						terrainJSON.put("name", name);
						terrainJSON.put("climate", climate);
						terrainJSON.put("topography", topography);
						terrainJSON.put("height", height);

						animalJSON.put("terrain", terrainJSON);
						Object obj = null;
						try {
							obj = new JSONParser().parse(new FileReader("animals.json"));
						} catch (FileNotFoundException e4) {

						} catch (IOException e3) {

						} catch (ParseException e2) {

						}

						try (FileWriter file = new FileWriter("animals.json")) {
							JSONArray animalArray;
							if (obj != null) {
								animalArray = (JSONArray) obj;
							} else
								animalArray = new JSONArray();

                                                        //animalArray.set(index, animalJSON);
							file.write(animalArray.toJSONString());
							file.flush();
							readJSONFile();

						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} catch (NumberFormatException ex) {
						Alert alert = new Alert(AlertType.ERROR);

						alert.setTitle("Error");
						alert.setContentText("Invalid type of input for height!");
						alert.show();
						return;
					}
					stage2.close();

				});

			} else if (winterMountain.isSelected()) {

				stage1.close();
				VBox root1 = new VBox(10);

				Text text1 = new Text("Enter winter mountain height:  ");
				Text text2 = new Text("Enter winter mountain temperature: ");

				TextField input11 = new TextField();
				input11.setMaxWidth(100);

				TextField input22 = new TextField();
				input22.setMaxWidth(100);

				Button button1 = new Button("Submit");

				Scene scene1 = new Scene(root1, 300, 300);
				root1.getChildren().add(text1);
				root1.getChildren().add(input11);
				root1.getChildren().add(text2);
				root1.getChildren().add(input22);
				root1.getChildren().add(button1);
				root1.setPadding(new Insets(30));
				Stage stage2 = new Stage();
				stage2.setScene(scene1);
				stage2.setTitle("Winter Mountain");
				stage2.show();

				button1.setOnAction(ee -> {
					try {
						double height = Double.parseDouble(input11.getText());
						double temperature = Double.parseDouble(input22.getText());

						terrainJSON.put("type", "winter mountain");
						terrainJSON.put("name", name);
						terrainJSON.put("climate", climate);
						terrainJSON.put("topography", topography);
						terrainJSON.put("height", height);
						terrainJSON.put("temperature", temperature);

						animalJSON.put("terrain", terrainJSON);
						Object obj = null;
						try {
							obj = new JSONParser().parse(new FileReader("animals.json"));
						} catch (FileNotFoundException e4) {

						} catch (IOException e3) {

						} catch (ParseException e2) {

						}

						try (FileWriter file = new FileWriter("animals.json")) {
							JSONArray animalArray;
							if (obj != null) {
								animalArray = (JSONArray) obj;
							} else
								animalArray = new JSONArray();

							//animalArray.set(index,animalJSON);
							file.write(animalArray.toJSONString());
							file.flush();
							readJSONFile();

						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} catch (NumberFormatException ex) {
						Alert alert = new Alert(AlertType.ERROR);

						alert.setTitle("Error");
						alert.setContentText("Invalid type of input for height or temperature!");
						alert.show();
						return;
					}
					stage2.close();

				});

			} else {
				Alert alert = new Alert(AlertType.ERROR);

				alert.setTitle("Error");
				alert.setContentText("Terrain type must be selected!");
				alert.show();
				return;
			}
                        
                        //

		});
                
                
        }
        
	private static void readJSONFile() {
		animals.clear();
		// parsing json file and store in animals array
		Object obj;
		try {
			try {
				obj = new JSONParser().parse(new FileReader("animals.json"));

			} catch (FileNotFoundException e) {
				System.out.println("There is no input json file named animals.json");
				System.out.println(e.toString());
				return;
			}
			JSONArray animalArray = (JSONArray) obj;

			// iterating animals
			Iterator animalIterator = animalArray.iterator();

			while (animalIterator.hasNext()) {
				JSONObject animalJson = (JSONObject) animalIterator.next();
				String name = (String) animalJson.get("name");
				Animal animal = new Animal(name);
				JSONArray foodArray = (JSONArray) animalJson.get("food");

				Iterator foodIterator = foodArray.iterator();
				while (foodIterator.hasNext()) {
					animal.addFood((String) foodIterator.next());
				}

				JSONObject terrain = (JSONObject) animalJson.get("terrain");
				String terrainType = (String) terrain.get("type");
				String terrainName = (String) terrain.get("name");
				String terrainClimate = (String) terrain.get("climate");
				String terrainTopography = (String) terrain.get("topography");
				double height, temperature, surface;

				switch (terrainType.toLowerCase()) {
				case "forest":
					surface = (double) terrain.get("surface");
					animal.setTerrain(new Forest(terrainName, terrainClimate, terrainTopography, surface));
					break;
				case "mountain":
					height = (double) terrain.get("height");
					animal.setTerrain(new Mountain(terrainName, terrainClimate, terrainTopography, height));
					break;
				case "winter mountain":
					height = (double) terrain.get("height");
					temperature = (double) terrain.get("temperature");
					animal.setTerrain(
							new WinterMountain(terrainName, terrainClimate, terrainTopography, height, temperature));
					break;

				}

				animals.add(animal);
			}

		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Viewing existing animals
	private static void viewExisting() {

		Alert alert = new Alert(AlertType.INFORMATION);

		String str = "";
		for (Animal animal : animals) {
			str += animal.toString() + "\n";
		}
		alert.setTitle("Existing animals");
		alert.setContentText(str);
		alert.show();
	}

	// Searching through existing animals object
	private static Animal searchAnimal(String name) {

		for (int i = 0; i < animals.size(); i++) {
			Animal animal = (Animal) animals.get(i);
			if (animal.getName().equalsIgnoreCase(name))
				return animal;
		}
		return null;
	}
}
