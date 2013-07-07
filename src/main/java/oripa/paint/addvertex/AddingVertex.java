package oripa.paint.addvertex;

import java.awt.geom.Point2D;

import oripa.doc.Doc;
import oripa.doc.DocHolder;
import oripa.geom.OriLine;
import oripa.paint.PaintContext;
import oripa.paint.PickingVertex;
import oripa.paint.geometry.GeometricOperation;

public class AddingVertex extends PickingVertex {

	@Override
	protected void initialize() {

	}

	
	
	@Override
	protected boolean onAct(PaintContext context, Point2D.Double currentPoint,
			boolean freeSelection) {
		
		boolean result = super.onAct(context, currentPoint, true);
		
		if(result == true){
			OriLine line = GeometricOperation.pickLine(
					context);

			if(line != null){
				context.pushLine(line);
			}
			else {
				result = false;
			}
		}
		
		return result;
	}



	@Override
	protected void onResult(PaintContext context) {

		if(context.getVertexCount() > 0){
		  Doc doc = DocHolder.getInstance().getDoc();
          doc.pushUndoInfo();
          if (!doc.addVertexOnLine(context.popLine(), context.popVertex())) {
              doc.loadUndoInfo();
          }

		}
		
		context.clear(false);
	}

}
