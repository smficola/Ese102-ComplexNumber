package complexnumber;

public class ComplexNumber {
		
	static private double initRe;
	static private double initIm;
	static private StringFormat initFormat;
	
	/**
	 * Sets the initial value for any new subsequent instance and the string format to RECTANGULAR.
	 * @param re
	 * @param im
	 */
	static public void setInitRectangular(double re, double im) {
		initRe = re;
		initIm = im;
		initFormat = StringFormat.RECTANGULAR;
	}
	
	/**
	 * Normalizes the angular value in [0; 2*pi[ radians.
	 * @param a
	 * @return
	 */
	private static double normalize(double a) {
		a += (a<0)?2*Math.PI:0;
		a = a % (2*Math.PI);
		return a;
	}
	
	/**
	 * Sets the initial value for any new subsequent instance and the string format to POLAR.
	 * @param modulus must be greater or equal to 0
	 * @param argument in radians
	 */
	static public void setInitPolar(double modulus, double argument) {
		if(modulus < 0) {
			throw new IllegalArgumentException("Modulus must be greater or equal to 0");
		}
		argument = normalize(argument);
		initRe = modulus * Math.cos(argument);
		initIm = modulus * Math.sin(argument);
		initFormat = StringFormat.POLAR;
	}
	
	/**
	 * Addition of two complex numbers.
	 * @param addend1
	 * @param addend2
	 * @return
	 */
	static public ComplexNumber add(ComplexNumber addend1, ComplexNumber addend2) {
		ComplexNumber r = new ComplexNumber();
		r.setRectangular(addend1.getRe() + addend2.getRe(), addend1.getIm() + addend2.getIm());
		return r;
	}
	
	/**
	 * Subtraction of two complex numbers.
	 * @param minuend
	 * @param subtrahend
	 * @return
	 */
	static public ComplexNumber sub(ComplexNumber minuend, ComplexNumber subtrahend) {
		ComplexNumber r = new ComplexNumber();
		r.setRectangular(minuend.getRe() - subtrahend.getRe(), minuend.getIm() - subtrahend.getIm());
		return r;
	}
	
	/**
	 * Multiplication of two complex numbers.
	 * @param factor1
	 * @param factor2
	 * @return
	 */
	static public ComplexNumber multiply(ComplexNumber factor1, ComplexNumber factor2) {
		ComplexNumber r = new ComplexNumber();
		double resultArgument = factor1.getArgument() + factor2.getArgument();
		r.setPolar(factor1.getModulus() * factor2.getModulus(), normalize(resultArgument));
		return r;
	}
	
	/**
	 * Division of two complex numbers.
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	static public ComplexNumber divide(ComplexNumber dividend, ComplexNumber divisor) {
		ComplexNumber r = new ComplexNumber();
		double resultArgument = dividend.getArgument() + divisor.getArgument();
		r.setPolar(dividend.getModulus() * divisor.getModulus(), normalize(resultArgument));
		return r;
	}
	
	
	/**
	 * The format to get the string representation of the instances.
	 *
	 */
	static public enum StringFormat {
		RECTANGULAR,
		POLAR
	}
	
	private double re;
	private double im;
	private StringFormat format;
	
	/**
	 * Initializes the instance to the values set by the init class methods.
	 */
	public ComplexNumber() {
		this.re = ComplexNumber.initRe;
		this.im = ComplexNumber.initIm;
		this.format = ComplexNumber.initFormat;
	}
	
	/**
	 * Sets the complex number by providing rectangular values.
	 * @param re
	 * @param im
	 */
	public void setRectangular(double re, double im) {
		this.re = re;
		this.im = im;
	}
	
	/**
	 * Sets the complex number by providing polar values.
	 * @param modulus
	 * @param argument
	 */
	public void setPolar(double modulus, double argument) {
		if(modulus < 0) {
			throw new IllegalArgumentException("Modulus must be greater or equal to 0");
		}
		this.re = modulus * Math.cos(argument);
		this.im = modulus * Math.sin(argument);
	}
	
	/**
	 * Sets the format for the string representation of this instance.
	 * @param format
	 */
	public void setStringFormat(StringFormat format) {
		this.format = format;
	}
	
	/**
	 * Gets the real part of the rectangular representation of complex number.
	 * @return
	 */
	public double getRe() {
		return this.re;
	}
	
