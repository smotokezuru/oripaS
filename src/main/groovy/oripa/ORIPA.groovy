package oripa

import oripa.view.main.MainFrame
import oripa.view.xray.XRayFrame
import oripa.view.xray3d.XRay3dFrame
import oripa.view.render.RenderFrame
import oripa.view.check.CheckFrame
import oripa.resource.ResourceHolder
import oripa.resource.ResourceKey
import oripa.resource.StringID
import java.awt.Toolkit
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JOptionPane

/**
 * Created with IntelliJ IDEA.
 * User: hisami
 * Date: 2013/07/14
 * Time: 0:41
 * To change this template use File | Settings | File Templates.
 */
public class ORIPA {

    public static String TITLE;

    public static MainFrame mainFrame;
    public static XRayFrame modelFrame;
    public static String infoString = "ORIPA S: (c) 2012 OUCHI Koji\n" +
            "http://github.com/Ooouch1\n" +
            "ORIPA: (c) 2005-2009 Jun Mitani\nhttp://mitani.cs.tsukuba.ac.jp/\n\n"+
            "This program comes with ABSOLUTELY NO WARRANTY;\n"+
            "This is free software, and you are welcome to redistribute it\n"+
            "under certain conditions; For details check:\nhttp://www.gnu.org/licenses/gpl.html";

    public static ResourceBundle res;
    public static XRay3dFrame modelFrame3D;
    public static int tmpInt;
    public static RenderFrame renderFrame;

    public static CheckFrame checkFrame;

    public static String iniFilePath = System.getProperty("user.home") + "\\oripa.ini";


    private static final String resourcePackage = "oripa.resource";


    private static ResourceBundle createResource(String classPath){
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle(classPath,
                    Locale.getDefault());
        } catch (Exception e) {
            bundle = ResourceBundle.getBundle(classPath,
                    Locale.ENGLISH);
        }
        bundle = ResourceBundle.getBundle(classPath, Locale.ENGLISH);


        return bundle;
    }

    public static void main(String[] args) {
        res = createResource(resourcePackage+".StringResource");

        ResourceHolder resources = ResourceHolder.getInstance();

        TITLE = resources.getString(ResourceKey.LABEL, StringID.Main.TITLE_ID);
//        TITLE = ORIPA.res.getString("Title") + "  v" + Version.ORIPA_VERSION;

        int uiPanelWidth = 0;//150;
        int modelFrameWidth = 400;
        int modelFrameHeight = 400;
        int mainFrameWidth = 1000;
        int mainFrameHeight = 800;

        int appTotalWidth = mainFrameWidth + uiPanelWidth;
        int appTotalHeight = mainFrameHeight;

        // Construction of the main frame
        mainFrame = new MainFrame();

        Toolkit toolkit = mainFrame.getToolkit();
        Dimension dim = toolkit.getScreenSize();
        int originX = (int) (dim.getWidth() / 2 - appTotalWidth / 2);
        int originY = (int) (dim.getHeight() / 2 - appTotalHeight / 2);

        mainFrame.setBounds(originX + uiPanelWidth, originY, mainFrameWidth, mainFrameHeight);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.updateTitleText();
        mainFrame.initialize();
        mainFrame.setVisible(true);

        // Expected folded origami frame (x-ray)
        modelFrame = new XRayFrame();
        modelFrame.setBounds(originX + (appTotalWidth - modelFrameWidth) / 2, originY + (appTotalHeight - modelFrameHeight) / 2,
                modelFrameWidth, modelFrameHeight);
        modelFrame.setVisible(false);

        // "Folded origami" frame. Estimation of the folded form.
        renderFrame = new RenderFrame();
        renderFrame.setVisible(false);

        // "Check Window". Check inputed data.
        checkFrame = new CheckFrame();
        checkFrame.setVisible(false);



        if (Config.FOR_STUDY) {
            modelFrame3D = new XRay3dFrame();
            modelFrame3D.setBounds(0, 0,
                    modelFrameWidth * 2, modelFrameHeight * 2);
            modelFrame3D.setVisible(true);
        }

    }

    public static void ERROR_END(String message) {
        JOptionPane.showMessageDialog(
                ORIPA.mainFrame, message, "ERROR",
                JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public static void outMessage(String s) {
        JOptionPane.showMessageDialog(
                ORIPA.mainFrame, s, "ORIPA",
                JOptionPane.DEFAULT_OPTION);

    }
}
