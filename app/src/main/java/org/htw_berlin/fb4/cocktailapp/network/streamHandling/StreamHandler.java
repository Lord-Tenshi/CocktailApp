package org.htw_berlin.fb4.cocktailapp.network.streamHandling;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamHandler {
    /**
     *  abstracts stream handling from protocol
     * @param is an InputStream
     * @param op an OutputStream
     * @throws IOException
     */
    void handleConnection(InputStream is, OutputStream op) throws IOException;
}
