package com.marcin.jacek.polewski.Task_Manager_Project;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/* this configuration adds javaFX classes to beans managed by Spring Container
 */
@Configuration
public class JavaFXConfiguration {
    // @TODO add any necesary beans if needed(like Application, Parameters, HostServices..)

    private final String defaultLanguageTag;
    JavaFXConfiguration(@Value("${language.default.tag}") String defaultLanguageTag)
    {
        this.defaultLanguageTag = defaultLanguageTag;
    }
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:languages/messages");
        messageSource.setDefaultEncoding("UTF-8");

        Locale.setDefault(Locale.forLanguageTag(defaultLanguageTag));
        return messageSource;
    }


}
