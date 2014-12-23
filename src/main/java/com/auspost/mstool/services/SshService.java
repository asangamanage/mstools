package com.auspost.mstool.services;

import com.auspost.mstool.utils.ReturnValue;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by managea on 10/12/2014.
 */
public class SshService implements IsshService {

    @Override
    public Map connect(String address, String userName, String password) {
        final SSHClient ssh = new SSHClient();
        Session session = null;
        try {
            ssh.loadKnownHosts();
            ssh.addHostKeyVerifier(new PromiscuousVerifier());
            ssh.connect(address);
            ssh.authPassword(userName, password);
            session = ssh.startSession();
            return ReturnValue.ssuccess(ReturnValue.CLIENT, ssh, ReturnValue.SESSION, session);
        } catch (IOException e) {
            e.printStackTrace();
            return ReturnValue.failed("Connection failed");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnValue.failed("Unknown exception !!");
        }
    }

    @Override
    public Map disconnect(Session session) {
        return null;
    }

    @Override
    public Map runCommand(Session session, String command) {
            try {
                final Session.Command cmd = session.exec(command);
                String cmdOutput = IOUtils.readFully(cmd.getInputStream()).toString();
                cmd.join(5, TimeUnit.SECONDS);
                System.out.println(cmdOutput);
                return ReturnValue.success(cmdOutput);
            } catch (ConnectionException e) {
                e.printStackTrace();
                return ReturnValue.failed("Connection error");
            } catch (TransportException e) {
                e.printStackTrace();
                return ReturnValue.failed("Connection failed");
            } catch (IOException e) {
                e.printStackTrace();
                return ReturnValue.failed("Network failure");
            }
    }
}
