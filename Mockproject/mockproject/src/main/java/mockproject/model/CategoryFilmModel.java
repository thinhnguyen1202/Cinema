package mockproject.model;

public class CategoryFilmModel {
	int id;
	int filmid;
	int categoryid;
	
	
	public CategoryFilmModel() {
		super();
	}
	public int getId() {
		return id;
	}
	public int getFilmid() {
		return filmid;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFilmid(int filmid) {
		this.filmid = filmid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	
	
}
