package com.marcin.jacek.polewski.Task_Manager_Project.view;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.stage.Window;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class StartScreenAnimator {
    private Node node;
    private Window window;
    private TranslateTransition translateTransition;
    private Duration sideTranslationDuration;
    private Duration middleTranslationDuration;

    public StartScreenAnimator(
            @Value("${animation.startScreen.translation.side.duration}") double sideTranslationDuration,
            @Value("${animation.startScreen.translation.middle.duration}") double middleTranslationDuration)
    {
        this.sideTranslationDuration = Duration.millis(sideTranslationDuration);
        this.middleTranslationDuration = Duration.millis(middleTranslationDuration);

        this.translateTransition = new TranslateTransition();
    }

    public void start(Node node, Window window)
    {
        double componentWidth = node.getLayoutBounds().getWidth();
        double middleTransitionX = 2*componentWidth;
        double sideTransitionX = (window.getWidth() - middleTransitionX)/2.0;

        // first side transition
        translateTransition.setByX(sideTransitionX);
        translateTransition.setDuration(sideTranslationDuration);
        translateTransition.play();

        // middle transition
        translateTransition.setByX(middleTransitionX);
        translateTransition.setDuration(middleTranslationDuration);
        translateTransition.play();

        // last side transition
        translateTransition.setByX(sideTransitionX);
        translateTransition.setDuration(sideTranslationDuration);
        translateTransition.play();
    }

}
