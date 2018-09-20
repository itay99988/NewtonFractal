import java.util.function.Function;

//Main class code
public class Main {
	//image fractal resolution
    private static int imageWidth = 1920, imageHeight = 1080;
    //complex coordinate system boundaries
    private static double xMin = -6.4, yMin = -3.6;
    private static ComplexNumber[] zeros = null;
    private static Function<ComplexNumber, ComplexNumber> function, preDerivative, derivative;

	public static void main(String[] args) {
		//stride size definition - the distance between two pixels in coordinate system terms
		double stride = 1.0/150;
		// loop through all the pixels 
		Pixel[][] pic = new Pixel[imageHeight][imageWidth];
		for(int i=0;i<imageHeight;i++) {
			for(int j=0;j<imageWidth;j++) {
				ComplexNumber c = new ComplexNumber(xMin+j*stride,yMin+i*stride);
				int k = (applyNewtonMethod(c.getReal(),c.getImaginary()))[0];
				int num = (applyNewtonMethod(c.getReal(),c.getImaginary()))[1];
				pic[i][j] = getColor(num,k);
			}
		}
		//output file path
		ImageLib.saveImage(pic, "C:\\output\\itay.png");
	}
	
	private static int[] applyNewtonMethod(double x, double y) {
		// epsilon value for convergence
		double h = 1E-8;
        function = z -> z.pow(3).subtract(1, 0);
        preDerivative = function.compose((ComplexNumber c) -> c.add(h, 0));
        derivative = c -> preDerivative.apply(c).subtract(function.apply(c)).divide(h);
        // the already known roots of the equation
        zeros = new ComplexNumber[] {new ComplexNumber(1, 0),
                                     new ComplexNumber(-.5, Math.sqrt(3)/2),
                                     new ComplexNumber(-.5, -Math.sqrt(3)/2)};
        
        // apply the newton method for a limited number of iterations
        // count how many iterations needed for each initial guess until convergence
        ComplexNumber c = new ComplexNumber(x, y);
        double tolerance = 1E-6;
        int iterations = 1, max = 512;
        while (iterations < max) {
            c = c.subtract(function.apply(c).divide(derivative.apply(c)));
            for (int k = 0; k < zeros.length; k++) {
                ComplexNumber z = zeros[k], difference = c.subtract(z);
                if (Math.abs(difference.getReal()) < tolerance && Math.abs(difference.getImaginary()) < tolerance) {
                	return new int[] {k, iterations};
                }
            }
            iterations++;
        }
        return new int[] {-1,-1};
    }
	
	//color palette definition. the colors gets brigther when it takes less iterations
	//to converge to a result
	//since the main equation has three roots, each fundamental color represents 
	//other root.
	private static Pixel getColor(int num, int k) {
        if(k==0) {
        	switch (num) {
            case 1: return new Pixel(244,194,194);
            case 2: return new Pixel(255,92,92);
            case 3: return new Pixel(255,8,0);
            case 4: return new Pixel(255,0,0);
            case 5: return new Pixel(227,66,52);
            case 6: return new Pixel(204,0,0);
            case 7: return new Pixel(179,27,27);
            case 8: return new Pixel(128,0,0);
            case 9: return new Pixel(112,28,28);
            case 10: return new Pixel(237,27,36);
            case 11: return new Pixel(237,27,36);
            case 12: return new Pixel(237,27,36);
            case 13: return new Pixel(237,27,36);
            case 14: return new Pixel(237,27,36);
            case 15: return new Pixel(237,27,36);
            default: return new Pixel(0,0,0);
        	}
        }
        if(k==1) {
        	switch (num) {
            case 1: return new Pixel(178,236,93);
            case 2: return new Pixel(124,252,0);
            case 3: return new Pixel(102,255,0);
            case 4: return new Pixel(119,221,119);
            case 5: return new Pixel(147,197,114);
            case 6: return new Pixel(133,187,101);
            case 7: return new Pixel(135,169,107);
            case 8: return new Pixel(0,128,0);
            case 9: return new Pixel(85,107,47);
            case 10: return new Pixel(85,107,47);
            case 11: return new Pixel(85,107,47);
            case 12: return new Pixel(85,107,47);
            case 13: return new Pixel(85,107,47);
            case 14: return new Pixel(85,107,47);
            case 15: return new Pixel(85,107,47);
            default: return new Pixel(0,0,0);
        	}
        }
        if(k==2) {
        	switch (num) {
            case 1: return new Pixel(204,204,255);
            case 2: return new Pixel(196,195,208);
            case 3: return new Pixel(146,161,207);
            case 4: return new Pixel(140,146,172);
            case 5: return new Pixel(42,82,190);
            case 6: return new Pixel(0,47,167);
            case 7: return new Pixel(0,51,153);
            case 8: return new Pixel(0,0,156);
            case 9: return new Pixel(0,0,139);
            case 10: return new Pixel(0,35,102);
            case 11: return new Pixel(0,35,102);
            case 12: return new Pixel(0,35,102);
            case 13: return new Pixel(0,35,102);
            case 14: return new Pixel(0,35,102);
            case 15: return new Pixel(0,35,102);
            default: return new Pixel(0,0,0);
        	}
        }
        
        return new Pixel(0,0,0);
    }
}
