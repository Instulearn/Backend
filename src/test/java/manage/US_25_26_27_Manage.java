package manage;

import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;

import static helperDB.JDBC_Structure_Methods.*;

@Getter
public class US_25_26_27_Manage {
    public static String getPublicAndWaitlistedWebinars =
            "SELECT teacher_id, start_date, price, capacity " +
                    "FROM webinars " +
                    "WHERE private = 0 AND enable_waitlist = 1";

    public static String getWaitlistCountByWebinarId =
           " SELECT COUNT(*) AS waitlist_count FROM waitlist WHERE webinar_id = 2009";


    public static String getWaitlistCount = "SELECT COUNT(*) AS waitlist_count FROM webinars WHERE enable_waitlist = 1";

    public static String getWebinarGroupStats =
            "SELECT certificate, downloadable, " +
                    "COUNT(*) AS total_webinars, " +
                    "AVG(price) AS avg_price, " +
                    "MIN(start_date) AS earliest_start " +
                    "FROM webinars " +
                    "GROUP BY certificate, downloadable";


    public static String getBestSellingProductsPerVendor =
            "SELECT po.seller_id, po.product_id, SUM(po.quantity) AS total_sold " +
                    "FROM product_orders po " +
                    "GROUP BY po.seller_id, po.product_id " +
                    "HAVING SUM(po.quantity) IN ( " +
                    "   SELECT MAX(total_quantity) " +
                    "   FROM ( " +
                    "       SELECT seller_id, SUM(quantity) AS total_quantity " +
                    "       FROM product_orders " +
                    "       GROUP BY seller_id, product_id " +
                    "   ) AS sub " +
                    "   WHERE sub.seller_id = po.seller_id " +
                    ");";
            }



