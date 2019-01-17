package com.fedorizvekov.soap.server.jax.ws.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileReader;
import javax.xml.bind.JAXBContext;
import javax.xml.stream.XMLInputFactory;
import com.fedorizvekov.soap.server.jax.ws.model.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {

    @InjectMocks
    private RegistrationServiceImpl registrationService;


    @DisplayName("Should return user data")
    @Test
    void shouldReturn_userData() throws Exception {
        var streamReader = XMLInputFactory.newFactory().createXMLStreamReader(new FileReader("client_request.xml"));

        while (streamReader.hasNext() && !(streamReader.isStartElement() && "registrationRequest".equals(streamReader.getLocalName()))) {
            streamReader.next();
        }

        var unmarshaller = JAXBContext.newInstance(UserDto.class).createUnmarshaller();
        var userDto = unmarshaller.unmarshal(streamReader, UserDto.class).getValue();

        var result = registrationService.registration(userDto);

        assertThat(result).isEqualTo("REGISTRATION COMPLETED, UserDto(email=test@email.com, firstName=TestFirstName, lastName=TestLastName)");
    }

}
