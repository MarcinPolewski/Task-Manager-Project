package com.marcin.jacek.polewski.Task_Manager_Project.view;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.stage.Window;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class StartScreenAnimator {
    private Duration sideTranslationDuration;
    private Duration middleTranslationDuration;
    private Duration pauseBetweenElements;

    private ParallelTransition parallelTransition;


    public StartScreenAnimator(
            @Value("${animation.startScreen.translation.side.duration}") double sideTranslationDuration,
            @Value("${animation.startScreen.translation.middle.duration}") double middleTranslationDuration,
            @Value("${animation.startScreen.pauseBetweenElements}") double pauseBetweenElements)
    {
        this.sideTranslationDuration = Duration.millis(sideTranslationDuration);
        this.middleTranslationDuration = Duration.millis(middleTranslationDuration);
        this.pauseBetweenElements = Duration.millis(pauseBetweenElements);

        this.parallelTransition = new ParallelTransition();
    }

    public void start(ArrayList<Node> nodes, Window window, Runnable onFinish)
    {
        Duration startDelay = Duration.millis(0);
        Node node;
        for(int i=0; i<nodes.size(); ++i)
        {
            node = nodes.get(i);
            double componentWidth = node.getLayoutBounds().getWidth();
            double middleTransitionX = componentWidth;
            double sideTransitionX = ((window.getWidth() - (2*componentWidth))/2.0) + componentWidth;

            TranslateTransition left = new TranslateTransition();
            left.setNode(node);
            left.setByX(sideTransitionX);
            left.setDuration(sideTranslationDuration);

            TranslateTransition middle = new TranslateTransition();
            middle.setNode(node);
            middle.setByX(middleTransitionX);
            middle.setDuration(middleTranslationDuration);

            TranslateTransition right = new TranslateTransition();
            right.setNode(node);
            right.setByX(sideTransitionX);
            right.setDuration(sideTranslationDuration);

            SequentialTransition oneNodeTransition = new SequentialTransition(left, middle, right);
            oneNodeTransition.setNode(node);
            oneNodeTransition.setDelay(startDelay);

            startDelay = startDelay.add(pauseBetweenElements);
            parallelTransition.getChildren().add(oneNodeTransition);
        }
        parallelTransition.setOnFinished(event->{
            onFinish.run();
        });

        Platform.runLater(parallelTransition::play);
    }



}
