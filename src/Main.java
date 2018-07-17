import Building.Building;
import Building.State;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  

	
    @Override 
    public void start(Stage primaryStage) {
        Building batiment = new Building("Batiment 1", 0, 0, 150, 100);
        Building batiment2 = new Building("Batiment 2", 100, 100, 150, 100);

        batiment.setState(State.StateValue.OK);
        batiment2.setState(State.StateValue.KO);

        Group root = new Group();
        root.getChildren().addAll(batiment, batiment2);
        final Scene scene = new Scene(root, 600, 600); 
        
        primaryStage.setTitle("Test de DnD"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    } 
  
    public static void main(String[] args) {
        launch(args); 
    } 
}