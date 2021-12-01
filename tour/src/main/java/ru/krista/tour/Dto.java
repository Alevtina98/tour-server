package ru.krista.tour;

import java.util.List;

public class Dto <T> {
    public enum Status {
        ok, error
    }
    public Status status;
    public T data; // information object / ArrayList<Io>
    public List<String> errorMsgList;

    public Dto(T data) {
        this.data = data;
        this.status = Status.ok;
    }
    public void setError (String error) {
        this.status = Status.error;
        this.errorMsgList.add(error);
    }
    public void addErrorMsg (String error) {
        this.errorMsgList.add(error);
    }
}
