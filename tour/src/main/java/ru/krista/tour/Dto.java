package ru.krista.tour;

public class Dto <T> {
    public enum Status {
        ok, error
    }
    public final Status status;
    public final T data; // information object / ArrayList<Io>

    public Dto(T data) {
        this.data = data;
        this.status = Status.ok;
    }
    public Dto(T data, Status status) {
        this.data = data;
        this.status = status;
    }
    public Dto(T data, Status status, String errorMessage) {
        this.data = data;
        this.status = status;
    }
}
