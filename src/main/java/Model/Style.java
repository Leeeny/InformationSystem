package Model;

import java.io.Serializable;

public class Style implements Serializable {
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