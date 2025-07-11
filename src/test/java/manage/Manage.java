package manage;

import lombok.Getter;

@Getter
public class Manage {

    /**
     * US_01
     **/


    private String Us01_calculates_the_total_meeting_time = "SELECT rm.user_id, SUM(TIMESTAMPDIFF(MINUTE, rm.start_at, rm.end_at)) AS total_meeting_duration FROM u201212290_qainstulearn.reserve_meetings rm JOIN u201212290_qainstulearn.users u ON rm.user_id = u.id WHERE u.email = 'oske.work@gmail.com';";

    /**
     * US_02
     **/

    private String Us02_showing_the_total_number_of_meetings = "SELECT status,COUNT(meeting_id) AS total_meetings,(COUNT(meeting_id) / (SELECT COUNT(*) FROM   u201212290_qainstulearn.reserve_meetings) * 100) AS percentage FROM u201212290_qainstulearn.reserve_meetings GROUP BY status;";

    /**
     * US_03
     **/

    private String Us03_Insert_a_data_entry_into_the_gifts_table = "INSERT INTO u201212290_qainstulearn.gifts (user_id, webinar_id, bundle_id, product_id, name, email, date, description, viewed, status, created_at)\n" +
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
            "    (SELECT id, full_name, email FROM u201212290_qainstulearn.users ORDER BY RAND() LIMIT 1) u\n" +
            "JOIN\n" +
            "    (SELECT id FROM u201212290_qainstulearn.webinars ORDER BY RAND() LIMIT 1) w\n" +
            "JOIN\n" +
            "    (SELECT id FROM u201212290_qainstulearn.bundles ORDER BY RAND() LIMIT 1) b\n" +
            "JOIN\n" +
            "    (SELECT id FROM u201212290_qainstulearn.products ORDER BY RAND() LIMIT 1) p;";


    private String us04_gift_information_for_product_orders = "SELECT po.id AS product_order_id, po.product_id, g.id AS gift_id, g.name AS gift_name FROM product_orders po JOIN gifts g ON po.product_id= g.product_id;";

    private String us05_quizzess_limited_questions = "SELECT * FROM quizzes WHERE display_limited_questions = 1 ORDER BY display_number_of_questions DESC;";

    private String us06_quizzess_questions = "SELECT qq.id, qq.quiz_id, qq.creator_id, qq.grade, qq.type, qq.image, qq.video, qq.order, qq.created_at, qq.updated_at\n" +
            "FROM u201212290_qainstulearn.quizzes_questions qq JOIN u201212290_qainstulearn.quizzes q ON  qq.quiz_id = q.id WHERE q.pass_mark = 100;";

    private String us07_In_the_supports_table_add_2_data_containing = "INSERT INTO u201212290_qainstulearn.supports (user_id, webinar_id, department_id, title, created_at, updated_at)\n" +
            "SELECT u1.id, w1.id, sd1.id, \n" +
            "  CONCAT_WS(' ',\n" +
            "    (SELECT word FROM (SELECT 'Hello' AS word UNION SELECT 'Hi' UNION SELECT 'Hey' UNION SELECT 'Greetings') AS words ORDER BY RAND() LIMIT 1),\n" +
            "    (SELECT word FROM (SELECT 'world' AS word UNION SELECT 'friend' UNION SELECT 'user' UNION SELECT 'guest') AS words ORDER BY RAND() LIMIT 1),\n" +
            "    (SELECT word FROM (SELECT '!' AS word UNION SELECT '.' UNION SELECT '!!' UNION SELECT '...') AS words ORDER BY RAND() LIMIT 1)\n" +
            "  ) AS title,\n" +
            "   UNIX_TIMESTAMP(),  UNIX_TIMESTAMP()\n" +
            "FROM (SELECT id FROM u201212290_qainstulearn.users ORDER BY RAND() LIMIT 1) u1\n" +
            "JOIN (SELECT id FROM u201212290_qainstulearn.webinars ORDER BY RAND() LIMIT 1) w1\n" +
            "JOIN (SELECT id FROM u201212290_qainstulearn.support_departments ORDER BY RAND() LIMIT 1) sd1\n" +
            "\n" +
            "UNION ALL\n" +
            "\n" +
            "SELECT u2.id, w2.id, sd2.id, \n" +
            "  CONCAT_WS(' ',\n" +
            "    (SELECT word FROM (SELECT 'Hello' AS word UNION SELECT 'Hi' UNION SELECT 'Hey' UNION SELECT 'Greetings') AS words ORDER BY RAND() LIMIT 1),\n" +
            "    (SELECT word FROM (SELECT 'world' AS word UNION SELECT 'friend' UNION SELECT 'user' UNION SELECT 'guest') AS words ORDER BY RAND() LIMIT 1),\n" +
            "    (SELECT word FROM (SELECT '!' AS word UNION SELECT '.' UNION SELECT '!!' UNION SELECT '...') AS words ORDER BY RAND() LIMIT 1)\n" +
            "  ) AS title,\n" +
            "   UNIX_TIMESTAMP(),  UNIX_TIMESTAMP()\n" +
            "FROM (SELECT id FROM u201212290_qainstulearn.users ORDER BY RAND() LIMIT 1) u2\n" +
            "JOIN (SELECT id FROM u201212290_qainstulearn.webinars ORDER BY RAND() LIMIT 1) w2\n" +
            "JOIN (SELECT id FROM u201212290_qainstulearn.support_departments ORDER BY RAND() LIMIT 1) sd2;";

