package it.codeland.support.managementcontrol.exception;

public class EntityNotFoundException extends AbstractGraphQLException {

    public EntityNotFoundException(String message) {
        super(message);
    }
    public EntityNotFoundException(String message, Object... arguments) {
        super(message, arguments);
    }

}
