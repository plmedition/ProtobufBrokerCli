package com.ecosystem.service.impl;

import com.ecosystem.service.exception.ProtobufBrokerCliException;
import com.ecosystem.service.response.FeedbackResponse;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.IOException;

public class ProtobufBrokerCliServiceUtils {

    public static final String FILE_NOT_FOUND = "File not found.";
    public static final String PARSING_ERROR = "Parsing error.";
    public static final String FILE_NAME_NOT_PROVIDED = "File name was not provided.";
    public static final String NO_MESSAGE_IN_BROKER = "No message present in broker.";

    public void handleException(Exception e, FeedbackResponse feedbackResponse) {
        if (e instanceof IllegalArgumentException) {
            feedbackResponse.setFeedback(FILE_NAME_NOT_PROVIDED);
        } else if (e instanceof InvalidProtocolBufferException) {
            feedbackResponse.setFeedback(PARSING_ERROR);
        } else if (e instanceof IOException) {
            feedbackResponse.setFeedback(FILE_NOT_FOUND);
        } else if (e instanceof ProtobufBrokerCliException) {
            feedbackResponse.setFeedback(NO_MESSAGE_IN_BROKER);
        } else {
            feedbackResponse.setFeedback(e.getLocalizedMessage());
        }

    }
}
