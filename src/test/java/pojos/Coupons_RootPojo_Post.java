package pojos;

import java.io.Serializable;

public class Coupons_RootPojo_Post implements Serializable {
	private String title;
	private String discountType;
	private String source;
	private String code;
	private int percent;
	private int amount;
	private int maxAmount;
	private int minimumOrder;
	private int count;
	private String productType;
	private int forFirstPurchase;
	private String expiredAt;

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setDiscountType(String discountType){
		this.discountType = discountType;
	}

	public String getDiscountType(){
		return discountType;
	}

	public void setSource(String source){
		this.source = source;
	}

	public String getSource(){
		return source;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setPercent(int percent){
		this.percent = percent;
	}

	public int getPercent(){
		return percent;
	}

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void setMaxAmount(int maxAmount){
		this.maxAmount = maxAmount;
	}

	public int getMaxAmount(){
		return maxAmount;
	}

	public void setMinimumOrder(int minimumOrder){
		this.minimumOrder = minimumOrder;
	}

	public int getMinimumOrder(){
		return minimumOrder;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setProductType(String productType){
		this.productType = productType;
	}

	public String getProductType(){
		return productType;
	}

	public void setForFirstPurchase(int forFirstPurchase){
		this.forFirstPurchase = forFirstPurchase;
	}

	public int getForFirstPurchase(){
		return forFirstPurchase;
	}

	public void setExpiredAt(String expiredAt){
		this.expiredAt = expiredAt;
	}

	public String getExpiredAt(){
		return expiredAt;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDTO{" + 
			"title = '" + title + '\'' + 
			",discount_type = '" + discountType + '\'' + 
			",source = '" + source + '\'' + 
			",code = '" + code + '\'' + 
			",percent = '" + percent + '\'' + 
			",amount = '" + amount + '\'' + 
			",max_amount = '" + maxAmount + '\'' + 
			",minimum_order = '" + minimumOrder + '\'' + 
			",count = '" + count + '\'' + 
			",product_type = '" + productType + '\'' + 
			",for_first_purchase = '" + forFirstPurchase + '\'' + 
			",expired_at = '" + expiredAt + '\'' + 
			"}";
		}
}