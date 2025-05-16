package org.example.testscenario.util;

import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

public abstract class TimeMeasure {

    private final StopWatch stopWatch = new StopWatch();
    private final String measureName;

    public TimeMeasure(String measureName) {
        this.measureName = measureName;
    }

    public void test() {
        stopWatch.start(measureName);
        execute();
        stopWatch.stop();
        System.out.println(stopWatch.shortSummary());
    }

    protected abstract void execute();

}
