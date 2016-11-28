package se.lemv.model;

public abstract class AbstractEntity {
	
	protected Long id;

	public AbstractEntity(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
}
