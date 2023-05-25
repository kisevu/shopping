package com.amedakevin.app.common.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(length = 128,nullable = false,unique = true)
    private String email;
    @Column(length = 64, nullable = false)
    private String password;
    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;
    @Column(name="last_name", length = 45,nullable = false)
    private String lastName;
    @Column(length = 64)
    private String photos;
    private boolean enabled;
    @ManyToMany
    @JoinTable(
            name="users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role>  roles=new HashSet<>();
    public void addRole(Role role){
        this.roles.add(role);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(
                roles.stream()
                        .map(role -> role.getName())
                        .findFirst()
                        .get()
        ));
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
