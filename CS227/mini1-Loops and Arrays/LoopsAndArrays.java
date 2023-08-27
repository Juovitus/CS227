package mini1;

public class LoopsAndArrays {
	
	public static int countMatches(String s, String t) {
		int matches = 0;
		for (int i = 0; i < t.length(); i++) {
			if(i > s.length() - 1) {
				break;
			}
			if (s.charAt(i) == t.charAt(i)) {
				matches++;
			}
		}
		return matches;
	}

	public static int numFirstChar(String s) {
		if (s.length() == 0) {
			return 0;
		}
		int repeats = 0;
		char firstChar = s.charAt(0);
		for (int i = 0; i < s.length(); i++) {
			if (firstChar == s.charAt(i)) {
				repeats++;
			}
		}
		return repeats;
	}

	public static int countSubstringsWithOverlap(String t, String s) {
		int overlap = 0;
		for(int i = 0; i < s.length(); i++) {
			if(t.charAt(0) == s.charAt(i)) {
				if(i+t.length()>s.length()) {
					break;
				}
				String subOfT = s.substring(i, i + t.length());
				if(subOfT.equals(t)) {
					overlap++;;
				}
			}
		}
		return overlap;
	}
	
	public static String arrayToString(int[] array) {
		String s = "";
		for(int i = array.length -1; i > -1; i--) {
			s = s + array[i];
		}
		return s;
	}
	
	public static boolean isArithmetic(int[] array) {
		if(array.length>1) {
			int difference = array[0] - array[1];
			for(int i = 0; i < array.length - 1; i ++) {
				if(array[i] - array[i+1] != difference) {
					return false;
				}
			}
		}
		return true;
	}

	public static int[] collatz(int start, int numIterations) {
		int[] intArray = new int[numIterations + 1];
		intArray[0] = start;
		int num = start;
		for(int i = 1; i < numIterations + 1; i++) {
			if(num % 2 == 0) {
				num = num/2;
			}else {
				num = (3 * num) + 1;
			}
			intArray[i] = num;
		}
		return intArray;
	}

	public static int[] interleaveArray(int[] a, int[] b) {
		int[] interleavedArray = new int[a.length + b.length];
		int n = 0;
		for(int i = 0; i < interleavedArray.length; i++) {
			if(i < a.length) {
				interleavedArray[n++] = a[i];
			}
			if(i < b.length) {
				interleavedArray[n++] = b[i];
			}
		}
		return interleavedArray;
	}
	
	public static boolean isAscending(int[] a) {
		int n = 0;
		for(int i = 0; i < a.length-1; i++) {
			if(a[i] < a[i+1]) {
				n++;
			}
		}
		if(n==a.length-1) {
			return true;
		}
		return false;
	}
}