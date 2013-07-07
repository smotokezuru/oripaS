package oripa.paint.deleteline;

import oripa.ORIPA;
import oripa.doc.Doc;
import oripa.doc.DocHolder;
import oripa.paint.PaintContext;
import oripa.paint.PickingLine;

public class DeletingLine extends PickingLine {

	
	
	public DeletingLine() {
		super();
	}

	@Override
	protected void initialize() {
	}
	

	@Override
	protected void onResult(PaintContext context) {

		if(context.getLineCount() > 0){
			Doc doc = DocHolder.getInstance().getDoc();
			doc.pushUndoInfo();
			doc.removeLine(context.popLine());
		}
		
		context.clear(false);
	}

}
