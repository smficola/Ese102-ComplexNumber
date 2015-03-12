package ese102;

import complexnumber.*;

public class Ese102 {

	public static void main(String[] args) {
		ComplexNumber.setInitRectangular(0, 0);
		ComplexNumber c1 = new ComplexNumber();
		ComplexNumber.setInitRectangular(1, 1);
		ComplexNumber c2 = new ComplexNumber();
		System.out.println("c1: " + c1);
		System.out.println("c2: " + c2);
		System.out.println("Addiction: " + c1.add(c2));
		ComplexNumber r = c1.add(c2);
		r.setStringFormat(ComplexNumber.StringFormat.POLAR);
		System.out.println("Addiction (polar): " + r);
		System.out.println("Subtraction: " + c1.sub(c2));
		/* Domanda: come posso controllare il formato della
		 * rappresentazione a stringa del risultato dell'operazione, senza dover assegnare
		 * il risultato ad una variabile?
		 */
		System.out.println("Subtraction (polar): " + c1.sub(c2));
		
		//----- Uguaglianza tra oggetti
		ComplexNumber numeri[] = new ComplexNumber[4];
		ComplexNumber.setInitRectangular(1, -2);
		numeri[0] = new ComplexNumber();
		numeri[1] = new ComplexNumber();
		numeri[2] = numeri[1];
		numeri[3] = new ComplexNumber();
		numeri[3].setRectangular(-3, 2);
		int i=0;
		for(i=1; i<numeri.length; i++) {
			if(numeri[i-1] == numeri[i]) {
				System.out.println("numeri[" + (i-1) + "] e numeri[" + i + "] si riferiscono alla medesima istanza");
			}
			else {
				System.out.println("numeri[" + (i-1) + "] e numeri[" + i + "] si riferiscono a due istanze differenti");
			}
		
			if(numeri[i-1].equals(numeri[i])) {
				System.out.println("numeri[" + (i-1) + "] e numeri[" + i + "] contengono il medesimo numero complesso");
			}
			else {
				System.out.println("numeri[" + (i-1) + "] e numeri[" + i + "] contengono numeri complessi diversi");
			}
		}
	}

}
