package checkByInterface;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public interface IMyCheckEqualsHashCodeExistsTest<T>  {
    T createValue();
  
    /**
     * if equals hashCode exists 
     * 2 Object have equals contains
     */
    @Test
    @DisplayName("if NO equals hashCode return false")
    default void chackEqualsHashCodeExists( ) {
     	Assertions.assertEquals(createValue() , createValue());
    	 assertThat(createValue()).isEqualTo(createValue());
    }
}
