package main.java.edu.csu2017sp314.DTR27.tripco.View;

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
		this.id = id.replaceAll("\\s","");
		this.displayedText = displayedText;
		this.x = x;
		this.y = y;
		this.textAnchor = textAnchor;
		this.fontSize = fontSize;
	}
}
