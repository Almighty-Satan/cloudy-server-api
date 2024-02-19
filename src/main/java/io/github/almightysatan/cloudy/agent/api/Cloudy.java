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

import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.NonBlocking;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public interface Cloudy {

    /**
     * Sets whether the current server is in-game or not.
     *
     * @param state {@code true} if this server is in-game.
     * @throws InvalidServerTypeException If the current server is a proxy.
     */
    void setInGame(boolean state) throws InvalidServerTypeException;

    /**
     * Returns an immutable {@link Collection} that contains all {@link Server servers}.
     *
     * @return An immutable {@link Collection} that contains all {@link Server servers}.
     */
    @NotNull Collection<@NotNull Server> getServers();

    /**
     * Returns an immutable {@link Collection} that contains all {@link Server servers} whose group matches the given {@link UUID}.
     * If the given {@link UUID} is {@code null}, this returns the value of {@link #getServers()}.
     *
     * @return An immutable {@link Collection} containing the {@link Server servers}.
     */
    @NotNull Collection<@NotNull Server> getServers(@Nullable UUID group);

    /**
     * Returns an immutable {@link Collection} that contains all {@link Server servers} whose group matches the given name.
     * If the given name is {@code null}, this returns the value of {@link #getServers()}.
     *
     * @return An immutable {@link Collection} containing the {@link Server servers}.
     */
    @NotNull Collection<@NotNull Server> getServers(@Nullable String groupName);

    /**
     * Returns any {@link Server server} whose group matches the given {@link UUID}.
     * If the given {@link UUID} is {@code null}, this returns any {@link Server server}. May
     * return {@code null} if no server is available.
     *
     * @return A {@link Server server} or {@code null}
     */
    @Nullable Server getAnyServer(@Nullable UUID group);

    /**
     * Returns any {@link Server server} whose group matches the given name.
     * If the given name is {@code null}, this returns any {@link Server server}. May
     * return {@code null} if no server is available.
     *
     * @return A {@link Server server} or {@code null}
     */
    @Nullable Server getAnyServer(@Nullable String groupName);

    /**
     * Permanently bans a player.
     *
     * @param target   The {@link UUID} of the player
     * @param reason   The reason
     * @param operator The name of the operator
     * @throws NullPointerException     If target, reason or operator is {@code null}.
     * @throws IllegalArgumentException If operator is empty.
     */
    void ban(@NotNull UUID target, @NotNull String reason, @NotNull String operator);

    /**
     * Temporarily bans a player for the given duration.
     *
     * @param target           The {@link UUID} of the player
     * @param reason           The reason
     * @param operator         The name of the operator
     * @param duration         The duration
     * @param durationTimeUnit The {@link TimeMeasure} of the duration
     * @throws NullPointerException     If target, reason, operator or durationTimeUnit is {@code null}.
     * @throws IllegalArgumentException If operator is empty or {@code duration <= 0}.
     */
    void tempban(@NotNull UUID target, @NotNull String reason, @NotNull String operator, long duration, @NotNull TimeMeasure durationTimeUnit);

    /**
     * Unbans a player.
     *
     * @param target   The {@link UUID} of the player
     * @param reason   The reason
     * @param operator The name of the operator
     * @throws NullPointerException     If target, reason or operator is {@code null}.
     * @throws IllegalArgumentException If operator is empty.
     */
    void unban(@NotNull UUID target, @NotNull String reason, @NotNull String operator);

    /**
     * Kicks a player from the server, if they are online.
     *
     * @param target   The {@link UUID} of the player
     * @param reason   The reason
     * @param operator The name of the operator
     * @throws NullPointerException     If target, reason or operator is {@code null}.
     * @throws IllegalArgumentException If operator is empty.
     */
    void kick(@NotNull UUID target, @NotNull String reason, @NotNull String operator);

    /**
     * Permanently mutes a player.
     *
     * @param target   The {@link UUID} of the player
     * @param reason   The reason
     * @param operator The name of the operator
     * @throws NullPointerException     If target, reason or operator is {@code null}.
     * @throws IllegalArgumentException If operator is empty.
     */
    void mute(@NotNull UUID target, @NotNull String reason, @NotNull String operator);

    /**
     * Temporarily mutes a player for the given duration.
     *
     * @param target           The {@link UUID} of the player
     * @param reason           The reason
     * @param operator         The name of the operator
     * @param duration         The duration
     * @param durationTimeUnit The {@link TimeMeasure} of the duration
     * @throws NullPointerException     If target, reason, operator or durationTimeUnit is {@code null}.
     * @throws IllegalArgumentException If operator is empty or {@code duration <= 0}.
     */
    void tempmute(@NotNull UUID target, @NotNull String reason, @NotNull String operator, long duration, @NotNull TimeMeasure durationTimeUnit);

    /**
     * Unmutes bans a player.
     *
     * @param target   The {@link UUID} of the player
     * @param reason   The reason
     * @param operator The name of the operator
     * @throws NullPointerException     If target, reason or operator is {@code null}.
     * @throws IllegalArgumentException If operator is empty.
     */
    void unmute(@NotNull UUID target, @NotNull String reason, @NotNull String operator);

    /**
     * Fetches information about the player and calls the given {@link Consumer} when ready. Does not block the current
     * thread.
     *
     * @param uuid     The {@link UUID} of the player
     * @param callback The callback
     * @throws NullPointerException If uuid or callback is {@code null}
     */
    @NonBlocking
    void fetchPlayerInfo(@NotNull UUID uuid, @NotNull Consumer<@NotNull PlayerInfo> callback);

    /**
     * Fetches information about the player and returns a {@link Future}. Does not block the current thread.
     *
     * @param uuid The {@link UUID} of the player
     * @return A {@link Future}
     * @throws NullPointerException If uuid or callback is {@code null}
     */
    @NonBlocking
    default @NotNull Future<PlayerInfo> fetchPlayerInfoFuture(@NotNull UUID uuid) {
        CompletableFuture<PlayerInfo> future = new CompletableFuture<>();
        this.fetchPlayerInfo(uuid, future::complete);
        return future;
    }

    /**
     * Fetches information about the player and blocks the current thread until the operation is complete.
     *
     * @param uuid The {@link UUID} of the player
     * @return An instance of {@link PlayerInfo} containing information about the player
     * @throws NullPointerException If uuid is {@code null}
     */
    @Blocking
    default @NotNull PlayerInfo fetchPlayerInfo(@NotNull UUID uuid) throws ExecutionException, InterruptedException {
        return this.fetchPlayerInfoFuture(uuid).get();
    }

    /**
     * Fetches information about a players ban and blocks the current thread until the operation is complete.
     *
     * @param uuid The {@link UUID} of the player
     * @return The ban (possibly {@code null})
     * @throws NullPointerException If uuid is {@code null}
     */
    @Blocking
    default @Nullable Ban fetchBan(@NotNull UUID uuid) throws ExecutionException, InterruptedException {
        return this.fetchPlayerInfo(uuid).getBan();
    }

    /**
     * Fetches information about a players mute and blocks the current thread until the operation is complete.
     *
     * @param uuid The {@link UUID} of the player
     * @return The mute (possibly {@code null})
     * @throws NullPointerException If uuid is {@code null}
     */
    @Blocking
    default @Nullable Ban fetchMute(@NotNull UUID uuid) throws ExecutionException, InterruptedException {
        return this.fetchPlayerInfo(uuid).getMute();
    }

    /**
     * Returns an instance of {@link Cloudy}.
     *
     * @return an instance of {@link Cloudy}
     */
    static @NotNull Cloudy getHandle() {
        throw new UnsupportedOperationException();
    }
}
