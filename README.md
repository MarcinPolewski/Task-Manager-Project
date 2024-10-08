### Ideas for change
- add support for choosing a font size
- make use of static directory(for static files like images, css; automatically read my spring)

### Design choices
- IDs of scenes are declared in application.properties
  - enum might have been better, but I wanted to keep everything organized in one place. 
  - another possibility would be to create an enum that reads this properties and in one place stores link and autoasigns id. I think it would be too much

### What to improve in the future projects
- separation of concerns - UI components should not less dependent on backend. Implement more interfaces 
  
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
    - use of  Platform.runLater() and a SceneLoadedListener(class that listens if a scene is assigned to object from that scene, then listens for adding this scene to a window, then listens for isShowing property of a window ) fixed the problem
### Attributions
- "list.png" <- <a href="https://www.flaticon.com/free-icons/to-do-list" title="to do list icons">To do list icons created by Freepik - Flaticon</a>
- "chart-histogram.png" Uicons by <a href="https://www.flaticon.com/uicons">Flaticon</a>
- "home.png" Uicons by <a href="https://www.flaticon.com/uicons">Flaticon</a>
- "list.png" Uicons by <a href="https://www.flaticon.com/uicons">Flaticon</a>
- "settings.png" Uicons by <a href="https://www.flaticon.com/uicons">Flaticon</a>
- "user.png" Uicons by <a href="https://www.flaticon.com/uicons">Flaticon</a>
- "users.png" Uicons by <a href="https://www.flaticon.com/uicons">Flaticon</a>
- "add.png" Uicons by <a href="https://www.flaticon.com/uicons">Flaticon</a>
- 
- colour combination <- <a href="https://colorhunt.co/palette/1e201e3c3d37697565ecdfcc" title="colour">colorhunt.co</a>