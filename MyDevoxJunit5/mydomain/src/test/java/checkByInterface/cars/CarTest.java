package checkByInterface.cars;

import checkByInterface.IMyCheckEqualsHashCodeExistsTest;

class CarTest implements 
			IMyCheckEqualsHashCodeExistsTest<Car> {
 
	@Override	
	public Car createValue() {
		 
		return new Car("BMW", "good car", 5);
	}

	@Override
	public Car createOtherValue() {
		 
		return new Car("BMW", "good car", 7);
	}

}
