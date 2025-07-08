package helperDB;

import lombok.Getter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Getter
public class Webinars {
    int id;
    int teacherId;
    int capacity;
    String slug;
    String teacherName;
    public static  List<Webinars> webinars ;

    public Webinars(int id, int teacherId, int capacity, String slug, String teacherName) {
        this.id = id;
        this.teacherId = teacherId;
        this.capacity = capacity;
        this.slug = slug;
        this.teacherName = teacherName;
    }
    public static void assertMaxCapacityWebinars(List<Webinars> webinars,String teacherName) {
        // Assert that the list is not empty
        assertFalse(webinars.isEmpty(), "Webinars list should not be empty");


        for (Webinars webinar : webinars) {
            assertEquals(teacherName, webinar.getTeacherName(),
                    "Teacher name should match the expected value");
        }
    }
}
