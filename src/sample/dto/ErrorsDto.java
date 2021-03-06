package sample.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorsDto {

    /**
     * List of errors.
     */
    private List<String> errorList = new ArrayList<String>();

    /**
     * Add error to the existing error list.
     * @param error error to add.
     */
    public void addError(String error) {
        this.getErrorList().add(error);
    }

    /**
     * Clears the error list.
     */
    public void clearErrors() {
        this.getErrorList().clear();
    }

    /**
     * Retrieves list of errors.
     * @return the errorList
     */
    public List<String> getErrorList() {
        return errorList;
    }

    /**
     * Sets list of errors.
     * @return the errorList to set
     */
    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

}
