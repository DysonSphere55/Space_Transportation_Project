package goit.lecture.feature.cli;

import goit.lecture.feature.ticket.Planet;

import java.util.Scanner;

public class PlanetChooser {

    private final Scanner scanner;

    public PlanetChooser(Scanner scanner) {
        this.scanner = scanner;

    }

    public Planet ask() {

        while (true) {
            String planetInput = scanner.nextLine();
            try {
                return Planet.valueOf(planetInput);
            } catch (Exception ex) {
                System.out.println("Planet " + planetInput + " not found.\nEnter correct planet.");
            }
        }
    }
}
