package com.amedakevin.app.webParent.admin.user;

import com.amedakevin.app.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTests {
    @Autowired
    private RoleRepository roleRepository;
    @Test
    public void testCreateFirstRole(){
        Role roleAdmin=Role.builder()
                .name("Admin")
                .description("manage everything")
                .build();
       Role savedRole=roleRepository.save(roleAdmin);
       assertThat(savedRole.getId()).isGreaterThan(0);
    }
}
