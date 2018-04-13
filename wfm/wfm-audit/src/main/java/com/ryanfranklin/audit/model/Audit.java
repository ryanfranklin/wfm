package com.ryanfranklin.audit.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Audit")
public class Audit {

    @Id
    @GeneratedValue
    private long id;

    /**
     * Sets id
     *
     * @param id the of id of the Audit
     */
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
