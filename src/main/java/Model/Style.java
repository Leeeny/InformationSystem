package Model;

public class Style {
    private String nameOfStyle;

    public Style(String nameOfStyle) {
        this.nameOfStyle = nameOfStyle;
    }

    public String getNameOfStyle() {
        return nameOfStyle;
    }

    public void setNameOfStyle(String nameOfStyle) {
        this.nameOfStyle = nameOfStyle;
    }

    @Override
    public String toString() {
        return "[" + nameOfStyle + "]";
    }

}