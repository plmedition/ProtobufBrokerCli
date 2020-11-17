package com.ecosystem.protobuf.mapper;

import com.ecosystem.protobuf.PersonProtobuf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration()
@SpringBootTest
public class ProtobufMapperTest {

    @Autowired
    ProtobufMapper protobufMapper;

    @Test
    void mapJSONToProtobug() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("jsonFile.json").getFile());
        String jsonString = readFileAsString(file.getAbsolutePath());
        PersonProtobuf.Person person = protobufMapper.toProtobuf(jsonString);
        assertNotNull(person);
        assertEquals("Pablo", person.getName());
        assertEquals("pablo@contactmail.com", person.getEmail());
        assertEquals(1, person.getId());
    }

    @Test
    void mapFromProtobufToJSON() throws Exception {
        PersonProtobuf.Person person = PersonProtobuf.Person.newBuilder().setName("Pablo").setId(1).setEmail("pablo@contactgmail.com").build();
        String jsonString = protobufMapper.toJson(person);
        System.out.println(jsonString);
    }

    public static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}