package manage;

import lombok.Getter;
import java.util.UUID;

@Getter
public class US_13_14_15_Manage {

    // US13 - Son 15 gün içinde onaylanmış instructor'ların bilgileri
    private String us13_accepted_instructors_last_15_days = "SELECT bi.id, bi.user_id, bi.role, bi.status, bi.created_at, u.full_name, u.email, u.role_name " +
            "FROM become_instructors bi " +
            "JOIN users u ON bi.user_id = u.id " +
            "WHERE bi.status = 'accept' " +
            "AND FROM_UNIXTIME(bi.created_at) >= NOW() - INTERVAL 15 DAY " + // Son 15 gün içinde onaylanmış instructor'ların bilgilerini aldım
            "ORDER BY bi.created_at DESC;";
    
    private String us13_accepted_instructors_count = "SELECT COUNT(*) AS accepted_instructors_count " + 
            "FROM become_instructors bi " +
            "WHERE bi.status = 'accept' " +
            "AND FROM_UNIXTIME(bi.created_at) >= NOW() - INTERVAL 15 DAY;";

    // US14 - Become instructors table analysis - teacher ve organization dışında rol kontrolü
    private String us14_become_instructors_role_stats = "SELECT role, COUNT(*) AS record_count FROM become_instructors GROUP BY role;";
    
    private String us14_become_instructors_total_count = "SELECT COUNT(*) AS total_instructors FROM become_instructors;";
    
    private String us14_become_instructors_invalid_roles = "SELECT role, COUNT(*) AS invalid_role_count FROM become_instructors WHERE " +
            "role NOT IN ('teacher', 'organization') GROUP BY role;";
    
    private String us14_become_instructors_valid_roles = "SELECT role, COUNT(*) AS valid_role_count FROM become_instructors WHERE role IN ('teacher', 'organization') GROUP BY role;";
    
    private String us14_become_instructors_all_roles = "SELECT DISTINCT role FROM become_instructors ORDER BY role;"; // DISTINCT ile tekrar eden rolleri almıyoruz

    // US15 - Products tablosunda stokta kalmamış ürünler
    private String us15_products_out_of_stock = "SELECT id, slug, inventory, status, created_at " +
            "FROM products " +
            "WHERE inventory = 0 " +
            "AND inventory IS NOT NULL " +
            "ORDER BY created_at DESC;";
    
    private String us15_products_out_of_stock_count = "SELECT COUNT(*) AS out_of_stock_count " +
            "FROM products " +
            "WHERE inventory = 0 " +
            "AND inventory IS NOT NULL;";
    
    private String us15_products_stock_analysis = "SELECT " +
            "CASE " +
            "    WHEN inventory = 0 THEN 'Stokta Yok' " +
            "    WHEN inventory > 0 THEN 'Stokta Var' " +
            "    WHEN inventory IS NULL THEN 'Satışa Sunulmayan' " +
            "END AS stock_status, " +
            "COUNT(*) AS product_count " +
            "FROM products " +
            "GROUP BY " +
            "CASE " +
            "    WHEN inventory = 0 THEN 'Stokta Yok' " +
            "    WHEN inventory > 0 THEN 'Stokta Var' " +
            "    WHEN inventory IS NULL THEN 'Satışa Sunulmayan' " +
            "END " +
            "ORDER BY product_count DESC;";

    // Dinamik sorgu oluşturan metodlar
    public String getUs13AcceptedInstructorsLast15Days() {
        return getUs13_accepted_instructors_last_15_days();
    }

    public String getUs13AcceptedInstructorsCount() {
        return getUs13_accepted_instructors_count();
    }

    public String getUs13AcceptedInstructorsByDateRange(String days) {
        return String.format(
            "SELECT bi.id, bi.user_id, bi.role, bi.status, bi.created_at, u.full_name, u.email, u.role_name " +
            "FROM become_instructors bi " +
            "JOIN users u ON bi.user_id = u.id " +
            "WHERE bi.status = 'accept' " +
            "AND FROM_UNIXTIME(bi.created_at) >= NOW() - INTERVAL %s DAY " +
            "ORDER BY bi.created_at DESC;",
            days
        );
    }

    // US14 için dinamik metodlar
    public String getUs14InvalidRoles() {
        return getUs14_become_instructors_invalid_roles();
    }

    public String getUs14ValidRoles() {
        return getUs14_become_instructors_valid_roles();
    }

    public String getUs14AllRoles() {
        return getUs14_become_instructors_all_roles();
    }

    // US15 için dinamik metodlar
    public String getUs15OutOfStockProducts() {
        return getUs15_products_out_of_stock();
    }

    public String getUs15OutOfStockCount() {
        return getUs15_products_out_of_stock_count();
    }

    public String getUs15StockAnalysis() {
        return getUs15_products_stock_analysis();
    }

}
