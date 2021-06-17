import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;


public class Bioskop {
	static final String driverJDBC = "com.mysql.jdbc.Driver";
	static final String DB = "jdbc:mysql://localhost:3306/bioskop";
	static final String User = "root";
	static final String Pass = "";
	
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
public static void main (String[]args) {
	
	
	try {
		Class.forName(driverJDBC);
		conn = DriverManager.getConnection(DB, User, Pass);
		stmt = conn.createStatement();
		while (!conn.isClosed()) 
			
			Menu();
		
		conn.close();
		stmt.close();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
}
static void Menu() {
	Scanner tap = new Scanner(System.in);
	System.out.println("1. Beli Tiket");
	System.out.println("2. Cek Kursi");
	System.out.print("Pilih : ");
	int pilih = tap.nextInt();
	if(pilih==1)
		insertTix();
	else if(pilih==2)
		cekKursi();
	else if(pilih==3)
		System.exit(0);
	else 
		System.out.println("Pilih Lagi!!!");
	
}
static void insertTix() {
	 String sql;
	 try {
	Scanner type = new Scanner(System.in);
	listFilm();
	String judul = type.nextLine();
	System.out.print("Nama Pembeli : ");
	String buyer = type.nextLine();
	System.out.print("Kode Kursi(A-Z) : ");
	char chair = type.next().charAt(0);
	if (judul.equalsIgnoreCase("naruto")){
	sql = "INSERT INTO naruto(Nama, Kursi) value('%s','%s')";
	sql = String.format(sql, buyer, chair);
	stmt.execute(sql);
	}
	if (judul.equalsIgnoreCase("boboiboy")){
		sql = "INSERT INTO boboiboy(Nama, Kursi) value('%s','%s')";
		sql = String.format(sql, buyer, chair);
		stmt.execute(sql);
	}
	if (judul.equalsIgnoreCase("One Piece")){
		sql = "INSERT INTO One_Piece(Nama, Kursi) value('%s','%s')";
		sql = String.format(sql, buyer, chair);
		stmt.execute(sql);
}
	 }
	 catch (Exception e) {
	 System.out.println("Kursi sudah dipesan, harap memilih yang lain");
	 }
}
static void cekKursi() {
	Scanner net = new Scanner(System.in);
	listFilm();
	String choose = net.nextLine();
	if(choose.equalsIgnoreCase("naruto"))
		getNaruto();
	else if(choose.equalsIgnoreCase("Boboiboy"))
		getBoboiboy();
	else if(choose.equalsIgnoreCase("One Piece"))
		getOneP();
	
}
static void listFilm(){
	System.out.println("Daftar Film");
	System.out.println("1. Naruto");
	System.out.println("2. Boboiboy");
	System.out.println("3. One Piece");
	System.out.print("Tulis Judul : ");
}


static void getNaruto() {
	String sql = "Select * from naruto";
	try {
	rs = stmt.executeQuery(sql);
	while (rs.next()) {
		
		String nameNar = rs.getString("Nama");
		String kursiNar = rs.getString("Kursi");
		System.out.println(String.format("%s -> %s",nameNar, kursiNar)); 
		
	}
	
} catch (Exception e) {
	e.printStackTrace();
}
}
	static void getBoboiboy() {
		String sql = "Select * from boboiboy";
		try {
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			
			String nameBoy = rs.getString("Nama");
			String kursiBoy = rs.getString("Kursi");
			System.out.println(String.format("%s -> %s",nameBoy, kursiBoy)); 
			
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	static void getOneP() {
		String sql = "SELECT * FROM one_piece";
		try {
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			
			String nameOP = rs.getString("Nama");
			String kursiOP = rs.getString("Kursi");
			System.out.println(String.format("%s -> %s",nameOP, kursiOP)); 
			
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}