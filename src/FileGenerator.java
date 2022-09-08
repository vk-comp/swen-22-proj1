package src;

import java.io.*;
import java.util.Scanner;

public class FileGenerator {
    final File file = new File("Statistics.txt");
    private String difficulty;
    private int[] number;
    private int score;

    private static int roundNum = 1;

    public FileGenerator(String difficulty, int[] number, int score){
        this.difficulty = difficulty;
        this.number = number;
        this.score = score;
    }

    public void writeFile(){
        try {
            Scanner myReader = new Scanner(file);
            if (!myReader.hasNextLine()){
                roundNum = 1;
                createFile();
                return;
            }else{
                String data = myReader.nextLine();
                if (data.contains(difficulty)){
                    FileWriter fileWriter = new FileWriter(file,true);
                    FileGenerator.increaseRound();
//                    String avg = myReader.nextLine();
                    writeNewRound(fileWriter);
                }else{
                    PrintWriter writer = new PrintWriter(file);
                    writer.print("");
                    writer.close();
                    createFile();
                }
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void createFile(){
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write("Difficulty: " + difficulty + "\n");
            myWriter.write("Average score per round: " + score+ "\n");
            writeNewRound(myWriter);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void increaseRound(){
        roundNum++;
    }
    public void writeNewRound(FileWriter filewriter) {
        try {
            BufferedWriter bf = new BufferedWriter(filewriter);
            bf.write("------------------------------------------\n");
            bf.write("Round # " + roundNum+ "\n");
            bf.write("Score: " + score+ "\n");
            for (int i=0; i< number.length; i++) {
                switch (i) {
                    case 0:
                        bf.write("I: " + number[i]+ "\n");
                        break;
                    case 1:
                        bf.write("J: " + number[i]+ "\n");
                        break;
                    case 2:
                        bf.write("L: " + number[i]+ "\n");
                        break;
                    case 3:
                        bf.write("O: " + number[i]+ "\n");
                        break;
                    case 4:
                        bf.write("S: " + number[i]+ "\n");
                        break;
                    case 5:
                        bf.write("T: " + number[i]+ "\n");
                        break;
                    case 6:
                        bf.write("Z: " + number[i]+ "\n");
                        break;
                    case 7:
                        bf.write("+: " + number[i]+ "\n");
                        break;
                    case 8:
                        bf.write("P: " + number[i]+ "\n");
                        break;
                    case 9:
                        bf.write("Q: " + number[i]+ "\n");
                        break;
                }
            }
            bf.close();
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
