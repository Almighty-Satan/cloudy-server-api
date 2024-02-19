/*
 * Copyright 2023 Almighty-Satan
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

package io.github.almightysatan.cloudy.agent.api;

public enum OperatorType {

    PANEL(true),
    CONSOLE(false),
    PLAYER(true),
    API(true);

    private final boolean hasName;

    OperatorType(boolean hasName) {
        this.hasName = hasName;
    }

    /**
     * Returns {@code true} if this type of operator has a name. For example players have a name while the console does
     * not.
     *
     * @return {@code true} if this type of operator has a name
     */
    public boolean hasName() {
        return hasName;
    }
}
