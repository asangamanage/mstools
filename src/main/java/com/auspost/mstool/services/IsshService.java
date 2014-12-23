package com.auspost.mstool.services;

import net.schmizz.sshj.connection.channel.direct.Session;

import java.util.Map;

/**
 * Created by managea on 10/12/2014.
 */
public interface IsshService {
    public Map connect(String address, String userName, String password);

    public Map disconnect(Session session);

    public Map runCommand(Session session, String command);
}
