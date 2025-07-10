package helperDB;

public class BanStatus {

    public static class BanInfo {
        String banStatus;
        int totalUsers;
        double percentage;

        public BanInfo(String banStatus, int totalUsers, double percentage) {
            this.banStatus = banStatus;
            this.totalUsers = totalUsers;
            this.percentage = percentage;
        }

        @Override
        public String toString() {
            return "BanInfo{" +
                    "banStatus='" + banStatus + '\'' +
                    ", totalUsers=" + totalUsers +
                    ", percentage=" + percentage +
                    '}';
        }

    }
}

