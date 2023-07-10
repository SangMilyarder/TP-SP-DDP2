import java.awt.Dimension;

/**
 * 
 * Perintah.java
 * <br><br>
 * Class {@code Perintah} merepresentasikan perintah-perintah umum yang 
 * dapat diberikan kepada kura-kura. Termasuk dalam class ini adalah
 * proses untuk membaca input (saat ini baru melalui satu baris perintah)
 * dan memanggil method yang berkesesuaian.
 * Dalam kelas ini juga disediakan method-method yang merupakan kumpulan-kumpulan
 * perintah berurutan yang dapat diterima oleh kurakura dan menghasilkan gambar 
 * tertentu. 
 * <br><br>
 * Tugas anda pada file ini: <br>
 * - Lengkapi javadoc comment pada tiap deklarasi method.<br>
 * - Lengkapi inline comment untuk tiap baris perintah yang penting.<br>
 * - Perbaiki method {@code lakukan} agar tidak menimbulkan error bila input salah<br>
 * - Buat (1) perintah {@code mundur <x>}" agar kura-kura bisa mundur sebanyak x pixel satuan.
 * - Buat (2) perintah {@code hadap kanan} dan {@code hadap kiri} yang akan membuat kura-kura 
 *   menghadap ke kanan (rotasi 90) dan ke kiri (rotasi -90) 
 * - Dapatkah anda membuat (3) kura-kura mengenali perintah 
 *   {@code loop 10 perintah-perintah} <br>
 *   yang artinya melakukan perintah-perintah sebanyak 10 kali? <br>
 *   contoh: "loop 10 rotasi 30 maju 30" <br>
 *           yang artinya akan melakukan perintah "rotasi 30", dilanjutkan <br>
 *           perintah "maju 30", secara berulang-ulang sebanyak 10 kali<br>
 *   contoh: "loop 5 maju 20 hadap kanan maju 30" <br>
 *           yang artinya akan melakukan perintah "maju 20", dilanjutkan <br>
 *           "hadap kanan", kemudian perintah "maju 10", <br> 
 *           secara berulang-ulang sebanyak 5 kali<br>
 * 
 * @author Ade Azurat for DPBO 2008 @ Fasilkom UI
 * @author Ade Azurat for DDP2 2023 @ Fasilkom UI
 */
public class Perintah {
    Canvas canvas;
    Kurakura kurakuraku; 
    
    /** Creates a new instance of Perintah */
    public Perintah(Kurakura k, Canvas canvas) {
        kurakuraku = k;
        this.canvas = canvas;
    }

