package nu.annat.andchart.utils;

public class Insets {
    public int left;
    public int top;
    public int right;
    public int bottom;

    public Insets() {
    }

    public Insets(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public Insets addLeft(int value) {
        left += value;
        return this;
    }

    public Insets addTop(int value) {
        top += value;
        return this;
    }

    public Insets addRight(int value) {
        right += value;
        return this;
    }

    public Insets addBottom(int value) {
        bottom += value;
        return this;
    }
}
