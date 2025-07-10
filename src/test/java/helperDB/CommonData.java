package helperDB;

import com.github.javafaker.Faker;
import lombok.Getter;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import static helperDB.JDBC_Structure_Methods.*;
import static org.junit.jupiter.api.Assertions.*;

@Getter
public class CommonData {

    public static Map<String, Integer> totalMeetingsMap;
    public static Map<String, Integer> becomeInsGroup;
    public static Map<String, Double> percentageMap;
    public static List<Integer> user_ids;
    public static String status;
    public static String currentStatus;
    public static Map<Integer, String> financial_installment_usersId_fullname;
    public static Map<String, Integer> productsMinInventory;
    public static List<String>discountCode;
    public static String slug;
    public static int totalMeetings;
    public static Double actualPercentage;
    public static int rowCount;
    public static boolean hasRows;
    public static List<String> product_gift;
    public static int limitedQuestionsId;
    public static Map<Integer,String> teacher_ids;
    public static Map<String,Integer> wLists;
    public static int quizId;
    public static int[] result;
    private String full_name;
    private String email;
    private String password;
    private String newPassword;
    private String role_name;
    private int role_id;
    private Timestamp now;
    Instant instant = Instant.now();
    long unixTimestamp = instant.getEpochSecond();
    public static int userId;
    private  String jwtToken;
    private  String apiKey;
    private  String apiSecret;
    private  String accountId;
    public static Map<String, Integer> statusCounts ;


    //(full_name, email, password, role_name, role_id, created_at)
    static Faker faker = new Faker();
    /**
     * userType Listesinde rastgele usertype secme işlemi
     */
    static Random random = new Random();
    static List<String> userTypes = Arrays.asList("user", "teacher", "organization", "admin");


    /**
     * Kullanıcı türlerine karşılık gelen rol ID'leri içeren haritayı oluşturan method
     **/

    public static int getRoleId(String role_name) {
        Map<String, Integer> userTypeToRoleId = new HashMap<>();
        userTypeToRoleId.put("user", 1);
        userTypeToRoleId.put("admin", 2);
        userTypeToRoleId.put("organization", 3);
        userTypeToRoleId.put("teacher", 4);

        return userTypeToRoleId.get(role_name);
    }

    /**
     * us09 user id yi döndürmek için methodumuz
     */
    public static int getLastInsertedUserId(String query, String full_name) throws SQLException {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, full_name);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        } else {
            throw new SQLException("Kullanıcı ID bulunamadı");
        }
    }

    /**
     * us09 password update işlemi için ilkiyle aynı olmayan password olusturma
     */
    private String generateNewPassword(Faker faker, String currentPassword) {
        String newPassword;
        do {
            newPassword = faker.internet().password();
        } while (newPassword.equals(currentPassword));
        return newPassword;
    }
    /** users tablosu idleri listeye atayıp random secme işlemi*/
    public static Integer getUserIdUsersTable(String query) throws SQLException {
        user_ids=new ArrayList<>();
        resultSet=getStatement().executeQuery(query);
       while (resultSet.next()) {
          user_ids.add(resultSet.getInt("id"));
       }
        Random random = new Random();
        int randomIndex = random.nextInt(user_ids.size());
        return user_ids.get(randomIndex);
    }
    public static void assertStatusCounts(Map<String, Integer> statusCounts) {
        // Assert that the map is not empty
        assertFalse(statusCounts.isEmpty(), "Status counts should not be empty");

        // Example assertions for specific statuses
        // These values should be adjusted based on your actual data
        // If there are no such statuses, the assertions will be skipped
        statusCounts.forEach((status, count) -> {
            System.out.printf("Asserting status: %s with count: %d%n", status, count);
            switch (status) {
                case "pending":
                    assertTrue(count >= 0, "Pending count should be non-negative");
                    break;
                case "waiting_delivery":
                    assertTrue(count >= 0, "waiting_delivery count should be non-negative");
                    break;
                case "success":
                    assertTrue(count >= 0, "Delivered count should be non-negative");
                    break;
                // Add more cases as needed
                default:
                    fail("Unexpected status: " + status);
            }
        });

    }


    public CommonData() throws SQLException {
        this.full_name = faker.name().fullName();
        this.email = faker.internet().emailAddress();
        this.password = faker.internet().password();
        this.role_name = userTypes.get(random.nextInt(userTypes.size()));
        this.role_id = getRoleId(role_name);
        this.newPassword = generateNewPassword(faker, this.password);
        this.jwtToken = faker.internet().uuid();
        this.apiKey = faker.internet().password();
        this.apiSecret = faker.internet().password();
        this.accountId = faker.internet().uuid();


    }

}
