package com.metrobuss.util;

import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Date;
import com.metrobuss.util.Kayitlar;
import javafx.scene.control.DatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class SampleController {

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
    private TableColumn<Kayitlar, String> col_hat;

    @FXML
    private TableColumn<Kayitlar, Integer> col_id;

    @FXML
    private TableColumn<Kayitlar, String> col_ilkDurak;

    @FXML
    private TableColumn<Kayitlar, Integer> col_kapasite;

    @FXML
    private TableColumn<Kayitlar, String> col_marka;

    @FXML
    private TableColumn<Kayitlar, String> col_model;

    @FXML
    private TableColumn<Kayitlar, String> col_plaka;

    @FXML
    private TableColumn<Kayitlar, String> col_sonDurak;

    //DatePicker kullanmak için: javafx.scene.control.DatePicker kütüphanesini ekledim 
    @FXML
    private TableColumn<Kayitlar, Date> col_tarih;

    @FXML
    private DatePicker dtGiris;

    @FXML
    private TextField tfCalistigiHat;

    @FXML
    private TextField tfFirstDurak;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfKapasite;

    @FXML
    private TextField tfLastDurak;

    @FXML
    private TextField tfMarka;

    @FXML
    private TextField tfModel;

    @FXML
    private TextField tfPlaka;

    @FXML
    private TableView<Kayitlar> tvEkran;

    //ekleme butonu, action eylemi
    @FXML
    void btnEkle_action(ActionEvent event) {

    	if(event.getSource() == btnEkle){
            insertRecord();  
            
    }
 }

    //güncelleme butonu action eylemi
    @FXML
    void btnGuncelle_action(ActionEvent event) {

    	if(event.getSource() == btnGuncelle){
            updateRecord(); 	
    	}
    	
    }

    //sil butonu action eylemi
    @FXML
    void btnSil_action(ActionEvent event) {

    	if(event.getSource() == btnSil){
            deleteRecord(); 	
    	}
    	
    }

    //textField'ları temizler
    @FXML
    void btnTemizle_action(ActionEvent event) {
    		clearField();
    	}
  
    
    //tableview'de tıklanan veriyi textField'larda YERİNE taşır.
    @FXML
    void tvEkran_clicked(MouseEvent event) {
    	Kayitlar sec = tvEkran.getSelectionModel().getSelectedItem();
    	
    	tfId.setText("" + sec.getId());
    	tfPlaka.setText("" + sec.getPlaka());
    	tfCalistigiHat.setText("" + sec.getHat());
    	tfMarka.setText("" + sec.getMarka());
    	tfModel.setText("" + sec.getModel());
    	tfFirstDurak.setText("" + sec.getIlkDurak());
    	tfLastDurak.setText("" + sec.getSonDurak());
    	tfKapasite.setText("" + sec.getKapasite());   
    	dtGiris.setValue(((java.sql.Date) sec.getTarih()).toLocalDate());

    }
    //textField'lar için temizleme fonksiyonu
    private void clearField() {
    	
    	tfId.setText("");
    	tfPlaka.setText("");
    	tfCalistigiHat.setText("");
    	tfMarka.setText("");
    	tfModel.setText("");
    	tfFirstDurak.setText("");
    	tfLastDurak.setText("");
    	tfKapasite.setText("");   
    	dtGiris.getEditor().clear();
    	
    }
    //java.sql* kütüphanesini import ettiğimiz takdirde çalışıyor
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

    public ObservableList<Kayitlar>getKayitList(){
    	ObservableList<Kayitlar>kayitList = FXCollections.observableArrayList();
    	Connection conn = getConnection();
    	String sorgu = "SELECT * FROM hat_bilgileri";
    	Statement st;
    	ResultSet rs;
    	try {
			st = conn.createStatement();
			rs = st.executeQuery(sorgu);
			Kayitlar kayit_islemleri;
			while(rs.next()) {
				kayit_islemleri = new Kayitlar(rs.getInt("id"), rs.getString("plaka"), rs.getString("hat"), rs.getString("marka"), rs.getString("model"),  rs.getString("ilkDurak"), rs.getString("sonDurak"), rs.getInt("kapasite") , rs.getDate("tarih"));
				kayitList.add(kayit_islemleri);				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	return kayitList;
    }
    
    
    public void kayitGoster() {
    	ObservableList<Kayitlar>list = getKayitList();
    	
    	col_id.setCellValueFactory(new PropertyValueFactory<Kayitlar, Integer>("id"));
    	col_plaka.setCellValueFactory(new PropertyValueFactory<Kayitlar, String>("plaka"));
    	col_hat.setCellValueFactory(new PropertyValueFactory<Kayitlar, String>("hat"));
    	col_marka.setCellValueFactory(new PropertyValueFactory<Kayitlar, String>("marka"));
    	col_model.setCellValueFactory(new PropertyValueFactory<Kayitlar, String>("model"));
    	col_ilkDurak.setCellValueFactory(new PropertyValueFactory<Kayitlar, String>("ilkDurak"));
    	col_sonDurak.setCellValueFactory(new PropertyValueFactory<Kayitlar, String>("sonDurak"));
    	col_kapasite.setCellValueFactory(new PropertyValueFactory<Kayitlar, Integer>("kapasite"));
    	col_tarih.setCellValueFactory(new PropertyValueFactory<Kayitlar, Date>("tarih"));
    	
    	tvEkran.setItems(list);
    }

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
    
    private void insertRecord() {
    	
    	String sorgu = "INSERT INTO hat_bilgileri VALUES(" 
    	+ tfId.getText()
    	+ ",'" + tfPlaka.getText() + "','"
    	+ tfCalistigiHat.getText() + "','"
    	+ tfMarka.getText() + "','"
    	+ tfModel.getText() + "','"
    	+ tfFirstDurak.getText() + "','"
    	+ tfLastDurak.getText()+ "',"
    	+ tfKapasite.getText()
    	+ ",'" + dtGiris.getValue() +
    	"')";
    	
    	executeQuery(sorgu);
    	kayitGoster();
    }
    
    private void updateRecord(){
    	String sorgu = "UPDATE  hat_bilgileri SET plaka  = '" + tfPlaka.getText()
    	+ "', hat = '" + tfCalistigiHat.getText() 
    	+ "', marka = '" + tfMarka.getText() 
    	+ "', model = '" + tfModel.getText() 
    	+ "', ilkDurak = '" + tfFirstDurak.getText() 
    	+ "', sonDurak = '" + tfLastDurak.getText()
    	+ "', kapasite = " + tfKapasite.getText()
    	+ ", tarih = '" + dtGiris.getValue() + "' WHERE id = " + tfId.getText() + "";
       		    	
       executeQuery(sorgu);
       kayitGoster();
    }
    
    private void deleteRecord(){
        String sorgu = "DELETE FROM hat_bilgileri WHERE id =" + tfId.getText() + "";
        executeQuery(sorgu);
        kayitGoster();
    }
    
    @FXML
    void initialize() {
        kayitGoster();
    }
}
