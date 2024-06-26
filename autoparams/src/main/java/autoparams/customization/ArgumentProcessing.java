package autoparams.customization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ArgumentProcessing {

    Class<? extends ArgumentProcessor> value();
}
