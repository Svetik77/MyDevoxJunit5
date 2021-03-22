package checkByInterface;

/**
 * 
 * @author Rimma
 *
 */
public class Calculators   {
	int num1;
	int num2;
	
	public Calculators(int num1, int num2) {
		this.num1 = num1;
		this.num2 = num2;
	}

	public Calculators add(int a, int b) {
		checkNum(a);
		return new Calculators(a, b)  ;
		
	}

	private void checkNum(int a) {
		if(a == 0 || a < 0) {
            throw new IllegalArgumentException("Invalid num1: should be > 0");

		}
	};

	  @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Calculators other = (Calculators) o;
	        if (num1 != other.num1)
				return false;
			return true;
	    }

	    
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + num1;
			return result;
		}
}
