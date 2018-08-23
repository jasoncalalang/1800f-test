package com.ibm.sample;

import com.google.common.base.Predicate;
import com.ibm.sample.controller.SampleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackageClasses = {SampleController.class})
public class SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

	@Bean
	public Docket sampleApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("sample")
				.apiInfo(apiInfo())
				.select()
				.paths(samplePaths())
				.build();
	}

	private Predicate<String> samplePaths() {
		return or (regex("/count"), regex("/update.*"), regex("/update/*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Springfox petstore API")
				.description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum " +
						"has been the industry's standard dummy text ever since the 1500s, when an unknown printer "
						+ "took a " +
						"galley of type and scrambled it to make a type specimen book. It has survived not only five " +
						"centuries, but also the leap into electronic typesetting, remaining essentially unchanged. " +
						"It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum " +
						"passages, and more recently with desktop publishing software like Aldus PageMaker including " +
						"versions of Lorem Ipsum.")
				.termsOfServiceUrl("http://springfox.io")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
				.version("2.0")
				.build();

	}
}