    private String us08_unixtime_date_validate = "select id from webinars Where start_date=UNIX_TIMESTAMP('2024-10-10 14:30:00');";

    private String us09_insert_users_table = "INSERT INTO users (full_name, email, password, role_name, role_id, created_at) \n" +
            // "VALUES ('John Doe', 'johnDoe@example.com', SHA2('password', 256), 'user', 1, unix_timestamp());";
            "VALUES (?, ?, SHA2(?, 256), ?,?, unix_timestamp());";

    private String us09_selected_id_users_table = "Select id FROM users  WHERE full_name = ?;";
    private String us09_update_password_users_table = "UPDATE users  SET password = SHA2(?, 256) WHERE id = ?;";

    private String us10_financial_and_installment_approval = "SELECT * FROM users WHERE financial_approval = 1 AND installment_approval = 1;";
    private String us11_groupby_meeting_id = "Select meeting_id ,sum(paid_amount) AS max_paid_amount FROM reserve_meetings GROUP BY meeting_id having max_paid_amount > 5000 ;";
    private String us12_zoom_api_insert_data = "INSERT INTO users_zoom_api (user_id, jwt_token, api_key, api_secret, account_id, created_at) \n" +
            // "VALUES (1361, SHA2('jwt_token', 256), 'example_api_key', 'example_api_secret', '3500', unix_timestamp());";
            "VALUES (?, SHA2(?, 256), ?, ?, ?, unix_timestamp());";
    private String usersTable_user_id = "SELECT id FROM users";
    private String us12_update = "update users_zoom_api SET account_id= ? Where user_id=?;";
    private String us13_insert_data_verifications_table = "INSERT INTO verifications (user_id, mobile, email, code, verified_at, expired_at, created_at) \n" +
            // "VALUES (1361,'1234567890', 'oske.work@gmail.com', '123456', UNIX_TIMESTAMP(NOW()), UNIX_TIMESTAMP(NOW() + INTERVAL 1 DAY), UNIX_TIMESTAMP(NOW()));";
            "VALUES (?,?, ?, ?, UNIX_TIMESTAMP(NOW()), UNIX_TIMESTAMP(NOW() + INTERVAL 1 DAY), UNIX_TIMESTAMP(NOW()));";
    private String us13_deleted_data_verifications_table = "update users_zoom_api SET account_id= ? Where user_id=?;";
    private String us14_become_instructors_table = "SELECT role, COUNT(*) AS record_count FROM become_instructors  GROUP BY role;";

