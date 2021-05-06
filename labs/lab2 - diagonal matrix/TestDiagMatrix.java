import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class TestDiagMatrix {
	
	StreamTokenizer streamTokenizer;
	InputStreamReader reader;
	DiagonalMatrix mat;
	
	public TestDiagMatrix() {
		reader = new InputStreamReader(System.in);
		streamTokenizer = new StreamTokenizer(reader);
		mat = new DiagonalMatrix(5);
	}
	

	public static void main(String[] args) throws IOException {
		TestDiagMatrix matrixTest = new TestDiagMatrix();
		matrixTest.parseCommand();
	}
	
	
	
	public void parseCommand() throws IOException {
		int t = streamTokenizer.nextToken();
		while (t != StreamTokenizer.TT_EOF) {

			if (!isValidCommand()) {
				t = streamTokenizer.nextToken();
				continue;
			}

			switch (streamTokenizer.sval) {
			case "Put":
				System.out.println(this.putEntry());
				break;

			case "Get":
				System.out.println(this.getEntry());
				break;

			case "PrintM":
				System.out.println(mat.toString());
				break;

			case "MultCons":
				System.out.println(this.mult());
				break;

			case "TransM":
				mat.transpose();
				break;
				
			case "Quit":
				System.exit(0);
				break;

			default:
				System.out.println("ERROR! cant find matching command:" + streamTokenizer.sval);
				clearStream();
				break;
			}
			t = streamTokenizer.nextToken();
		}
	}
	
	
	private boolean putEntry() throws IOException {
		int i = 0;
		int j = 0;
		int x = 0;
		
		streamTokenizer.nextToken();
		if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
			i = (int) streamTokenizer.nval;
		} else {
			clearStream();
			return false;
		}
		streamTokenizer.nextToken();
		if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
			j = (int) streamTokenizer.nval;
		} else {
			clearStream();
			return false;
		}
		streamTokenizer.nextToken();
		if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
			x = (int) streamTokenizer.nval;
		} else {
			clearStream();
			return false;
		}
		
		mat.put(i, j, x);
		return true;
	}
	
	
	private boolean getEntry() throws IOException {
		int i = 0;
		int j = 0;		
		streamTokenizer.nextToken();
		if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
			i = (int) streamTokenizer.nval;
		} else {
			clearStream();
			return false;
		}
		streamTokenizer.nextToken();
		if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
			j = (int) streamTokenizer.nval;
		} else {
			clearStream();
			return false;
		}
		double val = mat.get(i, j);
		if(val == -1) {
			return false;
		}
		System.out.println(val);
		return true;
	}
	
	private boolean mult() throws IOException {
		int c = 0;
		streamTokenizer.nextToken();
		if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
			c = (int) streamTokenizer.nval;
		} else {
			clearStream();
			return false;
		}
		mat.multByConstant(c);
		return true;
	}
	
	private boolean isValidCommand() throws IOException {
		if (streamTokenizer.ttype != StreamTokenizer.TT_WORD) {
			System.out.println("invalid command");
			clearStream();
			return false;
		}
		return true;
	}
	
	private void clearStream() throws IOException {
		int data = reader.read();
		while (data != '\n') {
			data = reader.read();
		}
	}

}
