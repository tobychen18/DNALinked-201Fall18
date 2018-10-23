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
		StringBuilder reversedStringBuilder = new StringBuilder(); //make a new string builder
		reversedStringBuilder.append(this.toString()).reverse(); //reverse all the strings in this list
		ArrayList<String> reversedStringsInSameOrder = new ArrayList<String>(); //make an arrayList to store the reversed strings but not in the correct order yet
		Node list = myFirst; //make a new node pointer
		int pointer = 0; //make a pointerValue
		int size = reversedStringBuilder.length(); //get the size of the string we reversed
		while(list != null) { //for all the things in list
			
			String nodeReversedString = reversedStringBuilder.substring(size-(list.info.length()+pointer), size - pointer).toString();
			reversedStringsInSameOrder.add(nodeReversedString);
			
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
