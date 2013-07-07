package oripa.viewsetting.uipanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeOnByValueButtonSelected implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		UIPanelSettingDB setting = UIPanelSettingDB.getInstance();
		
		setting.selectInputMode();
		
		setting.setValuePanelVisible(true);
		setting.setAlterLineTypePanelVisible(false);
		setting.setMountainButtonEnabled(true);
		setting.setValleyButtonEnabled(true);
		setting.setAuxButtonEnabled(true);

		setting.notifyObservers();

	}

}
