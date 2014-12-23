package com.auspost.mstool.services;

import com.auspost.mstool.utils.ReturnValue;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SshServiceTest {

    private IsshService sshService;
    private String serverIp;
    private String userName;
    private String password;

    private SSHClient sshClient;
    private Session session;


    @org.junit.Before
    public void setUp() throws Exception {
        sshService = new SshService();
        serverIp = "192.168.1.2";
        userName = "bigboy";
        password = "bigboy123";

        Map value = sshService.connect(serverIp, userName, password);
        assertTrue(ReturnValue.isSuccess(value));
        session = (Session) value.get(ReturnValue.SESSION);
        sshClient = (SSHClient) value.get(ReturnValue.CLIENT);

    }

    @org.junit.After
    public void tearDown() throws Exception {
        if (session != null) {
            session.close();
        }

        if (sshClient != null) {
            sshClient.disconnect();
        }
    }

    @org.junit.Test
    public void testConnect() throws Exception {
        assertNotNull(session);
        assertNotNull(sshClient);
    }

    @org.junit.Test
    public void testDisconnect() throws Exception {

    }

    @org.junit.Test
    public void testRunCommand() throws Exception {
        Map value = sshService.runCommand(session, "sudo tail -f /etc/motion/motion.conf");
        assertTrue(ReturnValue.isSuccess(value));

    }
}
