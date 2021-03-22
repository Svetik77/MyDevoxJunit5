package checkByInterface;

 
class CalculatorsTest implements 
	IMyCheckEqualsHashCodeExistsTest<Calculators> {

	@Override
	public Calculators createValue() {
		return  new Calculators(5, 7);
	}


}
