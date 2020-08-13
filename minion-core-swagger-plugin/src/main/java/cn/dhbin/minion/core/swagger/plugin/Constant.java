package cn.dhbin.minion.core.swagger.plugin;

/**
 * @author donghaibin
 * @date 2020/8/10
 */
public interface Constant {

    /**
     * swagger {@literal @ApiModel} 类
     */
    String SWAGGER_API_MODEL_CLASS_NAME = "io.swagger.annotations.ApiModel";

    /**
     * swagger {@literal @ApiModelProperty }类
     */
    String SWAGGER_API_MODEL_PROPERTY_CLASS_NAME = "io.swagger.annotations.ApiModelProperty";


    /**
     * swagger {@literal @Api} 类
     */
    String SWAGGER_API_CLASS_NAME = "io.swagger.annotations.Api";

    /**
     * swagger {@literal @ApiOperation} 类
     */
    String SWAGGER_API_OPERATION_CLASS_NAME = "io.swagger.annotations.ApiOperation";

    /**
     * swagger {@literal @ApiImplicitParams} 类
     */
    String SWAGGER_API_IMPLICIT_PARAMS_CLASS_NAME = "io.swagger.annotations.ApiImplicitParams";

    /**
     * swagger {@literal @ApiImplicitParam} 类
     */
    String SWAGGER_API_IMPLICIT_PARAM_CLASS_NAME = "io.swagger.annotations.ApiImplicitParam";

    /**
     * swagger {@literal @Authorization} 类
     */
    String SWAGGER_AUTHORIZATION_CLASS_NAME = "io.swagger.annotations.Authorization";

    /**
     * swagger {@literal @ApiParam}
     */
    String SWAGGER_API_PARAM_CLASS_NAME = "io.swagger.annotations.ApiParam";

    /**
     * spring {@literal @RequestMapping}
     */
    String SPRING_REQUEST_MAPPING = "org.springframework.web.bind.annotation.RequestMapping";
    /**
     * spring {@literal @GetMapping}
     */
    String SPRING_GET_MAPPING = "org.springframework.web.bind.annotation.GetMapping";
    /**
     * spring {@literal @PostMapping}
     */
    String SPRING_POST_MAPPING = "org.springframework.web.bind.annotation.PostMapping";
    /**
     * spring {@literal @PutMapping}
     */
    String SPRING_PUT_MAPPING = "org.springframework.web.bind.annotation.PutMapping";
    /**
     * spring {@literal @DeleteMapping}
     */
    String SPRING_DELETE_MAPPING = "org.springframework.web.bind.annotation.DeleteMapping";

    /**
     * spring {@literal @PatchMapping}
     */
    String SPRING_PATCH_MAPPING = "org.springframework.web.bind.annotation.PatchMapping";

    /**
     * spring @RestController
     */
    String SPRING_REST_CONTROLLER_CLASS_NAME = "org.springframework.web.bind.annotation.RestController";

    /**
     * Spring @Controller
     */
    String SPRING_CONTROLLER_CLASS_NAME = "org.springframework.stereotype.Controller";

    /**
     * Spring @RequestBody
     */
    String SPRING_REQUEST_BODY_CLASS_NAME = "org.springframework.web.bind.annotation.RequestBody";


    /**
     * 作用在api模型类上的注释 {@literal @apiModel} 标记生成注解{@literal @ApiModel}
     */
    String API_MODEL_TAG = "apiModel";

    /**
     * 作用在Controller类上的注释
     */
    String API_NOTE_TAG = "apiNote";


}
