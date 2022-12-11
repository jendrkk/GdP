public class Bigs {
	
	public static int[] removeLeadingZeroes(int [] input){

		// for optimisation, check if this is even necessary
		if (input[input.length - 1] != 0) return input;

		// checks how many leading Zeroes there are
		int leadingZeroes = 0;
		for (int i = input.length - 1; i >= 0; i--){
			if (input[i] == 0) leadingZeroes++;
		}

		// initiate new output array of proper size and copy everything up to (excluding) the leading Zeroes
		// should probably just call our copy method below? Would require some changes, though. Not worth it
		int [] output = new int [input.length - leadingZeroes];
		for (int i = 0; i < output.length; i++) {
			output[i] = input[i];
		}

		return output;

	}

	// addiert die Ziffernfelder a und b
	public static int[ ] add(int[ ] a, int[ ] b){
        int length;
		boolean aGreater = false;
		
		if(a.length > b.length){
			length = a.length;
			aGreater = true;
		} else {
			length = b.length;
		}

		int[] a_sameLength = new int[length];
		int[] b_sameLength = new int[length];
		
		for(int i = 0; i < length; i++){
			if(i < a.length){
				a_sameLength[i] = a[i];
			} else {
				a_sameLength[i] = 0;
			}
			if(i < b.length){
				b_sameLength[i] = b[i];
			} else {
				b_sameLength[i] = 0;
			}
		}

		int[] output = new int[length];

		if(a_sameLength[length-1] + b_sameLength[length-1] >= 10){
			output = new int[length+1];
			output[output.length-1] = 0;
		}
		if(aGreater){
			if(a_sameLength[b.length-1] + b_sameLength[b.length-1] >= 10){
				output = new int[length+1];
				output[output.length-1] = 0;
		}
		} else {
			if(a_sameLength[a.length-1] + b_sameLength[a.length-1] >= 10){
				output = new int[length+1];
				output[output.length-1] = 0;
			}
		}
		
		for(int i = 0; i < length; i++){
			output[i] = a_sameLength[i] + b_sameLength[i];
		}
		for(int i = 0; i < output.length; i++){
			if(output[i] >= 10){
				output[i+1] = output[i+1] + output[i]/10;
				output[i] = output[i] % 10;
			}
		}
		
		return output;
    }
	
	
	// gibt das Ziffernfeld n in lesbarer dezimaler Form aus
	static void print(int[ ] n){
		for(int i = 0; i < n.length; i++){
			System.out.print(n[n.length-1-i]);
		}
		System.out.println();

		return;
	}
	
	// konstruiert ein einstelliges Ziffernfeld aus d
	static int[ ] digit(int d){
		int[] output = new int[1];
		output[0] = d;

		return output;
	}
 
	// konstruiert das Ziffernfeld, welches den Wert Null repraesentiert
	static int[ ] Null(){
		int[] output = new int[1];
		output[0] = 0;

		return output;
	}
	
	// konstruiert das Ziffernfeld, welches den Wert Eins repraesentiert
	static int[ ] One(){
		int[] output = new int[1];
		output[0] = 1;
		
		return output;
	}

	// Rest des Ziffernfeldes n bei Division durch 10 (eine int-Zahl!)
	static int mod10(int[ ] n){
		int output = n[0];

		return output;
	}

	// ganzzahliger Quotient bei Division durch 10
	static int[ ] div10(int[ ] n){
		int[] output = new int[n.length-1];
		for(int i = 1; i < n.length; i++){
			output[i-1] = n[i];
		}

		return output;
	}

	// Umwandlung einer int-Zahl in ein Ziffernfeld	
	static int[ ] fromInt(int n){ 
		String nString = String.valueOf(n);
		int[] output = new int[nString.length()];

		for(int i = 0; i < output.length; i++){
			output[i] = n % 10;
			n = n / 10;
		}
		return output;
	}

	// kopiert den Wert von a
	static int[ ] copy(int[ ] n){
		int[] output = new int[n.length];

		for(int i = 0; i < n.length; i++){
			output[i] = n[i];
		}

		return output;
	}

	// multipliziert das Ziffernfeld a mit einer int-Zahl
	static int[ ] times(int[ ] n, int d){
		int[] longOutput = new int[n.length+1];
		int[] output = new int[longOutput.length];

		for(int i = 0; i < n.length; i++){
			longOutput[i] = n[i]*d;
		}
		for(int i = 0; i < longOutput.length; i++){
			if(longOutput[i] >= 10){
				longOutput[i+1] = longOutput[i+1] + longOutput[i]/10;
				longOutput[i] = longOutput[i] % 10;
			}
		}
		if(longOutput[longOutput.length-1] == 0){ 
			output = new int[longOutput.length-1];
			for(int i = 0; i < longOutput.length-1; i++){
				output[i] = longOutput[i];
			}
		} else {
			for(int i = 0; i< longOutput.length; i++){
				output[i] = longOutput[i];
			}
		}
		
		return output;
	}

    // multipliziert das Ziffernfeld n mit 10
	static int[ ] times10(int[ ] n){ 
		int[] output = new int[n.length+1];
		for(int i = 0; i < n.length; i++){
			output[i+1] = n[i];
		}
		output[0] = 0;

		return output;
	}
	
	// multipliziert zwei Ziffernfelder	
	static int[ ] times(int[ ]a, int[ ] b){
		int[] longOutput = new int[a.length+b.length];
		int[][] sumMatrix = new int[b.length][a.length+b.length];

		for(int i = 0; i < b.length; i++){
			int[] temp = times(a, b[i]);
			for(int j = 0; j < temp.length; j++){
				sumMatrix[i][j+i] = temp[j];
			}
		}
		for(int i = 0; i < a.length+b.length; i++){
			int sum = 0;
			for(int j = 0; j < b.length; j++){
				sum = sum + sumMatrix[j][i];
			}
			longOutput[i] = sum;
		}
		for(int i = 0; i < longOutput.length; i++){
			if(longOutput[i] >= 10){
				longOutput[i+1] = longOutput[i+1] + longOutput[i]/10;
				longOutput[i] = longOutput[i] % 10;
			}
		}

		int count = longOutput.length;
		while(true){
			if(longOutput[count-1] != 0){
				break;
			}
			count--;
		}

		int[] output = new int[count];

		for(int i = 0; i < output.length; i++){
			output[i] = longOutput[i];
		}
		
		return output;
	}
	
    // Quadratzahl eines Ziffernfeldes
	static int[ ] square(int[ ]a){
		int[] output = times(a, a);

		return output;
	}

    // Kubikzahl eines Ziffernfeldes
	static int[ ] cubic(int[ ]a){
		int[] output = times(square(a), a);

		return output;
	}
	
	// Test auf kleiner-Relation zweier Ziffernfelder: a < b ?
	static boolean less (int[ ] a, int[ ] b) {

		// first check if one is bigger than the other (after removing potential leading zeroes)
		if (removeLeadingZeroes(a).length < removeLeadingZeroes(b).length) return true;

		// make sure they're not equal
		if (equal(a, b)) return false;


		// only if the easy checks above don't return a result, go through each value/position
		// start at the end as that's the highest decimal place
		for (int i = a.length - 1; i >= 0; i--) {
			if (a[i] == b[i]) continue;
			if (a[i] < b[i]) return true;
			if (a[i] > b[i]) return false;
		}

		// IDE complains that it needs to return something. It should never get here, but if it does...
		return false;

	}

	// Test auf Gleichheit zweier Ziffernfelder
	static boolean equal (int[ ] a, int[ ] b){
		boolean output = false;
		int count = 0;
		
		if(a.length == b.length){
			for(int i = 0; i < a.length; i++){
				if(a[a.length-i-1] == b[b.length-i-1]){
					count++;
				}
			}
		}
		if(a.length == b.length && count == a.length){
			output = true;
		}
		return output;
	}

	/*
	Test auf Korrektheit eines Ziffernfeldes: Feld existiert und enthaelt
    mindenstens eine Ziffer, alle Positionen liegen zwischen 0 und 9
    keine fuehrenden Nullen (ausser bei Null selbst) 
	*/
	//static boolean ok (int[ ] n){ /* TODO */ }

    // gibt die (kleinste) Ziffer mit der groessten Haeufigkeit in n aus	
	static void maxDigit(int[] n){
		int[] probArray = new int[10];
		for(int i = 0; i < n.length; i++){
			for(int j = 0; j < 10; j++){
				if(n[i] == j){
					probArray[j]++;
				}
			}
		}
		int[] sortedArray = new int[10];
		for(int i = 0; i < 10; i++){
			sortedArray[i] = probArray[i];
		}
		int temp;

		for(int i = 0; i < 10; i++){  
            for(int j = i + 1; j < 10; j++){  
                if(sortedArray[i] > sortedArray[j]){  
                    temp = sortedArray[i];  
                    sortedArray[i] = sortedArray[j];  
                    sortedArray[j] = temp;  
                }  
            }  
        }
		for(int i = 0; i < 10; i++){
			if(probArray[i] == sortedArray[9]){
				System.out.println(i);
			}
		}
	}


	public static void main (String[ ] s) {
        int[] a = One();
		
		for (int i=0; i<33222; ++i) {
			a = times(a, 2);
		}
		
		System.out.println("2^33222 hat " + a.length + " Stellen");
		print(a); 
		System.out.println(); 

		
		int[] b = fromInt(13);
		int[] c = copy(b);
		
		for (int i=1; i<8978; ++i) {
			c=times(c, b);
		}
		
		System.out.println("13^8978 hat " + c.length + " Stellen");
		print(c); 
		System.out.println(); 
		
		System.out.println(less(a, c)); // beantwortet die Frage aus der Aufgabenueberschrift
                maxDigit(a);
                maxDigit(c);

	}
}