package oripa.paint.symmetric;

import java.awt.geom.Point2D.Double;

import javax.vecmath.Vector2d;

import oripa.ORIPA;
import oripa.doc.Doc;
import oripa.doc.DocHolder;
import oripa.paint.PaintContext;
import oripa.paint.PickingVertex;

public class SelectingVertexForSymmetric extends PickingVertex{
	
	public SelectingVertexForSymmetric(){
		super();
	}
	
	@Override
	protected void initialize() {
	}


	private boolean doingFirstAction = true;
	
	private boolean doSpecial = false;
	
	@Override
	protected boolean onAct(PaintContext context, Double currentPoint,
			boolean doSpecial) {
		Doc doc = DocHolder.getInstance().getDoc();
		if(doingFirstAction){
			doc.cacheUndoInfo();
			doingFirstAction = false;
		}
		
		boolean result = super.onAct(context, currentPoint, doSpecial);
		
		if(result == true){
			if(context.getVertexCount() < 3){
				result = false;
			}
		}

		this.doSpecial = doSpecial;
		
		return result;
	}

	@Override
	public void onResult(PaintContext context) {
		Doc doc = DocHolder.getInstance().getDoc();
		doc.pushCachedUndoInfo();
		
		Vector2d first = context.getVertex(0);
		Vector2d second = context.getVertex(1);
		Vector2d third = context.getVertex(2);
		
        if (doSpecial) {
            doc.addSymmetricLineAutoWalk(
            		first, second, third, 0, first);
        } else {
            doc.addSymmetricLine(first, second, third);
        }

        doingFirstAction = true;
        context.clear(false);
	}

	
}
