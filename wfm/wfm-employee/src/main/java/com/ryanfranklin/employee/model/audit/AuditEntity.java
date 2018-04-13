package com.ryanfranklin.employee.model.audit;

public enum AuditEntity {

    EMPLOYEE("employee");

    private String entity;
    AuditEntity(String entity) {
        this.entity = entity;
    }

    String getEntityString() {
        return entity;
    }
}
