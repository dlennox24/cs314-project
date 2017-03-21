package View;

class Stroke {
	String id;
	int strokeWidth;
	int x1;
	int y1;
	int x2;
	int y2;
	String color;

	Stroke(
			String id,
			int strokeWidth,
			int x1,
			int y1,
			int x2,
			int y2,
			String color
			){
		this.id = id;
		this.strokeWidth = strokeWidth;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}
}
