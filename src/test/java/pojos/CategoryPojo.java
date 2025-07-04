package pojos;

import java.io.Serializable;

public class CategoryPojo implements Serializable {
	private String title;

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"CategoryPojo{" + 
			"title = '" + title + '\'' + 
			"}";
		}

	public CategoryPojo(String title) {
		this.title = title;
	}

	public CategoryPojo() {
	}
}