/* Problem 3: Object Basics
Readings: Object Basics and Simple Data Objects, The Life Cycle of an Object, Characters and
Strings
In the following code soliloquy is analyzed character by character to determine the vowels, spaces, and letters used. 
Fill in the code that computes the number of spaces, vowels, and
consonants. */

//SOLUTION TO PROBLEM 3:

public class Q3CountCharacters {
    public static void main(String[] args) {
        String text = "To be or not to be, that is the question;"
                + "Whether `tis nobler in the mind to suffer"
                + " the slings and arrows of outrageous fortune,"
                + " or to take arms against a sea of troubles,"
                + " and by opposing end them?";
        int spaces = 0,
                vowels = 0,
                letters = 0;
        //Solution to Problem-3
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            // Check if it's a space
            if (ch == ' ') {
                spaces++;
            }

            // Check if it's a letter
            if (Character.isLetter(ch)) {
                letters++;

                // Check if it's a vowel
                char lower = Character.toLowerCase(ch);
                if (lower == 'a' || lower == 'e' || lower == 'i' || lower == 'o' || lower == 'u') {
                    vowels++;
                }
            }
        }
        System.out.println("The text contained vowels: " + vowels + "\n consonants: " + (letters - vowels)
                + "\n spaces: " + spaces);
    }
}


/* Output of Problem - 3:
The text contained vowels: 60
 consonants: 93
 spaces: 37
*/

