package com.mxixm.spring.boot.chapter03.argument;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ArgumentResolveController {

    @RequestMapping("argument1")
    public String argument(HttpServletRequest request) {
        System.out.println("访问者IP为" + request.getRemoteAddr());
        return "argumentView";
    }

    @RequestMapping("user/{userId}/topic/{topicId}")
    public String topic(@PathVariable Integer userId, @PathVariable Integer topicId, @PathVariable Optional<Integer> test) {
        System.out.println("userId is " + userId + ", topicId is " + topicId);
        return "argumentView";
    }

    // 访问/users/year/66;hobbies=computer,math;year=1992
    @GetMapping("/users/year/{userId}")
    public void findUserByIdWithYear(@PathVariable String userId, @MatrixVariable int year) {
        // userId = 66, year=1992
    }

    // pathVar属性的使用，访问 /users/66;q=11/things/21;q=22
    @GetMapping("/users/{userId}/things/{thingId}")
    public void findThingWithMatrix(
            @MatrixVariable(name = "q", pathVar = "userId") int q1,
            @MatrixVariable(name = "q", pathVar = "thingId") int q2) {
        // q1 == 11
        // q2 == 22
    }

    // 访问 /users/66，不提供q的值
    @GetMapping("/users/{userId}")
    public void findUserByIdWithDefault(@MatrixVariable(required = false, defaultValue = "1") int q) {
        // required即使不设置，使用默认值true，效果也和设置为false一样。
        // q == 1
    }

    // 访问 /usersMap/66;q=11;r=12/things/21;q=22;s=23
    @GetMapping("/usersMap/{userId}/things/{thingId}")
    public void findThingWithMatrixMap(
            @MatrixVariable MultiValueMap<String, String> matrixVars,
            @MatrixVariable(pathVar = "thingId") MultiValueMap<String, String> thingMatrixVars) {
        // 两个MultiValueMap合并的结果
        // matrixVars: ["q" : [11, 22], "r" : 12, "s" : 23]
        // thingMatrixVars: ["q" : 22, "s" : 23]
        // 参数也可以声明为Map类型，但是当一个矩阵变量对应多个值时，只会取第一个值，后面的值会丢失
    }

    // 访问 /users?userId=66
    @GetMapping("/users")
    public void findUserById(String userId) {
        // userId=66
    }

    // 访问 /findUser?firstName=Guang&lastName=shan&lastName=666
    @GetMapping("/findUser")
    public void findUserByName(@RequestParam MultiValueMap<String, String> params) {
        // params: ["firstName" : "Guang", "lastName": ["shan", "666"]]
        System.out.println(params);
    }

    // 请求头为Accept: text/html,application/xhtml+xml,application/xml;q=0.9
    @GetMapping("/getAccept")
    public void getAccept(@RequestHeader List<String> accept) {
        // accept: ["text/html", "application/xhtml+xml", "application/xml;q=0.9"]
        System.out.println(accept);
    }

    // 请求包含如下Cookie：JSESSIONID=415A4AC178C59DACE0B2C9CA727CDD84
    @GetMapping("/getCookie")
    public void getCookie(@CookieValue("JSESSIONID") String jSessionId) {
        // jSessionId: “415A4AC178C59DACE0B2C9CA727CDD84”
        System.out.println(jSessionId);
    }

}