    // Dapatkan anda membuat method ini lebih baik dan lebih mudah ditambahkan
    // atau di ubah? 
    public String lakukan(String inputPerintah){
        String[] in = inputPerintah.split(" ");
        if (in.length < 2 && !in[0].equalsIgnoreCase("selesai") && !in[0].equalsIgnoreCase("pohon") && !in[0].equalsIgnoreCase("hadapkanan")  && !in[0].equalsIgnoreCase("hadapkiri")) { //mengecek apakah perintah sesuai
            canvas.repaint();
            return "Perintah tidak lengkap. Masukkan kembali perintah."; //mencetak informasi jika perintah salah
        } else if (in[0].equalsIgnoreCase("segitigasikusiku") || in[0].equalsIgnoreCase("persegi")) {
            if (in.length < 3) {
                canvas.repaint();
                return "Perintah tidak lengkap. Masukkan kembali perintah.";
            } else if (in[0].equalsIgnoreCase("persegi")) {
                buatPersegi(Integer.parseInt(in[1]), Integer.parseInt(in[2])); //terdiri dari 2 variabel yaitu panjang dan lebar
            } else if (in[0].equalsIgnoreCase("segitigasikusiku")) {
                buatSegitigaSikuSiku(Integer.parseInt(in[1]), Integer.parseInt(in[2])); //terdiri dari 2 variabel yaitu panjang dan lebar
        }} else {
            if (in[0].equalsIgnoreCase("selesai")) {
                System.exit(0);
            } else if (in[0].equalsIgnoreCase("reset")) {
                kurakuraku.reset();
            } else if (in[0].equalsIgnoreCase("maju")) {
                kurakuraku.maju(Integer.parseInt(in[1]));
            } else if (in[0].equalsIgnoreCase("mundur")) {
                kurakuraku.mundur(Integer.parseInt(in[1]));
            } else if (in[0].equalsIgnoreCase("rotasi")) {
                kurakuraku.rotasi(Integer.parseInt(in[1]));
            } else if (in[0].equalsIgnoreCase("hadapkanan")){
                hadapkanan();
            } else if (in[0].equalsIgnoreCase("hadap") && in[1].equalsIgnoreCase("kanan")){
                hadapkanan();
            } else if (in[0].equalsIgnoreCase("hadapkiri")){
                hadapkiri();
            } else if (in[0].equalsIgnoreCase("hadap") && in[1].equalsIgnoreCase("kiri")){
                hadapkiri();
            }else if (in[0].equalsIgnoreCase("boxes")){
                buatBoxes(Integer.parseInt(in[1]));
            }else if (in[0].equalsIgnoreCase("custom")){
                buatTriangles(Integer.parseInt(in[1]));
            } else if (in[0].equalsIgnoreCase("kotak")) {
                buatKotak(Integer.parseInt(in[1]));
            } else if (in[0].equalsIgnoreCase("segitiga")) {
                buatSegitiga(Integer.parseInt(in[1])); //terdiri dari 1 variabel yaitu ukuran
            } else if (in[0].equalsIgnoreCase("persegi")) {
                buatPersegi(Integer.parseInt(in[1]), Integer.parseInt(in[2])); //terdiri dari 2 variabel yaitu panjang dan lebar
            } else if (in[0].equalsIgnoreCase("segitigasikusiku")) {
                buatSegitigaSikuSiku(Integer.parseInt(in[1]), Integer.parseInt(in[2])); //terdiri dari 2 variabel yaitu panjang dan lebar
            } else if (in[0].equalsIgnoreCase("pohon")) {
                buatPohon();
            } else if (in[0].equalsIgnoreCase("jejak")) {
                kurakuraku.setJejak(Boolean.parseBoolean(in[1]));
            } else if (in[0].equalsIgnoreCase("pindah")) {
                kurakuraku.setPosition(new Dimension(Integer.parseInt(in[1]), Integer.parseInt(in[2])));
            } else if (in[0].equalsIgnoreCase("loop")) { //untuk mengenali apakah ada perintah loop
                int jumlah = Integer.parseInt(in[1]); //untuk mengambil jumlah loop yang diminta user
                for (int i = 0; i < jumlah; i++) { //inisiasi loop
                    for (int j = 2; j < in.length; j += 2) { //dimulai dari indeks ke-2, karena indeks ke-0 dan ke-1 telah digunakan untuk kata "loop" dan jumlah iterasi.
                        String perintah = in[j];
                        int nilai = Integer.parseInt(in[j + 1]); //untuk mengambil nilai yang akan digunakan dalam eksekusi perintah
                        if (perintah.equalsIgnoreCase("maju")) {
                            kurakuraku.maju(nilai);
                        } else if (perintah.equalsIgnoreCase("mundur")) {
                            kurakuraku.mundur(nilai);
                        } else if (perintah.equalsIgnoreCase("rotasi")) {
                            kurakuraku.rotasi(nilai);
                        } else if (perintah.equalsIgnoreCase("kotak")) {
                            buatKotak(nilai);
                        } else if (perintah.equalsIgnoreCase("segitiga")) {
                            buatSegitiga(nilai);
                        } else if (perintah.equalsIgnoreCase("persegi")) {
                            int panjang = Integer.parseInt(in[j + 1]);
                            int lebar = Integer.parseInt(in[j + 2]);
                            buatPersegi(panjang, lebar);
                            j += 2;
                        } else if (perintah.equalsIgnoreCase("segitigasikusiku")) {
                            int panjang = Integer.parseInt(in[j + 1]);
                            int lebar = Integer.parseInt(in[j + 2]);
                            buatSegitigaSikuSiku(panjang, lebar);
                            j += 2;
                        } else if (perintah.equalsIgnoreCase("pohon")) {
                            buatPohon();
                        } else if (perintah.equalsIgnoreCase("jejak")) {
                            boolean jejak = Boolean.parseBoolean(in[j + 1]);
                            kurakuraku.setJejak(jejak);
                            j++;
                        } else if (perintah.equalsIgnoreCase("pindah")) {
                            int x = Integer.parseInt(in[j + 1]);
                            int y = Integer.parseInt(in[j + 2]);
                            kurakuraku.setPosition(new Dimension(x, y));
                            j += 2;
                        } else {
                            canvas.repaint();
                            return "Perintah tidak dipahami.";
                    }              
                }
            }
        } else {
            canvas.repaint();
            return "Perintah tidak dipahami.";
            } 
        }       
        canvas.repaint();    
        return "Perintah sudah dilaksanakan.";
    }

