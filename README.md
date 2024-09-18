
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

### Attributions
- "list.png" <- <a href="https://www.flaticon.com/free-icons/to-do-list" title="to do list icons">To do list icons created by Freepik - Flaticon</a>