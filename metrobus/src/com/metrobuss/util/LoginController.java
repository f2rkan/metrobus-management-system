package com.metrobuss.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.ConnectionUt;

import java.sql.*;


public class LoginController {
	
	//buradaki sql bağlantısı, util paketindeki ConnectionUt sınıfına bağlı
	public LoginController() {
		con = ConnectionUt.conDB();
	}
	Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
    @FXML
    private ResourceBundle resources;

 

    @FXML
    private Button btnKayit;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnReset;

    @FXML
    private Label label_info;

    @FXML
    private TextField pass;

    @FXML
    private TextField username;

    //kullanıcı kaydı event'i, Kullanicikaydi.fxml'e yönlendirme yapar
    @FXML
    void btnKayit_action(ActionEvent event) {

    	if(event.getSource() == btnKayit) {
    		
			try {
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.close();
				
				Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Kullanicikaydi.fxml")));
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				System.err.println(e.getMessage());
		}
	}
    	
    }

    //şifremi unuttum butonu event'i
    @FXML
    void btnReset_action(ActionEvent event) {
    	showDialog("Yeni kayıt yapabilirsin..", "BİLGİ", "ŞİFREMİ UNUTTUM");
    }
    
    //giriş yap butonu event'i
    @FXML
    void btnLogin_action(ActionEvent event) {

    	if(event.getSource() == btnLogin) {
    		if(login().equals("basarili")) {
    		
    			try {
					Node node = (Node) event.getSource();
					Stage stage = (Stage) node.getScene().getWindow();
					stage.close();
					
					Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Sample.fxml")));
					stage.setScene(scene);
					stage.show();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}    				
    		}
    	}
    }

   



	@FXML
    void initialize() {
       
    }
	//md5 şifreleme
	public static String MD5Generator(String pass) {
		try {
			
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] encrypted = md5.digest(pass.getBytes());
			BigInteger no = new BigInteger(1, encrypted);
			String hashPass = no.toString(16);
			while(hashPass.length() < 32) {
				hashPass = "0" + hashPass;
			}
			return hashPass;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	//login fonksiyonu
	private String login() {
    	String kullanici_adi = username.getText().toString();
    	String sifre = pass.getText().toString();
    	
    	//login sorgusu
    	String sorgu = "SELECT * FROM kullanicilar WHERE kullanici_adi = ? and sifre = ?";
    	try {
			preparedStatement = con.prepareStatement(sorgu);			
			preparedStatement.setString(1, kullanici_adi);
			preparedStatement.setString(2, sifre.valueOf(MD5Generator(sifre)));
			
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) {
				label_info.setTextFill(Color.TOMATO);
				label_info.setText("Geçerli bir giriş yap");
				return "error";
			}
			else {
				label_info.setTextFill(Color.GREEN);
				label_info.setText("Giriş Başarılı");
				return "basarili";
			}			
		} catch (SQLException e) {
			
			System.err.println(e.getMessage());
			return "exception";
		}
    }

	//şifremi unuttum kısmı alert penceresi olarak tanımlı
	 private void showDialog(String info, String header, String title) {
	    	
	    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText(info);
			alert.setHeaderText(header);
			alert.showAndWait();	
	    }
	
}

