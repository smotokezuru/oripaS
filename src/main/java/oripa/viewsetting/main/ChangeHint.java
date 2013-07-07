package oripa.viewsetting.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import oripa.resource.ResourceHolder;
import oripa.resource.ResourceKey;

public class ChangeHint implements ActionListener {

	private MainFrameSettingDB frameSetting = MainFrameSettingDB.getInstance();

	private String id;
	
	public ChangeHint(String resourceID){
		this.id = resourceID;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ResourceHolder holder = ResourceHolder.getInstance();
		
		ResourceBundle resource = holder.getResource(ResourceKey.EXPLANATION);

		String hint = null;
		try{
			hint = resource.getString(id);
		}
		catch (Exception ex) {
			//e.printStackTrace();
		}
		frameSetting.setHint(hint);
		
		frameSetting.notifyObservers();
	}
}
