package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product_PatchPojo {
    private String type;
    private Integer price;
    private Integer category_id;
    private String title;
    private String summary;
    private String description;

    // Default constructor
    public Product_PatchPojo() {
    }

    // Constructor with all fields
    public Product_PatchPojo(String type, Integer price, Integer category_id, String title, String summary, String description) {
        this.type = type;
        this.price = price;
        this.category_id = category_id;
        this.title = title;
        this.summary = summary;
        this.description = description;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product_PatchPojo{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", category_id=" + category_id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
} 