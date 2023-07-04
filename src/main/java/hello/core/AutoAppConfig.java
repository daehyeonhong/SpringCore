package hello.core;

import org.springframework.context.annotation.Configuration;

@Configuration(
//        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Configuration.class})
//        , basePackageClasses = {AutoAppConfig.class}
//        , basePackages = {"hello.core"}
)
public class AutoAppConfig {
}
