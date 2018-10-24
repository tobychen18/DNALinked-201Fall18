import java.util.ArrayList;

public class LinkStrand implements IDnaStrand {
	
		private Node myFirst,myLast;
		   private long mySize;
		   private int myAppends;
		   private int myLocalIndex;
		  private int myIndex;
		  private Node myCurrent;
		   
	
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
		myLocalIndex = 0;;
		   myIndex = 0;
		  myCurrent = myFirst;
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
			
			String nodeReversedString = reversedStringBuilder.substring(size-(list.info.length()+pointer), size - pointer).toString(); //this is the string we want from the reversed string builder
			//you go from reverse aka the back of the reversed string to get the first node's string and so on, you go from the back 
			
			reversedStringsInSameOrder.add(nodeReversedString); //add this reversed string to list
			
			pointer += list.info.length(); //update pointer
			list = list.next; //update list
		}
		
		/*
		if(reversedStringsInSameOrder.size() == 0) {
			return null; //if there's nothing then it's null
		}
		*/
		
		LinkStrand reversed = new LinkStrand(reversedStringsInSameOrder.get(reversedStringsInSameOrder.size()-1).toString()); //initialize a new LinkStrand with the last element of the arrayList aka the last node
		for(int i = reversedStringsInSameOrder.size()-2; i >= 0; i--) { //go backwards so you add the nodes in reverse order since we are reversing
			reversed.append(reversedStringsInSameOrder.get(i).toString()); //add the nodes in reverse order such that order 1,2,3,4 is reversed to 4,3,2,1
		}
		return reversed; //return the new reversed linkstrand
	}
	@Override
	public int getAppendCount() {
		return myAppends;
	}
	@Override
	public char charAt(int index) {
		if(index > this.size() || index< 0) {
			throw new IndexOutOfBoundsException("not a valid index");
		}
		if(myIndex>index || myCurrent == null) {
			myCurrent = myFirst;
			myLocalIndex = 0;
			myIndex = 0;
		}
		while(myIndex != index) {
			myIndex++;
			myLocalIndex++; //got to do this before you test the if statement because if not you could get an index out of bounds error
			if(myLocalIndex >= myCurrent.info.length())
			{
				myCurrent = myCurrent.next;
				myLocalIndex = 0;
			}
		}
		return myCurrent.info.charAt(myLocalIndex);
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
