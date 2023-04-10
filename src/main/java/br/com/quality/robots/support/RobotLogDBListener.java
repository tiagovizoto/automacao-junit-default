package br.com.quality.robots.support;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.SimpleDateFormat;
import java.time.Duration;

import java.util.Date;
import java.util.List;

public class RobotLogDBListener implements ConcurrentEventListener {
    private Date startTime;
    private Date endTime;
    private SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    PropertiesUtils prop = new PropertiesUtils();
    boolean toLog;

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        toLog = prop.getProperty(PropertiesUtils.NttRobotProperties.PROPERTY_REPORT_ENABLE).equalsIgnoreCase("true");
        if (toLog){
            publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
            publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
        }
    }
    private void handleTestCaseStarted(TestCaseStarted event) {
        startTime = new Date();
    }
    private void handleTestCaseFinished(TestCaseFinished event) {
        endTime = new Date();
        TestCase testCase = event.getTestCase();
        Result result = event.getResult();
        Status status = result.getStatus();
        String statusID = "";
        Throwable error = result.getError();
        String exception = "";
        if (error != null) exception = ExceptionUtils.getStackTrace(error);
        List<String> tags = testCase.getTags();
        String iD = "Adicionar a Tag @IDXX";
        for (String tag : tags) {
            if (tag.contains("@ID")) iD = tag.replace("@ID", "");
        }
        Duration duration = result.getDuration();
        if (status.is(Status.PASSED)) statusID = "1";
        else if (status.is(Status.FAILED)) statusID = "0";
        else if (status.is(Status.SKIPPED)) statusID = "2";
        else if (status.is(Status.PENDING)) statusID = "3";
        else if (status.is(Status.UNDEFINED)) statusID = "4";
        else if (status.is(Status.AMBIGUOUS)) statusID = "5";
        else if (status.is(Status.UNUSED)) statusID = "6";
    }
}