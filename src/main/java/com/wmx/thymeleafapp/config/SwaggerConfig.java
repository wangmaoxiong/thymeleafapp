package com.wmx.thymeleafapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 配置
 * <p>
 * 1、@EnableSwagger2：表示应用开启 Swagger2 支持。
 * 2、@EnableSwagger2 注解需要和 @Configuration 注解一起使用
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/20 9:50
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * {@link Docket} 是一个构建器，它是 SpringFox 框架的主要接口，用于提供合理的默认值和方便的配置方法。
     * 1、必须使用 @Bean 将 Docket 添加到容器中
     * 2、DocumentationType.SWAGGER_2：表示 文档类型为 2.0 版本.
     * 3、apiInfo(ApiInfo apiInfo): 将 api 的元信息设置为包含在 ResourceListing 响应 json 中
     * 4、ApiSelectorBuilder select(): 启动用于 api 选择生成器。
     * 5、RequestHandlerSelectors.basePackage：表示生成 api 的基础包路径，即对这个包下进行扫描
     * 6、PathSelectors.any：表示对任意请求路径都加入文档、同理还有 none 都不加入、regex 满足正则的请求加入
     * 7、build(): 返回构建好的的 docket
     *
     * @return
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wmx"))
                .paths(PathSelectors.any())
                .build().pathMapping("");
    }

    /**
     * 创建 api 基本信息
     * 1、title(String title)：页面标题内容，会显示在页面头部，默认 <h2> 标签包裹.
     * 2、description(String description)：Api 描述信息，显示在 title 下方
     * 3、termsOfServiceUrl: 服务条款 url。页面上默认显示在描述信息下面，显示为 "Terms of service"，然后用值作为超链接地址.
     * 4、version：更新版本
     * 5、contact：联系人信息，有: 名称、网站 url 地址、联系 email 地址。显示在服务条款下面。
     * 6、license：更新此 API 的许可证信息，显示在联系人下面
     * 7、这些值可以为空
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("thymeleafApp 应用提供的 REST ful APIs")
                .description("技术支持 2268461750@qq.com ")
                .termsOfServiceUrl("https://wangmaoxiong.blog.csdn.net/")
                .version("1.0")
                .contact(new Contact("汪茂雄", "https://blog.csdn.net/wangmx1993328", "2268461750@qq.com"))
                .license("马自达 & 汪茂雄 为您提供技术支持!")
                .build();
    }
}
