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

import org.jetbrains.annotations.NotNull;

public interface Server {

    /**
     * Returns the internal id of this server. This id does never change.
     *
     * @return the id
     */
    int getId();

    /**
     * Returns the name of this server. This is usually {@code groupName-id}.
     *
     * @return the name
     */
    @NotNull String getName();

    /**
     * Returns {@code true} if this server is a default/fallback server. Always returns {@code false} if
     * {@link Server#isProxy()} is {@code true}.
     *
     * @return {@code true} if this server is a default/fallback server.
     */
    boolean isDefaultServer();

    /**
     * Returns {@code true} if this server is a proxy.
     *
     * @return {@code true} if this server is a proxy
     */
    boolean isProxy();

    /**
     * Returns {@code true} if this server is a game-server.
     *
     * @return {@code true} if this server is a game-server
     */
    default boolean isGameServer() {
        return !this.isProxy();
    }

    /**
     * Returns the ip of this server.
     *
     * @return the ip
     */
    @NotNull String getIp();

    /**
     * Returns the port of this server.
     *
     * @return the port
     */
    int getPort();

    /**
     * Returns the number of players currently playing on this server.
     *
     * @return the number of players
     */
    int getPlayers();

    /**
     * Returns the number of slots of this server.
     *
     * @return the number of slots
     */
    int getMaxPlayers();

    /**
     * Returns {@code true} if this server is in game. This always returns {@code false} if {@link Server#isProxy()} is
     * {@code true}.
     *
     * @return {@code true} if this server is in game
     */
    boolean isInGame();
}
