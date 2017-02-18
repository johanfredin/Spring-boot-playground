package com.pylonmusic.playground.repositiory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
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

		String id1 = getRepository().save(getEntity1()).getId();
		String id2 = getRepository().save(getEntity2()).getId();

		assertTrue("Entity 1 ID", id1 != null);
		assertTrue("Entity 2 ID", id2 != null);
		log.fine("testCreate() was called");
	}

	@Test
	public void testFindById() {

		String id = getRepository().save(getEntity1()).getId();
		T entity = getRepository().findOne(id);
		assertNotNull("Entity is not null", entity);
		assertEquals("Id is correct", id, entity.getId());
	}

	@Test
	public void testDelete() {

		String id = getRepository().save(getEntity1()).getId();
		T entity = getRepository().findOne(id);
		assertNotNull("Entity is not null", entity);

		getRepository().delete(entity);
		assertNull("Deleted object return null", getRepository().findOne(id));
	}

	@Test
	public void testUpdate() {
		String id = getRepository().save(getEntity1()).getId();
		T entity = getRepository().findOne(id);

		assertEquals(1, getRepository().findAll().size());
		entity.setCreationDate("2015-09-25:08:25:24");
		
		// This doesn´t test much, but is still some control that no exception
		// is thrown.
		getRepository().save(entity);

		T dbEntity = getRepository().findOne(entity.getId());
		assertEquals("2015-09-25:08:25:24", dbEntity.getCreationDate());
		assertEquals(1, getRepository().findAll().size());
	}
	
	@After
	public void clear() {
		getRepository().deleteAll();
	}
	
	protected List<T> persistEntities1And2() {
		return save(getEntity1(), getEntity2());
	}
	
	@SafeVarargs
	protected final List<T> save(T... entities) {
		return getRepository().save(Arrays.asList(entities));
	}
	

}
