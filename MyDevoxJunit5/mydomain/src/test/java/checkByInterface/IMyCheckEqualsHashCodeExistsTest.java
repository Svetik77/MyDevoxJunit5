package checkByInterface;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public interface IMyCheckEqualsHashCodeExistsTest<T>  {
    T createValue();
    T createOtherValue();
    /**
     * if equals hashCode exists 
     * 2 Object have equals contains
     */
    @Test
    @DisplayName("if NO equals hashCode return false")
    default void chackEqualsHashCodeExists( ) {
     	Assertions.assertEquals(createValue() , createValue());
    	 assertThat(createValue()).isEqualTo(createValue());
    	    assertThat(createValue().hashCode()).isEqualTo(createValue().hashCode());
    }
    
    @Test
    @DisplayName("The values should not be equal")
    default void should_not_be_equal() {
        assertThat(createValue()).isNotEqualTo(createOtherValue());
        assertThat(createValue().hashCode()).isNotEqualTo(createOtherValue().hashCode());
    }
}
