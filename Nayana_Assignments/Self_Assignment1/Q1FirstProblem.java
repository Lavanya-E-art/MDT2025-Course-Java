
	/*calculate,
	(a) the volume of the Earth in cubic miles
	(b) the volume of the Sun in cubic miles
	(c) the ratio of the volume of the Sun to the volume of the Earth
	and then output the three values. Treat both the earth and sun as spheres. The volume of a
	sphere is given by the formula 4 pi r^3/3 where r is the radius.
	Run the code you have written, and turn in both your code and the output of the program. The
	output should say something like: The volume of the Earth is X cubic miles, the volume of the
	sun is Y cubic miles, and the ratio of the volume of the Sun to the volume of the Earth is Z.*/

package SelfAssignment_1;
	
public class Q1FirstProblem {

		public static void main(String[] args) {
	        // Diameters in miles
	        double earthDiameter = 7600.0;
	        double sunDiameter = 865000.0;

	        // Radii
	        double earthRadius = earthDiameter / 2.0;
	        double sunRadius = sunDiameter / 2.0;

	        // Volumes using formula (4/3) * π * r^3
	        double earthVolume = (4.0 / 3.0) * Math.PI * Math.pow(earthRadius, 3);
	        double sunVolume = (4.0 / 3.0) * Math.PI * Math.pow(sunRadius, 3);

	        // Ratio of Sun’s volume to Earth’s volume
	        double ratio = sunVolume / earthVolume;

	        // Output
	        System.out.println("The volume of the Earth is " + earthVolume + " cubic miles,");
	        System.out.println("The volume of the Sun is " + sunVolume + " cubic miles,");
	        System.out.println("and the ratio of the volume of the Sun to the volume of the Earth is " + ratio + ".");
	    }
	}

	/*Output
	 The volume of the Earth is 2.2984729611703882E11 cubic miles,
	The volume of the Sun is 3.388807851993121E17 cubic miles,
	and the ratio of the volume of the Sun to the volume of the Earth is 1474373.5990122468.*/



