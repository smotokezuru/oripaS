package oripa.paint;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javax.vecmath.Vector2d;

import oripa.geom.OriLine;
import oripa.paint.ActionState;

/**
 * abstract class specified for picking vertex.
 * @author koji
 *
 */
public abstract class PickingLine extends AbstractAction {
	

	public PickingLine(){
		super();
	}
	
	/**
	 * Picks the nearest line and push it into context.
	 * @return true if the action succeed, false otherwise.
	 */
	
	@Override
	protected boolean onAct(MouseContext context, Double currentPoint,
			boolean freeSelection) {

		OriLine picked = GeometricalOperation.pickLine(
				currentPoint, context.scale);

		if(picked == null){
			System.out.println("onAct() failed");
			return false;
		}
		
		context.pushLine(picked);		
		
		
		return true;
	}
	
	
	/**
	 * delete from context the latest picked line.
	 * @return Previous state
	 */
	@Override
	protected void undoAction(MouseContext context) {
		
		if(context.getLineCount() > 0){
			context.popLine();
		}
		
	}

	
}