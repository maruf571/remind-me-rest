package me.remind.rest.sandbox.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class User extends AbstractEntity {

    public User() {
        super();
    }

    @Column(length = 32)
    private String firstName;

    @Column(length = 32)
    private String surname;

    @Column(length = 32)
    private String position;

    @Column(length = 128)
    private String gitHubProfileUrl;
}
