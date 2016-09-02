package io.github.com;

import io.github.dherik.Condition;
import io.github.dherik.State;
import io.github.dherik.StateMachine;
import io.github.dherik.Transition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class StateMachineTest {

    @Test
    public void stateMachineDemonstration() {
        State one = new State("one");
        State two = new State("two");
        State three = new State("three");

        Condition sunday = new Condition("Sunday");
        Condition raining = new Condition("Raining");
        Condition notSunday = new Condition("Not Sunday");
        Condition notRaining = new Condition("Not Raining");

        List<Transition> transitions = new ArrayList<>();
        transitions.add(new Transition(one, new HashSet<>(Arrays.asList(sunday)), three));
        transitions.add(new Transition(one, new HashSet<>(Arrays.asList(sunday)), two));
        transitions.add(new Transition(one, new HashSet<>(Arrays.asList(raining)), three));
        transitions.add(new Transition(one, new HashSet<>(Arrays.asList(sunday, raining)), three));
        transitions.add(new Transition(one, new HashSet<>(Arrays.asList(notSunday, notRaining)), three));

        StateMachine machine = new StateMachine(one, transitions);
        
        Assert.assertEquals("one", machine.getCurrent().getState());
        
        machine.apply(new HashSet<>(Arrays.asList(sunday, raining)));
        
        Assert.assertEquals("three", machine.getCurrent().getState());
    }

}
