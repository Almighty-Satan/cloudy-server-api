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
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface PlayerInfo {

    /**
     * Returns the uuid of the player.
     *
     * @return the uuid
     */
    @NotNull UUID getUuid();

    /**
     * Returns the current ban or {@code null} if the player is not currently banned.
     *
     * @return The ban or {@code null}
     */
    @Nullable Ban getBan();

    /**
     * Returns the current mute or {@code null} if the player is not currently banned.
     *
     * @return The mute or {@code null}
     */
    @Nullable Ban getMute();
}
