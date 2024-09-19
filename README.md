
### Design choices
- IDs of scenes are declared in application.properties
  - enum might have been better, but I wanted to keep everything organized in one place. 
  - another possibility would be to create an enum that reads this properties and in one place stores link and autoasigns id. I think it would be too much
  
### Additional things that I have learned
- i18n stands for internationalization

### Encountered bugs
 - spring printed a warning that resource has not been found(pertaining css file) and provided by it path was correct, resource was successfully moved to right folder in target directory, even clicking the path resulted in opening the right file
   - buggy version: 
       ```java  
                // load css file
                String defaultCss = SceneWrapper.getDefaultCSSResource().getFile().toPath().toString();
                String scenesCss = wrapper.getSceneCSSResource().getFile().toPath().toString();
    
                scene.getStylesheets().add(defaultCss);
                scene.getStylesheets().add(scenesCss); 
       ```

   - fixed version:
     ```java             
             // load css file
             String defaultCss = SceneWrapper.getDefaultCSSResource().getURL().toExternalForm();
             String scenesCss = wrapper.getSceneCSSResource().getURL().toExternalForm();

             scene.getStylesheets().add(defaultCss);
             scene.getStylesheets().add(scenesCss); 
     ```
 -  aplication icon would not change. Using standard scene.getIcon.add(icon) did not work
    - still not fixed 
 - Could not automatically run animation on start screen. Animations would finish before screen was shown
    - tried using Platform.runLater(). This fixed a bit the problem - now window is displaying but scene is not fully loaded
   ```java
        iconView.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                // Listen for when the scene's window property is set (i.e., when the scene is attached to a window)
                newScene.windowProperty().addListener((obs, oldWindow, newWindow) -> {
                    if (newWindow != null) {
                        // The scene is added to the window, now listen for when it's shown
                        newWindow.showingProperty().addListener((o, oldValue, newValue) -> {

                            if (newValue) {
                                // The window is shown
                                //windowStartedBeingShown();
                                // Use Platform.runLater to delay the animation until the window is fully shown
                                Platform.runLater(this::windowStartedBeingShown);
                            }
                        });
                    }
                });
            }
        });
      ```
   
   - tried creating a listener class, but it did not help 
      ```java
        public class WindowLoadedListener {
     
        public static void addWindowLoadedListener(Node nodeFromScene, Runnable onWindowLoaded) {

        nodeFromScene.sceneProperty().addListener((o, oldVal, newVal) -> {
            if(newVal != null)
            {
                newVal.windowProperty().addListener((oo, oldWindow, newWindow) -> {
                    if(newWindow != null)
                    {
                        newWindow.showingProperty().addListener((ooo, oldIsShowing, newIsShowing) -> {
                            Platform.runLater(onWindowLoaded);
                        });
                    }

                });
            }
        });
      }
     }
     ```
### Attributions
- "list.png" <- <a href="https://www.flaticon.com/free-icons/to-do-list" title="to do list icons">To do list icons created by Freepik - Flaticon</a>