package com.harsha.cloudcomputing.courseservice.util;

/**
 * ExceedsMaxCharactersException
 */
public class ExceedsMaxCharactersException extends Exception {

    private static final long serialVersionUID = 1L;

    private int maxCharactersAllowed = 0;

    public ExceedsMaxCharactersException(int maxCharactersAllowed) {
        this.maxCharactersAllowed = maxCharactersAllowed;
    }

    @Override
    public String toString() {
        return "ExceedsMaxCharactersException [ Can't allow characters more than "
                + maxCharactersAllowed + " ]";
    }

}
