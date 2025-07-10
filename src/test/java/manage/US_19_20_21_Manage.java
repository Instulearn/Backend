package manage;

import lombok.Getter;

@Getter
public class US_19_20_21_Manage {

    private String failed_jobs_add_data = "INSERT INTO failed_jobs (uuid,connection,queue,payload,exception) VALUES ('deneme_mySQL_uuid_1','connection_deneme1','queue_mySQL1','payload_mySQL1','exception_mySQL1'),('deneme_mySQL_uuid_2','connection_deneme2','queue_mySQL2','payload_mySQL2','exception_mySQL2'),('deneme_mySQL_uuid_3','connection_deneme3','queue_mySQL3','payload_mySQL3','exception_mySQL3'),('deneme_mySQL_uuid_4','connection_deneme4','queue_mySQL4','payload_mySQL4','exception_mySQL4'),('deneme_mySQL_uuid_5','connection_deneme5','queue_mySQL5','payload_mySQL5','exception_mySQL5');";


}
