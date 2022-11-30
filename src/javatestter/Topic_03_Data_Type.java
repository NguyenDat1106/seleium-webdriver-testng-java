package javatestter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_03_Data_Type {

	public static void main(String[] args) {
	// Thông tin của một nhân viên 
		//Tên/tuổi/ngày tháng năm sinh/giới tính/Quê quán/ lương
		// ánh xạ các thông tin này vào trong lập trình/ phần nềm
		
		// 2 loại kiểu dữ liệu 
		
		// I  kiểu dữ liệu nguyên thủy (Int/ long/ double/ (float)/ boolean)
		// 8 loại
		// số nguyên: byte/ short/ int/ long (số mà không có phần thập phân)
		
		byte bNumber = 5;
		
		
		short sNumber = 500;
		
		int iNumber = 6000;
	
		long lNumber = 12464781;
		
		// số thực: float/ double
		float salary = 15.5f;
		
		double point = 9.8d;
			
		// kí tự: char
		// dấu nháy đơn''
		// Chứa duy nhất 1 kí tự 
		
		char a = 'a';
		
		
		//Logic: boolean
		boolean marriedStatus = true;
		marriedStatus = false;
		
		
		// II kiểu dữ liệu tham chiếu
		
		// chuỗi: String ( chữ/ số/ kí tự đặc biệt/....)
		// Dấu nháy đôi
		String emailInvalid ="asff@234.Gmail.com";
		
		
		// Class/ Interface (Date Time)
		

		
		WebDriver driver = new FirefoxDriver();
		
		
		
		// Đối tượng; object
		
		Object Students; 
		
		// Array/  ( Khai báo số lượng dữ liệu trước )- số lượng cố định 
		
		int nembers [] = {15,30,45};
		
		String addresses[] = { "Sai Gon", "Ha Noi", "Da Nang"};
		//List/ Set/ Queue (collection)- động 
		List<Integer> studentsNumber = new ArrayList<Integer>();
		List<String> studentAddress = new ArrayList<String>();
		Set<String> studentCity = new LinkedHashSet<>();
	}

}
