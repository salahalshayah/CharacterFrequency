import java.util.Scanner; 
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.nio.BufferUnderflowException;
public class CharacterFrequencyCounter{
    public static void main(String[] args) throws IOException{
        Scanner sc =new Scanner(System.in);
        System.out.print("Please enter the name of the file: ");
        String inputFileName = sc.nextLine();
        System.out.println();
        File file = new File(inputFileName);
        Scanner inputScanner = new Scanner(file);
        inputScanner.useDelimiter("");
        int[] charFrequencyArray = new int[500];
        int otherCount = 0;
        char[] mostFrequentChar = new char[500];
        int[] mostFrequentCharCount = new int[500];

        FileWriter fileWrite = new FileWriter("results.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWrite);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);

        while(inputScanner.hasNext()){
            String thistoken = inputScanner.next();
            if((thistoken.charAt(0)) < 500){
            char thischar = thistoken.charAt(0);
            charFrequencyArray[0 + thischar]++;
            }
            else otherCount++;
        }
        
        int maxCount = 0;
        int idxmax = 0;
        for(int idd = 0; idd<charFrequencyArray.length; idd++){
            maxCount = 0;
            idxmax = 0;
            for(int i = 0; i < charFrequencyArray.length; i++){
                if (charFrequencyArray[i] > maxCount) {
                    maxCount = charFrequencyArray[i];
                    idxmax = i;
                    charFrequencyArray[i] = 0;
                }
                mostFrequentChar[idd] = ((char) idxmax);
                mostFrequentCharCount[idd] = maxCount;
            }
        }
        
        printWriter.println("Character Frequencies for File: " + inputFileName);
        printWriter.println("===============================================");
        printWriter.println("Character\t| ASCII Code\t| Frequency");
        printWriter.println("-----------------------------------------------");
        for (int i = 0; i < charFrequencyArray.length; i++) {
            if (mostFrequentCharCount[i] == 0) {
                continue;
            } else {
                printWriter.printf("%c\t\t| %d\t\t| %d\n", mostFrequentChar[i], (int) mostFrequentChar[i], mostFrequentCharCount[i]);
            }
        }
        printWriter.println("-----------------------------------------------");
        printWriter.printf("Other Characters\t| %d\n", otherCount);
        printWriter.close();
    }
}
