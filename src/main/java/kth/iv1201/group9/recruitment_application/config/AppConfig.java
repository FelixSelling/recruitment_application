package kth.iv1201.group9.recruitment_application.config;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
public class AppConfig implements WebMvcConfigurer {


/**
 * Configures and provides a {@link org.springframework.web.servlet.LocaleResolver} bean for managing user locale preferences.
 * The configured resolver is an instance of {@link org.springframework.web.servlet.i18n.SessionLocaleResolver},
 * which stores the user's locale information in the session and defaults to {@link java.util.Locale#ENGLISH}.
 *
 * @return A {@link org.springframework.web.servlet.LocaleResolver} bean configured as a {@link org.springframework.web.servlet.i18n.SessionLocaleResolver}.
 *         The default locale is set to {@link java.util.Locale#ENGLISH}.
 */

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;
    }

    /**
 * Configures and provides a {@link org.springframework.web.servlet.i18n.LocaleChangeInterceptor} bean
 * for intercepting requests to change the user's locale based on a specified parameter.
 * The configured interceptor is an instance of {@link org.springframework.web.servlet.i18n.LocaleChangeInterceptor},
 * and the parameter name for specifying the desired language is set to "lang".
 *
 * @return A {@link org.springframework.web.servlet.i18n.LocaleChangeInterceptor} bean configured to intercept
 *         requests for changing the user's locale based on the "lang" parameter.
 *         The parameter name is set to "lang" to identify the desired language in incoming requests.
 */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /**
 * Overrides the default implementation to add a {@link org.springframework.web.servlet.i18n.LocaleChangeInterceptor}
 * to the {@link org.springframework.web.servlet.config.annotation.InterceptorRegistry}.
 * This method is responsible for configuring and registering the locale change interceptor
 * to handle requests for changing the user's locale based on a specified parameter.
 *
 * @param registry The {@link org.springframework.web.servlet.config.annotation.InterceptorRegistry} to which
 *                the {@link org.springframework.web.servlet.i18n.LocaleChangeInterceptor} is added.
 *                Interceptors in the registry will be invoked in the specified order during request processing.
 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

/**
 * Configures and provides a {@link org.springframework.context.support.ReloadableResourceBundleMessageSource} bean
 * for handling message resources, such as internationalized messages, in the application.
 * The configured message source is an instance of {@link org.springframework.context.support.ReloadableResourceBundleMessageSource},
 * with the base name set to "classpath:/Messages" and default encoding set to "UTF-8".
 *
 * @return A {@link org.springframework.context.support.ReloadableResourceBundleMessageSource} bean configured to handle
 *         message resources in the application. The base name is set to "classpath:/Messages" to specify the location
 *         of the message properties files, and the default encoding is set to "UTF-8" for character encoding.
 */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/Messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
}

/**
 * Configures and provides a {@link org.thymeleaf.spring5.SpringTemplateEngine} bean
 * for processing Thymeleaf templates in the Spring application.
 * The configured template engine is an instance of {@link org.thymeleaf.spring5.SpringTemplateEngine},
 * with the template resolver set to the result of {@link #templateResolver()} and
 * the message source set to the result of {@link #messageSource()}.
 *
 * @return A {@link org.thymeleaf.spring5.SpringTemplateEngine} bean configured to process Thymeleaf templates
 *         in the Spring application. The template resolver and message source are set to the results of
 *         {@link #templateResolver()} and {@link #messageSource()}, respectively.
 */
@Bean
public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver());
    templateEngine.setMessageSource(messageSource());
    return templateEngine;
}

/**
 * Configures and provides a {@link org.thymeleaf.spring5.view.ThymeleafViewResolver} bean
 * for resolving Thymeleaf views in the Spring application.
 * The configured view resolver is an instance of {@link org.thymeleaf.spring5.view.ThymeleafViewResolver},
 * with the template engine set to the result of {@link #templateEngine()} and character encoding set to "UTF-8".
 *
 * @return A {@link org.thymeleaf.spring5.view.ThymeleafViewResolver} bean configured to resolve Thymeleaf views
 *         in the Spring application. The template engine is set to the result of {@link #templateEngine()},
 *         and the character encoding is set to "UTF-8" for view resolution.
 */
@Bean
public ThymeleafViewResolver thymeleafViewResolver() {
    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    resolver.setTemplateEngine(templateEngine());
    resolver.setCharacterEncoding("UTF-8");
    return resolver;
}

/**
 * Configures and provides a {@link org.thymeleaf.templateresolver.SpringResourceTemplateResolver} bean
 * for resolving Thymeleaf templates in the Spring application.
 * The configured template resolver is an instance of {@link org.thymeleaf.templateresolver.SpringResourceTemplateResolver},
 * with the prefix set to "classpath:/templates/", suffix set to ".html", template mode set to "HTML",
 * character encoding set to "UTF-8", and caching disabled for development.
 *
 * @return A {@link org.thymeleaf.templateresolver.SpringResourceTemplateResolver} bean configured to resolve
 *         Thymeleaf templates in the Spring application. The prefix, suffix, template mode, character encoding,
 *         and caching settings are specified to define how templates are resolved and processed.
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
