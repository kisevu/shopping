package com.amedakevin.app.common.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    @Column(length = 40, nullable = false, unique = true)
    private String name;
    @Column(length = 150,nullable = false)
    private String description;
    public String toString(){
        return this.name;
    }
}
