package com.example.demo.scalar;

import graphql.language.StringValue;
import graphql.schema.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GraphqlDateScalar {
    public static final GraphQLScalarType Date = GraphQLScalarType.newScalar()
            .name("Date")
            .description("A custom scalar that represents the date value")
            .coercing(new Coercing<LocalDate, String>() {
                @Override
                public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
                    if (dataFetcherResult instanceof LocalDate) {
                        return ((LocalDate) dataFetcherResult).format(DateTimeFormatter.ISO_DATE);
                    } else {
                        throw new CoercingSerializeException("Not a valid DateTime");
                    }
                }

                @Override
                public LocalDate parseValue(Object input) throws CoercingParseValueException {
                    return LocalDate.parse(input.toString(), DateTimeFormatter.ISO_DATE);
                }

                @Override
                public LocalDate parseLiteral(Object input) throws CoercingParseLiteralException {
                    if (input instanceof StringValue) {
                        return LocalDate.parse(((StringValue) input).getValue(), DateTimeFormatter.ISO_DATE);
                    }

                    throw new CoercingParseLiteralException("Value is not a valid ISO date time");
                }
            })
            .build();
}
