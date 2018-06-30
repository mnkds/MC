package com.example.routes.demo.model;

import java.util.Objects;

public class Route {

    private String origin;
    private String destination;

    public Route(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(getOrigin(), route.getOrigin()) &&
                Objects.equals(getDestination(), route.getDestination());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getOrigin(), getDestination());
    }
}
