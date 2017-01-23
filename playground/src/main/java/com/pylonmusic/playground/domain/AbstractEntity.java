package com.pylonmusic.playground.domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Extension of {@link IdHolder} with some more convenient
 * methods for entities. 
 * @author johan
 *
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6256322936983908489L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Access(AccessType.FIELD)
	@Column(name="ENTITY_ID")
	protected long id;
	
	@Length(max=Constants.LENGTH_SHORT_FIELD)
	@Pattern(regexp = Constants.REGEX_DATE_TIME_PATTERN)
	@Access(AccessType.FIELD)
	@Column(name="CREATION_DATE", length=Constants.LENGTH_SHORT_FIELD)
	protected String creationDate;
	
	@Length(max=Constants.LENGTH_SHORT_FIELD)
	@Pattern(regexp = Constants.REGEX_DATE_TIME_PATTERN)
	@Access(AccessType.FIELD)
	@Column(name="LAST_UPDATE", length=Constants.LENGTH_SHORT_FIELD)
	protected String lastChangeDate;
	
	protected static final char NEW_LINE = '\n';
	
	/**
	 * Creates an empty instance and sets creation and last change date to a new date.
	 * Last change date will later be updated every time a change occur whereas creation date
	 * will stay the same
	 */
	protected AbstractEntity() {
		this.creationDate = this.lastChangeDate = getNewDate();
	}
	
	/** 
	 * Set the id of the entity
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	
	public long getId() {
		return this.id;
	}
	
	/**
	 * Intended to be used for copying the fields from the populatedEntity
	 * Default method does the following:<br>
	 * calls {@link #copyDataFromEntity(AbstractEntity, boolean)} with args:populatedEntity,<code>true</code><br>
	 *  
	 * @param populatedEntity the {@link AbstractEntity} with the data
	 */
	public void copyDataFromEntity(AbstractEntity populatedEntity) {
		copyDataFromEntity(populatedEntity, true);
	}
	
	/**
	 * Intended to be used for copying the fields from the populatedEntity
	 * Default method does the following:<br>
	 * Copies the id of the passed in entity<br>
	 * calls {@link #updateLastChangeDate()} 
	 * 
	 * @param populatedEntity the {@link AbstractEntity} with the data
	 * @param copyReferencesAsWell if <code>true</code> references from populatedEntity will be copied over as well (default is <code>true</code>)
	 */
	public void copyDataFromEntity(AbstractEntity populatedEntity, boolean copyReferencesAsWell) {
		setId(populatedEntity.getId());
		
		if(copyReferencesAsWell) {
			copyReferencesFromEntity(populatedEntity);
		}
		
		updateLastChangeDate();
	}
	
	/**
	 * Intended to be used similarly to {@link #copyDataFromEntity(AbstractEntity)}
	 * but only the reference entities will be copied and not the fields
	 * @param populatedEntity the {@link AbstractEntity} owning the references
	 */
	public abstract void copyReferencesFromEntity(AbstractEntity populatedEntity);
	
	/**
	 * Our entities have {@link OneToOne}, {@link ManyToOne}, {@link OneToMany} relations
	 * this method should make sure that relations are set both ways.<br>
	 * <b>Example</b>:<br>
	 * class Entity1 has an instance of Class Entity2 and they have a {@link OneToOne} relation<br>
	 * Entity1 must then both set the Entity2 instance the usual way<br>
	 * and assign itself to Entity2 so that:<br><br>
	 * <code>public void setRelations() {<br>
	 * this.getEntity2().setEntity1(this);<br>
	 * }<br>
	 * Many-To-Many relations will most likely not be possible with this (not in a good way at least)
	 *
	 */
	public abstract void setRelations();
	
	/**
	 * @return <code>true</code> if {@link #getId()} > 0
	 */
	@JsonIgnore
	public boolean isExistingEntity() {
		return isExistingEntity(getId());
	}
	
	/**
	 * @param id
	 * @return <code>true</code> if passed in id > 0
	 */
	@JsonIgnore
	public boolean isExistingEntity(long id) {
		return id > 0;
	}
	
	/** @return the date when the last change occurred formatted as "yyyy-MM-dd HH:mm:ss" */
	public String getLastChangeDate() {
		return lastChangeDate;
	}
	
	/**
	 * Set the date when the last change occurred.
	 * @param lastChangeDate must be formatted as "yyyy-MM-dd HH:mm:ss"
	 */
	public void setLastChangeDate(String lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}
	
	/** Set lastChangeDate instance to new date calling {@link #getNewDate()} */
	@JsonIgnore
	public void updateLastChangeDate() {
		this.lastChangeDate = getNewDate();
	}
	
	/** @param creationDate the date the instance was created */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	
	/** @return the date and time the instance was created */
	public String getCreationDate() {
		return creationDate;
	}
	
	/**
	 * {@link #getLastChangeDate()} without displaying seconds
	 * @return
	 */
	public String getDisplayLastChangeDate() {
		return getLastChangeDate().substring(0, getLastChangeDate().lastIndexOf(':'));
	}

	@Override
	public String toString() {
		return new StringBuilder().
			append(this.getClass().getSimpleName().toUpperCase()).append("_ID=").append(getId()).append(NEW_LINE).
			append("Creation Date=").append(getCreationDate()).append(NEW_LINE).
			append("Last Change Date=").append(getLastChangeDate()).
		toString();
	}
	
	
	
	/**
	 * @return A new date with format "yyyy-MM-dd HH:mm:ss" 
	 */
	private String getNewDate() {
		return new DateTime().toString(DateTimeFormat.forPattern("yyy-MM-dd HH:mm:ss"));
	}
	

}
