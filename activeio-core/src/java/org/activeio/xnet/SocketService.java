/**
 *
 * Copyright 2005 the original author or authors.
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
package org.activeio.xnet;

import java.io.IOException;
import java.net.Socket;

/**
 * @version $Revision: 1.1 $ $Date: 2004/04/09 19:04:01 $
 */
public interface SocketService {
    void service(Socket socket) throws ServiceException, IOException;

    /**
     * Gets the name of the service.
     * Used for display purposes only
     */
    String getName();
}