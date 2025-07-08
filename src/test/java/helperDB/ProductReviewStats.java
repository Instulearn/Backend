package helperDB;

import lombok.Getter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Getter
public class ProductReviewStats {
    private int productId;
    private int totalReviews;
    private double averageRating;
    private int maxRating;
    private int minRating;

    public ProductReviewStats(int productId, int totalReviews, double averageRating, int maxRating, int minRating) {
        this.productId = productId;
        this.totalReviews = totalReviews;
        this.averageRating = averageRating;
        this.maxRating = maxRating;
        this.minRating = minRating;
    }
    public static void assertProductReviewStats(List<ProductReviewStats> productReviewStatsList) {
        // Assert that the list is not empty
        assertFalse(productReviewStatsList.isEmpty(), "Product review stats list should not be empty");

        // Example assertion for min rating < 3
        for (ProductReviewStats stats : productReviewStatsList) {
            assertTrue(stats.getMinRating() < 3, "Minimum rating should be less than 3");
        }

        // Additional assertions can be added as required
    }
}
