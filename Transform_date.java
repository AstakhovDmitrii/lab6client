package com.company.Models;

import org.simpleframework.xml.transform.Transform;

import java.time.ZonedDateTime;

public class Transform_date implements Transform<ZonedDateTime> {// так как конвертер заполняет все значения, то будет предупреждение или ошибка. Этим классом
    //мы говорим о том, что время он будет заполнять через наш вариант, и не будет лезть туда, где будет ошибка
    @Override
    public ZonedDateTime read(String s) {
        return ZonedDateTime.parse(s);
    }

    @Override
    public String write(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toString();
    }
}
