package com.godel.engine;

public record TaxableEntity ( int id, String name, int age, char sex, String location, TaxParamVO taxVO ) {
}
