
public class Scrip {
	String name;
	float LTP;
	float buy;
	float sell;
	String buyTarget;
	String sellTarget;
	
	public Scrip() {
		this.name = "";
		LTP = 0.0f;
		this.buy = 0.0f;
		this.sell = 0.0f;
		this.buyTarget = "";
		this.sellTarget = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLTP() {
		return LTP;
	}

	public void setLTP(float lTP) {
		LTP = lTP;
	}

	public float getBuy() {
		return buy;
	}

	public void setBuy(float buy) {
		this.buy = buy;
	}

	public float getSell() {
		return sell;
	}

	public void setSell(float sell) {
		this.sell = sell;
	}

	public String getBuyTarget() {
		return buyTarget;
	}

	public void setBuyTarget(String buyTarget) {
		this.buyTarget = buyTarget;
	}

	public String getSellTarget() {
		return sellTarget;
	}

	public void setSellTarget(String sellTarget) {
		this.sellTarget = sellTarget;
	}
	
}
