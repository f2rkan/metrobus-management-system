package com.metrobuss.util;

public class kullaniciKaydi {

	private Integer id;
	private String ad;
	private String soyad;
	private String gorev;
	private Integer sistem_yetkisi;
	private String kullanici_adi;
	private String sifre;
	
	//getter ve setterlar
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getGorev() {
		return gorev;
	}

	public void setGorev(String gorev) {
		this.gorev = gorev;
	}

	public Integer getSistem_yetkisi() {
		return sistem_yetkisi;
	}

	public void setSistem_yetkisi(Integer sistem_yetkisi) {
		this.sistem_yetkisi = sistem_yetkisi;
	}

	public String getKullanici_adi() {
		return kullanici_adi;
	}

	public void setKullanici_adi(String kullanici_adi) {
		this.kullanici_adi = kullanici_adi;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	//constructure
	public kullaniciKaydi(Integer id, String ad, String soyad, String gorev, Integer sistem_yetkisi,
			String kullanici_adi, String sifre) {
		super();
		this.id = id;
		this.ad = ad;
		this.soyad = soyad;
		this.gorev = gorev;
		this.sistem_yetkisi = sistem_yetkisi;
		this.kullanici_adi = kullanici_adi;
		this.sifre = sifre;
	}
}
