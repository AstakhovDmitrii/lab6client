package com.company.Models;

import java.lang.reflect.Field;

public class Pole {
    private Field field;
    private Object main;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setMain(Object main) {
        this.main = main;
    }

    public Object getMain() {
        return main;
    }

    public Pole(Field field, Object main) {
        this.field = field;
        this.main = main;
    }
}
