import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Create a Scanner for reading user input from the console
        Scanner scanner = new Scanner(System.in);
        boolean exit = false; // Flag to control the menu loop

        // Loop until the user chooses to exit
        while (!exit) {
            // Display the menu options to the user
            System.out.println("=== StringsSecondAssignments Menu ===");
            System.out.println("1 - Run Part1 tests (find stop codons and genes)");
            System.out.println("2 - Run Part2");
            System.out.println("3 - Run Part3 (Count Genes)");
            System.out.println("4 - Exit");
            System.out.print("Enter your choice: ");

            // Read the user's choice as a string (to handle both numbers and text like "exit")
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "1":
                    // Run Part1 tests: find stop codons and find genes
                    Part1 part1 = new Part1();
                    System.out.println("\n--- Running Part1 tests ---");
                    part1.testFindStopCodon();
                    part1.testFindGene();
                    part1.testPrintAllGenes();
                    break;
                case "2":
                    System.out.println("\nPlaceholder");
                    break;
                case "3":
                    System.out.println("\n--- PlaceholdeR");
                    break;
                case "4":
                case "exit":
                    // Exit the program loop
                    exit = true;
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    // Handle any invalid menu inputs
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
                    break;
            }

            System.out.println(); // Print a blank line for spacing before the next menu display
        }

        scanner.close(); // Close the Scanner resource
    }
}
