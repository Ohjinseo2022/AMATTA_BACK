package com.amatta.scheduler.amatta_back.app.common.dto;

public abstract class AbstractAuditingDTO<T> {

    public abstract T getId();

    public abstract void setId(T id);
}
