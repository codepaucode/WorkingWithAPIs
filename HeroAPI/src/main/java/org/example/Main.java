package org.example;

import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class Main {
    private static final String BASE_URL = "https://superheroapi.com/api/639ed25f2c2693419b97cd2d7ec89008";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for the first hero's name
        System.out.print("Enter the name of the first superhero: ");
        String hero1Name = scanner.nextLine();

        // Prompt user for the second hero's name
        System.out.print("Enter the name of the second superhero: ");
        String hero2Name = scanner.nextLine();

        // Fetch IDs for both heroes
        int hero1Id = getHeroId(hero1Name);
        int hero2Id = getHeroId(hero2Name);

        // Fetch powerstats for both heroes
        int hero1Strength = getStrength(hero1Id);
        int hero2Strength = getStrength(hero2Id);

        System.out.println(hero1Name + "'s strength: "+ hero1Strength + "    and    " + hero2Name + "'s strength: "+ hero2Strength);

        // Compare strengths and print result
        if (hero1Strength > hero2Strength) {
            System.out.println(hero1Name + " is stronger than " + hero2Name);
        } else if (hero1Strength < hero2Strength) {
            System.out.println(hero2Name + " is stronger than " + hero1Name);
        } else {
            System.out.println(hero1Name + " and " + hero2Name + " are equally strong.");
        }
        scanner.close();
    }

    // Method to fetch hero ID by name
    private static int getHeroId(String heroName) {
        String url = BASE_URL + "/search/" + heroName;
        String response = new RestTemplate().getForObject(url, String.class);

        // Extract the first hero ID from the response
        String id = response.split("\"id\":\"")[1].split("\"")[0];
        return Integer.parseInt(id);
    }

    // Method to fetch the strength of a hero by ID
    private static int getStrength(int heroId) {
        String url = BASE_URL + "/" + heroId + "/powerstats";
        String response = new RestTemplate().getForObject(url, String.class);

        // Extract the "strength" value from the response
        String strength = response.split("\"strength\":\"")[1].split("\"")[0];
        return Integer.parseInt(strength);
    }
}
