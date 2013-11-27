package uol.collective.offer.commons.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;




/**
 * Anotacao utilizada para padronizar o equals do tipo BigDecimal. 
 * 
 * */
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlTypeBigDecimal
{

}
