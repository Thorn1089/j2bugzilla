/*
 * Copyright 2011 Thomas Golden
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * <p>This package groups all of the remote procedure calls provided by the
 * J2Bugzilla API. Each class in this package represents a method which the
 * Bugzilla XML-RPC interface provides for use by clients. To call these methods
 * on a Bugzilla installation, the user must construct a new instance of the
 * object and pass it to the 
 * {@link com.j2bugzilla.base.BugzillaConnector#executeMethod(com.j2bugzilla.base.BugzillaMethod)}
 * for processing.</p>
 */
package com.j2bugzilla.rpc;