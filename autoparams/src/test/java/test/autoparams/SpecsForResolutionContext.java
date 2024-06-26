package test.autoparams;

import autoparams.AutoSource;
import autoparams.ObjectQuery;
import autoparams.ResolutionContext;
import autoparams.customization.Customizer;
import autoparams.generator.ObjectContainer;
import autoparams.generator.ObjectGenerator;
import autoparams.processor.ObjectProcessor;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConverter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpecsForResolutionContext {

    @SuppressWarnings("DataFlowIssue")
    @ParameterizedTest
    @AutoSource
    void resolve_has_guard_clause(ResolutionContext sut) {
        assertThatThrownBy(() -> sut.resolve((ObjectQuery) null))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @AutoSource
    void resolve_correctly_returns_generated_value(
        ExtensionContext extensionContext,
        int value
    ) {
        ResolutionContext sut = new ResolutionContext(
            extensionContext,
            (query, context) -> new ObjectContainer(value),
            ObjectProcessor.DEFAULT
        );

        Object actual = sut.resolve(int.class);

        assertThat(actual).isEqualTo(value);
    }

    @Deprecated
    @SuppressWarnings("ConstantConditions")
    @ParameterizedTest
    @AutoSource
    void generate_has_guard_clause(ResolutionContext sut) {
        ObjectQuery query = null;
        assertThatThrownBy(() -> sut.generate(query))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Deprecated
    @ParameterizedTest
    @AutoSource
    void generate_correctly_returns_generated_value(
        ExtensionContext extensionContext,
        int value
    ) {
        ResolutionContext sut = new ResolutionContext(
            extensionContext,
            (query, context) -> new ObjectContainer(value),
            ObjectProcessor.DEFAULT
        );

        Object actual = sut.generate(int.class);

        assertThat(actual).isEqualTo(value);
    }

    @SuppressWarnings("ConstantConditions")
    @ParameterizedTest
    @AutoSource
    void applyCustomizer_has_guard_clause_for_customizer(
        ResolutionContext sut
    ) {
        assertThatThrownBy(() -> sut.applyCustomizer((Customizer) null))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @SuppressWarnings("ConstantConditions")
    @ParameterizedTest
    @AutoSource
    void applyCustomizer_has_guard_clause_for_generator(
        ResolutionContext sut
    ) {
        assertThatThrownBy(() -> sut.applyCustomizer((ObjectGenerator) null))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @SuppressWarnings("ConstantConditions")
    @ParameterizedTest
    @AutoSource
    void applyCustomizer_has_guard_clause_for_processor(
        ResolutionContext sut
    ) {
        assertThatThrownBy(() -> sut.applyCustomizer((ObjectProcessor) null))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @AutoSource
    void applyCustomizer_correctly_replaces_generator(
        ExtensionContext extensionContext,
        int value1,
        int value2
    ) {
        ResolutionContext sut = new ResolutionContext(
            extensionContext,
            (query, context) -> new ObjectContainer(value1),
            ObjectProcessor.DEFAULT
        );

        sut.applyCustomizer((query, context) -> new ObjectContainer(value2));

        Object actual = sut.resolve(int.class);
        assertThat(actual).isEqualTo(value2);
    }

    @ParameterizedTest
    @AutoSource
    void sut_resolves_ArgumentConverter(ResolutionContext sut) {
        ArgumentConverter actual = sut.resolve(ArgumentConverter.class);
        assertThat(actual).isNotNull();
    }
}
