package unsw.automata;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLifeController {

    @FXML
    private GridPane grid;

    @FXML
    private Button playButton;

    private GameOfLife gol;
    private boolean play;
    private Timeline timeline;

    public GameOfLifeController() {
        gol = new GameOfLife();
        timeline = new Timeline();

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gol.tick();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    @FXML
    public void initialize() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                CheckBox checkbox = new CheckBox();
                gol.cellProperty(x, y).bindBidirectional(checkbox.selectedProperty());
                grid.add(checkbox, x, y);
            }
        }
    }

    @FXML
    public void handleTick(ActionEvent event) {
        gol.tick();
    }

    @FXML
    public void handlePlay(ActionEvent event) {
        if (!play) {
            play = true;
            playButton.setText("Stop");
            timeline.play();
        } else {
            play = false;
            playButton.setText("Play");
            timeline.stop();
        }
    }
}

