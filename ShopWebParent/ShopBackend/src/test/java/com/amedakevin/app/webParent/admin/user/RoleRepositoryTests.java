package com.amedakevin.app.webParent.admin.user;

import com.amedakevin.app.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
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
       assertThat(savedRole.getRoleId()).isGreaterThan(0);
    }
    @Test
    public void testCreateRestRoles(){
        Role roleSalesPerson=Role.builder()
                .name("Salesperson")
                .description("manage product price, customers, shipping," +
                        "orders and sales repor")
                .build();
        Role roleEditor=Role.builder()
                .name("Editor")
                .description("manage categories, brands,products," +
                        "articles and menus")
                .build();
        Role roleShipper=Role.builder()
                .name("Shipper")
                .description("view products, view orders and update order status")
                .build();
        Role roleAssistant=Role.builder()
                .name("Assistant")
                .description("manage questions and reviews")
                .build();
        roleRepository.saveAll(List.of(roleSalesPerson,roleEditor,roleShipper,roleAssistant));
    }
}