    private String us17_payku_translation_insert_bulk = "INSERT INTO payku_transactions (id, status, `order`, email, subject, url, amount, notified_at, created_at, updated_at, full_name) VALUES (?,?,?,?,?,?,?,NOW(), NOW(),NOW(),?)";
    private String us18_produtcs = "SELECT slug, inventory\n" +
            "FROM products\n" +
            "WHERE inventory = (SELECT MIN(inventory) FROM products WHERE inventory IS NOT NULL);\n";
    /**
     * US19
     */
    private String us19_productOrders = "UPDATE products p\n" +
            "JOIN (\n" +
            "    SELECT product_id, SUM(quantity) AS total_quantity\n" +
            "    FROM product_orders\n" +
            "    WHERE product_id = '5'\n" +
            "    GROUP BY product_id\n" +
            ") o ON p.id = o.product_id\n" +
            "SET p.inventory = p.inventory - o.total_quantity;";
    /**
     * US20
     */
    private String us20payments_methods_credit = "SELECT status,payment_method, SUM(total_amount) AS total_credit_card_sales\n" +
            "FROM orders\n" +
            "WHERE payment_method = 'credit'\n" +
            "AND DATE(created_at) = '1715085585' AND status='paid';";
    /**
     * US21
     */
    private String us21discounts_ph_virtual = "SELECT * FROM discounts WHERE count > 0 AND (product_type = 'physical' OR product_type = 'virtual');";
    /**
     * US22
     */
    private String us22failed_jobs = "Insert into failed_jobs (uuid, connection, queue, payload, exception, failed_at)\n" +
            "Values (?,?,?,?,?,?)";
    /**
     * US23
     */
    private String us23deleted_failed_jobs = "Delete from failed_jobs Where uuid=?;";
    /**
     * US24
     */
    private String us24banStatus_users = "SELECT \n" +
            "    ban_status, \n" +
            "    COUNT(*) AS total_users, \n" +
            "    ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM users), 2) AS percentage\n" +
            "FROM (\n" +
            "    SELECT \n" +
            "        CASE \n" +
            "            WHEN ban = 1 AND ban_end_at IS NULL THEN 'Currently Banned'\n" +
            "            WHEN ban = 1 AND ban_end_at IS NOT NULL THEN 'Previously Banned'\n" +
            "            ELSE 'Never Banned'\n" +
            "        END AS ban_status\n" +
            "    FROM users\n" +
            ") AS ban_info\n" +
            "GROUP BY ban_status;\n";
    /**
     * US25
     */
    private String us25bonus_amount_of_users = "SELECT \n" +
            "    financial_approval, \n" +
            "    installment_approval, \n" +
            "    enable_registration_bonus, \n" +
            "    AVG(registration_bonus_amount) AS avg_registration_bonus, \n" +
            "    COUNT(*) AS user_count\n" +
            "FROM users\n" +
            "GROUP BY financial_approval, installment_approval, enable_registration_bonus;";
    /**
     * US26
     */
    private String us26 = "SELECT \n" +
            "    language, \n" +
            "    currency, \n" +
            "    COUNT(*) AS user_count, \n" +
            "    SUM(commission) AS total_commission\n" +
            "FROM users\n" +
            "GROUP BY language, currency\n" +
            "ORDER BY total_commission DESC;\n";

    /**
     * US27
     */
    private String us27 = "SELECT \n" +
            "    t.full_name, \n" +
            "    c.slug, \n" +
            "    AVG(w.price) AS avg_price, \n" +
            "    SUM(w.capacity) AS total_capacity, \n" +
            "    MAX(w.updated_at) AS last_updated\n" +
            "FROM webinars w\n" +
            "JOIN users t ON w.teacher_id = t.id\n" +
            "JOIN categories c ON w.category_id = c.id\n" +
            "GROUP BY t.full_name, c.slug\n" +
            "ORDER BY last_updated DESC;";


    /**
     * US28
     */
    private String us28webinarsTeacher = "SELECT \n" +
            "    w.id, \n" +
            "    t.full_name, \n" +
            "    w.start_date, \n" +
            "    w.price, \n" +
            "    w.capacity, \n" +
            "    (SELECT COUNT(*) FROM waitlists wl WHERE wl.webinar_id = w.id) AS waitlist_count\n" +
            "FROM webinars w\n" +
            "JOIN users t ON w.teacher_id = t.id\n" +
            "WHERE w.private = 0 AND w.enable_waitlist = 1\n" +
            "ORDER BY w.start_date ASC;";

