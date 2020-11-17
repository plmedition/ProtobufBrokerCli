package com.ecosystem.service.impl;

import com.ecosystem.service.ProtobufBrokerCliService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProtobufBrokerCliServiceImplTest {

    private static final String OK_TEST_FILE = "src/test/resources/jsonFile.json";
    private static final String KO_TEST_FILE_PARSING_ERROR = "src/test/resources/parsingErrorJsonFile.json";

    @Autowired
    private ProtobufBrokerCliService protobufBrokerCliService;

    @Test
    public void testSendToMessageBrokerAndGetOK() throws Exception {
        String feedbackText = protobufBrokerCliService.sendToMessageBroker(OK_TEST_FILE).getFeedback();
        assertNotNull(feedbackText);
        assertEquals("OK", feedbackText);
        String jsonContent = protobufBrokerCliService.get().getJsonContent();
        assertNotNull(jsonContent);
    }

    @Test
    public void testSendToMessageBrokerKODueToMissingFile() throws Exception {
        String feedbackText = protobufBrokerCliService.sendToMessageBroker("").getFeedback();
        assertNotNull(feedbackText);
        assertEquals(ProtobufBrokerCliServiceUtils.FILE_NOT_FOUND, feedbackText);
    }

    @Test
    public void testSendToMessageBrokerKODueToParsingError() throws Exception {
        String feedbackText = protobufBrokerCliService.sendToMessageBroker(KO_TEST_FILE_PARSING_ERROR).getFeedback();
        assertNotNull(feedbackText);
        assertEquals(ProtobufBrokerCliServiceUtils.PARSING_ERROR, feedbackText);
    }

    @Test
    public void testSendToMessageBrokerKODueNotProvidedFilename() throws Exception {
        String feedbackText = protobufBrokerCliService.sendToMessageBroker(null).getFeedback();
        assertNotNull(feedbackText);
        assertEquals(ProtobufBrokerCliServiceUtils.FILE_NAME_NOT_PROVIDED, feedbackText);
    }

    @Test
    public void testGetKODueToMissingMessageInBroker() throws Exception {
        String feedbackText = protobufBrokerCliService.get().getFeedback();
        assertNotNull(feedbackText);
        assertEquals(ProtobufBrokerCliServiceUtils.NO_MESSAGE_IN_BROKER, feedbackText);
    }
}