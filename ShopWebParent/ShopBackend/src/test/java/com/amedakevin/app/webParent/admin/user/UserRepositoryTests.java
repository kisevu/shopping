package com.amedakevin.app.webParent.admin.user;

import com.amedakevin.app.common.entity.Role;
import com.amedakevin.app.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
       Role roleAdmin=entityManager.find(Role.class,1);
        User userAmeda=User.builder()
                .email("amedakevin@gmail.com")
                .password("ameda")
                .firstName("Kevin")
                .lastName("Ameda")
                .roles(Set.of(roleAdmin))
                .build();
        User savedUser=userRepository.save(userAmeda);
        assertThat(savedUser.getUserId()).isGreaterThan(0);
    }
}
