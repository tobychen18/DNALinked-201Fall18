/*
 * use a linked list to represent a more efficient DNA strand
 * @author Toby Chen tc248
 */
import java.util.ArrayList;

public class LinkStrand implements IDnaStrand {

	private Node myFirst,myLast;
	private long mySize;
	private int myAppends;
	private int myLocalIndex;
	private int myIndex;
	private Node myCurrent;

	
	/*
	 * Internal node class used for having an internal linked list for linked Strand
	 */
	private class Node {
		String info; //the info of the node
		Node next; //the next node that this node is pointing at
		/*
		 * Make a node that houses a string and points to null
		 * @param s string that the node will house
		 */
		public Node(String s) {
			info = s;
			next = null;
		}
	}

/*
 * Default constructor that makes this DNALinkStrand's string empty
 */
	public LinkStrand() {
		this("");
	}
	
	/*
	 * Constructor that takes in a string str as the DNA strand and initializes the rest of the fields
	 *
	 */
	public LinkStrand(String str) {
		initialize(str);
	}
	
	/*
	 * Initialize by copying the given strand as the DNA strand we will be using as our first node as well as resetting all other fields.
	 * set our nodes as initialize will create the one and only node in the DNA strand at the moment
	 * @param source is the string used to initialize this strand
 * @see IDnaStrand#initialize(java.lang.String) for more info
 */
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

	/**
	 * Returns a new LinkStrand with DNA of string source and only 1 node
	 * @param the dna string that we want to return a new linkstrand with
	 * @return new linkStrand with one node of DNA that is given in the parameter
	 */
	@Override 
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}
	
	/*
	 *finds the size of this DNA linkStrand's DNA strand of all nodes combined
	 *@return size of myDNA strand
	 */
	@Override
	public long size() {
		return mySize;
	}
	
	/*
	 * (non-Javadoc)
	 * @see IDnaStrand#append(java.lang.String)
	 */
	@Override
	public IDnaStrand append(String dna) {
		myLast.next = new Node(dna);
		myLast = myLast.next;
		mySize += dna.length();
		myAppends += 1;
		return this;
	}

	/*
	 * Reverse the DNA strand string and it's corresponding nodes without mutating this linkStrand but by returning a new linkedstrand
	 * @return a new LinkedStrand that has its nodes and its nodes values reverse, aka reversing the entire linked strand
	 */
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
	
	/*
	 * return the number of times you have added a new node aka added new parts of DNA
	 * @return the number of times we called appends
	 */
	@Override
	public int getAppendCount() {
		return myAppends;
	}
	
	/*
	 * Returns a character at a specified index of the DNA linked strand but with this linked list charAt will be more efficient 
	 * by starting the search from the end of our previous search, so we don't have to start from the beginning each time or if necessary start from the beginning
	 * start the search from end of our previous search using local variables
	 * @param the index that we want to find the character at
	 * @return the character at the given index
	 * @throws IndexOutOfBoundsException if index < 0 or index >= size() aka index is not possible for our DNA strand
	*/
	
	@Override
	public char charAt(int index) { 
		if(index >= this.size() || index< 0) { //if the index is not in the DNA strand aka not possible
			throw new IndexOutOfBoundsException("not a valid index"); //throw an exception that says this index is not a valid index
		}
		if(myIndex>index || myCurrent == null) { //if myIndex is more than index then start from the beginning rather than starting from myIndex because you have to start search from beginning or if you are at the end and its the last node then reset to start from the beginning
			myCurrent = myFirst; //reset to the beginning
			myLocalIndex = 0;
			myIndex = 0;
		}
		while(myIndex != index) { //if myIndex is not index
			myIndex++; //then add one to index and myLocal index
			myLocalIndex++; //got to do this before you test the if statement because if not you could get an index out of bounds error
			if(myLocalIndex >= myCurrent.info.length()) //if my local index is more than the size of the node, go to the next node because you have traversed the whole node
			{
				myCurrent = myCurrent.next; //go to the next node
				myLocalIndex = 0; //reset my local index because now you are looking at the first thing in the next node
			}
		}
		//loop ends when myIndex = index and myCurrent and myLocal index point at the char you want
		return myCurrent.info.charAt(myLocalIndex); //return the character at the index of the string in the node
	}
	
	/*
	 * return the string of the DNA strand, linking the linkedStrand node information together
	 * Use stringBuilder to be more efficient to add the strings together in the order of the linkedList nodes
	 * @return the String of all the nodes in linkedList combined
	 */

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();  //create a new string builder
		Node list = myFirst; //list pointer to myFirst
		while(list != null) { //keep going till the end of the linkedList
			str.append(list.info); //add the info of the next node
			list = list.next; //go to the nextNode
		}
		return str.toString();
	}
}
