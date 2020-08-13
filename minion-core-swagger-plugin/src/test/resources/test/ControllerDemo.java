import org.springframework.web.bind.annotation.RequestBody;
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
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @return 返回
     * @apiNote say hi
     */
    @RequestMapping
    public String hi(String s, int b, Integer c, @RequestBody RequestDto d) {
        return "hi";
    }

    /**
     * 无apiNote
     *
     * @return 返回
     */
    @RequestMapping
    public String hi2() {
        return "hi";
    }

    public static class RequestDto {

        private String a;

        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }
}