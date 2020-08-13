import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author donghaibin
 * @apiNote 例子
 */
@RestController
@RequestMapping("/demo")
public class ControllerDemo {


    /**
     * 说hi
     *
     * @param s 参数1
     * @return 返回
     * @apiNote say hi
     */
    @RequestMapping
    public String hi(String s) {
        return "hi";
    }

}