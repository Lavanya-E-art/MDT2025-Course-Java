


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
			System.out.println(words[i]+" ");
		}
		sort(words);
		System.out.println("\nWords after Sorting : ");
		for(int i=0;i<words.length;i++) {
			System.out.println(words[i]+" ");
		}
	}
//	bubble sort 	
	public static void sort(String words[]) {
		for(int i=0;i<words.length-1;i++) {
			boolean swap=false;
			for(int j=0;j<words.length-i-1;j++) {
				if(words[j].compareTo(words[j+1])>0) {
//				if(compare2Words(words[j],words[j+1])) {
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
	public static boolean compare2Words(String word1, String word2) {
	    int max = Math.min(word1.length(), word2.length());

	    for (int i = 0; i < max; i++) {
	        if (word1.charAt(i) < word2.charAt(i)) {
	            return false; 
	        } else if (word1.charAt(i) > word2.charAt(i)) {
	            return true;  
	        }
	    }

	   
	    return word1.length() > word2.length();
	}


}

