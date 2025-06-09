package Models;

public class Actor {
	private String actor_id;
	private String actor_name;
	private String actor_image;
	private String nationality;
	private java.sql.Date birth;

	private String biography;

	public Actor(String actor_id, String actor_name, String actor_image, String nationality, java.sql.Date birth,
			String biography) {
		this.actor_id = actor_id;
		this.actor_name = actor_name;
		this.actor_image = actor_image;
		this.nationality = nationality;
		this.birth = birth;
		this.biography = biography;
	}

	
	public Actor(String actor_id,String actor_name) {
		this.actor_id=actor_id;
		this.actor_name=actor_name;
	}

	public String getActor_id() {
		return actor_id;
	}

	public void setActor_id(String actor_id) {
		this.actor_id = actor_id;
	}

	public String getActor_name() {
		return actor_name;
	}

	public void setActor_name(String actor_name) {
		this.actor_name = actor_name;
	}

	public String getActor_image() {
		return actor_image;
	}

	public void setActor_image(String actor_image) {
		this.actor_image = actor_image;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public java.sql.Date getBirth() {
		return birth;
	}

	public void setBirth(java.sql.Date birth) {
		this.birth = birth;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	@Override
	public String toString() {
		return actor_name;
	}
}
