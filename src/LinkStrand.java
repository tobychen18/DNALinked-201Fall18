import java.util.ArrayList;

public class LinkStrand implements IDnaStrand {
	
		private Node myFirst,myLast;
		   private long mySize;
		   private int myAppends;
	
	   private class Node {
		   	String info;
		   	Node next;
		   	public Node(String s) {
		      	info = s;
		      	next = null;
		   	}
		   }
		  
	
		   
	public LinkStrand() {
		this("");
	}
	public LinkStrand(String str) {
		initialize(str);
	}
	
	@Override 
	public void initialize(String source) {
		myFirst = new Node(source);
		myLast = myFirst;
		mySize = source.length();
		myAppends = 0;			
	}
	
	@Override 
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}
	@Override
	public long size() {
		return mySize;
	}
	@Override
	public IDnaStrand append(String dna) {
		myLast.next = new Node(dna);
		myLast = myLast.next;
		mySize += dna.length();
		myAppends += 1;
		return this;
	}
	
	@Override
	public IDnaStrand reverse() {
		StringBuilder reversedStringBuilder = new StringBuilder();
		reversedStringBuilder.append(this.toString()).reverse();
		ArrayList<String> reversedStringsInSameOrder = new ArrayList<String>();
		Node list = myFirst;
		int pointer = 0;
		while(list != null) {
			reversedStringsInSameOrder.add(reversedStringBuilder.substring(pointer, list.info.length()));
			pointer += list.info.length();
			list = list.next;
		}
		if(reversedStringsInSameOrder.size() == 0) {
			return null;
		}
		LinkStrand reversed = new LinkStrand(reversedStringsInSameOrder.get(reversedStringsInSameOrder.size()-1).toString());
		for(int i = reversedStringsInSameOrder.size()-2; i >= 0; i--) {
			reversed.append(reversedStringsInSameOrder.get(i).toString());
		}
		return reversed;
	}
	@Override
	public int getAppendCount() {
		return myAppends;
	}
	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder(); 
		Node list = myFirst;
		while(list != null) {
			str.append(list.info);
			list = list.next;
		}
		return str.toString();
	}
}
