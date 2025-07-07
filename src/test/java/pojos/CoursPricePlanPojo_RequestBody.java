package pojos;

public class CoursPricePlanPojo_RequestBody {

    private String title;
    private String dateRange;
    private int discount;
    private int capacity;
    private int webinar_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getWebinar_id() {
        return webinar_id;
    }

    public void setWebinar_id(int webinar_id) {
        this.webinar_id = webinar_id;
    }

    public CoursPricePlanPojo_RequestBody() {
    }

    public CoursPricePlanPojo_RequestBody(String title, String dateRange, int discount, int capacity, int webinar_id) {
        this.title = title;
        this.dateRange = dateRange;
        this.discount = discount;
        this.capacity = capacity;
        this.webinar_id = webinar_id;
    }

    @Override
    public String toString() {
        return "CoursPricePlanPojo_RequestBody{" +
                "title='" + title + '\'' +
                ", dateRange='" + dateRange + '\'' +
                ", discount=" + discount +
                ", capacity=" + capacity +
                ", webinar_id=" + webinar_id +
                '}';
    }
}
