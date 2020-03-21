package org.tls;

import java.applet.Applet;
import java.io.IOException;

public class RSLoader {
    private final RSAppletStub rsAppletStub;
    private final RSClassLoader rsClassLoader;

    private RSLoader(RSAppletStub rsAppletStub, RSClassLoader rsClassLoader) {
        this.rsAppletStub = rsAppletStub;
        this.rsClassLoader = rsClassLoader;
    }

    public static RSLoader load() throws IOException {
        RSAppletStub rsAppletStub = RSAppletStub.load();
        return new RSLoader(rsAppletStub, RSClassLoader.from(rsAppletStub.getGamePackURL()));
    }

    public Applet createApplet() throws Exception {
        Applet applet = (Applet) rsClassLoader.loadClass(rsAppletStub.getInitialClass()).getDeclaredConstructor().newInstance();
        applet.setStub(rsAppletStub);
        applet.setMinimumSize(rsAppletStub.getMinSize());
        applet.setMaximumSize(rsAppletStub.getMazSize());
        applet.setPreferredSize(applet.getMinimumSize());
        return applet;
    }

    public RSAppletStub getRsAppletStub() {
        return rsAppletStub;
    }
}
