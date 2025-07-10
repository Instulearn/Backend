package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product_PostPojo implements Serializable {
    private String type;
    private double price;
    private int category_id;
    private String title;
    private String summary;
    private String description;

    public Product_PostPojo() {
    }

    public Product_PostPojo(String type, double price, int category_id, String title, String summary, String description) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
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
        return "Product_PostPojo{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", category_id=" + category_id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
} 