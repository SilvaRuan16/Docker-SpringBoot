package spring.avaliacao.spring.controller;

import java.util.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import spring.avaliacao.spring.model.UserModel;

@RestController
@RequestMapping("/users")
public class UserController {
    private static List<UserModel> users = new ArrayList<>();
    BCryptPasswordEncoder hash = new BCryptPasswordEncoder();

    public UserController() {

        if (users.isEmpty()) {
            users.add(new UserModel(1L, "James", hash.encode("201v45")));
            users.add(new UserModel(2L, "Lucas", hash.encode("091ce4")));
            users.add(new UserModel(3L, "Clebin", hash.encode("11/1v8")));
            users.add(new UserModel(4L, "Marcia", hash.encode("201vcc")));
        }
    }

    @GetMapping
    public List<UserModel> listAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable("id") Long userId) {
        return users.stream().filter(identify -> identify.getId().equals(userId)).findFirst().orElse(null);
    }

    @PostMapping
    public UserModel createUser(@RequestBody UserModel user) {
        String password = hash.encode(user.getPassword());
        user.setPassword(password);
        users.add(user);
        return user;
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable("id") Long userId, @RequestBody UserModel updateUser) {
        Optional<UserModel> user = users.stream().filter(identify -> identify.getId().equals(userId)).findFirst();
        if (user.isPresent()) {
            UserModel userModel = user.get();
            userModel.setUserName(updateUser.getUserName());
            String password = hash.encode(updateUser.getPassword());
            userModel.setPassword(password);
            return userModel;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public UserModel deleteUser(Long userId) {
        Optional<UserModel> user = users.stream().filter(identify -> identify.getId().equals(userId)).findFirst();
        if (user.isPresent()) {
            this.users.removeIf(identify -> identify.getId().equals(userId));
        }
        return new UserModel();
    }
}
