package oripa.paint.selectline;

import oripa.doc.Doc;
import oripa.doc.DocHolder;
import oripa.paint.PaintContext;

public class SelectAllLineAction extends SelectLineAction {

	public SelectAllLineAction(PaintContext context) {
		super(context);
	}

	@Override
	public void recover(PaintContext context) {
		Doc doc = DocHolder.getInstance().getDoc();
		doc.selectAllOriLines();
		super.recover(context);
	}
	


}
