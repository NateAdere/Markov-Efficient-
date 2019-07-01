import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}
	
	/**
	 * Default constructor has order 3
	 */
	public EfficientMarkov() {
		this(3);
	}
	
	@Override
	public void setTraining(String text) {
		myText = text;
		for (int index = 0; index < myText.length(); index+= 1 ) {
			if (index+ myOrder > myText.length()) break;
			String keys =  myText.substring(index, index+myOrder);
			String ch = PSEUDO_EOS;
	         if (index+myOrder+1 < myText.length() +1) {
	            ch = myText.substring(index+myOrder, index+myOrder+1);
	         }
	         else {
	            ch = PSEUDO_EOS;
	         }
			ArrayList<String> follows = new ArrayList<>();
			myMap.putIfAbsent(keys, follows);
			myMap.get(keys).add(ch);
		
		}
	}
	
	/**
	 * public void setTraining(String text) {
		myText = text;
		for (int index = 0; index < myText.length(); index+= 1 ) {
			if (index+ myOrder > myText.length()) break;
			String keys =  myText.substring(index, index+myOrder);
			int pos = 0;
			ArrayList<String> follows = new ArrayList<String>();
			while (pos < myText.length()){
				int start = myText.indexOf(keys,pos);
				if (start == -1){
					//System.out.println("didn't find "+key);
					break;
				}
				if (start + keys.length() >= myText.length()){
					//System.out.println("found end with "+key);
					follows.add(PSEUDO_EOS);
					break;
				}
				// next line is string equivalent of myText.charAt(start+key.length())
				String next = myText.substring(start+keys.length(), start+keys.length()+1);
				follows.add(next);
				pos = start+1;  // search continues after this occurrence
				
			}
			myMap.put(keys, follows);
		}
		
		
	}
	*/
	@Override
	public ArrayList<String> getFollows(String key){
		ArrayList<String> ans;
		if (myMap.get(key) == null) {
			throw new NoSuchElementException(key+" not in map");
		}
		else {
			ans = myMap.get(key);
		}
		
		return ans;
	}
	
}	
