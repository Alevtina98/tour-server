create function "setDateChangeNow"() returns trigger
    language plpgsql
as
$$
BEGIN
    NEW."date_change" = now();
    RETURN NEW;
END;
$$;

alter function "setDateChangeNow"() owner to postgres;

