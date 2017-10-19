
public class Product {
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Product(int id){
		this.id = id;
	}
	
	public String toString(){
		return "²úÆ·: " +this.id;
	}
}
