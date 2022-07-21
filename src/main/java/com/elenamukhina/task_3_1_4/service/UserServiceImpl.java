package com.elenamukhina.task_3_1_4.service;

import com.elenamukhina.task_3_1_4.entity.User;
import com.elenamukhina.task_3_1_4.repository.UserServiceCrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    private final PasswordEncoder passwordEncoder;
    private final UserServiceCrudRepository userServiceCrudRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserServiceCrudRepository userServiceCrudRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userServiceCrudRepository = userServiceCrudRepository;
    }

    // получение списка всех юзеров
    public List<User> getUsers() {
        return (List<User>) userServiceCrudRepository.findAll();
    }

    // добавление нового юзера
    public void saveUser(User user) {
        /*Optional<User> timeUser = Optional.ofNullable(getUser(user.getId()));
        if (timeUser.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUsername(user.getEmail());
            userServiceCrudRepository.save(user);
        } else {
            user.setUsername(user.getEmail());
            User user1 = timeUser.get();
            String passwordForm = user.getPassword();
            String passwordBD = user1.getPassword();
            if (passwordForm.equals(passwordBD)) {
                userServiceCrudRepository.save(user);
                user.setPassword(passwordBD);
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userServiceCrudRepository.save(user);
            }
        }*/

        // Optional<User> timeUser = Optional.ofNullable(getUser(user.getId()));
        User tUser = getUser(user.getId());
        if (tUser == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUsername(user.getEmail());
            userServiceCrudRepository.save(user);
        } else {
            user.setUsername(user.getEmail());
            User user1 = tUser;
            String passwordForm = user.getPassword();
            String passwordBD = user1.getPassword();
            if (passwordForm.equals(passwordBD)) {
                userServiceCrudRepository.save(user);
                user.setPassword(passwordBD);
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userServiceCrudRepository.save(user);
            }
        }
    }

    // получение юзера по id
    public User getUser(int id) {
        Optional<User> user = userServiceCrudRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }
    /*public User getUser(int id) {
        Optional<User> user = userServiceCrudRepository.findById(id);
        return user.get();
    }*/

    // удаление юзера по id
    public void deleteUser(int id) {
        userServiceCrudRepository.deleteById(id);
    }
}
