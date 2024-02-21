package kth.iv1201.group9.recruitment_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.Locale;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    /**
    * Create a org.springframework.web.servlet.SessionLocaleResolver
    * that stores the user's current locale in the session object.
    */
    @Bean
    LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;
    }

    /**
     * Create a LocaleChangeInterceptor bean that intercepts requests
     * to change the user's locale based on the "lang" parameter.
     */
    @Bean
    @NonNull
    LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /**
     * Add the configured LocaleChangeInterceptor to the InterceptorRegistry.
     * This interceptor will handle requests to change the user's locale based on the "lang" parameter.
     */
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /**
     * Configure a ReloadableResourceBundleMessageSource bean for handling
     * message sources in the application. The base name is set to "classpath:/Messages"
     * with a default encoding of UTF-8.
     */
    @Bean
    ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/Messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * Configure a SpringTemplateEngine bean for processing templates
     * in the application. It sets the template resolver and message source.
     */
    @Bean
    SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setMessageSource(messageSource());
        return templateEngine;
    }

    /**
     * Configure a ThymeleafViewResolver bean for resolving Thymeleaf views
     * in the application. It sets the template engine and character encoding.
     */
    @Bean
    ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    /**
     * Configure a SpringResourceTemplateResolver bean for resolving templates
     * in the application. It sets the prefix, suffix, template mode, character encoding,
     * and cacheability (disabled for development).
     */
    @Bean
    SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false); // Disable caching for development
        return templateResolver;
    }
}