    /**
     * Untuk melakukan perintah hadap kanan yaitu melakukan rotasi ke kanan sebanyak 90 derajat
     */
    public void hadapkanan(){
        kurakuraku.rotasi(90);
    }

    /**
     * Untuk melakukan perintah hadap kiri yaitu melakukan rotasi ke kiri sebanyak -90 derajat
     */
    public void hadapkiri(){
        kurakuraku.rotasi(-90);
    }

    /**
     * Untuk membuat bentuk kotak secara rekursif
     * Setiap level rekursi akan membuat kotak lebih kecil dan digambar di tengah kotak sebelumnya
     * @param ukuran Ukuran kotak pada level sekarang
     * Jika ukuran <= 0 kotak tidak akan digambar
     */
    public void buatBoxes(int ukuran) {
        if (ukuran <= 0){
            return;
        } else {
            buatKotak(ukuran);
            kurakuraku.setJejak(false);
            kurakuraku.maju(10);
            kurakuraku.rotasi(90);
            kurakuraku.maju(10);
            kurakuraku.rotasi(-90);
            kurakuraku.setJejak(true);
            buatBoxes(ukuran - 20);
        }
    }

    /**
     * Untuk membuat bentuk segitiga secara rekursif
     * Setiap level rekursi akan membuat segitiga lebih kecil dan digambar di tengah segitiga sebelumnya
     * @param ukuran Ukuran segitiga pada level sekarang
     * jika ukuran <= 0 segitiga tidak akan digambar
     */
    public void buatTriangles(int ukuran) {
        if (ukuran <= 0){
            return;
        } else {
            buatSegitiga(ukuran);
            kurakuraku.setJejak(false);
            kurakuraku.rotasi(-30);
            kurakuraku.maju(10);
            kurakuraku.rotasi(30);
            kurakuraku.setJejak(true);
            buatTriangles(ukuran - 20);
        }
    }

    /**
     * Untuk membuat persegi sama sisi pada turtle
     * @param ukuran sebagai ukuran keempat sisi yang akan dipakai untuk menggambar kotak
     */
    public void buatKotak(int ukuran ){        
        for (int i=0;i<4;i++){
            kurakuraku.maju(ukuran);
            kurakuraku.rotasi(90);
        }
    }

    /**
     * Untuk membuat bentuk segitiga sama sisi pada turtle
     * @param ukuran sebagai ukuran sisi yang akan dipakai untuk menggambar segitiga
     */
    public void buatSegitiga(int ukuran){
        // TODO: Lengkapi isi method ini agar kura-kura bisa membuat segitiga sama sisi
        for (int i=0;i<3;i++){ //for loop untuk 3 kali pengulangan
            kurakuraku.maju(ukuran); //untuk memajukan kura-kura
            kurakuraku.rotasi(-120); //untuk memutar kura-kura
        }
    }       
    
