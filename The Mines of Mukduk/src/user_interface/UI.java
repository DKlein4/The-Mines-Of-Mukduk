package user_interface;
import java.util.Scanner;

public class UI {

	public void mainMenu() {
		Scanner scan = new Scanner(System.in);
		int playerChoice = 0;
		
		System.out.println("1: Play Game");
		System.out.println("2: How to Play");
		System.out.println("3: Credits");
		System.out.print("Choice: ");
		playerChoice = scan.nextInt();
		
		if (playerChoice == 1) {
			//startGame Function		
		}
		else if (playerChoice == 2) {
			helpScreen();
		}
		else if (playerChoice == 3) {
			//credits Function
		}
		else {
			System.out.println("Please enter valid function.");
			scan.nextLine();
			mainMenu();
		}
	}
	
	public static void helpScreen() {
		System.out.println("This is how to play the game!");
	}
}
