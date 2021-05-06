import java.awt.Point;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class TestPointListTok {

	ArrayPointList points;
	StreamTokenizer streamTokenizer;
	InputStreamReader reader;

	public TestPointListTok() {
		points = new ArrayPointList();
		reader = new InputStreamReader(System.in);
		streamTokenizer = new StreamTokenizer(reader);
	}

	public static void main(String[] args) throws IOException {
		TestPointListTok cl = new TestPointListTok();
		cl.parseCommand();
	}

	public void parseCommand() throws IOException {
		int t = streamTokenizer.nextToken();
		while (t != StreamTokenizer.TT_EOF) {

			if (!isValidCommand()) {
				t = streamTokenizer.nextToken();
				continue;
			}

			switch (streamTokenizer.sval) {
			case "add":
				System.out.println(createAndAddNewPoint());
				break;

			case "curr":
				printCurr();
				break;

			case "next":
				System.out.println(points.goToNext());
				break;

			case "prev":
				System.out.println(points.goToPrior());
				break;

			case "start":
				System.out.println(points.goToBeginning());
				break;

			case "end":
				System.out.println(points.goToEnd());
				break;

			case "empty":
				System.out.println(points.isEmpty());
				break;

			case "full":
				System.out.println(points.isFull());
				break;

			case "clear":
				points.clear();
				break;

			case "quit":
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

	private void printCurr() {
		Point temp = points.getCursor();
		if (points.getCursor() == null) {
			System.out.println("list is empty");
		} else {
			System.out.println("(" + temp.getX() + ", " + temp.getY() + ")");
		}
	}

	private boolean isValidCommand() throws IOException {
		if (streamTokenizer.ttype != StreamTokenizer.TT_WORD) {
			System.out.println("ilegal command");
			clearStream();
			return false;
		}
		return true;
	}

	private boolean createAndAddNewPoint() throws IOException {
		int x = 0;
		int y = 0;
		streamTokenizer.nextToken();
		if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
			x = (int) streamTokenizer.nval;
		} else {
			clearStream();
			return false;
		}
		streamTokenizer.nextToken();
		if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
			y = (int) streamTokenizer.nval;
		} else {
			clearStream();
			return false;
		}
		Point point = new Point(x, y);
		points.append(point);
		return true;
	}

	private void clearStream() throws IOException {
		int data = reader.read();
		while (data != '\n') {
			data = reader.read();
		}
	}
}
