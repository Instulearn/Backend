package manage;

import lombok.Getter;

@Getter
public class US_1_2_3_Manage {

    private String Us01_calculates_the_total_meeting_time =
            "SELECT rm.user_id, SUM(TIMESTAMPDIFF(MINUTE, rm.start_at, rm.end_at))" +" "+
            "AS total_meeting_duration FROM users u" +" "+
            "JOIN reserve_meetings rm ON u.id = rm.user_id" +" "+
            "WHERE u.email = 'oske.work@gmail.com';";

    private String Us02_showing_the_total_number_of_meetings =
            "SELECT status, COUNT(meeting_id) AS total_meetings, (COUNT(meeting_id) / (SELECT COUNT(*)" +" "+
            "FROM reserve_meetings) * 100) AS percentage" +" "+
            "FROM reserve_meetings" +" "+
            "GROUP BY status;";

    private String Us03_Insert_a_data_entry_into_the_gifts_table = "INSERT INTO u168183796_qainstulearn.gifts (user_id, webinar_id, bundle_id, product_id, name, email, date, description, viewed, status, created_at)\n" +
            "SELECT u.id,w.id,b.id,p.id,u.full_name,u.email,\n" +
            "    UNIX_TIMESTAMP(NOW()) AS unix_timestamp,\n" +
            "    CONCAT_WS(' ',\n" +
            "        (SELECT word FROM (SELECT 'Hello' AS word UNION SELECT 'Hi' UNION SELECT 'Hey' UNION SELECT 'Greetings') AS words ORDER BY RAND() LIMIT 1),\n" +
            "        (SELECT word FROM (SELECT 'world' AS word UNION SELECT 'friend' UNION SELECT 'user' UNION SELECT 'guest') AS words ORDER BY RAND() LIMIT 1),\n" +
            "        (SELECT word FROM (SELECT '!' AS word UNION SELECT '.' UNION SELECT '!!' UNION SELECT '...') AS words ORDER BY RAND() LIMIT 1)\n" +
            "    ) AS description,\n" +
            "    FLOOR(RAND() * 2) AS viewed,\n" +
            "    CASE FLOOR(RAND() * 3)\n" +
            "        WHEN 0 THEN 'active'\n" +
            "        WHEN 1 THEN 'pending'\n" +
            "        ELSE 'cancel'\n" +
            "    END AS status,\n" +
            "    UNIX_TIMESTAMP(NOW()) AS created_at\n" +
            "FROM\n" +
            "    (SELECT id, full_name, email FROM u168183796_qainstulearn.users ORDER BY RAND() LIMIT 1) u\n" +
            "JOIN\n" +
            "    (SELECT id FROM u168183796_qainstulearn.webinars ORDER BY RAND() LIMIT 1) w\n" +
            "JOIN\n" +
            "    (SELECT id FROM u168183796_qainstulearn.bundles ORDER BY RAND() LIMIT 1) b\n" +
            "JOIN\n" +
            "    (SELECT id FROM u168183796_qainstulearn.products ORDER BY RAND() LIMIT 1) p;";

    private String us03_gift_information_for_product_orders = "SELECT po.id AS product_order_id, po.product_id, g.id\n" +
            "AS gift_id, g.name AS gift_name FROM product_orders po\n" +
            "JOIN gifts g ON po.product_id= g.product_id;";



}
