package tp2;

public abstract class D {
	String un(int n) {
		return "D un " + Integer.toString(n);
	}
	abstract String un(char x);
    abstract String un(String s);
    String deux() {
        return "D deux";
    }
}
