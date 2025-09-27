package com.training.digitalgoldloan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Table(name = "addresses")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDRESS_ID")
	private Integer addressId;

	@NotBlank(message = "Street is required")
	@Size(max = 255, message = "Street must be less than 255 characters")
	@Column(name = "STREET", nullable = false)
	private String street;

	@NotBlank(message = "City is required")
	@Pattern(regexp = "[A-Za-z]+", message = "City should contain only Alphabets")
	@Size(max = 100, message = "City must be less than 100 characters")
	@Column(name = "CITY", nullable = false)
	private String city;

	@NotBlank(message = "State is required")
	@Pattern(regexp = "[A-Za-z\\s]+", message = "State should contain only Alphabets and Spaces")
	@Size(max = 100, message = "State must be less than 100 characters")
	@Column(name = "STATE", nullable = false)
	private String state;

	@Pattern(regexp = "\\d{5,6}", message = "Postal code must be 5 or 6 digits")
	@NotBlank(message = "Postal code is required")
	@Column(name = "POSTAL_CODE")
	private String postalCode;

	@NotBlank(message = "Country is required")
	@Size(max = 100, message = "Country must be less than 100 characters")
	@Column(name = "COUNTRY", nullable = false)
	private String country;

	public String getFullAddress() {
		return street + ", " + city + ", " + state + " - " + postalCode + ", " + country;
	}
}