package com.manuelrojas.fixture.data.exception;

import com.manuelrojas.fixture.domain.exception.ErrorBundle;

/**
 * Wrapper around Exceptions used to manage errors in the repository.
 */
class FixtureErrorBundle implements ErrorBundle {

    private final Exception exception;

    FixtureErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        String message = "";
        if (this.exception != null) {
            message = this.exception.getMessage();
        }
        return message;
    }
}