package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Coupons_PostPojo implements Serializable {
	private String title;
	private String discount_type;
	private String source;
	private String code;
	private int percent;
	private int amount;
	private int max_amount;
	private int minimum_order;
	private int count;
	private String product_type;
	private int for_first_purchase;
	private String expired_at;

	@Override
	public String toString() {
		return "{" +
				"title='" + getTitle() + '\'' +
				", discount_type='" + getDiscount_type() + '\'' +
				", source='" + getSource() + '\'' +
				", code='" + getCode() + '\'' +
				", percent=" + getPercent() +
				", amount=" + getAmount() +
				", max_amount=" + getMax_amount() +
				", minimum_order=" + getMinimum_order() +
				", count=" + getCount() +
				", product_type='" + getProduct_type() + '\'' +
				", for_first_purchase=" + getFor_first_purchase() +
				", expired_at='" + getExpired_at() + '\'' +
				'}';
	}
}