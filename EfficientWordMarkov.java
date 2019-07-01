import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {
	private Map<WordGram,ArrayList<String>> myMap;
	
	
	public EfficientWordMarkov() {
		this(2);
	}
	
	public EfficientWordMarkov(int order){
		super(order);
		myMap = new HashMap();
	}
	
	
	@Override
	public void setTraining(String text) {
		myWords = text.split("\\s+");
		for (int index = 0; index < myWords.length; index+= 1 ) {
			if (index+ myOrder > myWords.length) break;
			WordGram a = new WordGram(myWords,index,myOrder);
			String ch = PSEUDO_EOS;
	         if (index+myOrder+1 < myWords.length +1) {
	            ch = myWords[index+myOrder];
	         }
	         else {
	            ch = PSEUDO_EOS;
	         }
			ArrayList<String> follows = new ArrayList<>();
			myMap.putIfAbsent(a, follows);
			myMap.get(a).add(ch);
		
		}
	}
	
	@Override
	public ArrayList<String> getFollows(WordGram key){
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
