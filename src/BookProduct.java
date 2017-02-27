
public class BookProduct extends Product{
	
	private int pageSizes;

	public BookProduct(String name, Integer price, int pageSizes){
		super(name, price);
		this.pageSizes = pageSizes;
	}

}
