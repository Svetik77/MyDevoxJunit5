package checkByInterface;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarTest implements 
			IMyCheckEqualsHashCodeExistsTest<Car> {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}
 
	@Override	
	public Car createValue() {
		 
		return new Car("BMW", "good car", 5);
	}

	@Override
	public Car createOtherValue() {
		 
		return new Car("BMW", "good car", 7);
	}

}
