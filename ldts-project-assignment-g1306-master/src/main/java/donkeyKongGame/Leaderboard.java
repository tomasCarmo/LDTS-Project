package donkeyKongGame;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Leaderboard {

    String fileName;
    ArrayList<ArrayList<Object>> leaderboardList = new ArrayList<>();

    public Leaderboard(String fileName){
        this.fileName = fileName;

        try {
            readLeaderboard();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean goesToLeaderboard(int points){
        Object lastPlacePoints = leaderboardList.get(leaderboardList.size() - 1).get(1);
        return points > Integer.parseInt(lastPlacePoints.toString());
    }

    public void updateFile(String name, int points) throws IOException {
        ArrayList<Object> player = new ArrayList<>(Arrays.asList(name,points));

        for (int i = 0; i < leaderboardList.size(); i++){
            if (Integer.parseInt(leaderboardList.get(i).get(1).toString()) < points){
                leaderboardList.add(i,player);
                break;
            }
        }
        if (leaderboardList.size() > 4) {leaderboardList.remove(leaderboardList.size() - 1);}
        writeInFile();
    }

    public void writeInFile() throws IOException {
        FileWriter file = new FileWriter(fileName);
        for (ArrayList<Object> player: leaderboardList){
            file.write(player.get(0).toString() + " " + player.get(1).toString() + '\n');
        }
        file.close();
    }

    public void readLeaderboard() throws FileNotFoundException {
        this.leaderboardList = new ArrayList<>();
        FileInputStream file= new FileInputStream(fileName);
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String playerName = line.substring(0,5);
            String points = line.substring(6);
            ArrayList<Object> player = new ArrayList<>(Arrays.asList(playerName, Integer.valueOf(points)));
            leaderboardList.add(player);
        }
        scanner.close();
    }

    public void displayLeaderboard(TextGraphics graphics){
        graphics.putString(22, 28, "RANK");
        graphics.putString(30, 28, "NAME");
        graphics.putString(38, 28, "POINTS");
        for (int i = 0; i < leaderboardList.size(); i++){
            graphics.putString(22, 28 + (i+1)*2, String.valueOf(i+1));
            graphics.putString(30, 28 + (i+1)*2, leaderboardList.get(i).get(0).toString());
            graphics.putString(38, 28 + (i+1)*2, leaderboardList.get(i).get(1).toString());
        }
    }
}
