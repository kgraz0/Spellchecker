
class soundex {

    /*
     This method gets the code for any word.
     The characters are parsed in as method parameters
     and a switch statement is used to make up a code
     based on the characters in the word.
     */
    public static String getCode(char a) {
        switch (a) {
            case 'B':
            case 'F':
            case 'P':
            case 'V':
                return "1"; 
            case 'C':
            case 'G':
            case 'J':
            case 'K':
            case 'Q':
            case 'S':
            case 'X':
            case 'Z':
                return "2";
            case 'D':
            case 'T':
                return "3"; 
            case 'L':
                return "4"; 
            case 'M':
            case 'N':
                return "5";
            case 'R':
                return "6"; 
            default:
                return "";
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
        String gCode = a.toUpperCase().charAt(0) + ""; 
        String last = "7"; 

        for (int i = 1; i < a.length(); i++) { 
            String current = getCode(a.toUpperCase().charAt(i)); 
            if (current.length() > 0 && !current.equals(last)) {
                gCode += current;
            }
            last = current; 
        }
        soundex = (gCode + "0000").substring(0, 4); 
        return soundex;
}
}