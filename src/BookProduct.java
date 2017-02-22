
public class BookProduct extends Product{
	
	private int numOfTracks;

	public BookProduct(String name, int price, int numOfTracks){
		super(name, price);
		this.numOfTracks = numOfTracks;
	}

}
