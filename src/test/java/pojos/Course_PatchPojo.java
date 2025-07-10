package pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course_PatchPojo {

    private int duration;
    private int capacity;
    private int price;
    private String title;

}
