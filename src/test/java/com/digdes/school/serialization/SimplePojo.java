package com.digdes.school.serialization;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

class SimplePojo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int val;
    private Date date;

    @JsonIgnore
    private transient String skipSaving;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSkipSaving() {
        return skipSaving;
    }

    public void setSkipSaving(String skipSaving) {
        this.skipSaving = skipSaving;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplePojo that = (SimplePojo) o;
        return val == that.val &&
                Objects.equals(name, that.name) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getVal(), getDate());
    }
}
