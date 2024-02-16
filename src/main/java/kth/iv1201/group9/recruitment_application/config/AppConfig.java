package kth.iv1201.group9.recruitment_application.config;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
public class AppConfig implements WebMvcConfigurer {


    /**
 * Create a <code>org.springframework.web.servlet.SessionLocaleResolver</code>
 * that stores the user's current locale in the session object.
 */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;
    }

/**
 * Create a <code>LocaleChangeInterceptor</code> bean that intercepts requests
 * to change the user's locale based on the "lang" parameter.
 */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

/**
 * Add the configured <code>LocaleChangeInterceptor</code> to the InterceptorRegistry.
 * This interceptor will handle requests to change the user's locale based on the "lang" parameter.
 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

/**
 * Configure a <code>ReloadableResourceBundleMessageSource</code> bean for handling
 * message sources in the application. The base name is set to "classpath:/Messages"
 * with a default encoding of UTF-8.
 */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/Messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
}

/**
 * Configure a <code>SpringTemplateEngine</code> bean for processing templates
 * in the application. It sets the template resolver and message source.
 */
@Bean
public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver());
    templateEngine.setMessageSource(messageSource());
    return templateEngine;
}

/**
 * Configure a <code>ThymeleafViewResolver</code> bean for resolving Thymeleaf views
 * in the application. It sets the template engine and character encoding.
 */
@Bean
public ThymeleafViewResolver thymeleafViewResolver() {
    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    resolver.setTemplateEngine(templateEngine());
    resolver.setCharacterEncoding("UTF-8");
    return resolver;
}

/**
 * Configure a <code>SpringResourceTemplateResolver</code> bean for resolving templates
 * in the application. It sets the prefix, suffix, template mode, character encoding,
 * and cacheability (disabled for development).
 */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false); // Disable caching for development
        return templateResolver;
    }


}
