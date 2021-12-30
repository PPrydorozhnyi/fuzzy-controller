package com.petro.fuzzy;

import static fuzzy4j.flc.Term.term;
import static fuzzy4j.flc.Variable.input;
import static fuzzy4j.flc.Variable.output;

import fuzzy4j.flc.ControllerBuilder;
import fuzzy4j.flc.FLC;
import fuzzy4j.flc.InputInstance;
import fuzzy4j.flc.Term;
import fuzzy4j.flc.Variable;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FuzzyApplication {

  public static void main(String[] args) {
    SpringApplication.run(FuzzyApplication.class, args);
  }

}
