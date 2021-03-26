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
    	
    	T cVal = createValue();
     	T cVal2 = createValue();
     	System.out.println("hashCode : " +  cVal.hashCode() );
     	System.out.println("hashCode : " +  cVal2.hashCode() );
     	
		Assertions.assertEquals(cVal , cVal2);
     	
    	 assertThat(cVal).isEqualTo(createValue());
    	 
    	    assertThat(createValue().hashCode()).isEqualTo(createValue().hashCode());
    	    
    	    
    } 
    
    @Test
    @DisplayName("The values should not be equal")
    default void should_not_be_equal() {
        assertThat(createValue()).isNotEqualTo(createOtherValue());
        assertThat(createValue().hashCode()).isNotEqualTo(createOtherValue().hashCode());
    }
}
