
public class Transaction {
	public Transaction (String name, String date, String product) {
		this.name = name;
		this.date = date;
		this.product = product;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}


	private String name ;
	private String date;
	private String product;
	
	
	
	
	
}