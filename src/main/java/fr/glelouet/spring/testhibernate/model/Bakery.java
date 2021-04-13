package fr.glelouet.spring.testhibernate.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = { "offers", "id" })
public class Bakery {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NonNull
	private String name;

	@NonNull
	@Embedded
	private Address address;

	@Setter
	@ManyToMany(
			// fetch = FetchType.EAGER,
			cascade = CascadeType.ALL)
	@JoinTable(
			name = "bakery_offers",
			joinColumns = @JoinColumn(name = "bakery_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "pastry_id", referencedColumnName = "id"))
	private Set<Pastry> offers = new HashSet<>();
}
