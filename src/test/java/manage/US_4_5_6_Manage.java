package manage;

import lombok.Getter;

@Getter
public class US_4_5_6_Manage {
    public String getUs04_gift_information_for_product_orders() {
        return "SELECT gift_name FROM product_orders WHERE gift_name IS NOT NULL";
    }
    public String getUs05_quizzes_limited_questions() {
        return "SELECT id FROM quizzes WHERE limited_questions IS NOT NULL AND number_of_questions > 0";
    }

    public String getUs06_quizzess_questions() {
        return "SELECT quiz_id FROM quizzes_questions WHERE pass_mark = 100";
    }


}
