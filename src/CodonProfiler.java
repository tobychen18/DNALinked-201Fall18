/**
 * create a more efficient version of get Codon profile
 * @author toby chen tc248
 */

import java.util.*;

public class CodonProfiler {

	/**
	 * Count how many times each codon in an array of codons occurs
	 * in a strand of DNA. Return int[] such that int[k] is number
	 * of occurrences of codons[k] in strand. Strand codons can start
	 * at all valid indexes that are multiples of 3: 0, 3, 6, 9, 12, ...
	 * maps all the occurrences of all different codons  using iterator
	 * then uses this map to see how many instances of the codons we want to look at are
	 * @param strand is DNA to be analyzed for codon occurrences. The strand of DNA we are using.
	 * @param codons is an array of strings, each has three characters. Gives an arrayList of Strings that we want to find the codons for
	 * @return int[] such that int[k] is number of occurrences of codons[k] in strand. 
	 */
	public int[] getCodonProfile(IDnaStrand strand, String[] codons) {
		HashMap<String,Integer> map = new HashMap<>(); //create a new hashMap of strings keys, and integer values that will map codons and the number of occurences of the codons
		int[] ret = new int[codons.length]; //create an array that is the same length of codon array to find # of instances of the codons that matches up to its position in codons and ret
		Iterator<Character> iter = strand.iterator(); //create an iterator of the dna strand we are looking at
		while (iter.hasNext()) { // as long as this DNA strand still has characters
			char a = iter.next(); //char a is the next letter 
			char b = 'z';           // not part of any real codon
			char c = 'z';			//not part of any real codon
			if (iter.hasNext()) { //if DNA has another character assign it to b
				b = iter.next();
			}
			if (iter.hasNext()) { //if DNA has even another character assign it to c
				c = iter.next();
			}
			if(b != 'z' && c != 'z') { //if b and c got assigned to possible values in the DNA aka the DNA strand had enough characters to update b and c

				String codon = "" + a + b + c; //the codon is the next 3 characters
				if(!map.containsKey(codon)) { //if the map doesn't contain the codon
					map.put(codon, 1); //put the codon into the map and put that its the first occurrence
				}
				else {
					map.put(codon, map.get(codon)+1); //add one to the occurrences of this codon
				}
			}
		}
		for( int i = 0; i < codons.length; i++) { //traverse the codons array
			if(map.containsKey(codons[i])) { //if the map contains the codon at index i, we are looking at in codons array
				ret[i] = map.get(codons[i]); //put the amount of occurrences of that codon into ret at the same index i
			}
			else { //otherwise the codon is not in our map/dna strand
				ret[i] = 0;  //so set that index to 0
			}
		}

		return ret; //return the occurrences of codons that indexes match up 
	}
}


//old inefficient code
/*


		for(int k=0; k < codons.length; k++) {
			Iterator<Character> iter = strand.iterator();
			while (iter.hasNext()) {
				char a = iter.next();
				char b = 'z';           // not part of any real codon
				char c = 'z';
				if (iter.hasNext()) {
					b = iter.next();
				}
				if (iter.hasNext()) {
					c = iter.next();
				}
				String cod = ""+a+b+c;
				if (cod.equals(codons[k])) {
					ret[k] += 1;
				}
			}
		}
		return ret;
	}
}
 */
