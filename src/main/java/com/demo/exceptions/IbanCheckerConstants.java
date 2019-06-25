/**
 * 
 */
package com.demo.exceptions;

/**
 * @author ZJankunas
 *
 */
public enum IbanCheckerConstants
{
    BAD_REQUEST("Could not deserialize request"), INTERNAL_ERROR("An internal error occurred");

    private String message;

    private IbanCheckerConstants(String message)
    {
        this.setMessage(message);
    }

    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

}
