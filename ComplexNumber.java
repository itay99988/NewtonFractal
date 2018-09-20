//This class is in charge of the complex number representation and related functions 
public class ComplexNumber {

	double real, imaginary;
	//constructor 
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    
    //adding one complex number to another - separated presentation
    public ComplexNumber add(double real, double imaginary) {
        return new ComplexNumber(this.real + real, this.imaginary + imaginary);
    }

    //adding one complex number to another
    public ComplexNumber add(ComplexNumber c) {
        return new ComplexNumber(this.real + c.real, this.imaginary + c.imaginary);
    }

    //subtract one complex number from another - separated presentation
    public ComplexNumber subtract(double real, double imaginary) {
        return new ComplexNumber(this.real - real, this.imaginary - imaginary);
    }

    //subtract one complex number from another
    public ComplexNumber subtract(ComplexNumber c) {
        return new ComplexNumber(this.real - c.real, this.imaginary - c.imaginary);
    }
    
    //scalar multiplication
    public ComplexNumber multiply(double scalar) {
        return new ComplexNumber(real * scalar, imaginary * scalar);
    }

    //multiplication with another complex number
    public ComplexNumber multiply(ComplexNumber c) {
        return new ComplexNumber(real * c.real - imaginary * c.imaginary, real * c.imaginary + imaginary * c.real);
    }

    //division with scalar
    public ComplexNumber divide(double scalar) {
        return multiply(1.0 / scalar);
    }

    //division with another complex number
    public ComplexNumber divide(ComplexNumber c) {
        return multiply(c.getConjugate()).multiply(1.0 / (c.real * c.real + c.imaginary * c.imaginary));
    }

    //calculate the conjugate number
    public ComplexNumber getConjugate() {
        return new ComplexNumber(real, imaginary * -1);
    }

    //raise the complex number to power
    public ComplexNumber pow(int exp) {
        ComplexNumber c = new ComplexNumber(real, imaginary);
        for (int k = 1; k < exp; k++) {
            c = multiply(c);
        }
        return c;
    }

    public ComplexNumber exp() {
        return new ComplexNumber(Math.exp(real) * Math.cos(imaginary), Math.exp(real) * Math.sin(imaginary));
    }

    public static ComplexNumber exp(ComplexNumber c) {
        return c.exp();
    }

    //return the cosine of the complex number
    public ComplexNumber cos() {
        return exp(multiply(new ComplexNumber(0, 1))).add(exp(multiply(new ComplexNumber(0, -1)))).divide(2);
    }
    
    //return the cosine of the other complex number
    public static ComplexNumber cos(ComplexNumber c) {
        return c.cos();
    }

    //return the sinus of the complex number
    public ComplexNumber sin() {
        return exp(multiply(new ComplexNumber(0, 1))).subtract(exp(multiply(new ComplexNumber(0, -1)))).divide(new ComplexNumber(0, 2));
    }
    
    //return the sinus of the other complex number
    public static ComplexNumber sin(ComplexNumber c) {
        return c.sin();
    }

    //return the tangent of the complex number
    public ComplexNumber tan() {
        return sin().divide(cos());
    }
    
    //return the tangent of the other complex number
    public static ComplexNumber tan(ComplexNumber c) {
        return c.sin().divide(c.cos());
    }
    
    //return the complex number's real part only
    public double getReal() {
        return real;
    }

    //return the number's imaginary part only
    public double getImaginary() {
        return imaginary;
    }

    @Override
    public String toString() {
        return "" + real + (imaginary >= 0 ? "+" : "") + imaginary + "i";
    }
}
