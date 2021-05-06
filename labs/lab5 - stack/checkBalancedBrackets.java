
public class checkBalancedBrackets {

	private char[] opening = { '(', '[', '{' };
	private char[] closing = { ')', ']', '}' };
	StackArray<Character> stack;

	public checkBalancedBrackets() {
		stack = new StackArray<Character>(30);
	}

	public static void main(String[] args) {
		
	    if(args.length == 0)
	    {
	        System.out.println("please pass a command line argument");
	        System.exit(0);
	    }
		System.out.println(args[0]);
		checkBalancedBrackets check = new checkBalancedBrackets();
		System.out.println(check.checkBrackets(args[0]));
	}

	public boolean checkBrackets(String s) {

		for (int i = 0; i < s.length(); i++) {
			char currentChar = s.charAt(i);

			for (int j = 0; j < opening.length; j++) {
				if (currentChar == opening[j]) {
					stack.push(currentChar);
					break;
				}
			}

			for (int w = 0; w < closing.length; w++) {
				if (currentChar == closing[w]) {

					Character b = stack.pop();

					if (b == null) {
						return false;
					}
					if (b != opening[w]) {
						return false;
					}
					break;
				}
			}
		}

		if (stack.isEmpty()) {
			return true;
		}

		return false;

	}

}
