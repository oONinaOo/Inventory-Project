
public class Main {

	public static void main(String[] args) {
		StoreManager storeManager = new StoreManager();
		StoreCapable storeCapable = new PersistentStore();
		storeManager.addStorage(storeCapable);
		storeManager.addCDProduct("Valamilyen album", 1899, 15);
		storeManager.addBookProduct("Valamilyen könyv", 3500, 329);
		System.out.println(storeManager.listProducts());
		System.out.println(storeManager.getTotalProductPrice());
	}
}