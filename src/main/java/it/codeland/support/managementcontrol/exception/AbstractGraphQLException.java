package it.codeland.support.managementcontrol.exception;

import graphql.GraphQLException;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AbstractGraphQLException extends RuntimeException {

    private transient Map<String, Object> parameters = new HashMap<>();

    public AbstractGraphQLException(String message) {
        super(message);
    }

    public AbstractGraphQLException(String message, Map<String, Object> additionParams) {
        this(message);
        if (additionParams != null) {
            this.parameters = additionParams;
        }
    }

    public AbstractGraphQLException(String message, Object... arguments) {
        this(MessageFormat.format(message, arguments));
    }

    public Map<String, Object> getExtensions() {
        return parameters.entrySet().stream()
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
