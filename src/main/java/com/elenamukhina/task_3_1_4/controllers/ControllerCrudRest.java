package com.elenamukhina.task_3_1_4.controllers;

import com.elenamukhina.task_3_1_4.entity.User;
import com.elenamukhina.task_3_1_4.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class ControllerCrudRest {

    private final UserServiceImpl userServiceImpl;

    public ControllerCrudRest(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    // получение списка всех юзеров
    @GetMapping
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userServiceImpl.getUsers();
        if (users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // получение юзера по id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = userServiceImpl.getUser(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // добавление нового юзера
    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        userServiceImpl.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // изменение юзера
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        userServiceImpl.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // удаление юзера
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
