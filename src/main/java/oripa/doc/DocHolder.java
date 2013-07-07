package oripa.doc;

import oripa.resource.Constants;

public class DocHolder {
	private static DocHolder instance = new DocHolder();
	private Doc doc;	

	public static DocHolder getInstance(){
		return instance;
	}
	
	private DocHolder(){
		doc = new Doc(Constants.DEFAULT_PAPER_SIZE);
	}
	
	public Doc getDoc(){
		return doc;
	}
	
	public void setDoc(Doc value){
		doc = value;
	}
}
