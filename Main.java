import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
// various module imports

public class Main {

  static ArrayList<String> favorite_foods = new ArrayList<String>(); // Creates main ArrayList which will store favorite foods
  static boolean system_on = true; // placeholder boolean for the while loop in main()
  static Scanner sc = new Scanner(System.in); // starts the scanner which handles getting user input

  public static void main(String[] args) throws IOException {
    // this function is what holds the main action loop and calls other functions for each part of the process
    
    int action;
    System.out.println();
    System.out.println("Welcome to the Favorite Food Tracker v1.0!");
    System.out.println();

    while (system_on == true) {
      // calls get_action() to get an input being an integer 1 through 5, and checks for which number they entered and does that function
      action = get_action();
      if (action == 1) {
        // adds new food to the arrayList
        new_food();
      } else if (action == 2) {
        // deletes food from the arrayList
        delete_food();
      } else if (action == 3) {
        // saves the list as "favorite_foods.txt"
        save_list();
      } else if (action == 4) {
        // reads from "favorite_foods.txt" (must have already saved it beforehand)
        read_list();
      } else if (action == 5) {
        System.out.println("Program Terminated");
        System.exit(0);
      }
      System.out.println();

      // This next section is what prints the current list of favorite foods to the user
      System.out.println("Current List:");
      int i = 1;
      for(String str: favorite_foods) {
        System.out.println(i + " - " + str);
        i++;
      }
      i = 1;
      System.out.println();
      System.out.println("Total: " + favorite_foods.size() + " items");
      System.out.println();
    }
    System.out.println("Program Terminated");
    System.exit(0);
  }

  public static int get_action() {
    // gets input (any integer 1 through 5) from the user and returns it to main()

    int user_input = 0;
    do {
      System.out.println("Please enter a number corresponding to the action you would like to perform.");
      System.out.println("[1] - Add a new favorite food to the list");
      System.out.println("[2] - Remove a favorite food from the list");
      System.out.println("[3] - Save the list");
      System.out.println("[4] - Read the previously saved list");
      System.out.println("[5] - Quit program");
      System.out.println();

      // gets integer from the user and verifies that it is a valid option
      while (!sc.hasNextInt()) {
        System.out.println("You did not enter a valid integer, please try again.");
        sc.next();
      }
      user_input = sc.nextInt();
    } while (user_input <= 0 || user_input > 5);
    return user_input;
  }

  public static void new_food() {
    System.out.println("Please enter the name of your favorite food:");

    String temp = sc.nextLine(); // this is the solution to a stupid problem. 
    // nextInt() will not consume the /n after a user inputs something so nextLine() would just enter a blank into the arrayList.
    // The easiest way to solve this is to just have a placeholder variable consume the /n so I can actually get an input for the favorite food.
    System.out.println(temp); // just printing the blank line
    
    String fav_food = sc.nextLine(); // saves input from user as a string called fav_food
    favorite_foods.add(fav_food); // adds new food to the list
  }

  public static void delete_food() {
    // sets placeholder variable for index
    int index_input = 0;

    do {
      // asks user to input an index to remove from the ArrayList
      System.out.println("Please enter the INDEX of the favorite food you would like to remove from the list:");

      // checks for valid integer
      while (!sc.hasNextInt()) {
        System.out.println("You did not enter a valid integer, please try again.");
        sc.next();
      }

      // sets variable to given integer
      index_input = sc.nextInt();
    } while (index_input <= 0 || index_input > favorite_foods.size()); // checks that the inputted integer is within the bounds of the arrayList
    
    index_input--; // reduces index by 1 to normalize it for accessing the arrayList
    String the_food = favorite_foods.get(index_input); // saves the food that was in the given index
    favorite_foods.remove(index_input); // deletes the index from the arrayList
    System.out.println(the_food + " has been removed from the list."); // prints to the user what has been deleted
  }

  public static void save_list() throws IOException {
    // saves current favorite_foods arrayList to a file called favorite_foods.txt
    FileWriter writer = new FileWriter("favorite_foods.txt"); 

    // writes each index of the ArrayList to a new line in the txt file
    for(String str: favorite_foods) {
      writer.write(str + System.lineSeparator()); // this is what actually does the writing
    }
    writer.close(); // closes the writer to prevent a resource leak (Thank you VScode?)
  }

  public static void read_list() throws IOException {
    // reads the file you previously saved
    try (BufferedReader br = new BufferedReader(new FileReader("favorite_foods.txt"))) {
      String line; // gets the line

      // while the line is not blank (has text)
      while ((line = br.readLine()) != null) {
        favorite_foods.add(line); // adds the line to the ArrayList
      }
    }
  }
}