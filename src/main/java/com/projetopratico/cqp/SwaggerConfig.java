// package com.projetopratico.cqp;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

// @Configuration
// @EnableSwagger2
// public class SwaggerConfig {
//     @Bean
//     public Docket api() {
//         return new Docket(DocumentationType.SWAGGER_2)
//                 .select()
//                 .apis(RequestHandlerSelectors.basePackage("com.projetopratico.cqp.controllers"))
//                 .paths(PathSelectors.any())
//                 .build();
//     }
// }

// // import org.springframework.web.bind.annotation.*;
// // import io.swagger.annotations.*;

// // @RestController
// // @RequestMapping("/api")
// // @Api(tags = "Exemplo", description = "Controlador de Exemplo")
// // public class ExemploController {
    
// //     @GetMapping("/exemplo")
// //     @ApiOperation(value = "Obter exemplo", notes = "Este endpoint retorna um exemplo")
// //     public String getExemplo() {
// //         return "Exemplo";
// //     }
// // }
