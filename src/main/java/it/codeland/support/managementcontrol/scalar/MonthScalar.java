package com.example.demo.scalar;

import graphql.Internal;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.*;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Function;

import static graphql.scalar.CoercingUtil.typeName;

@Internal
public final class MonthScalar  {

    private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    public static final GraphQLScalarType INSTANCE;

    private MonthScalar() {}

    static {
        Coercing<String, String> coercing = new Coercing<String, String>() {
            @Override
            public String serialize(Object input) throws CoercingSerializeException {
                if (input instanceof String) {
                    parseLocalDate(input.toString(), CoercingSerializeException::new);
                } else {
                    throw new CoercingSerializeException(
                            "Expected a 'String' but was '" + typeName(input) + "'."
                    );
                }
                try {
                    return input.toString();
                } catch (DateTimeException e) {
                    throw new CoercingSerializeException(
                            "Unable to turn String into acceptable month because of : '" + e.getMessage() + "'."
                    );
                }
            }

            @Override
            public String parseValue(Object input) throws CoercingParseValueException {
                if (input instanceof String) {
                    parseLocalDate(input.toString(), CoercingParseValueException::new);
                } else {
                    throw new CoercingParseValueException(
                            "Expected a 'String' or 'java.time.temporal.TemporalAccessor' but was '" + typeName(input) + "'."
                    );
                }
                return input.toString();
            }

            @Override
            public String parseLiteral(Object input) throws CoercingParseLiteralException {
                if (!(input instanceof StringValue)) {
                    throw new CoercingParseLiteralException(
                            "Expected AST type 'StringValue' but was '" + typeName(input) + "'."
                    );
                } else {
                    parseLocalDate(((StringValue) input).getValue(), CoercingParseLiteralException::new);
                }
                return ( (StringValue)input ).getValue();
            }

            @Override
            public Value<?> valueToLiteral(Object input) {
                String s = serialize(input);
                return StringValue.newStringValue(s).build();
            }

            private void parseLocalDate(String s, Function<String, RuntimeException> exceptionMaker) {
                try {
                    MONTH_FORMATTER.parse(s);
                } catch (DateTimeParseException e) {
                    throw exceptionMaker.apply("Invalid RFC3339 month value : '" + s + "'. because of : '" + e.getMessage() + "'");
                }
            }
        };

        INSTANCE = GraphQLScalarType.newScalar()
                .name("Month")
                .description("An RFC-3339 compliant Month Scalar")
                .coercing(coercing)
                .build();
    }
}
