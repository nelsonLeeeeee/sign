package com.nelson.sign.entity;

import javax.persistence.*;

@Entity
public class Clazz {

    @Id
    @GeneratedValue
    @Column(name = "clazz_id")
    private Long clszzId;

    @Column(nullable = false)
    private String name;

    @JoinColumn(name = "pclazz_id")
    @OneToOne
    private Clazz pClazz;

    public Long getClszzId() {
        return clszzId;
    }

    public void setClszzId(Long clszzId) {
        this.clszzId = clszzId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clazz getpClazz() {
        return pClazz;
    }

    public void setpClazz(Clazz pClazz) {
        this.pClazz = pClazz;
    }
}
