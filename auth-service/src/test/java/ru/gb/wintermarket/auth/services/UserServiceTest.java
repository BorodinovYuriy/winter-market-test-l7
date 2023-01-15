package ru.gb.wintermarket.auth.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gb.wintermarket.auth.entities.User;
import ru.gb.wintermarket.auth.repositories.UserRepository;

@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    void saveAndReturnNewUser() {
        //второй раз при запуске-> исключение, так как Юзер уже был создан (можно удалить тестовую БД)
        User testUser = new User();
        testUser.setUsername("testUsername");
        testUser.setPassword("testPassword");

        User user = userService.saveAndReturnNewUser(testUser);

        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals(testUser.getUsername(), user.getUsername());

        User savedUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        Assertions.assertEquals(user.getId(), savedUser.getId());
        log.warn("OK------------"+savedUser.getUsername()+"......delete....");
        userRepository.delete(savedUser);

    }

}