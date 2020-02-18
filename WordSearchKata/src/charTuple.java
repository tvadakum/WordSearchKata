//class for storing a letter and it's coordinates in the word search. 
//to be used in an array that will be mapped to a word in the main program
public class charTuple {
	private char letter;
	private int x;
	private int y;
	public charTuple() {
		letter = ' ';
		x = 0;
		y = 0;
	}
	public charTuple(char let, int xco, int yco) {
		letter = let;
		x = xco;
		y = yco;
	}
	//setter/getter methods
	public int getX() {return x;}
	public void setX(int xco) {x = xco;}
	public int getY() {return y;}
	public void setY(int yco) {y = yco;}
	public char getLetter() {return letter;}
	public void setLetter(char let) {letter = let;}
}
