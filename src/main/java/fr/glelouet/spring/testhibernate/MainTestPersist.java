package fr.glelouet.spring.testhibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.glelouet.spring.testhibernate.model.Address;
import fr.glelouet.spring.testhibernate.model.Bakery;
import fr.glelouet.spring.testhibernate.model.Client;
import fr.glelouet.spring.testhibernate.model.Pastry;
import fr.glelouet.spring.testhibernate.repository.BakeryRepo;
import fr.glelouet.spring.testhibernate.repository.ClientRepo;
import fr.glelouet.spring.testhibernate.repository.PastryRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class MainTestPersist {

	public static void main(String[] args) {
		SpringApplication.run(MainTestPersist.class, args);
	}

	@Autowired
	PastryRepository pastries;

	@Autowired
	BakeryRepo bakeries;

	@Autowired
	ClientRepo clients;

	@Bean
	public CommandLineRunner createCall() {
		return this::execute;
	}

	protected void execute(String[] args) {
		var pac = pastries.save(new Pastry("Pain au chcolat"));
		var cr = pastries.save(new Pastry("Croissant"));
		var religieuse = pastries.save(new Pastry("Religieuse"));
		var cap = pastries.save(new Pastry("Chausson aux pommes"));

		var lgap = bakeries.save(new Bakery("Le grenier à pain", new Address(44100, "boulevard Boulay Paty", 53)));
		var mj = bakeries.save(new Bakery("Maison Jamet", new Address(44300, "Rue de la Bourgeonnière", 80)));

		// fetch all bakeries
		log.info("Bakeries found with findAll():");
		log.info("-------------------------------");
		for (Bakery bkr : bakeries.findAll()) {
			log.info(bkr.toString());
		}
		log.info("");


		// fetch all pastries
		log.info("Pastries found with findAll():");
		log.info("-------------------------------");
		for (Pastry pstr : pastries.findAll()) {
			log.info(pstr.toString());
		}
		log.info("");

		// fetch an individual pastry by ID
		Pastry pstr = pastries.findById(1L);
		log.info("Pastry found with findById(1L):");
		log.info("--------------------------------");
		log.info(pstr.toString());
		log.info("");

		// fetch pastry by name
		log.info("Pastry found with findByName('croissant'):");
		log.info("--------------------------------------------");
		pastries.findByName("croissant").forEach(croissant -> {
			log.info(croissant.toString());
		});

		// fetch pastry by name
		log.info("Pastry found with findByNameIgnoreCase('croissant'):");
		log.info("--------------------------------------------");
		pastries.findByNameIgnoreCase("croissant").forEach(croissant -> {
			log.info(croissant.toString());
		});

		log.info("bakery is " + lgap);
		lgap.getOffers().add(pac);
		log.info("after adding Pain au chocolat " + lgap);
		log.info("searched for by name : " + bakeries.findByNameIgnoreCase("le grenier à pain"));
		lgap.getOffers().add(cr);
		lgap.getOffers().add(religieuse);
		bakeries.save(lgap);

		mj.getOffers().add(pac);
		mj.getOffers().add(cr);
		mj.getOffers().add(cap);
		bakeries.save(mj);


		log.info("pain au chocolat is sold by : " + pac.getSellers());
		for (Pastry ps : pastries.findAll()) {
			log.info(ps.getName() + " is sold by : " + ps.getSellers());
		}

		var jvj = clients.save(new Client("Jean", "Valjean", new Address(91000, "ma rue", 12)));
		var jm = clients.save(new Client("Jean", "Moulin", new Address(28000, "street life", 42)));
		var pj = clients.save(new Client("Pierre", "Jeantil", new Address(10000, "place des étoiles", 208558746)));

		log.info("search for jean");
		for (Client cl : clients.findMatching("jean")) {
			log.info("found " + cl);
		}
	}

}
