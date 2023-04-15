import java.util.Random;
public class rollDie {
	
	private static Random generator = new Random();
	private int numSides;
	
		public rollDie() {
			numSides = 6;
		}
		
		public rollDie(int sides) {
			numSides = sides;
		}
		
			public int roll() {
				int roll = generator.nextInt(numSides);
				return roll++;
			}
	
	
	
  }





