package nu.annat.andchart.utils;

public class Insets {
    public int left;
    public int top;
    public int right;
    public int bottom;

    public Insets() {
        this(0, 0, 0, 0);
    }

    public Insets(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public Insets append(Insets insets) {
        left += insets.left;
        top += insets.top;
        right += insets.right;
        bottom += insets.bottom;
        return this;
    }

    public Insets max(Insets insets) {
        left += Math.max(left, insets.left);
        top += Math.max(top, insets.top);
        right += Math.max(right, insets.right);
        bottom += Math.max(bottom, insets.bottom);
        return this;
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
