package oripa.viewsetting.uipanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeOnAlterTypeButtonSelected implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		UIPanelSettingDB setting = UIPanelSettingDB.getInstance();
		
		setting.setValuePanelVisible(false);
		
		setting.setAlterLineTypePanelVisible(true);

		setting.setMountainButtonEnabled(false);
		setting.setValleyButtonEnabled(false);
		setting.setAuxButtonEnabled(false);

		setting.notifyObservers();

	}
}
