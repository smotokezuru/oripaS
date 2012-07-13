package oripa.paint.line;

import javax.management.RuntimeErrorException;
import javax.vecmath.Vector2d;

import oripa.Constants;
import oripa.Globals;
import oripa.ORIPA;
import oripa.geom.GeomUtil;
import oripa.geom.OriLine;
import oripa.paint.MouseContext;
import oripa.paint.PickingVertex;

public class SelectingSecondVertexForLine extends PickingVertex{


	public SelectingSecondVertexForLine(){
		super();
	}

	@Override
	protected void onResult(MouseContext context) {

		if(context.getVertexCount() != 2){
			throw new RuntimeException();
		}

		Vector2d p0, p1;
		p0 = context.getVertex(0);
		p1 = context.getVertex(1);

		Vector2d dir = new Vector2d(p0.x - p1.x, p0.y - p1.y);
		dir.normalize();
		dir.scale(Constants.DEFAULT_PAPER_SIZE * 8);
		OriLine line = new OriLine(p0.x - dir.x, p0.y - dir.y,
				p0.x + dir.x, p0.y + dir.y, Globals.inputLineType);
		if (GeomUtil.clipLine(line, ORIPA.doc.size / 2)) {
			ORIPA.doc.pushUndoInfo();
			ORIPA.doc.addLine(line);
		}

		context.clear();
	}

	@Override
	protected void initialize() {
		setPreviousClass(SelectingFirstVertexForLine.class);
		setNextClass(SelectingFirstVertexForLine.class);

		System.out.println("SelectingSecondVertex.initialize() is called");
	}
}	