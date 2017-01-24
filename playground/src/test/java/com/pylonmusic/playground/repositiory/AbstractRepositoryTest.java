package com.pylonmusic.playground.repositiory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.pylonmusic.playground.domain.AbstractEntity;
import com.pylonmusic.playground.repository.BaseRepository;



/**
 * An abstract test class that tests basic CRUD functionality. All repositories
 * can reuse these tests, hereby reducing code duplication.
 * 
 * This class uses the Template pattern. This abstract class is the template
 * that coordinates basic test cases. The concrete implementations provide the
 * specific details, such as which entities and repositories to use etc.
 * 
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
@TestPropertySource(locations="classpath:application_test.properties")
public abstract class AbstractRepositoryTest<T extends AbstractEntity, G extends BaseRepository<T>> {

	private static Logger log = Logger.getLogger(AbstractRepositoryTest.class.getName());

	/**
	 * Subclasses must return the repository to use
	 */
	protected abstract G getRepository();

	/**
	 * Sublcasses must return a valid entity to persist.
	 */
	protected abstract T getEntity1();

	/**
	 * Sublclasses must return a second valid entity. Should be different from
	 * entity 1.
	 */
	protected abstract T getEntity2();

	@Test
	public void testCreate() {

		long id1 = getRepository().save(getEntity1()).getId();
		long id2 = getRepository().save(getEntity2()).getId();

		assertTrue("Entity 1 ID", id1 > 0);
		assertTrue("Entity 2 ID", id2 > id1);
		log.fine("testCreate() was called");
	}

	@Test
	public void testFindById() {

		long id = getRepository().save(getEntity1()).getId();
		T entity = getRepository().findOne(id);
		assertNotNull("Entity is not null", entity);
		assertEquals("Id is correct", id, entity.getId());
	}

	@Test
	public void testDelete() {

		long id = getRepository().save(getEntity1()).getId();
		T entity = getRepository().findOne(id);
		assertNotNull("Entity is not null", entity);

		getRepository().delete(entity);
		assertNull("Deleted object return null", getRepository().findOne(id));
	}

	@Test
	public void testUpdate() {
		long id = getRepository().save(getEntity1()).getId();
		T entity = getRepository().findOne(id);

		// This doesn´t test much, but is still some control that no exception
		// is thrown.
		getRepository().save(entity);

	}
	
	@Test
	public void testIsPropertyUnique() {
		long id1 = getRepository().save(getEntity1()).getId();
		
		// we persist a second object just to make sure no errors occur when making a query in a table with multiple records
		save(getEntity2());
		
		assertFalse("id1 already exists in db!", getRepository().isPropertyUnique("id", id1));
	}
	
	@Test
	public void testIsNoOtherEntityWithProperty() {
		List<T> es = persistEntities1And2();
		T e1 = es.get(0);
		T e2 = es.get(1);
		
		String date1 = "2015-09-25 08:46:10";
		String date2 = "2016-09-25 08:46:10";
		
		// Update creation date, this should never get updated in production but for the intent we use it here
		e1.setCreationDate(date1);
		e2.setCreationDate(date2);
		
		save(e1, e2);
		
		// e1 should have unique creation date
		boolean noOtherEntityWithProperty = getRepository().isNoOtherEntityWithProperty(e1.getId(), "creationDate", date1);
		assertTrue("Creation date for entity 1 should be uniqe", noOtherEntityWithProperty);
		
		// Now give e2 same date
		e2.setCreationDate(date1);
		getRepository().save(e2);
		
		// e1 should no longer have unique creation date
		assertFalse("Creation date for entity 1 should no longer be uniqe", getRepository().isNoOtherEntityWithProperty(e1.getId(), "creationDate", date1));
	}
	
	@Test
	public void testGetEntityWithMatchingProperty() {
		List<T> es = persistEntities1And2();
		T e1 = es.get(0);
		T e2 = es.get(1);
		
		String date1 = "2015-09-25 08:46:10";
		String date2 = "2016-09-25 08:46:10";
		
		// Update creation date, this should never get updated in production but for the intent we use it here
		e1.setCreationDate(date1);
		e2.setCreationDate(date2);
		
		save(e1, e2);
		
		T match = getRepository().getFirstEntityMatchingProperty("creationDate", date1);
		assertEquals("The resulting match should have the date1 value we queried for", date1, match.getCreationDate());
	}
	
	@Test
	public void testGetEntitiesWithMatchingProperty() {
		List<T> es = persistEntities1And2();
		T e1 = es.get(0);
		T e2 = es.get(1);
		
		String date1 = "2015-09-25 08:46:10";
		String date2 = date1;
		
		// Update creation date, this should never get updated in production but for the intent we use it here
		e1.setCreationDate(date1);
		e2.setCreationDate(date2);
		
		save(e1, e2);
		
		List<T> matches = getRepository().getEntitiesMatchingProperty("creationDate", date1);
		assertEquals("The resulting matches should be 2", 2, matches.size());
	}

	protected List<T> persistEntities1And2() {
		return save(getEntity1(), getEntity2());
	}
	
	@SafeVarargs
	protected final List<T> save(T... entities) {
		return getRepository().save(Arrays.asList(entities));
	}
	

}
