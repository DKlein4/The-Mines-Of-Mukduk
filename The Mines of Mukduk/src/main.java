import user_interface.UI;
import world_gen.Map;

public class main {
	public static void main(String[] args) {
		// UI userInterface = new UI();
		// userInterface.mainMenu();

		Map map = new Map(20);
		map.printGrid();


	}
}
