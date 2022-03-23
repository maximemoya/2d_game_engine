package kotlin_folder.window

import java.awt.Toolkit
import javax.swing.JFrame

class WindowManager : JFrame() {

    companion object STATIC {

        var SCREEN_WIDTH: Int = 0;
        var SCREEN_HEIGHT: Int = 0;

    }

    init {

        //        window.setUndecorated(true);
        val dimension = Toolkit.getDefaultToolkit().screenSize

        STATIC.SCREEN_WIDTH = dimension.width
        STATIC.SCREEN_HEIGHT = dimension.height

        print("" + STATIC.SCREEN_WIDTH + " | " + STATIC.SCREEN_HEIGHT)

    }

}