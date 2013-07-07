package oripa.bind.binder;

import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import oripa.viewsetting.main.ScreenUpdater;

public class ViewChangeBinder extends AbstractButtonBinder<ActionListener> {

	
	public ViewChangeBinder() {
	}
	
	@Override
	public AbstractButton createButton(
			Class<? extends AbstractButton> buttonClass,
			ActionListener target, String textID) {
		AbstractButton button = createEmptyButton(buttonClass, textID);

		// For catching key actions which requires immediate drawing(e.g., for catching Ctrl pressed)
		ScreenUpdater screenUpdater = ScreenUpdater.getInstance();
		button.addKeyListener(screenUpdater.new KeyListener());

		button.addActionListener(target);
		
		return button;
	}


}
