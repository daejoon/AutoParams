package autoparams.customization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@CustomizerSource(AggressiveConstructorResolutionCustomizerFactory.class)
public @interface ResolveConstructorAggressively {

    Class<?> value();
}
