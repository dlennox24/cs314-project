package View;

class Label {
	String fontFamily = "Sans-serif";
	int fontSize;
	String textAnchor;
	String id;
	int x;
	int y;
	String displayedText;

	Label(String id,
	String displayedText,
	int x,
	int y,
	String textAnchor,
	int fontSize
	){
		this.id = id.trim();
		this.displayedText = displayedText;
		this.x = x;
		this.y = y;
		this.textAnchor = textAnchor;
		this.fontSize = fontSize;
	}
}
