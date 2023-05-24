package com.amedakevin.app.webParent.admin.user;

import com.amedakevin.app.common.entity.Role;
import com.amedakevin.app.common.entity.User;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;
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
    @Test
    public void testCreateUserWithTwoRoles(){
        Role roleSalesPerson=entityManager.find(Role.class,2);
        Role roleAssistant=entityManager.find(Role.class,5);
        User userGitau=User.builder()
                .email("paulgitau@gmail.com")
                .password("gitau")
                .firstName("Paul")
                .lastName("Gitau")
                .roles(Set.of(roleSalesPerson,roleAssistant))
                .build();
        User savedUser=userRepository.save(userGitau);
        assertThat(savedUser.getUserId()).isGreaterThan(0);
    }
    @Test
    public void testListAllUsers(){
        Iterable<User> listUsers=userRepository.findAll();
        listUsers.forEach(user->System.out.println(user));
    }
    @Test
    public void testGetUserById(){
        Optional<User> userName=userRepository.findById(2);
        assertThat(userName.get()).isNotNull();
        System.out.println(userName);
    }

    @Test
    public void testUpdateUserDetails(){
        User userName=userRepository.findById(2).get();
        userName.setEnabled(true);
        userRepository.save(userName);
    }

    @Test
    public void testUpdateUserRoles(){
        User userToChange=userRepository.findById(2).get();
        Role roleSalesPerson=Role.builder().roleId(2).build();
        Role roleEditor=Role.builder().roleId(3).build();
        userToChange.getRoles().remove(roleSalesPerson);
        userToChange.addRole(roleEditor);
        userRepository.save(userToChange);
    }
    @Test
    public void deleteUser(){
        Integer userId=2;
        userRepository.deleteById(userId);
    }
}
