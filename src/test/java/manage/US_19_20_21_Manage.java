package manage;

import lombok.Getter;
import java.util.UUID;

@Getter
public class US_19_20_21_Manage {

    private String failed_jobs_add_data = "INSERT INTO failed_jobs (uuid,connection,queue,payload,exception) VALUES ('deneme_mySQL_uuid_1','connection_deneme1','queue_mySQL1','payload_mySQL1','exception_mySQL1'),('deneme_mySQL_uuid_2','connection_deneme2','queue_mySQL2','payload_mySQL2','exception_mySQL2'),('deneme_mySQL_uuid_3','connection_deneme3','queue_mySQL3','payload_mySQL3','exception_mySQL3'),('deneme_mySQL_uuid_4','connection_deneme4','queue_mySQL4','payload_mySQL4','exception_mySQL4'),('deneme_mySQL_uuid_5','connection_deneme5','queue_mySQL5','payload_mySQL5','exception_mySQL5');";

    // Dinamik sorgu oluşturan metod - orijinal UUID kullan
    public String getFailed_jobs_dynamic_data(String uuid, String connection, String queue, String payload, String exception) {
        return String.format(
            "INSERT INTO failed_jobs (uuid,connection,queue,payload,exception) VALUES ('%s','%s','%s','%s','%s')",
            uuid, connection, queue, payload, exception
        );
    }

    private String failed_jobs_delete_data = "DELETE FROM failed_jobs WHERE uuid=?;";

    //US21
    private String us21banStatus_users = "SELECT \n" +
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




}
