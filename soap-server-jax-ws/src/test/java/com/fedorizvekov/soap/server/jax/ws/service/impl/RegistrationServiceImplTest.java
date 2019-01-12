package com.fedorizvekov.soap.server.jax.ws.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileReader;
import javax.xml.bind.JAXBContext;
import javax.xml.stream.XMLInputFactory;
import com.fedorizvekov.soap.server.jax.ws.model.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceImplTest {

    @InjectMocks
    private RegistrationServiceImpl registrationService;


    @Test
    public void should_return_user_data() throws Exception {
        var streamReader = XMLInputFactory.newFactory().createXMLStreamReader(new FileReader("client_request.xml"));

        while (streamReader.hasNext() && !(streamReader.isStartElement() && "registrationRequest".equals(streamReader.getLocalName()))) {
            streamReader.next();
        }

        var unmarshaller = JAXBContext.newInstance(UserDto.class).createUnmarshaller();
        var userDto = unmarshaller.unmarshal(streamReader, UserDto.class).getValue();

        var result = registrationService.registration(userDto);

        assertThat(result).isEqualTo("REGISTRATION COMPLETED, User contain: ( email = test@email.com, firstName = TestFirstName, lastName = TestLastName )" );
    }

}
