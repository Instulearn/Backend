package pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blogs_PostPojo {
    private String title;
    private int category_id;
    private String description;
    private String content;



}
