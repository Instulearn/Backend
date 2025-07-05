package pojos;

import java.io.Serializable;
import java.util.List;

public class DataPojo implements Serializable {
	private int id;
	private String slug;
	private Object parentId;
	private String icon;
	private int order;
	private Object title;
	private List<Object> subCategories;
	private List<TranslationsPojo> translations;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setParentId(Object parentId){
		this.parentId = parentId;
	}

	public Object getParentId(){
		return parentId;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setOrder(int order){
		this.order = order;
	}

	public int getOrder(){
		return order;
	}

	public void setTitle(Object title){
		this.title = title;
	}

	public Object getTitle(){
		return title;
	}

	public void setSubCategories(List<Object> subCategories){
		this.subCategories = subCategories;
	}

	public List<Object> getSubCategories(){
		return subCategories;
	}

	public void setTranslations(List<TranslationsPojo> translations){
		this.translations = translations;
	}

	public List<TranslationsPojo> getTranslations(){
		return translations;
	}

	@Override
 	public String toString(){
		return 
			"DataPojo{" + 
			"id = '" + id + '\'' + 
			",slug = '" + slug + '\'' + 
			",parent_id = '" + parentId + '\'' + 
			",icon = '" + icon + '\'' + 
			",order = '" + order + '\'' + 
			",title = '" + title + '\'' + 
			",sub_categories = '" + subCategories + '\'' + 
			",translations = '" + translations + '\'' + 
			"}";
		}

	public DataPojo(int id, String slug, Object parentId, String icon, int order, Object title, List<Object> subCategories, List<TranslationsPojo> translations) {
		this.id = id;
		this.slug = slug;
		this.parentId = parentId;
		this.icon = icon;
		this.order = order;
		this.title = title;
		this.subCategories = subCategories;
		this.translations = translations;
	}

	public DataPojo() {
	}
}