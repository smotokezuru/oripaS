package oripa.paint.segment;

import oripa.doc.Doc;
import oripa.doc.DocHolder;
import oripa.geom.OriLine;
import oripa.paint.Globals;
import oripa.paint.PaintContext;
import oripa.paint.PickingVertex;

public class SelectingSecondVertexForSegment extends PickingVertex{

		
	public SelectingSecondVertexForSegment(){
		super();
	}

	@Override
	protected void onResult(PaintContext context) {
		Doc doc = DocHolder.getInstance().getDoc();
		if(context.getVertexCount() != 2){
			throw new RuntimeException();
		}
		
		OriLine line = new OriLine(context.getVertex(0),
				context.getVertex(1), Globals.inputLineType);

		doc.pushUndoInfo();
        doc.addLine(line);

        context.clear(false);
	}

	@Override
	protected void initialize() {
		setPreviousClass(SelectingFirstVertexForSegment.class);
		setNextClass(SelectingFirstVertexForSegment.class);

//		System.out.println("SelectingSecondVertex.initialize() is called");
	}
}	
