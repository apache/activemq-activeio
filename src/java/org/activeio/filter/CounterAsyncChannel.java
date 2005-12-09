/**
 *
 * Copyright 2004 Hiram Chirino
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.activeio.filter;

import java.io.IOException;

import org.activeio.AsyncChannel;
import org.activeio.FilterAsyncChannel;
import org.activeio.Packet;


/**
 * A CounterAsyncChannel is a simple {@see org.activeio.AsyncChannelFilter} 
 * that counts the number bytes that been sent down and up through the channel.
 * 
 * The {@see org.activeio.counter.CounterAttribueEnum.COUNTER_INBOUND_COUNT}
 * and {@see org.activeio.counter.CounterAttribueEnum.COUNTER_OUTBOUND_COUNT}
 * attributes can be used to find query the channel to get the current inbound and outbound
 * byte counts.
 * 
 * @version $Revision$
 */
final public class CounterAsyncChannel extends FilterAsyncChannel {

    long inBoundCounter = 0;

    long outBoundCounter = 0;

    /**
     * @param next
     */
    public CounterAsyncChannel(AsyncChannel next) {
        super(next);
    }

    /**
     * @see org.activeio.FilterAsyncChannel#onPacket(org.activeio.Packet)
     */
    public void onPacket(Packet packet) {
        inBoundCounter += packet.remaining();
        super.onPacket(packet);
    }

    /**
     * @see org.activeio.FilterAsyncChannel#write(org.activeio.Packet)
     */
    public void write(Packet packet) throws IOException {
        outBoundCounter += packet.position();
        super.write(packet);
    }

    public long getInBoundCounter() {
        return inBoundCounter;
    }
    
    public long getOutBoundCounter() {
        return outBoundCounter;
    }
}