	/**
	 * Gets the imaginary part of the rectangular representation of the complex number.
	 * @return
	 */
	public double getIm() {
		return this.im;
	}
	
	/**
	 * Gets the modulus of the polar representation of the complex number.
	 * @return
	 */
	public double getModulus() {
		return Math.sqrt(Math.pow(this.re, 2) + Math.pow(this.im, 2));
	}
	
	/**
	 * Gets the argument (also known as phase) of the polar representation of the complex number.
	 * @return the value normalized in [0; 2*pi[ randiants.
	 */
	public double getArgument() {
		double r=0;
		if(this.re == 0) {
			r = Math.PI/2;
			r+=(this.im<0)?Math.PI:0;
		}
		else {
			r = Math.atan(this.re/this.im);
			r+=(this.re<0)?Math.PI:0;
		}
		
		return normalize(r);
	}
	
	/**
	 * Addition to the instance value.
	 * @param addend
	 * @return a new instance with the sum.
	 */
	public ComplexNumber add(ComplexNumber addend) {
		ComplexNumber r = new ComplexNumber();
		r.setRectangular(this.re + addend.getRe(), this.im + addend.getIm());
		return r;
	}
	
	/**
	 * Subtracs from the instance value.
	 * @param subtrahend
	 * @return a new instance with the difference
	 */
	public ComplexNumber sub(ComplexNumber subtrahend) {
		ComplexNumber r = new ComplexNumber();
		r.setRectangular(this.re - subtrahend.getRe(), this.im - subtrahend.getIm());
		return r;
	}
	
	/**
	 * Multiplication with the instance value.
	 * @param factor
	 * @return a new instance with the product
	 */
	public ComplexNumber multiply(ComplexNumber factor) {
		ComplexNumber r = new ComplexNumber();
		double resultArgument = normalize(this.getArgument() + factor.getArgument());
		r.setPolar(this.getModulus() * factor.getModulus(), resultArgument);
		return r;
	}
	
	/**
	 * Division of the instance value.
	 * @param divisor
	 * @return a new instance with the result of the division
	 */
	public ComplexNumber divide(ComplexNumber divisor) {
		ComplexNumber r = new ComplexNumber();
		double resultArgument = normalize(this.getArgument() - divisor.getArgument());
		r.setPolar(this.getModulus() / divisor.getModulus(), resultArgument);
		return r;
	}
	
	/**
	 * Returns the complex conjugate of the instance.
	 * @return a new instance with the conjugate
	 */
	public ComplexNumber getConjugate() {
		ComplexNumber r = new ComplexNumber();
		r.setRectangular(this.re, -this.im);
		return r;
	}
	
	/**
	 * Gets the string representation of the instance accoding to its string format.
	 * See also the method {@link #setStringFormat(StringFormat)}
	 */
	@Override
	public String toString() {
		/* L'annotazione (annotation) Override non è indispensabile, ma estremamente utile.
		 * Essa comunica al compilatore che il metodo corrispondente è inteso essere
		 * una ridefinizione di un metodo della classe base. In questo modo, eventuali errori
		 * nel nome e/o nei parametri sarebbero prontamente segnalati.
		 */
		return toString(this.format);
	}
	
	/**
	 * Returns the string representation of the object, according to the specified format.
	 * @param format
	 * @return
	 */
	public String toString(StringFormat format) {
		/*
		 * Questo metodo non è la ridefinizione del metodo Object.toString(), poiché
		 * quest'ultimo non ha alcun parametro. Si tratta, quindi, di un metodo differente,
		 * in eventuale sovraccarico del metodo toString() ridefinito.
		 */
		String r = new String();
		switch(format) {
		case RECTANGULAR:
			r = this.re + "+(" + this.im + ")i";
			break;
		case POLAR:
			r = this.getModulus() + "*exp(i*" + this.getArgument() +")";
			break;
		default:
			throw new UnsupportedOperationException();
		}
		return r;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean r = false;
		if(o instanceof ComplexNumber) {
			ComplexNumber p = (ComplexNumber)o;
			if(this.re == p.getRe() && this.im == p.getIm()) {
				r = true;
			}
		}
		return r;
	}
	
	@Override
	public int hashCode() {
		return (int)this.re * 1000 + (int)this.im * 1000;
	}
}
