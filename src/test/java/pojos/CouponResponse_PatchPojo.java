package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CouponResponse_PatchPojo implements Serializable {
	private String remark;
	private int status;
	private String message;
	@JsonProperty("Updated Coupon ID")
	private int UpdatedCouponID;


}