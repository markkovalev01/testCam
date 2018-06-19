public class Distance {
    double diag, angle, lenght, focus, r;

    public Distance() {
    }

    public Distance(double diag, double angle, double lenght) {
        this.diag = diag;
        this.lenght = lenght;
        this.angle = 0.017 * angle;
        focus = 2 * Math.tan(angle / 2) / diag;
    }

    public Distance(double height, double width, double angle, double lenght) {
        this.diag = Math.sqrt(height * height + width * width);
        this.lenght = lenght;
        this.angle = 0.017 * angle;
    }

    public double calcDistance(double xL, double xR) {
        double d = xL - xR;
        r = lenght * focus / d;
        return r;
    }

}
