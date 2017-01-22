package com.pylonmusic.playground.repositiory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pylonmusic.playground.domain.Address;
import com.pylonmusic.playground.domain.Person;
import com.pylonmusic.playground.repository.PersonRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class JpaPersonRepositoryTest {
	
	@Autowired
	private PersonRepository repo;
	
	@Test
	public void test() {
		Person p = new Person("Johan", "Fredin", "johan@taikes.com", "0761253033", 
				new Address("Hagåkersgatan", "Mölndal", "43141", "VGR", "SE"));
		p.setRelations();
		Person p2 = new Person();
		p2.copyDataFromEntity(p);
		p2.setLastName("Babadook");
		
		repo.save(Arrays.asList(p, p2));
		
		assertThat(p.getId()).isGreaterThan(0L);
		
		List<Person> lns = repo.findByLastName("Fredin");
		assertThat(lns).isNotNull().size().isGreaterThanOrEqualTo(1);
	}

}
