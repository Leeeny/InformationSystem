public class Style {
    private String nameOfStyle;

    public Style(String nameOfStyle) {
        this.nameOfStyle = nameOfStyle;
    }

    @Override
    public String toString() {
        return "[" + nameOfStyle + "]";
    }

}