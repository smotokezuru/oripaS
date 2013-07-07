package oripa.paint.deleteline;

import java.awt.Graphics2D;
import java.util.Collection;

import oripa.ORIPA;
import oripa.doc.Doc;
import oripa.doc.DocHolder;
import oripa.geom.OriLine;
import oripa.paint.EditMode;
import oripa.paint.PaintContext;
import oripa.paint.RectangularSelectableAction;

public class DeleteLineAction extends RectangularSelectableAction {


	public DeleteLineAction(){
		setEditMode(EditMode.OTHER);

		setActionState(new DeletingLine());

	}

	@Override
	public void onDraw(Graphics2D g2d, PaintContext context) {

		super.onDraw(g2d, context);

		drawPickCandidateLine(g2d, context);
		
	}

	@Override
	protected void afterRectangularSelection(Collection<OriLine> selectedLines,
			PaintContext context) {

		if(selectedLines.isEmpty() == false){
			Doc doc = DocHolder.getInstance().getDoc();
			doc.pushUndoInfo();
			for (OriLine l : selectedLines) {
				doc.removeLine(l);
			}
		}		
	}



}
