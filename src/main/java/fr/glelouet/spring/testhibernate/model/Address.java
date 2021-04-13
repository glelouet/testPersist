package fr.glelouet.spring.testhibernate.model;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Embeddable
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class Address {

	@NonNull
	private Integer ZIP;

	@NonNull
	private String street;

	@NonNull
	private Integer streetNumber;

}
