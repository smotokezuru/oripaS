package oripa.paint.linetype;

import oripa.doc.Doc;
import oripa.doc.DocHolder;
import oripa.paint.PaintContext;
import oripa.paint.PickingLine;
import oripa.viewsetting.uipanel.UIPanelSettingDB;

public class SelectingLineForLineType extends PickingLine {

	
	
	public SelectingLineForLineType() {
		super();
	}

	@Override
	protected void initialize() {
	}
	
	
	@Override
	protected void undoAction(PaintContext context) {
		super.undoAction(context);
	}

	@Override
	protected void onResult(PaintContext context) {
		Doc doc = DocHolder.getInstance().getDoc();
        doc.pushUndoInfo();

    	UIPanelSettingDB setting = UIPanelSettingDB.getInstance();
        doc.alterLineType(context.peekLine(),  setting.getTypeFrom(), setting.getTypeTo());

        context.clear(false);
	}

}
