package com.marcin.jacek.polewski.Task_Manager_Project.controller;


import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.ImageId;
import com.marcin.jacek.polewski.Task_Manager_Project.view.StartScreenAnimator;
import com.marcin.jacek.polewski.Task_Manager_Project.view.ViewHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.WindowLoadedListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class StartViewController implements Initializable{
    @FXML
    private ImageView iconView;
    @FXML
    private Label greetingLabel;
    @FXML
    private Label mottoLabel;
    private MemoryHandler memoryHandler;
    private MessageSource messageSource;
    private StartScreenAnimator animator;
    private ViewHandler viewHandler;
    private Double elementPadding;

    @Autowired
    StartViewController(MemoryHandler memoryHandler,
                        MessageSource messageSource,
                        StartScreenAnimator animator,
                        ViewHandler viewHandler,
                        @Value("${scene.startScene.elementPadding}") Double padding)
    {
        this.memoryHandler = memoryHandler;
        this.messageSource = messageSource;
        this.animator = animator;
        this.viewHandler = viewHandler;
        this.elementPadding = padding;
    }

    private void runAnnimations() {
        Window window = iconView.getScene().getWindow();
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(iconView);
        nodes.add(greetingLabel);
        nodes.add(mottoLabel);
        animator.start(nodes, window, viewHandler::switchToLogInScene);
    }

    public void positionObjectsForAnimation()
    {
        // Position X
        iconView.setX(-iconView.getBoundsInLocal().getWidth());
        mottoLabel.setLayoutX(-mottoLabel.getBoundsInLocal().getWidth());
        greetingLabel.setLayoutX(-greetingLabel.getBoundsInLocal().getWidth());

        // Position Y
        double windowHeight = iconView.getScene().getWindow().getHeight();
        double totalComponentHeight = 2.0*elementPadding + mottoLabel.getHeight()
                + greetingLabel.getHeight()
                + iconView.getFitHeight();
        double iconViewY = (windowHeight-totalComponentHeight)/2.0;
        double greetingLabelY = iconViewY + iconView.getFitHeight() + elementPadding;
        double mottoLabelY = greetingLabelY + greetingLabel.getHeight() + elementPadding;

        iconView.setY(iconViewY);
        greetingLabel.setLayoutY(greetingLabelY);
        mottoLabel.setLayoutY(mottoLabelY);

    }

    public void windowStartedBeingShown()
    {
        positionObjectsForAnimation();
        runAnnimations();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // adding logo
        try{
            iconView.setImage(memoryHandler.getImage(ImageId.APP_LOGO));
        } catch(IOException e)
        {
            System.out.println("failed to load image");
        }

        // setting text on labels
        greetingLabel.setText(messageSource.getMessage("startScreenPrompt", null, "notFound", Locale.getDefault()));
        mottoLabel.setText(messageSource.getMessage("startScreenMotto", null, "notFound", Locale.getDefault()));

        // adding a listener to a scene, so a method will be called when scene with this button is added to a window
        WindowLoadedListener.addWindowLoadedListener(iconView, this::windowStartedBeingShown);
    }
}
