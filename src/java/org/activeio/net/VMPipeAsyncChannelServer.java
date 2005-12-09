/**
 * 
 * Copyright 2004 Hiram Chirino
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 * 
 **/

package org.activeio.net;

import java.io.IOException;
import java.net.URI;

import org.activeio.AcceptListener;
import org.activeio.AsyncChannel;
import org.activeio.AsyncChannelServer;

/**
 * @version $Revision$
 */
final public class VMPipeAsyncChannelServer implements AsyncChannelServer {

    private final URI bindURI;
    private final URI connectURI;
    private AcceptListener acceptListener;
    private boolean disposed;
    
    public VMPipeAsyncChannelServer(URI bindURI) {
        this.bindURI = this.connectURI = bindURI;
    }

    public URI getBindURI() {
        return bindURI;
    }

    public URI getConnectURI() {
        return this.connectURI;
    }

    public void dispose() {
        if( disposed )
            return;
        
        VMPipeAsyncChannelFactory.unbindServer(bindURI);
        disposed=true;
    }

    public void start() throws IOException {
        if( acceptListener==null )
            throw new IOException("acceptListener has not been set.");
    }

    public void stop(long timeout) {
    }

    public Object getAdapter(Class target) {
        if( target.isAssignableFrom(getClass()) ) {
            return this;
        }
        return null;
    }
    
    public String toString() {
        return "VM Pipe Server: "+getConnectURI();
    }

    public void setAcceptListener(AcceptListener acceptListener) {
        this.acceptListener = acceptListener;
    }

    public AsyncChannel connect() {
        VMPipeAsyncChannelPipe pipe = new VMPipeAsyncChannelPipe();
        acceptListener.onAccept(pipe.getRightAsyncChannel());
        return pipe.getLeftAsyncChannel();
    }
    
}