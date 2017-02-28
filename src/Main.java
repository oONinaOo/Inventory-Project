
public class Main {

	public static void main(String[] args) {
		StoreManager storeManager = new StoreManager();
		StoreCapable storeCapable = new PersistentStore();
		storeManager.addStorage(storeCapable);
		storeManager.addCDProduct("Valamilyen album1", 1899, 15);
		storeManager.addBookProduct("Valamilyen könyv1", 2999, 200);
		storeManager.addCDProduct("Valamilyen album2", 2599, 12);
		storeManager.addBookProduct("Valamilyen könyv2", 3500, 329);
		System.out.println(storeManager.listProducts());
		System.out.println(storeManager.getTotalProductPrice());
	}
}