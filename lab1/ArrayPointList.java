import java.awt.Point;

public class ArrayPointList implements PointList {
	private Point[] points;
	private int size;
	private int cursor;
	
	public ArrayPointList() {
		this(MAX_SIZE);
	}

	public ArrayPointList(int maxSize) {
		points = new Point[maxSize];
		size = 0;
		cursor = -1;
	}
	
	@Override
	public void append(Point newPoint) {
        if(!isFull()) {
        	points[size] = newPoint;
        	cursor = size;
        	size++;
        }else {
        	return;
        }
        //TODO exception
	}

	@Override
	public void clear() {
        size = 0;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		if(size == points.length) {
			return true;
		}
		return false;
	}

	@Override
	public boolean goToBeginning() {
		if(isEmpty()) {
			return false;
		}
		cursor = 0;
		return true;
	}

	@Override
	public boolean goToEnd() {
		if(isEmpty()) {
			return false;
		}
		cursor = size-1;
		return true;
	}

	@Override
	public boolean goToNext() {
		if(cursor == size-1) {
			return false;
		}
		cursor++;
		return true;
	}

	@Override
	public boolean goToPrior() {
		if(cursor <=  0) {
			return false;
		}
		cursor--;
		return true;
	}

	@Override
	public Point getCursor() {
		if(size == 0) {
			return null;
		}
		return new Point(points[cursor]);
	}

	@Override
	public String toString() {
		String pointString = "";
		for(Point point : points) {
			pointString += "(" + point.getX() + ", " + point.getY() + ") ";
		}
		return pointString;
	}

}
