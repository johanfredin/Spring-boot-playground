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

import com.pylonmusic.playground.TestFixture;
import com.pylonmusic.playground.domain.Person;
import com.pylonmusic.playground.repository.PersonRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class JpaPersonRepositoryTest extends AbstractRepositoryTest<Person, PersonRepository> {
	
	@Autowired
	private PersonRepository repo;
	
	@Test
	public void testFindByLastName() {
		Person p = getEntity1();
		p.setRelations();
		Person p2 = getEntity2();
		
		repo.save(Arrays.asList(p, p2));
		
		assertThat(p.getId()).isGreaterThan(0L);
		
		List<Person> lns = repo.findByLastName("Doe");
		assertThat(lns).isNotNull().size().isGreaterThanOrEqualTo(1);
	}

	@Override
	protected PersonRepository getRepository() {
		return this.repo;
	}

	@Override
	protected Person getEntity1() {
		return TestFixture.getValidPerson();
	}

	@Override
	protected Person getEntity2() {
		return TestFixture.getValidPerson2();
	}

}
