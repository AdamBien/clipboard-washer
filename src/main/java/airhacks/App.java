package airhacks;


import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author airhacks.com
 */
interface App {

    String VERSION = "cw v0.0.1";

    static String readClipboard() throws IOException, UnsupportedFlavorException {
        var cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        return (String) cb.getData(DataFlavor.stringFlavor);
    }

    static void writeClipboard(String content){
        var cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        cb.setContents(new Transferable() {
            @Override
            public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[] { DataFlavor.stringFlavor };
            }

            @Override
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                return true;
            }

            @Override
            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                return content;
            }

        }, (c, t) -> {}
        );
        
    }

    static void main(String... args) throws Exception {
        System.out.println(VERSION);
        var content = readClipboard();
        System.out.println("cleaning %s".formatted(content));
        writeClipboard(content);
    }
}
