import java.util.*;
import java.io.*;

public class Spellchecker {

    static soundex se = new soundex(); // create soundex class object

    /*
     The main method. This is first called when the program is run.
     The main method calls the readDictionary method which returns an arrayList that is then
     stored in the variable 'dictArray'.
     After that is complete, the method offerSuggestions with the dictArray being a parameter
     is then called.
     */
    public static void main(String[] args) throws IOException {

        ArrayList<String> dictArray = readDictionary();
        offerSuggestions(dictArray);
    }

    /*
     1. An arraylist is created in this method where each word from the dictionary file will
     be stored. 
     2. A BufferedReader object is created to access the dictionary file which is dictionary.txt
     3. Create a String to store each word from the file as it will be accessed
     4. Set up a while loop to loop through each word in the file (dictionary.txt)
     5. Add each word to the dictionaryArray.
     6. close the File Reader as the reading process has been finished
     7. Return the arrayList of words from the dictionary
     */
    public static ArrayList<String> readDictionary() throws IOException {

        ArrayList<String> dictionaryArray = new ArrayList<String>(); // 1)

        BufferedReader dictRead = new BufferedReader(new FileReader("dictionary.txt")); // 2)

        String readDictionary; // 3)

        while ((readDictionary = dictRead.readLine()) != null) { // 4)
            dictionaryArray.add(readDictionary); // 5)
        }
        dictRead.close(); // 6)

        return dictionaryArray; // 7)
    }

    /*
     - The method takes in a String arrayList as a parameter
     1. A BufferedWriter object is created to access the output file with the 'true' parameter as words
     will be continuously appended to the file.
     2. A bufferedWriter object is created to write to the dictionary file
     3. A file type Scanner object is created to select the file that the program will read from
     4. Once the input file has been set, the program begins to read each word
     in the input file and store it in the word String
     5. Remove numbers or any punctuation from the word so it can be easily compared to a dictionary word

     For each word:
     6. Check whether any of the words in the dictionary (arrayList) contain the word from input file

     If the word is not in the dictionary:
     7. create a new arrayList where suggested words will be stored
     8. Use a for loop to check whether any of the words in the dictionary
     have the same soundEx code as the input word
     If the word is in the dictionary:
     9. Append the word into the output file

     If the word has the same soundEx code:
     10. Add the word into the arrayList where suggested words are stored
     11. Print each suggestion word so user can potentially select one of them

     USER CHOICE:
     - Once a misspelt word is found in the input file, suggestions are
     given based on the same soundEx code and presented to the user.
     - The user is given a choice to either accept a certain word that was given as a suggestions 
     and enter its number next to the suggestion, or enter a replacement word.

     If a user enters an integer (selects one of the suggestions):
     12. Check whether the number that user entered does not exceed the code for that suggestion selection
     or that the code is not less than 0
     If a user does not enter an integer (wants to enter a replacement):
     13. Remove numbers or any punctuation from the word

     If the word that user entered is already in the dictionary:
     14. Append the word into the output file
     If the word that user entered is not in the dictionary:
     15. Add the word into the arrayList that contains the words from the dictionary
     as the dictionary file will be alphabetically sorted later on
     16. Append the word into the output file

     17. Sort the dictionary arrayList with any words that were added by the user
     in alphabetical order 
     18. Use a for loop to re-write each word into the dictionary,
     in alphabetical order
     */
    public static void offerSuggestions(ArrayList<String> dictionaryWord) throws IOException {

        BufferedWriter outputFile = new BufferedWriter(new FileWriter("output.txt", true)); // 1)
        BufferedWriter dictAdd = new BufferedWriter(new FileWriter("dictionary.txt", false)); // 2)

        File file = new File("input.txt"); // 3)
        Scanner readInput = new Scanner(file); // 3)

        String word;
        outputFile.newLine();

        while (readInput.hasNext()) { // 4)
            word = readInput.next(); // 4)
            String strippedWord = word.replaceAll("\\W", ""); // 5)

            if (!dictionaryWord.contains(strippedWord.toLowerCase())) { // 6)

                Scanner option = new Scanner(System.in);

                System.out.println("The misspelt word is: " + strippedWord);
                System.out.println("Here are the suggestions: ");

                ArrayList<String> suggestionWord = new ArrayList<String>(); // 7)

                for (int i = 0; i < dictionaryWord.size(); i++) { // 8)
                    if (se.soundEx(strippedWord).equals(se.soundEx(dictionaryWord.get(i).toString()))) { // 8)
                        suggestionWord.add(dictionaryWord.get(i)); // 10)
                    }
                }

                for (int j = 0; j < suggestionWord.size(); j++) { // 11)
                    System.out.println(j + " - " + suggestionWord.get(j)); // 1)
                }

                System.out.println("Enter the code next to the word you want to choose, or enter a new word");
                String userSelection = "";

                if (option.hasNextInt()) {
                    userSelection = option.next();

                    while (Integer.parseInt(userSelection) > suggestionWord.size() - 1 || Integer.parseInt(userSelection) < 0) { // 12)
                        System.out.println("Incorrect input. Please try again.");
                        userSelection = option.next();
                    }
                    int y = Integer.parseInt(userSelection);
                    outputFile.write(suggestionWord.get(y) + " ");
                } else { // 13)
                    userSelection = option.next();
                    userSelection = userSelection.replaceAll("\\W", "");
                    userSelection = userSelection.replaceAll("\\d", "");

                    if (!dictionaryWord.contains(userSelection)) {
                        dictionaryWord.add(userSelection.toLowerCase()); // 15)
                        outputFile.write(userSelection.toLowerCase() + " "); // 16)
                        outputFile.flush();
                    } else {
                        outputFile.write(userSelection.toLowerCase() + " "); // 14)
                        outputFile.flush();
                    }
                }
            } else {
                outputFile.write(word + " "); // 9)
                outputFile.flush();
            }
        }
        outputFile.close();
        readInput.close();

        Collections.sort(dictionaryWord); // 17)
        for (int i = 0; i < dictionaryWord.size(); i++) { // 18)
            dictAdd.write(dictionaryWord.get(i)); // 18)
            dictAdd.flush();
            dictAdd.newLine();
        }
        dictAdd.close();
    }
}
