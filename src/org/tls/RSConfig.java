package org.tls;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RSConfig {
    private static final String JAV_CONFIG_URL = "http://oldschool.runescape.com/jav_config.ws";

    private final Map<String, String> properties;
    private final Map<String, String> parameters;


    public RSConfig(Map<String, String> properties, Map<String, String> parameters) {
        this.properties = properties;
        this.parameters = parameters;
    }

    public static RSConfig load() throws IOException {
        Map<String, String> properties = new HashMap<>();
        Map<String, String> parameters = new HashMap<>();
        URL url = new URL(JAV_CONFIG_URL);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.ISO_8859_1))) {
            reader.lines().forEach((String line) -> {
                String[] prefixSplit = line.split("=", 2);
                switch (prefixSplit[0]) {
                    case "param":
                        String[] paramSplit = prefixSplit[1].split("=", 2);
                        parameters.put(paramSplit[0], paramSplit[1]);
                        break;
                    case "msg":
                        break;
                    default:
                        properties.put(prefixSplit[0], prefixSplit[1]);
                }
            });
        }
        return new RSConfig(properties, parameters);
    }

    public Dimension getAppletMinSize() {
        return new Dimension(Integer.parseInt(properties.get("applet_minwidth")), Integer.parseInt(properties.get("applet_minheight")));
    }

    public Dimension getAppletMaxSize() {
        return new Dimension(Integer.parseInt(properties.get("applet_maxwidth")), Integer.parseInt(properties.get("applet_maxheight")));
    }

    public URL getCodeBase() throws MalformedURLException {
        return new URL(properties.get("codebase"));
    }

    public URL getGamePackURL() throws MalformedURLException {
        return new URL(properties.get("codebase") + properties.get("initial_jar"));
    }

    public String getInitialClass() {
        return properties.get("initial_class").substring(0, properties.get("initial_class").length() - 6);
    }

    public String getParameter(String param) {
        return parameters.get(param);
    }

    public String getTitle() {
        return properties.get("title");
    }
}
