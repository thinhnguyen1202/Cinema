package mockproject.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FilmModel {

		private int id;
		private String name;
		private String actors;
		private String producer;
		private int duration;
		private String description;
		private String imagepath;
		private Date created_at;
		private Timestamp update_at;
		private List<CategoryModel> listCategory;
		private List<String> listCategoryName;
		private String showdateString;
		private String showDateString;
		
		
		//upload files 
		private CommonsMultipartFile fileDatas;
		//Description for image file
		private String imageFile;
		
		public FilmModel() {
			super();
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getActors() {
			return actors;
		}

		public String getProducer() {
			return producer;
		}

		public int getDuration() {
			return duration;
		}

		public Date getCreated_at() {
			return created_at;
		}

		public Timestamp getUpdate_at() {
			return update_at;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setActors(String actors) {
			this.actors = actors;
		}

		public void setProducer(String producer) {
			this.producer = producer;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}

		public void setCreated_at(Date created_at) {
			this.created_at = created_at;
		}

		public void setUpdate_at(Timestamp update_at) {
			this.update_at = update_at;
		}

		public String getDescription() {
			return description;
		}

		public String getImagepath() {
			return imagepath;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public void setImagepath(String imagepath) {
			this.imagepath = imagepath;
		}

		public CommonsMultipartFile getFileDatas() {
			return fileDatas;
		}

		public String getImageFile() {
			return imageFile;
		}

		public void setFileDatas(CommonsMultipartFile fileDatas) {
			this.fileDatas = fileDatas;
		}

		public void setImageFile(String imageFile) {
			this.imageFile = imageFile;
		}

		public List<CategoryModel> getListCategory() {
			return listCategory;
		}

		public void setListCategory(List<CategoryModel> listCategory) {
			this.listCategory = listCategory;
		}

		public List<String> getListCategoryName() {
			return listCategoryName;
		}

		public void setListCategoryName(List<String> listCategoryName) {
			this.listCategoryName = listCategoryName;
		}
		
		public String getShowdateString() {
			return showdateString;
		}

		public void setShowdateString(String showdateString) {
			this.showdateString = showdateString;
		}

		public String getShowDateString() {
			return showDateString;
		}
		
		public void setShowDateString(String showDateString) {
			this.showDateString = showDateString;
		}
	
}
