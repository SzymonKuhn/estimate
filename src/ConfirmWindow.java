import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmWindow {

    static boolean answer;

    public static boolean display (String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(280);
        window.setMinHeight(120);
        Label label = new Label();
        label.setText(message);

        //Buttons
        Button yesButton = new Button("Tak");
        yesButton.setMinWidth(70);
        Button noButton = new Button("Nie");
        noButton.setMinWidth(70);

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        HBox buttons = new HBox(15);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(noButton, yesButton);

        VBox layout = new VBox(15);
        layout.getChildren().addAll(label, buttons);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
