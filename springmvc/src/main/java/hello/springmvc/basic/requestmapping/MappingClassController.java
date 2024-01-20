package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    @GetMapping
    public String users() {
        return "get users";
    }

    @PostMapping
    public String addUser() {
        return "post user";
    }

    @GetMapping("/{userId}")
    public String user(@PathVariable("userId") String userId) {
        return "get user = " + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") String userId) {
        return "update user = " + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        return "delete user = " + userId;
    }
}
