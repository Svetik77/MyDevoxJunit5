package checkByInterface;

 
class CalculatorsTest implements 
	IMyCheckEqualsHashCodeExistsTest<Calculators> {

	@Override
	public Calculators createValue() {
		return  new Calculators(5, 7);
	}

	@Override
	public Calculators createOtherValue() {
		 
		return new Calculators(1, 7);
	}


}