    /**
     * US29
     */
    private String us29totalWebinars = "SELECT \n" +
            "    certificate, \n" +
            "    downloadable, \n" +
            "    COUNT(*) AS total_webinars, \n" +
            "    AVG(price) AS avg_price, \n" +
            "    MIN(start_date) AS earliest_start_date\n" +
            "FROM webinars\n" +
            "GROUP BY certificate, downloadable\n" +
            "ORDER BY total_webinars DESC, earliest_start_date ASC;";
    /**
     * US30
     */
    private String us30best_seller_product = "SELECT seller_id, product_id, \n" +
            "       COUNT(*) AS total_sales\n" +
            "FROM product_orders\n" +
            "GROUP BY seller_id, product_id\n" +
            "ORDER BY total_sales DESC;";
    /** US31*/
    private String us31totalsales="\n" +
            "SELECT seller_id, COUNT(*) AS total_sales\n" +
            "FROM product_orders\n" +
            "GROUP BY seller_id\n" +
            "ORDER BY total_sales DESC\n" +
            "LIMIT 1;";
    /** US32 */
    private String us32statusProductOrders="SELECT status, COUNT(*) AS status_count\n" +
            "FROM product_orders\n" +
            "GROUP BY status;";
    /** US33 */
    private String us33webinarsMaxCapacity="SELECT w.id,w.teacher_id,w.capacity,w.slug, u.full_name AS teacher_name\n" +
            "FROM webinars w\n" +
            "JOIN users u ON w.teacher_id = u.id\n" +
            "WHERE w.capacity = (\n" +
            "    SELECT MAX(capacity) FROM webinars\n" +
            ");";
    /** US34 */
    private String us34_minimum_rates_product="SELECT \n" +
            "    product_id, \n" +
            "    COUNT(*) AS total_reviews, \n" +
            "    AVG(rates) AS average_rating, \n" +
            "    MAX(rates) AS max_rating, \n" +
            "    MIN(rates) AS min_rating\n" +
            "FROM product_reviews\n" +
            "GROUP BY product_id Having min_rating < 3;";
    /** US35 */
    private String us35support_transactions_status="SELECT sdt.title, sd.department_id, status,count(sd.title) AS support_count\n" +
            "FROM supports AS sd\n" +
            "JOIN support_department_translations AS sdt ON sd.department_id = sdt.id\n" +
            "GROUP BY sdt.title, sd.department_id;";
    /** US36 */
    private String us36stokList="SELECT *\n" +
            "FROM products\n" +
            "WHERE inventory > 0 \n" +
            "AND status = 'active' \n" +
            "AND FROM_UNIXTIME (created_at )>= NOW() - INTERVAL 30 DAY \n" +
            "ORDER BY created_at desc; ";
    /** US37  **/
    private String us37ticketOpen="SELECT t.id,t.capacity, COUNT(tu.user_id) AS participant_count\n" +
            "FROM tickets t\n" +
            "LEFT JOIN ticket_users tu ON t.id = tu.ticket_id\n" +
            "GROUP BY t.capacity;";

    /** US38  **/
    private  String us38webinarIdSessionOrder="SELECT *\n" +
            "FROM sessions\n" +
            "WHERE webinar_id = ? \n" +
            "AND deleted_at IS NULL\n" +
            "ORDER BY `order` ASC;\n";
    /** US38  **/
    private String us39="SELECT product_order_id, SUM(total_amount) AS total_sales, SUM(commission) AS total_commission\n" +
            "FROM sales\n" +
            "WHERE product_order_id IS NOT NULL\n" +
            "GROUP BY product_order_id\n" +
            "ORDER BY total_sales DESC;";
    private String us40salesInterval_15days="SELECT *, FROM_UNIXTIME(created_at) AS formatted_created_at\n" +
            "FROM sales\n" +
            "WHERE FROM_UNIXTIME(created_at) >= DATE_SUB(CURDATE(), INTERVAL 15 DAY )\n" +
            "ORDER BY formatted_created_at DESC;";
}