package pojos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Course_PostPojo {
        private String title;
        private String type;
        private String slug;
        private String start_date;
        private int duration;
        private int capacity;
        private int price;
        private String description;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int teacher_id;

}
