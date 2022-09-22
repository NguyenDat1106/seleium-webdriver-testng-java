package javatestter;

public class Topic_01_And_Or {

	public static void main(String[] args) {
		// Có 2 điều kiện 
		// Kết hợp and hoặc or giữa 2 điều kiện này 
		// Ra kết quả (True/ False)
		boolean firstCondition;
		boolean secondCondition;
		
		// AND: Nếu 1 trong 2 điều kiện mà sai = sai
		// Chỉ khi cả 2 đều đúng = đúng 
		// ĐK 1 =   TRUE   FALSE   TRUE   FALSE
		// ĐK 2 =   FALSE  TRUE    TRUE   FALSE
		// Result = FALSE  FALSE   TRUE   FALSE
		
		firstCondition = true;
		secondCondition = false;
		System.out.println(firstCondition && secondCondition);
		
		firstCondition = false;
		secondCondition = true;
		System.out.println(firstCondition && secondCondition);
		
		firstCondition = false;
		secondCondition = false;
		System.out.println(firstCondition && secondCondition);
		
		firstCondition = true;
		secondCondition = true;
		System.out.println(firstCondition && secondCondition);
	}

}
