package ru.krista.tour;

import java.util.ArrayList;
import java.util.List;

public class Dto <T> {
    public enum Status {
        ok, error
    }

    public Status status;
    public T data; // information object / ArrayList<Io>
    public List<String> errorMsgList = new ArrayList<>();

    public Dto(T data) {
        this.data = data;
        this.status = Status.ok;
    }
    public void setData (T data) {
        this.data = data;
    }
    public void setError (String msg) {
        this.status = Status.error;
        this.errorMsgList.add(msg);
    }

    public void addErrorMsg (String error) {
        this.errorMsgList.add(error);
    }
    public void addErrorMsg (List<String> errorList) {
        this.errorMsgList.addAll(errorList);
    }


}
