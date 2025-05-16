package org.example.testscenario.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

public abstract class TimeMeasure {

    @Autowired
    private StopWatch stopWatch;

    private final String measureName;

    public TimeMeasure(String measureName) {
        this.measureName = measureName;
    }

    public void test() {
        stopWatch.start(measureName);
        execute();
        stopWatch.stop();

    }

    protected abstract void execute();

}
