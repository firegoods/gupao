package register;

/**
 * Created by liushaoshuai on 2018/3/12.
 */
public enum  Color {

    RED(255,0,0),BLACK(0,0,0),WHITE(255,255,255);
    private int red;
    private int green;
    private int black;

    Color(int red, int green, int black) {
        this.red = red;
        this.green = green;
        this.black = black;
    }

    @Override
    public String toString() {
        return "red: "+this.red+",green: "+this.green+"black"+this.black;
   }

}
