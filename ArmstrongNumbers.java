public class ArmstrongNumbers {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		printArray(giveArmstrongNumbers(n));
	}

	public static boolean isArmstrongNumber(int number) {
		String numberString = Integer.toString(number);
		int [] numberArray = new int[numberString.length()];
		int sum = 0;
		int temp = number;
		boolean output = false;

		for(int i = 0; i < numberArray.length; i++){
			numberArray[i] = temp % 10;
			temp = temp/10;
		}
		
		for(int i = 0; i < numberArray.length; i++){
			sum = (int) sum + (int) Math.pow(numberArray[i], numberArray.length);
		}
		
		if(sum == number){
			output = true;
		}

		return output;
	}

	public static int[] giveArmstrongNumbers(int n) {
		int[] output = new int[n];
		int i = 0;
		int count = 0;
	
		do{
			if(isArmstrongNumber(i)){
				output[count] = i;
				count++;
				i++;
			} else {
				i++;
			}
		}while(count < n);
		
		return output;
	}

	private static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
			System.out.print(i < a.length - 1 ? ", " : "\n");
		}
	}
}