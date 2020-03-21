package org.tls;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.applet.AudioClip;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.Enumeration;
import java.util.Iterator;

public class RSAppletStub implements AppletStub, AppletContext {
    private final RSConfig config;

    private RSAppletStub(RSConfig config) {
        this.config = config;
    }

    public static RSAppletStub load() throws IOException {
        return new RSAppletStub(RSConfig.load());
    }

    public URL getGamePackURL() throws MalformedURLException {
        return config.getGamePackURL();
    }

    public String getInitialClass() {
        return config.getInitialClass();
    }

    public Dimension getMinSize() {
        return config.getAppletMinSize();
    }

    public Dimension getMazSize() {
        return config.getAppletMaxSize();
    }

    public String getTitle() {
        return config.getTitle();
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public URL getDocumentBase() {
        return getCodeBase();
    }

    @Override
    public URL getCodeBase() {
        try {
            return config.getCodeBase();
        } catch (MalformedURLException e) {
            throw new InvalidParameterException();
        }
    }

    @Override
    public String getParameter(String name) {
        return config.getParameter(name);
    }

    @Override
    public AppletContext getAppletContext() {
        return this;
    }

    @Override
    public void appletResize(int width, int height) {

    }

    @Override
    public AudioClip getAudioClip(URL url) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Image getImage(URL url) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Applet getApplet(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Enumeration<Applet> getApplets() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showDocument(URL url) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showDocument(URL url, String target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showStatus(String status) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setStream(String key, InputStream stream) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public InputStream getStream(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<String> getStreamKeys() {
        throw new UnsupportedOperationException();
    }
}
