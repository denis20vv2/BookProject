package com.example.authorizationservice.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

   @Id
   @SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_seq",
            allocationSize = 1
   )
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
   @Column(nullable = false)
   private Long user_id;

   @Column(nullable = false)
   private String login;

   @Column(nullable = false)
   private Long rule;

   @Column(nullable = false)
   private String password;

   @Column(nullable = false)
   private String name;


}
