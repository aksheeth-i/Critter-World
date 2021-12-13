package package1;
import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import javafx.scene.shape.*;


public class Main extends Application{

    public static int size = 35;
    private double howMany = 1;
    private GridPane critterGrid;
    private long frameSpeed;
    private ArrayList<Button> buttons = new ArrayList<Button>();
    private Button stopAnimation =  new Button("Stop Animation");
    private Button runStatsBtn        = new Button("Enter");
    private static String myPackage; // package of Critter file.

    /* Critter cannot be in default pkg. */
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }
    public static void main(String[] args) {
         launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // Set the window Title
        primaryStage.setTitle("Project 5: Critter World GUI");
        // Create a Grid Layout for the controller
        GridPane controllerLayout = new GridPane();

          controllerLayout.setVgap(12.5);
       // controllerLayout.setHgap();

        //Create Buttons For The GUI

        //////
        Text createCrittersLabel  = new Text("Create Critters");

        Text generationSuccessful = new Text("");
        createCrittersLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        Button btnCreate = new Button("Generate");
        buttons.add(btnCreate);

        Label typeCritter = new Label("Type of Critter:");
        Label numCritter  = new Label("Number of Critters:");
        Text createError = new Text();
        TextField userTypeInfo = new TextField();
        TextField userNumInfo  = new TextField();
        Random rand = new Random();
        btnCreate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                generationSuccessful.setText("");

                String className =  userTypeInfo.getText().toString();
                String howMany = userNumInfo.getText().toString();
                int howManny=1;
                if(!howMany.equals(""))
                    howManny = Integer.parseInt(howMany);
                try {
                    for(int i =0; i<howManny;i++)
                        Critter.createCritter(className);
                } catch (InvalidCritterException e) {
                    createError.setText("Invalid Critter Input");
                    createError.setFill(Color.RED);
                    return;
                }
                // Change the text field of statRan, initially empty
                createError.setText("");
                Critter.displayWorld(critterGrid);

                generationSuccessful.setText("Success!");
                generationSuccessful.setFill(Color.color(rand.nextDouble(),1.0, rand.nextDouble()));
            }
        });
        /////

        Text runStatsLabel        = new Text("Run Stats");
        runStatsLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        Label critterTypeStats =  new Label("Type of Critter:");
        TextField classNameField  = new TextField();
        // Button runStatsBtn        = new Button("Enter");
        buttons.add(runStatsBtn);
        Label statsRanLabel = new Label("Stats:");
        Text statsRan = new Text();

        Stage runStage = new Stage();
        GridPane runLayout = new GridPane();
        Text stats = new Text("Stats");
        stats.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        runLayout.add(stats, 0 , 0);
        runLayout.add(statsRan,1,1);
        Button quitStat = new Button("Close Stats");
        buttons.add(quitStat);
        runLayout.add(quitStat, 1,5);
        runLayout.setVgap(10);
        Scene runScene = new Scene(runLayout, 300,300);
        runStage.setTitle("Stats");
        runStage.setScene(runScene);
        quitStat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                runStage.hide();
            }
        });



        // Produces output for requested run stats request
        runStatsBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get the class Name
                String className =  classNameField.getText().toString();
                String stats = "";
                if(className.equals(""))
                {
                    statsRan.setText("");
                    runStage.hide();
                    return;
                }
                if(className.equals("All") || className.equals("all"))
                {
                    String stat = "";
                    try {
                        stat += Goblin.runStats(Critter.getInstances("Goblin"));
                        stat += "\nClover: ";
                        stat += Clover.runStats(Critter.getInstances("Clover"));
                        stat += "\nDragon: ";
                        stat += Critter.runStats(Critter.getInstances("Dragon"));
                        stat += "\nFish: ";
                        stat += Critter.runStats(Critter.getInstances("Fish"));
                        stat += "\nEagles: ";
                        stat += Critter.runStats(Critter.getInstances("Eagle"));
                        stat += "\nUnicorns: ";
                        stat += Critter.runStats(Critter.getInstances("Unicorn"));
                        statsRan.setText(stat);
                        runStage.show();

                    } catch (InvalidCritterException e) {
                        e.printStackTrace();
                    }


                    return;
                }
                // Get the stats for the critter class type
                try {
                    stats = (String) Class.forName(myPackage + "." + className).getMethod("runStats", List.class).invoke(null,Critter.getInstances(className));
                    //Class.forName(myPackage + "." + parsedInput[1]).getMethod("runStats", List.class).invoke(null,Critter.getInstances(parsedInput[1]));
                } catch (IllegalAccessException | NoSuchMethodException | ClassNotFoundException | InvocationTargetException | InvalidCritterException e) {
                    statsRan.setText("Invalid Critter Input");
                    statsRan.setFill(Color.RED);

                    runStage.show();
                    return;
                }
                // Change the text field of statRan, initially empty
                statsRan.setText(stats);
                statsRan.setFill(Color.BLACK);
                runStage.show();
            }
        });

        ////
        ////
        Text worldTimeStepsLabel = new Text("World Time Steps");
        worldTimeStepsLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        TextField enterSpeed = new TextField();
        //
        TextField actualSpeed = new TextField();
        Label enterSpeedLabelActual =  new Label("Speed (FPS)");
        //
        Label enterSpeedLabel = new Label("Number of World Time Steps:");
        Button btnTimeStep  = new Button("Start Animation");
        buttons.add(btnTimeStep);

        Button btnJumpTime = new Button("Jump Time Steps");
        buttons.add(btnJumpTime);
        btnJumpTime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String howManyTimes = enterSpeed.getText().toString();
                int howManySteps = howManyTimes.equals("") ? 1 : Integer.parseInt(howManyTimes);
                for(int i = 0; i < howManySteps; i ++)
                {
                    Critter.worldTimeStep();
                }
                Critter.displayWorld(critterGrid);
            }
        });

        buttons.add(stopAnimation);
        stopAnimation.setDisable(true);
        // Event Handler for Stop Animation



        // Create another
        critterGrid = new GridPane();
        paintGridLines(critterGrid, size);
        Scene scene2 = new Scene(critterGrid, size * Params.WORLD_WIDTH +  size,size * Params.WORLD_HEIGHT + size);
        Stage CritterWorld = new Stage();
        CritterWorld.setTitle("Critter World");
        CritterWorld.setScene(scene2);
        CritterWorld.show();

        AnimationTimer timer = new MyTimer();

        btnTimeStep.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String howManyTimes = enterSpeed.getText().toString();
                howMany = howManyTimes.equals("") ? 1 : Integer.parseInt(howManyTimes);
                Critter.displayWorld(critterGrid);

                String fps = actualSpeed.getText().toString();
                frameSpeed = fps.equals("") ? 1 : Integer.parseInt(fps);
                // Disable all the buttons, except for stopAnimation
                for(Button btn : buttons)
                {
                    if(btn ==  stopAnimation)
                    {
                        stopAnimation.setDisable(false);
                        continue;
                    }
                    btn.setDisable(true);
                }


                timer.start();


            }
        });
        stopAnimation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.stop();
                for(Button btn : buttons)
                {
                    btn.setDisable(false);
                }
                stopAnimation.setDisable(true);
            }
        });



        Text setSeed              = new Text("Set Random Seed");
        setSeed.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        Label seedLabel = new Label("Seed:");
        TextField enterSeedField = new TextField();
        Button    btnSeed        = new Button("Enter Seed");
        buttons.add(btnSeed);

        btnSeed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int seed = Integer.parseInt(enterSeedField.getText().toString());
                Critter.setSeed(seed);
            }
        });
        //////
        Text Quit = new Text("Quit Program");
        Quit.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        Button quitBtn = new Button("Quit");
        buttons.add(quitBtn);

        quitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });


        // Add all the objects to the controller layout with appropriate geometry
        controllerLayout.add(createCrittersLabel, 0,0);
        controllerLayout.add(userTypeInfo, 1,1);
        controllerLayout.add(createError,2,3);
        controllerLayout.add(userNumInfo, 1, 2);
        controllerLayout.add(typeCritter, 0, 1);
        controllerLayout.add(numCritter, 0,2);
        controllerLayout.add(btnCreate, 1, 3);
        controllerLayout.add(generationSuccessful, 2,3);


        controllerLayout.add(worldTimeStepsLabel, 0,6);
        controllerLayout.add(enterSpeedLabel, 0,7);
        controllerLayout.add(enterSpeed, 1, 7);
        controllerLayout.add(enterSpeedLabelActual,0, 8);
        controllerLayout.add(actualSpeed, 1,8);
        controllerLayout.add(btnTimeStep, 1,10);
        controllerLayout.add(stopAnimation, 1, 11);
        controllerLayout.add(btnJumpTime,1 ,9);

        controllerLayout.add(runStatsLabel, 0, 12);
        controllerLayout.add(critterTypeStats, 0,13);
        controllerLayout.add(classNameField, 1,13);
        //controllerLayout.add(statsRanLabel, 0, 13);

        //controllerLayout.add(statsRan, 1, 13);

        controllerLayout.add(runStatsBtn, 1, 14);

        controllerLayout.add(setSeed, 0,18);
        controllerLayout.add(seedLabel, 0,19);
        controllerLayout.add(enterSeedField, 1, 19);
        controllerLayout.add(btnSeed, 1,20);

        controllerLayout.add(Quit, 0, 21);
        controllerLayout.add(quitBtn, 1,22);





/*
        Polygon t =  new Star().getStar();
        //t =  new Triangle().getTriangle();
        t.setStroke(Color.BLACK);
        critterGrid.add(t,1,12);
*/

        Scene scene = new Scene(controllerLayout, 500,750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }




    public static void paintGridLines(GridPane grid, int size) {
        for (int i = 0; i < Params.WORLD_WIDTH; i++) {
            for (int j = 0; j < Params.WORLD_HEIGHT; j++) {
                Shape s = new Rectangle(size, size);
                s.setFill(null);
                s.setStroke(Color.GRAY);
                grid.add(s, i, j);
            }
        }
    }
    private class MyTimer extends AnimationTimer{

        @Override
        public void handle(long now) {
            Critter.worldTimeStep();
            Critter.displayWorld(critterGrid);
            try {
                Thread.sleep(1000/ frameSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            howMany--;

            runStatsBtn.setDisable(false);
            runStatsBtn.fire();
            runStatsBtn.setDisable(true);


            if(howMany==0)
            {

                // Enable all the buttons
                for(Button btn : buttons)
                {
                    btn.setDisable(false);
                }
                // Disable Stop ANimation
                stopAnimation.setDisable(true);
                // Stop the timer
                stop();
            }

        }
    }

}
