package Main;
import user_interface.GUIMain;
import user_interface.UI;
import world_gen.Map;

public class main {
	
	public static final int gridSize = 30;
	
	public static void main(String[] args) {
		Map map = new Map(gridSize);

		new GUIMain(map);
	}
}
