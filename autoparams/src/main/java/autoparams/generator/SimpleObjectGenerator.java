package autoparams.generator;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;

final class SimpleObjectGenerator extends CompositeObjectGenerator {

    public SimpleObjectGenerator() {
        super(
            new ClassGenerator(),
            new RootGenerator(),
            new StringGenerator(),
            new UUIDGenerator(),
            new BigIntegerGenerator(),
            new TypeMatchingGenerator(
                Factories::createBigDecimal,
                BigDecimal.class,
                Number.class
            ),
            new TypeMatchingGenerator(Factories::createClock, Clock.class),
            new TypeMatchingGenerator(
                Factories::createDuration,
                Duration.class,
                TemporalAmount.class
            ),
            new TypeMatchingGenerator(
                Factories::createPeriod,
                Period.class,
                ChronoPeriod.class
            ),
            new TypeMatchingGenerator(Factories::createLocalDate, LocalDate.class),
            new TypeMatchingGenerator(Factories::createLocalTime, LocalTime.class),
            new TypeMatchingGenerator(Factories::createLocalDateTime, LocalDateTime.class),
            new TypeMatchingGenerator(Factories::createOffsetDateTime, OffsetDateTime.class),
            new TypeMatchingGenerator(
                Factories::createZonedDateTime,
                ZonedDateTime.class,
                Temporal.class
            ),
            new TypeMatchingGenerator(Factories::createZoneId, ZoneId.class),
            new EnumGenerator(),
            new URLGenerator()
        );
    }
}
