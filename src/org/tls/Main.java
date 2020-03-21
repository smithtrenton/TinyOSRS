package org.tls;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.applet.Applet;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws Exception {
	    System.setProperty("sun.awt.noerasebackground", "true");

		String username = System.getProperty("java.net.socks.username");
		if (username != null) {
			Optional<String> password = Optional.ofNullable(System.getProperty("java.net.socks.password"));
			Authenticator.setDefault(new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return (new PasswordAuthentication(username, password.orElse("").toCharArray()));
				}
			});
		}

		RSFrame.start();
    }
}
