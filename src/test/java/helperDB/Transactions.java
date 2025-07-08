package helperDB;

import com.github.javafaker.Faker;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
@Getter

public class Transactions {
    static Faker faker=new Faker();


    private int id;
    private static List<String> status=new ArrayList<>(Arrays.asList("successful","failed","pending","invalid"));
    private  String orderStatus;
    private String order;
    private String email;
    private String subject;
    private String url;
    private Integer amount;
    private String full_name;
    private static int  randomIndex;

    public Transactions(int id, String orderStatus, String order, String email, String subject, String url, Integer amount, String full_name) {

        this.id = id;
        this.orderStatus = orderStatus;
        this.order = order;
        this.email = email;
        this.subject = subject;
        this.url = url;
        this.amount = amount;
        this.full_name = full_name;

    }

    //(id, status, `order`, email, subject, url, amount, notified_at, created_at, updated_at, full_name)
    private List<Transactions> transactions;



    public static List<Transactions> getPayku() {
        return List.of(
           new Transactions(faker.number().numberBetween(19000, 100000),status.get(randomIndex), "order"+faker.number().randomNumber(), faker.internet().emailAddress(), faker.lorem().word(), faker.company().url(), (int) faker.number().randomNumber(), faker.name().fullName())  ,
           new Transactions(faker.number().numberBetween(19000, 100000),status.get(randomIndex), "order"+faker.number().randomNumber(), faker.internet().emailAddress(), faker.lorem().word(), faker.company().url(), (int) faker.number().randomNumber(), faker.name().fullName())  ,
           new Transactions(faker.number().numberBetween(19000, 100000),status.get(randomIndex), "order"+faker.number().randomNumber(), faker.internet().emailAddress(), faker.lorem().word(), faker.company().url(), (int) faker.number().randomNumber(), faker.name().fullName())  ,
           new Transactions(faker.number().numberBetween(19000, 100000),status.get(randomIndex), "order"+faker.number().randomNumber(), faker.internet().emailAddress(), faker.lorem().word(), faker.company().url(), (int) faker.number().randomNumber(), faker.name().fullName())  ,
           new Transactions(faker.number().numberBetween(19000, 100000),status.get(randomIndex), "order"+faker.number().randomNumber(), faker.internet().emailAddress(), faker.lorem().word(), faker.company().url(), (int) faker.number().randomNumber(), faker.name().fullName())

        );
    }
    public static List<Transactions> generateAccounts(int count) {
        List<Transactions> accounts = new ArrayList<>();
        Random random=new Random();
        randomIndex = random.nextInt(status.size());




        for (int i = 0; i < count; i++) {
            Transactions account = new Transactions(
                    faker.number().numberBetween(19000, 100000),
                    status.get(randomIndex),
                    "order"+faker.number().randomNumber(),
                    faker.internet().emailAddress(),
                    faker.lorem().word(),
                    faker.company().url(),
                    (int) faker.number().randomNumber(),
                    faker.name().fullName()
            );
            accounts.add(account);
        }
        return accounts;
    }
}
