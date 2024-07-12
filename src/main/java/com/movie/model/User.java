/**
 * 
 */
package com.movie.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * This is used for storing the user details in the database along with the other information
 */
@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User {

	@Id
	@SequenceGenerator(name = "user_id_sequence", sequenceName = "sq_user", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
	private int id;

	private String firstName;

	private String lastName;

	private String email; // This is for email confirmation

	private int phoneNumber; // this should be unique and should be validated

	private String password; // This will be encrypted and persisted in User table
}
