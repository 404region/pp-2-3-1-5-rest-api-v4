package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String roleType;

    // constructors
    public Role() {
    }

    public Role(String roleType) {
        this.roleType = roleType;
    }

    public Role(Integer id) {
        this.id = id;
    }

    public Role(Integer id, String roleType) {
        this.id = id;
        this.roleType = roleType;
    }

    // getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }


    // override methods
    @Override
    public String getAuthority() {
        return getRoleType();
    }

    @Override
    public String toString() {
        return roleType.substring(5);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(roleType, role.roleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleType);
    }
}
