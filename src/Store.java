import java.util.ArrayList;
import java.io.File;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public abstract class Store implements StoreCapable{
	
	static File inputFile = new File("inventory.xml");
	static String fileName = "Products.xml";
	ArrayList<Product> productList = new ArrayList<>();
	
	public ArrayList<Product> getAllProduct() {
		return this.productList;
	}
	
	public void storeCDProduct(String name, int price, int tracks){
		Product CD = new CDProduct(name, price, tracks);
		store(CD);
	}
	public void storeBookProduct(String name, int price, int pages){
		Product book = new BookProduct(name, price, pages);
		store(book);	
	}
	
	protected abstract void storeProduct(Product product);
	
	public Product createProduct(String type, String name, int price, int size){
		if(type == "CD"){
			CDProduct CD = new CDProduct(name,price,size);
			return CD;
			
		}else{
			BookProduct Book = new BookProduct(name,price,size);
			return Book;
		}
	}
	
	public void saveToXML(Product product) {
		File xmlFile = new File(fileName);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			Document doc;
			Element rootElement;
			if (xmlFile.exists()) {
				doc = dBuilder.parse(xmlFile);
				rootElement = doc.getDocumentElement();
			} else {
				doc = dBuilder.newDocument();
				rootElement = doc.createElement("Products");
				doc.appendChild(rootElement);
			}
			Element name = doc.createElement("Product");
			rootElement.appendChild(name);

			Attr attr = doc.createAttribute("Name");
			attr.setValue(product.name);
			name.setAttributeNode(attr);
			Attr attr2 = doc.createAttribute("Price");
			attr2.setValue(product.price.toString());
			name.setAttributeNode(attr2);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(fileName));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Product> loadProducts() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("Product");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
                    			String instanceName = eElement.getAttribute("Name");
                    			int instancePrice = Integer.parseInt(eElement.getAttribute("Price"));
                    			productList.add(createProduct("CD", instanceName, instancePrice, 0));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.productList;
}
	public void store(Product product){
		saveToXML(product);
		storeProduct(product);	
	}
	
}
