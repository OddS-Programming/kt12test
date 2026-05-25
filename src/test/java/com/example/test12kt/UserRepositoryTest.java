package com.example.test12kt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@DataJpaTest
class UserRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveUser() {

        User user = new User();
        user.setName("Ivan");

        User saved = userRepository.save(user);

        assertNotNull(saved.getId());
    }

    @Test
    void shouldFindUser() {

        User user = new User();
        user.setName("Ivan");

        userRepository.save(user);

        List<User> users = userRepository.findAll();

        assertFalse(users.isEmpty());
    }
}
