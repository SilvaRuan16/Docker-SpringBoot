package spring.avaliacao.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import spring.avaliacao.spring.model.UserModel;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final String FILE_PATH = "users.ser";
    private List<UserModel> users = new ArrayList<>();

    public UserController() {
        loadUsers();

        if (users.isEmpty()) {
            users.add(new UserModel(1L, "James", "201v45"));
            users.add(new UserModel(2L, "Lucas", "091ce4"));
            users.add(new UserModel(3L, "Clebin", "11/1v8"));
            users.add(new UserModel(4L, "Marcia", "201vcc"));
            saveUsers();
        }
    }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(users);
        } catch (Exception e) { e.printStackTrace(); }
    }

    @SuppressWarnings("unchecked")
    private void loadUsers() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                users = (List<UserModel>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        }
    }

    @GetMapping
    public List<UserModel> listAllUsers() { return users; }

    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable("id") Long userId) {
        return users.stream().filter(identify -> identify.getId().equals(userId)).findFirst().orElse(null);
    }

    @PostMapping
    public UserModel createUser(@RequestBody UserModel user) {
        BCryptPasswordEncoder hash = new BCryptPasswordEncoder();
        String password = hash.encode(user.getPassword());
        user.setPassword(password);
        users.add(user);
        saveUsers();
        return user;
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable("id") Long userId, @RequestBody UserModel updateUser) {
        Optional<UserModel> user = users.stream().filter(identify -> identify.getId().equals(userId)).findFirst();
        if (user.isPresent()) {
            UserModel userModel = user.get();
            userModel.setUserName(updateUser.getUserName());
            userModel.setPassword(updateUser.getPassword());
            saveUsers();
            return userModel;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public UserModel deleteUser(Long userId) {
        Optional<UserModel> user = users.stream().filter(identify -> identify.getId().equals(userId)).findFirst();
        if (user.isPresent()) {
            this.users.removeIf(identify -> identify.getId().equals(userId));
            saveUsers();
        }
        return new UserModel();
    }
}
