package org.openjfx.bioreactor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;

public interface IProtocole {
    public abstract void execute(InputStream is, OutputStream os, IContext c);

}