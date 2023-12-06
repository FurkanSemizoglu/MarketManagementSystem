import java.util.ArrayList;

public class ProductList {
	 private String name;
	 private String date;
	 private String productName;
	 private ArrayList<Product> products;
	 
	 
	 public ProductList(String name ,String date ,String productName) {
	        this.name = name;
	        this.date = date;
	        this.productName = productName;
	        this.products = new ArrayList<Product>();
	 }
	 
	 
	



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public ArrayList<Product> getProducts() {
		return products;
	}

	public int getProductsSize() {
		return products.size();
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}



	public void addProduct(String productName, String date) {
	        Product product = new Product(productName, date);
	        products.add(product);
	 }    
	    
	 public void displayProducts() {
	        System.out.println(name);
	        for (Product product : products) {
	            System.out.println( product.getProductName() + ", " + product.getDate());
	        }
	 }
	    

	   
}
