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

//    Term cold = term("cold", 10, 40, 70);
//    Term ok   = term("ok", 40, 70, 100);
//    Term hot  = term("hot", 70, 100, 130);
//
//    Variable room = input("room", cold, ok, hot).start(40).end(100);
//
//    Term low  = term("low", -50, 0, 50);
//    Term medi = term("medium", 0, 50, 100);
//    Term high = term("high", 50, 100, 150);
//
//    Variable effort = output("effort", low, medi, high).start(-50).end(150);
//
//    FLC impl = ControllerBuilder.newBuilder()
//        .when().var(room).is(cold).then().var(effort).is(high)
//        .when().var(room).is(ok).then().var(effort).is(medi)
//        .when().var(room).is(hot).then().var(effort).is(low)
//        .create();
//
//    InputInstance instance = new InputInstance().is(room, 60);
//
//    System.out.println("fuzzy = " + impl.applyFuzzy(instance));
//
//    Map<Variable, Double> crisp = impl.apply(instance);
//
//    System.out.println("crisp = " + crisp);
  }

}
