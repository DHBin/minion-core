# minion-core-swagger-plugin

swagger注解生成插件，通过javadoc注释生成Swagger文档注解，实现swagger零侵入。

目前支持生成以下注解：

- @Api
- @ApiOperation
- @ApiImplicitParams
- @ApiImplicitParam
- @ApiModel
- @ApiModelProperty

## 快速开始

### 引入依赖

#### maven

```xml
        <dependency>
            <groupId>cn.dhbin</groupId>
            <artifactId>minion-core-swagger-plugin</artifactId>
            <version>1.0.1</version>
        </dependency>
```



#### gradle

```groovy
annotationProcessor group: 'cn.dhbin', name: 'minion-core-swagger-plugin', version: '1.0.1'
```

## 编写javadoc

### Controller/RestController

```java
/**
 * @apiNote Demo
 */
@RequestMapping("/demo")
public class DemoController {

}
```

生成

```java
@Api(
    tags = {"Demo"}
)
@RestController
@RequestMapping({"/demo"})
public class DemoController {
    
}
```

> @apiNote Demo ---> @Api(tags = {"Demo"})

支持多个@apiNote，如果@apiNote不存在，取注释中的首句

```java
/**
 * Demo Controller
 * <p>
 * 描述
 *
 * @apiNote Demo
 */
```

上面例子中，首句是Demo Controller

### Mapping方法

```java
    /**
     * 这是一个例子
     * <p>
     * 我是描述
     *
     * @param a    a参数
     * @param b    b参数
     * @param i    数字
     * @param uuid uuid
     * @return hi
     * @apiNote say hi
     */
    @GetMapping("/hi")
    public String hi(String a, String b, int i, UUID uuid) {
        return "";
    }
```

生成

```java
    @ApiImplicitParams({@ApiImplicitParam(
    dataType = "string",
    name = "a",
    value = "a参数"
), @ApiImplicitParam(
    dataType = "string",
    name = "b",
    value = "b参数"
), @ApiImplicitParam(
    dataType = "int",
    name = "i",
    value = "数字"
), @ApiImplicitParam(
    dataType = "uuid",
    name = "uuid",
    value = "uuid"
)})
    @ApiOperation(
        notes = "这是一个例子<p>\n 我是描述",
        value = "say hi"
    )
    @GetMapping({"/hi"})
    public String hi(String a, String b, int i, UUID uuid) {
        return "";
    }
```

### model

```java
/**
 * @apiModel 用户
 */
public class User {


    /**
     * 年龄
     */
    private int age;

    /**
     * 名字
     */
    private String name;
    
    getter/setter...
}
```

生成

```java
@ApiModel("用户")
public class User {
    
    @ApiModelProperty("年龄")
    private int age;
    
    @ApiModelProperty("名字")
    private String name;
```

  }

## 自定义生成规则

提供spi机制扩展，实现`cn.dhbin.processor.spi.DocumentParser`中的方法，如果不想生成某个注解可以返回null。

```java
public interface DocumentParser {

    /**
     * 解析生成@Api
     *
     * @param docInfo controller类上的注释信息
     * @return {@link ApiMetadata}
     */
    @Nullable
    default ApiMetadata resolveApi(@Nullable DocInfo docInfo) {
        return null;
    }

    /**
     * 解析生成@ApiOperation
     *
     * @param docInfo 带mapping注解的方法上的注释信息
     * @return {@link ApiOperationMetadata}
     */
    @Nullable
    default ApiOperationMetadata resolveApiOperation(@Nullable DocInfo docInfo) {
        return null;
    }

    /**
     * 解析生成@ApiImplicitParams
     *
     * @param docInfo 带mapping注解的方法上的注释信息
     * @return {@link ApiImplicitParamMetadata}
     */
    @Nullable
    default List<ApiImplicitParamMetadata> resolveApiImplicitParam(@Nullable DocInfo docInfo) {
        return null;
    }

    /**
     * 解析生成@ApiModel
     *
     * @param docInfo bean类的的注释信息
     * @return {@link ApiModelMetadata}
     */
    @Nullable
    default ApiModelMetadata resolveApiModel(@Nullable DocInfo docInfo) {
        return null;
    }


    /**
     * 解析生成@ApiModelProperty
     *
     * @param docInfo bean类的方法的注释信息
     * @return {@link ApiModelPropertyMetadata}
     */
    @Nullable
    default ApiModelPropertyMetadata resolveApiModelProperty(@Nullable DocInfo docInfo) {
        return null;
    }

}
```