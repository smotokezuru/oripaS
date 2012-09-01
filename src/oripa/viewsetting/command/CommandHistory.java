package oripa.viewsetting.command;

import java.awt.event.ActionListener;

public class CommandHistory extends oripa.UndoManager<ActionListener[]>{

	private static CommandHistory instance = null;
	
	private CommandHistory(){}
	
	public static CommandHistory getInstance(){
		if(instance == null){
			instance = new CommandHistory();
		}
		
		return instance;
	}
	
	
	
}