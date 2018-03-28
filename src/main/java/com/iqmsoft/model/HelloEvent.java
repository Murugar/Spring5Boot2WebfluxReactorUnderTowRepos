package com.iqmsoft.model;

import java.util.Date;

public class HelloEvent {

    private Hello greeting;

    private Date when;

    public HelloEvent(Hello greeting, Date when) {
        this.greeting = greeting;
        this.when = when;
    }

    public Hello getGreeting() {
        return greeting;
    }

    public void setGreeting(Hello greeting) {
        this.greeting = greeting;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }
}
