package com.metrobuss.util;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.ResourceBundle;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import util.ConnectionUt;
public class KullanicikaydiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEkle;

    @FXML
    private Button btnGuncelle;

    @FXML
    private Button btnSil;

    @FXML
    private Button btnTemizle;

    @FXML
    private TableColumn<kullaniciKaydi, Integer> colId;

    @FXML
    private TableColumn<kullaniciKaydi, String> col_ad;

    @FXML
    private TableColumn<kullaniciKaydi, String> col_gorev;

    @FXML
    private TableColumn<kullaniciKaydi, String> col_pass;

    @FXML
    private TableColumn<kullaniciKaydi, String> col_soyad;

    @FXML
    private TableColumn<kullaniciKaydi, String> col_username;

    @FXML
    private TableColumn<kullaniciKaydi, Integer> col_yetki;

    @FXML
    private TextField tfGorev;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfIsim;

    @FXML
    private TextField tfKullanici;

    @FXML
    private TextField tfSifre;

    @FXML
    private TextField tfSoyisim;

    @FXML
    private TextField tfYetki;

    @FXML
    private TableView<kullaniciKaydi> tvEkran;

    //ekleme butonu event'i
    @FXML
    void btnEkle_action(ActionEvent event) {
    	if(event.getSource() == btnEkle){
            insertRecord();  
            
    	}
    }

    //güncelleme butonu event'i
    @FXML
    void btnGuncelle_action(ActionEvent event) {
    	
    	if(event.getSource() == btnGuncelle){
            updateRecord(); 	
    	}
    	
    }

    //silme butonu event'i
    @FXML
    void btnSil_action(ActionEvent event) {

    	if(event.getSource() == btnSil){
            deleteRecord(); 	
    	}
    	
    }

    //temizleme butonu event'i
    @FXML
    void btnTemizle_action(ActionEvent event) {
    	
    	clearField();
    	
    }
    //veri tabanı bağlantısı
    public Connection getConnection() {
    	Connection conn;
    	try {
    		//jdbc:mysql://"veri tabanı konumu", "db user name", "db password"
    		conn = DriverManager.getConnection("jdbc:mysql://localhost/metrobus", "root", "mysql");
    		return conn;
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
    }
    //veri tabanındaki kullanicilar tablosuna ulaşma fonksiyonu
    public ObservableList<kullaniciKaydi>getKayitList(){
    	ObservableList<kullaniciKaydi>kayitList = FXCollections.observableArrayList();
    	Connection conn = getConnection();
    	String sorgu = "SELECT * FROM kullanicilar";
    	Statement st;
    	ResultSet rs;
    	try {
			st = conn.createStatement();
			rs = st.executeQuery(sorgu);
			kullaniciKaydi kayit_islemleri;
			while(rs.next()) {
				kayit_islemleri = new kullaniciKaydi(rs.getInt("id"), rs.getString("ad"), rs.getString("soyad"), rs.getString("gorev"), rs.getInt("sistem_yetkisi"),  rs.getString("kullanici_adi"), rs.getString("sifre"));
				kayitList.add(kayit_islemleri);				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	return kayitList;
    }
    
    //veri tabanındaki kayıtları çeken fonksiyon
    public void kayitGoster() {
    	ObservableList<kullaniciKaydi>list = getKayitList();
    	
    	colId.setCellValueFactory(new PropertyValueFactory<kullaniciKaydi, Integer>("id"));
    	col_ad.setCellValueFactory(new PropertyValueFactory<kullaniciKaydi, String>("ad"));
    	col_soyad.setCellValueFactory(new PropertyValueFactory<kullaniciKaydi, String>("soyad"));
    	col_gorev.setCellValueFactory(new PropertyValueFactory<kullaniciKaydi, String>("gorev"));
    	col_yetki.setCellValueFactory(new PropertyValueFactory<kullaniciKaydi, Integer>("sistem_yetkisi"));
    	col_username.setCellValueFactory(new PropertyValueFactory<kullaniciKaydi, String>("kullanici_adi"));
    	col_pass.setCellValueFactory(new PropertyValueFactory<kullaniciKaydi, String>("sifre"));   	
    	
    	tvEkran.setItems(list);
    }

    //sorgu çalıştırma fonksiyonu
    private void executeQuery(String sorgu) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(sorgu);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    //ekle butonu action sorgusu
    	private void insertRecord() {
    	
    	String sorgu = "INSERT INTO kullanicilar VALUES(" 
    	+ tfId.getText()
    	+ ",'" + tfIsim.getText() + "','"
    	+ tfSoyisim.getText() + "','"
    	+ tfGorev.getText() + "',"
    	+ tfYetki.getText() + ",'"
    	+ tfKullanici.getText() + "','"
    	+ tfSifre.getText().valueOf(MD5Generator(tfSifre.getText()))+ "')";
    	
    	//sorguyu çalıştır
    	executeQuery(sorgu);
    	//çalışmış sorguyla güncellenen tabloyu kayitGoster() ile getir
    	kayitGoster();
    }
    
    	//güncelleme butonu ve md5 şifreleme
    	private void updateRecord(){
        	String sorgu = "UPDATE  kullanicilar SET ad  = '" + tfIsim.getText()
        	+ "', soyad = '" + tfSoyisim.getText() 
        	+ "', gorev = '" + tfGorev.getText() 
        	+ "', sistem_yetkisi = " + tfYetki.getText() 
        	+ ",  kullanici_adi = '" + tfKullanici.getText() 
        	+ "', sifre = '" + tfSifre.getText().valueOf(MD5Generator(tfSifre.getText())) + "' WHERE id = " + tfId.getText() + "";
           		    	
           executeQuery(sorgu);
           kayitGoster();
        }
    	//md5 şifreleme algoritması
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
    	
    	//sil butonu sorgusu
    	private void deleteRecord(){
            String sorgu = "DELETE FROM kullanicilar WHERE id =" + tfId.getText() + "";
            executeQuery(sorgu);
            kayitGoster();
        }
    	
    	//tableview'de tıklanan veriyi getiren tanımlama
    @FXML
    void tvEkran_click(MouseEvent event) {

    	kullaniciKaydi sec = tvEkran.getSelectionModel().getSelectedItem();
    	
    	tfId.setText("" + sec.getId());
    	tfIsim.setText("" + sec.getAd());
    	tfSoyisim.setText("" + sec.getSoyad());
    	tfGorev.setText("" + sec.getGorev());
    	tfYetki.setText("" + sec.getSistem_yetkisi());
    	tfKullanici.setText("" + sec.getKullanici_adi());
    	tfSifre.setText("" + sec.getSifre());
    	 
    	
    }
    //textField'ları temizle butonu fonksiyonu
    private void clearField() {
    	
    	tfId.setText("");
    	tfIsim.setText("");
    	tfSoyisim.setText("");
    	tfGorev.setText("");
    	tfYetki.setText("");
    	tfKullanici.setText("");
    	tfSifre.setText("");    	
    }

    //açılış ekranında kayitGoster() fonksiyonu sayesinde veriler çekiliyor
    @FXML
    void initialize() {
    	kayitGoster();
    }

}
