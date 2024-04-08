package com.oppspark.shop_porject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Member {
    @Id
    private String username;

    private String password;

    private String displayName;
}
