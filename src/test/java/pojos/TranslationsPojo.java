package pojos;

import java.io.Serializable;

public class TranslationsPojo implements Serializable {
	private int id;
	private int categoryId;
	private String locale;
	private String title;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCategoryId(int categoryId){
		this.categoryId = categoryId;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public void setLocale(String locale){
		this.locale = locale;
	}

	public String getLocale(){
		return locale;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"TranslationsPojo{" + 
			"id = '" + id + '\'' + 
			",category_id = '" + categoryId + '\'' + 
			",locale = '" + locale + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}

	public TranslationsPojo(int id, int categoryId, String locale, String title) {
		this.id = id;
		this.categoryId = categoryId;
		this.locale = locale;
		this.title = title;
	}

	public TranslationsPojo() {
	}
}