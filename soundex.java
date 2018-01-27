
class soundex { // soundex class

    /*
     This method gets the code for any word.
     The characters are parsed in as method parameters
     and a switch statement is used to make up a code
     based on the characters in the word.
     */
    public static String getCode(char a) {
        switch (a) { // switch statement for the character 
            case 'B':
            case 'F':
            case 'P':
            case 'V':
                return "1"; // if the character is B,F,P or V, return 1
            case 'C':
            case 'G':
            case 'J':
            case 'K':
            case 'Q':
            case 'S':
            case 'X':
            case 'Z':
                return "2"; // if the character is C,G,J,K,Q,S,X or Z return 2
            case 'D':
            case 'T':
                return "3"; // if the character is D or T, return 3
            case 'L':
                return "4"; // if the character is L, return 4
            case 'M':
            case 'N':
                return "5"; // if the character is M or N, return 5
            case 'R':
                return "6"; // if the character is R, return 6
            default:
                return ""; // return an empty string if none of the characters above match
        }
    }

    /*
     This method gets a String as a parameter,
     uses a for loop to go through each character
     in the word and calls the getCode method with
     each character to create a 4 letter code
     that will be used to identify similar sounding
     words.

     - set start of the code to the first character of the word
     - use for loop to get a number (if applies) using getCode method for each character
     - construct a 4 character soundex code
     - return the soundex code
     */
    public static String soundEx(String a) {
        String soundex;
        String gCode = a.toUpperCase().charAt(0) + ""; // initialize gCode variable to the first letter of the word
        String last = "7"; // initialize last variable to 7

        for (int i = 1; i < a.length(); i++) { // for loop to loop through each character in the String
            String current = getCode(a.toUpperCase().charAt(i)); // initialize current variable to the number (or empty string) of each character in getCode method
            if (current.length() > 0 && !current.equals(last)) { // if length of current is greater than 0 and each character code doesn't equal to 7, this condition is satisfied
                gCode += current; // add number that equals to character from getCode method to gCode 
            }
            last = current; // change value of last to value of current so character are not repeated 
        }
        soundex = (gCode + "0000").substring(0, 4); // format the soundex code into a 4 character code
        return soundex; // return the soundex code
    }
}
