package com.godel.common.entity;

public record TaxableEntity ( int id, String name, int age, char sex, String location, TaxParamVO taxVO ) {
}
