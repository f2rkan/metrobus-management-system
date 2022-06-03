package com.metrobuss.util;
//metrobus kaydı için gerekli tanımlamalar (constructure, getter setter) burada tanımlanıyor
//SampleController ile kullanılıyor
import java.util.Date;

public class Kayitlar {

	private Integer id;
	private String hat;
	private String marka;
	private String model;
	private String plaka;
	private String ilkDurak;
	private String sonDurak;
	private Integer kapasite;
	private Date tarih;
	
	//Constructurelar 
	public Kayitlar(Integer id, String plaka,String hat, String marka, String model, String ilkDurak, String sonDurak,
			Integer kapasite, Date tarih) {
		super();
		this.id = id;
		this.hat = hat;
		this.marka = marka;
		this.model = model;
		this.ilkDurak = ilkDurak;
		this.sonDurak = sonDurak;
		this.kapasite = kapasite;
		this.tarih = tarih;
		this.plaka = plaka;
	}

	//getterlar
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHat() {
		return hat;
	}

	public void setHat(String hat) {
		this.hat = hat;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}
	
	public String getPlaka() {
		return plaka;
	}

	//setterlar
	public void setModel(String model) {
		this.model = model;
	}


	public void setPlaka(String plaka) {
		this.plaka = plaka;
	}
	
	public String getIlkDurak() {
		return ilkDurak;
	}

	public void setIlkDurak(String ilkDurak) {
		this.ilkDurak = ilkDurak;
	}

	public String getSonDurak() {
		return sonDurak;
	}

	public void setSonDurak(String sonDurak) {
		this.sonDurak = sonDurak;
	}

	public Integer getKapasite() {
		return kapasite;
	}

	public void setKapasite(Integer kapasite) {
		this.kapasite = kapasite;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}
	
	
	
}