    /**
     * Untuk membuat bentuk persegi panjang pada turtle
     * @param panjang sebagai ukuran panjang yang akan dipakai untuk menggambar persegi
     * @param lebar sebagai ukuran lebar yang akan dipakai untuk menggambar persegi
     */
    public void buatPersegi(int panjang, int lebar){
        // Membuat method untuk persegi
        for (int i=0;i<2;i++){ //for loop untuk 2 kali pengulangan
            kurakuraku.maju(panjang); //untuk memajukan kura-kura sepanjang panjang
            kurakuraku.rotasi(90); //untuk memutar kura-kura
            kurakuraku.maju(lebar); //untuk memajukan kura-kura sepanjang lebar
            kurakuraku.rotasi(90); //untuk memutar kura-kura
        }
    }

    /**
     * Untuk membuat bentuk segitiga siku-siku pada turtle
     * @param panjangAlas Sebagai alas untuk segitiga yang akan dibuat
     * @param tinggi Sebagai tinggi untuk segitiga yang akan dibuat
     */
    public void buatSegitigaSikuSiku(int panjangAlas, int tinggi){
        // Membuat method untuk segitiga siku siku
        kurakuraku.maju(panjangAlas); //untuk menggambar alas segitiga
        kurakuraku.rotasi(-135); //untuk memutar kura-kura
        kurakuraku.maju(Math.sqrt(panjangAlas * panjangAlas + tinggi * tinggi)); //untuk menggambar sisi miring
        kurakuraku.rotasi(-135); //untuk memutar kura-kura
        kurakuraku.maju(tinggi); //untuk menggambar tinggi segitiga
    }
    
    /**
     * Untuk menggambar bentuk pohon sesuai dengan perintah yang sudah diberikan
     * Setelah membuat pohon, posisi dan rotasi kurakura akan direset
     */
    public void buatPohon(){        
        kurakuraku.setJejak(false);
        kurakuraku.reset();
        kurakuraku.rotasi(90);
        kurakuraku.maju(100);
        kurakuraku.rotasi(180);
        buatPohon(6,50);        
        kurakuraku.reset();
    }
    
    /**
     * Untuk menggambar pohon secara rekursif dengan ukuran dan tinggi yang sudah diberikan
     * @param ukuran Jumlah level rekursi untuk membuat pohon.
     * @param tinggi Tinggi pohon untuk setiap level rekursi
     */
    private void buatPohon(int ukuran, int tinggi){
        if (ukuran>0){
            kurakuraku.setJejak(true);
            kurakuraku.maju(tinggi);                        
            kurakuraku.rotasi(-45);
            Dimension posAwal = kurakuraku.getPosition();
            double arah = kurakuraku.getArah();
            double sudut = arah;
            for(int i=0;i<3;i++){  
                buatPohon(ukuran-1,(int)(tinggi/1.5));
                kurakuraku.setJejak(false);
                kurakuraku.setPosition(posAwal);
                kurakuraku.setArah(arah);                
                sudut+=45;
                kurakuraku.rotasi(sudut);  
            }     
        } else {
            kurakuraku.setJejak(true);
            buatPersegiKecil(); // Menambahkan persegi kecil di ujung ranting pohon
        }
        kurakuraku.reset();
    }

    /**
     * Untuk membuat persegi kecil pada ujung ranting pohon
     * Fungsi ini melakukan gerakan maju dan rotasi turtle untuk menempatkan persegi kecil
     * Persegi kecil berukuran 5
     */
    private void buatPersegiKecil() {
        // Menggambar persegi kecil di ujung ranting pohon
        kurakuraku.maju(10);
        kurakuraku.rotasi(45);
        buatPersegi(5,5); // membuat ukuran 5 untuk persegi kecil
    }
}