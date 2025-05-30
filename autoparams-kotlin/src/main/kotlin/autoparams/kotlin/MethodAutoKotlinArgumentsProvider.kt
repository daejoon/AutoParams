package autoparams.kotlin

import java.util.stream.Stream
import autoparams.MethodAutoArgumentsProvider
import autoparams.MethodAutoSource
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.support.AnnotationConsumer

/**
 * An [ArgumentsProvider] and [AnnotationConsumer] for [MethodAutoKotlinSource].
 *
 * This class provides arguments for JUnit 5 parameterized tests, sourcing them
 * from a method specified in a [MethodAutoKotlinSource] annotation. It adapts
 * the functionality of [MethodAutoArgumentsProvider] for Kotlin tests, allowing
 * any remaining parameters not provided by the source method to be
 * automatically generated by AutoParams, with Kotlin-specific considerations.
 *
 * @see MethodAutoKotlinSource
 * @see MethodAutoArgumentsProvider
 */
class MethodAutoKotlinArgumentsProvider :
    ArgumentsProvider,
    AnnotationConsumer<MethodAutoKotlinSource> {

    private val provider = MethodAutoArgumentsProvider()

    /**
     * Provides a stream of arguments for a parameterized test.
     *
     * This method delegates to an underlying [MethodAutoArgumentsProvider] to
     * provide arguments sourced from the specified method and supplemented by
     * AutoParams for any remaining parameters, with Kotlin-specific
     * considerations.
     *
     * @param context The extension context for the current test.
     * @return A stream of [Arguments] to be used by the parameterized test.
     */
    override fun provideArguments(
        context: ExtensionContext
    ): Stream<out Arguments> = provider.provideArguments(context)

    /**
     * Consumes the [MethodAutoKotlinSource] annotation to configure the
     * underlying argument provider.
     *
     * This method extracts the method name from the provided
     * [MethodAutoKotlinSource] annotation and uses it to configure an internal
     * [MethodAutoArgumentsProvider]. This setup allows the provider to
     * correctly source arguments from the specified method and combine them
     * with automatically generated arguments for Kotlin parameterized tests.
     *
     * @param annotation The [MethodAutoKotlinSource] annotation instance to
     *                   consume.
     */
    override fun accept(annotation: MethodAutoKotlinSource) {
        provider.accept(MethodAutoSource.ProxyFactory.create(annotation.value))
    }
}
