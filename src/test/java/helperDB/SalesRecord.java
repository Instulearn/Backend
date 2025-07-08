package helperDB;

import lombok.Getter;

import java.util.List;

@Getter
public class SalesRecord {
    int sellerId;
    int productId;
    int totalSales;
    public static List<SalesRecord> salesRecords;

    public SalesRecord(int sellerId, int productId, int totalSales) {
        this.sellerId = sellerId;
        this.productId = productId;
        this.totalSales = totalSales;
    }
    public static SalesRecord findSalesRecord(List<SalesRecord> salesRecords, int sellerId, int productId) {
        return salesRecords.stream()
                .filter(record -> record.getSellerId() == sellerId && record.getProductId() == productId)
                .findFirst()
                .orElse(null);
    }

}

