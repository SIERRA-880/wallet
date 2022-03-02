package com.example.accessingdatamariadb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String userID;
	private Integer natID;
	private String password;
	private String lastName;
	private String firstName;
	private String language;
	private Long id;


}
