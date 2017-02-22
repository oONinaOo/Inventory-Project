
public class CDProduct extends Product {
	
	private int pageSize;

	public CDProduct(String name, int price, int pageSize){
		super(name, price);
		this.pageSize = pageSize;
	}

}
