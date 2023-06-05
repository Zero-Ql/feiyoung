package com.example.passwordgui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;


public class HelloController {
    private static final String[] DateEnum = {
            "5084972163", "9801567243", "7286059143", "1850394726", "1462578093", "5042936178", "0145937682", "0964238571", "3497651802", "9125780643", "8634972150",
            "5924673801", "8274053169", "5841792063", "2469385701", "8205349671", "7429516038", "3769458021", "5862370914", "8529364170", "7936082154", "5786241930",
            "0728643951", "9418360257", "5093287146", "5647830192", "3986145207", "0942587136", "4357069128", "0956723814", "1502796384",
    };
    @FXML
    public TextField textfield;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    protected void onHelloButtonClick() throws NoSuchAlgorithmException {
        String password = textfield.getText();
        label1.setText("加密后的密码为：");
        label2.setText((RC4Tool.Encrypt(DateEnum[LocalDate.now().getDayOfMonth() - 1], password).length() == 32 ?
                RC4Tool.Encrypt(DateEnum[LocalDate.now().getDayOfMonth() - 1], password).substring(8, 24) :
                RC4Tool.Encrypt(DateEnum[LocalDate.now().getDayOfMonth() - 1], password)));
    }

    @FXML
    protected void onHttpMyBlogClick() {
        String url = "https://zero-li.fun";
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI(url);
            desktop.browse(uri);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onGitHubClick() {
        String url = "https://github.com/Zero-Ql/feiyoung";
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI(url);
            desktop.browse(uri);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}