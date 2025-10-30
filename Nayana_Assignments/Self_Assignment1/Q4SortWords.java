
/* METHODS USED IN THIS CODE -> 
split(String regex) – Splits a string into an array of substrings based on the given regular expression.
compareTo(String anotherString) – Compares two strings lexicographically for sorting; .
returns 0 if equal, positive if the first string is greater, and negative if the first string is smaller.
toLowerCase() – Converts all characters in a string to lowercase.

  HERE WE HAVE INCLUDED ONLY ALPHABETS IN THE WORDS NOT THE PUNCTUATION */


package SelfAssignment_1;

public class Q4SortWords {
	public static void main(String[] args) {
		String text = "To be or not to be, that is the question;"
				+"Whether `tis nobler in the mind to suffer" 
				+" the slings and arrows of outrageous fortune,"
				+" or to take arms against a sea of troubles,"
				+" and by opposing end them?";
		
		String words[]=text.split("[^a-zA-Z]+");
		
		for(int i=0;i<words.length;i++) {
			words[i]=words[i].toLowerCase();
		}
		
		System.out.println("Words before Sorting : ");
		for(int i=0;i<words.length;i++) {
			System.out.print(words[i]+" ");
		}
		
		sort(words);
		
		System.out.println("\nWords after Sorting : ");
		for(int i=0;i<words.length;i++) {
			System.out.print(words[i]+" ");
		}
	}
//	bubble sort 	
	public static void sort(String words[]) {
		for(int i=0;i<words.length-1;i++) {
			boolean swap=false;
			for(int j=0;j<words.length-i-1;j++) {
				if(words[j].compareTo(words[j+1])>0) {
					String temp=words[j];
					words[j]=words[j+1];
					words[j+1]=temp;	
					swap=true;
				}
			}
			if(!swap) {
				break;
			}
		}		
	}
}

// Output
/*Words before Sorting : 
to be or not to be that is the question whether tis nobler in the mind to suffer the slings and arrows of outrageous fortune or to take arms against a sea of troubles and by opposing end them 
Words after Sorting : 
a against and and arms arrows be be by end fortune in is mind nobler not of of opposing or or outrageous question sea slings suffer take that the the the them tis to to to to troubles whether */