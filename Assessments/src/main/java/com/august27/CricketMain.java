package com.august27;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CricketMain {
    public static void main(String[] args) throws PlayerNotFoundException {


        Map<String,Player> allPlayers = new TreeMap<>();
        Map<String,Player> selectedTeam = new TreeMap<>();
        TeamMaker teamMaker = new TeamMaker(allPlayers,selectedTeam);

        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println(" 1: Display All Players \n 2: Update Player Information By Name \n 3: Display Final Team \n 4: Add Player Information \n 5: Exit");
            switch (sc.nextInt()){
                case 1:
                    teamMaker.displayAllPlayers();
                    break;
                case 2:
                    teamMaker.updatePlayerByName();
                    break;
                case 3:
                    teamMaker.selectTeam();
                    break;
                case 4:
//                    while(! teamMaker.acceptAllPlayerDetails()){
//                        System.out.println("Please ensure at least 3 bowlers and 1 wicketkeeper.");
//                    }
                    teamMaker.setAllPlayers();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
