package helperDB;

import lombok.Getter;

@Getter
public class Sales {
    private int productOrderId;
    private double totalSales;
    private double totalCommission;

    public Sales(int productOrderId, double totalSales, double totalCommission) {
        this.productOrderId = productOrderId;
        this.totalSales = totalSales;
        this.totalCommission = totalCommission;
    }
}
