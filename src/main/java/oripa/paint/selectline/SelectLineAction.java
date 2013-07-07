package oripa.paint.selectline;

import java.awt.Graphics2D;
import java.util.Collection;

import oripa.ORIPA;
import oripa.doc.Doc;
import oripa.doc.DocHolder;
import oripa.geom.OriLine;
import oripa.paint.EditMode;
import oripa.paint.Globals;
import oripa.paint.PaintContext;
import oripa.paint.RectangularSelectableAction;

public class SelectLineAction extends RectangularSelectableAction {


	public SelectLineAction(PaintContext context){
		setEditMode(EditMode.SELECT);
		setNeedSelect(true);
		
		setActionState(new SelectingLine());


		recover(context);
	}

		
	@Override
	public void undo(PaintContext context) {
		Doc doc = DocHolder.getInstance().getDoc();
		doc.loadUndoInfo();

		recover(context);
	}


	@Override
	public void recover(PaintContext context) {
		context.clear(false);
		Doc doc = DocHolder.getInstance().getDoc();
		Collection<OriLine> docLines = doc.creasePattern;
		if(docLines == null){
			return;
		}
		
		for(OriLine line : doc.creasePattern){
			if(line.selected){
				context.pushLine(line);
			}
		}
	}


	@Override
	protected void afterRectangularSelection(Collection<OriLine> selectedLines,
			PaintContext context) {
		Doc doc = DocHolder.getInstance().getDoc();
		if(selectedLines.isEmpty() == false){

			doc.pushUndoInfo();

			for(OriLine line : selectedLines){
				if (line.typeVal == OriLine.TYPE_CUT) {
					continue;
				}
				// Don't select if the line is hidden
				if (!Globals.dispMVLines && (line.typeVal == OriLine.TYPE_RIDGE
						|| line.typeVal == OriLine.TYPE_VALLEY)) {
					continue;
				}
				if (!Globals.dispAuxLines && line.typeVal == OriLine.TYPE_NONE) {
					continue;
				}

				if(context.getLines().contains(line) == false){
					line.selected = true;
					context.pushLine(line);
				}

			}

		}
	}



	@Override
	public void onDraw(Graphics2D g2d, PaintContext context) {
		super.onDraw(g2d, context);

		this.drawPickCandidateLine(g2d, context);
	}





}
