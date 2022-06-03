-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 28 May 2022, 08:43:30
-- Sunucu sürümü: 8.0.29
-- PHP Sürümü: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `metrobus`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hat_bilgileri`
--

CREATE TABLE `hat_bilgileri` (
  `id` int NOT NULL,
  `plaka` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL,
  `hat` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL,
  `marka` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL,
  `model` varchar(4) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL,
  `ilkDurak` varchar(75) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL,
  `sonDurak` varchar(75) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL,
  `kapasite` int NOT NULL,
  `tarih` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hat_bilgileri`
--

INSERT INTO `hat_bilgileri` (`id`, `plaka`, `hat`, `marka`, `model`, `ilkDurak`, `sonDurak`, `kapasite`, `tarih`) VALUES
(1, '34TN2525', '34A', 'MERCEDES', '2015', 'TÜYAP', 'Beykent', 70, '2022-05-03'),
(2, '34TN2527', '34AS', 'ISUZU', '2019', 'Cumhuriyet Mahallesi', 'Beylikdüzü Belediye', 70, '2022-05-23'),
(3, '34TN2528', '34B', 'OTOKAR', '2021', 'Beylikdüzü', 'Güzelyurt', 80, '2022-05-23'),
(4, '34TN5050', '34G', 'OTOKAR', '2021', 'Haramidere', 'Haramidere Sanayi', 80, '2022-05-23'),
(5, '34TN5757', '34C', 'OTOKAR', '2021', 'Saadetdere Mahallesi', 'Mustafa Kemal Paşa', 80, '2022-05-02'),
(6, '34TN4747', '34U', 'MERCEDES', '2022', 'Cihangir', 'Şükrübey', 70, '2022-04-28'),
(7, '34TN4040', '34T', 'MERCEDES', '2020', 'İBB Sosyal Tesisleri', 'Küçükçekmece', 70, '2022-04-29'),
(8, '34TN4242', '34Z', 'MERCEDES', '2020', 'Cennet Mahallesi', 'Florya', 70, '2022-04-29'),
(9, '34TN4646', '34G', 'MERCEDES', '2020', 'Cennet Mahallesi', 'Florya', 70, '2022-04-29'),
(10, '34TN0707', '34G', 'MERCEDES', '2020', 'Beşyol', 'Sefaköy', 70, '2022-04-29');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kullanicilar`
--

CREATE TABLE `kullanicilar` (
  `id` int NOT NULL,
  `ad` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL,
  `soyad` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL,
  `gorev` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL,
  `sistem_yetkisi` int NOT NULL,
  `kullanici_adi` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL,
  `sifre` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `kullanicilar`
--

INSERT INTO `kullanicilar` (`id`, `ad`, `soyad`, `gorev`, `sistem_yetkisi`, `kullanici_adi`, `sifre`) VALUES
(1, 'Cevdet', 'Akarsuu', 'Satın Alma Daire Başkanı', 0, 'cevdet', 'b5e67e166a2f3310be741d371ff084bd'),
(2, 'Adem', 'Özcan', 'Proje ve İnşaat Müdürü', 1, 'adem', '4297f44b13955235245b2497399d7a93'),
(3, 'İsmail', 'Durman', 'Yolcu Hizmet Yönetim Müdürü', 1, 'ismail', 'fcea920f7412b5da7be0cf42b8c93759'),
(4, 'Vahdet', 'Melikoğlu', 'Anadolu Garaj Müdürü', 1, 'vahdet', '698d51a19d8a121ce581499d7b701668'),
(5, 'Kazım Taylan', 'Sever', 'Araç Bakım Planlama', 1, 'kazim', 'b59c67bf196a4758191e42f76670ceba'),
(6, 'Mehmet', 'Yalçın', 'İşçi Personel Müdürü', 1, 'mehmet', 'b0baee9d279d34fa1dfd71aadb908c3f'),
(7, 'Büşra', 'Boysan', 'Yolcu Hizmetleri Müdürü', 1, 'busra', '7d539fa7514bc639f23e752e4418c049'),
(8, 'Ülkü', 'Yazıcı', 'Yazı İşleri Müdürü', 2, 'ulku', 'f4b9f27f8f3525b9b6b19912bde268f7'),
(9, 'Elif Üçüncü', 'Talebi', 'Bilgi Teknolojileri Müdürü', 0, 'elif', '202cb962ac59075b964b07152d234b70');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `hat_bilgileri`
--
ALTER TABLE `hat_bilgileri`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `kullanicilar`
--
ALTER TABLE `kullanicilar`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `hat_bilgileri`
--
ALTER TABLE `hat_bilgileri`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Tablo için AUTO_INCREMENT değeri `kullanicilar`
--
ALTER TABLE `kullanicilar`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
