import java.util.Scanner; 
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class CharacterFrequencyCounter{
    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);

        System.out.print("Please enter the name of the file: ");
        String inputFileName = sc.nextLine();

        System.out.print("Please enter the minimum time a character should appear to be counted seperatly: ");
        int countMin = sc.nextInt();

        File file = new File(inputFileName);
        Scanner inputScanner = new Scanner(file);
        inputScanner.useDelimiter("");

        int[] charFrequencyArray = new int[5000];
        char[] mostFrequentChar = new char[5000];
        int[] mostFrequentCharCount = new int[5000];
        int otherCount = 0;
        int maxCount;
        int idxmax;

        FileWriter fileWrite = new FileWriter("results.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWrite);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);

        while(inputScanner.hasNext()){
            String thistoken = inputScanner.next();
            if((thistoken.charAt(0)) < charFrequencyArray.length){
            char thischar = thistoken.charAt(0);
            charFrequencyArray[0 + thischar]++;
            }
            else otherCount++;
        }
        

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
        printWriter.println("=====================================================");
        printWriter.println("Character\t| Unicode code point\t| Frequency");
        printWriter.println("-----------------------------------------------------");
        for (int i = 0; i < charFrequencyArray.length; i++) {
            if (mostFrequentCharCount[i] == 0) {
                continue;
            }else{

                if(mostFrequentCharCount[i]<countMin){
                    otherCount += mostFrequentCharCount[i];
                }else{
                    if(mostFrequentChar[i] == '\s'){
                        printWriter.printf("%s\t\t|\tU+%04X\t\t|%d", "Space", (int) mostFrequentChar[i], mostFrequentCharCount[i]);
                    }else if(mostFrequentChar[i] == '\t'){
                        printWriter.printf("%s\t\t|\tU+%04X\t\t|%d", "Tab", (int) mostFrequentChar[i], mostFrequentCharCount[i]);
                    }else if(mostFrequentChar[i] == '\n'){
                        printWriter.printf("%s\t|\tU+%04X\\tt|%d", "New Line", (int) mostFrequentChar[i], mostFrequentCharCount[i]);
                    }else{
                        printWriter.printf("%c\t\t|\tU+%04X\t\t|%d\n", mostFrequentChar[i], (int) mostFrequentChar[i], mostFrequentCharCount[i]);                
                    }
                }
            }
        }
        printWriter.println("-----------------------------------------------------");
        printWriter.printf("Other Characters\t| %d\n", otherCount);
        printWriter.close();
        sc.close();
        inputScanner.close();
    }
}
