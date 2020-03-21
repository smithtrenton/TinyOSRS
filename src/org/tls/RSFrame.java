package org.tls;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.applet.Applet;

public class RSFrame {
    private final RSLoader loader;
    private final Applet applet;
    private final JFrame frame;

    private RSFrame(RSLoader loader, Applet applet, JFrame frame) {
        this.loader = loader;
        this.applet = applet;
        this.frame = frame;
    }

    public static RSFrame start() throws Exception {
        RSLoader loader = RSLoader.load();
        Applet applet = loader.createApplet();

        JFrame frame = new JFrame(loader.getRsAppletStub().getTitle());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(applet);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setPreferredSize(frame.getSize());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        applet.init();
        applet.start();

        return new RSFrame(loader, applet, frame);
    }
}
