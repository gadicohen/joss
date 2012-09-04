package nl.t42.openstack.command.container;

import nl.t42.openstack.command.core.BaseCommandTest;
import nl.t42.openstack.command.core.CommandExceptionError;
import nl.t42.openstack.model.Container;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.when;

public class ContainerRightsCommandTest extends BaseCommandTest {

    @Before
    public void setup() throws IOException {
        super.setup();
    }

    @Test
    public void createContainerSuccess() throws IOException {
        when(statusLine.getStatusCode()).thenReturn(202);
        new ContainerRightsCommand(httpClient, defaultAccess, new Container("containerName"), true).execute();
    }

    @Test
    public void createContainerFail() throws IOException {
        checkForError(404, new ContainerRightsCommand(httpClient, defaultAccess, new Container("containerName"), true), CommandExceptionError.CONTAINER_DOES_NOT_EXIST);
    }

    @Test
    public void unknownError() throws IOException {
        checkForError(500, new ContainerRightsCommand(httpClient, defaultAccess, new Container("containerName"), true), CommandExceptionError.UNKNOWN);
    }
}
