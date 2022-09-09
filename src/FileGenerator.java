package src;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;

public class FileGenerator {
    final File file = new File("Statistics.txt");
    private String difficulty;
    private int[] number;

    private int score;

    private static int roundNum = 1;

    private double newAvg;

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

//                    String avg = myReader.nextLine();
                    String avgScore = myReader.nextLine();
                    calculateAverage(avgScore);

                    List<String> newLines = new ArrayList<>();
                    Path path = Paths.get("Statistics.txt");
                    for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
                        if (line.contains("Average score per round:")) {
                            newLines.add(line.replace(line, "Average score per round: "+ newAvg));
                        } else {
                            newLines.add(line);
                        }
                    }
                    Files.write(path, newLines, StandardCharsets.UTF_8);

                    writeNewRound(fileWriter);
                }else{
                    cleanFile();
                    createFile();
                }
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void calculateAverage(String avgScore) {
        avgScore = avgScore.substring(25);
        double totalScore = Double.parseDouble(avgScore) * (double) roundNum;
        totalScore += score;
        FileGenerator.increaseRound();
        newAvg = totalScore/(double) roundNum;
    }

    public void cleanFile() {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void createFile(){
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write("Difficulty: " + difficulty + "\n");
            myWriter.write("Average score per round: " + score + "\n");
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
            bf.write("Round# " + roundNum+ "\n");
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
