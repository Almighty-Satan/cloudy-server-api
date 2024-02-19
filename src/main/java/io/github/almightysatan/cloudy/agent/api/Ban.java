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

public interface Ban {

    /**
     * Returns the type of this ban.
     *
     * @return Returns the type of this ban
     */
    @NotNull BanType getType();

    /**
     * Returns the duration of this ban.
     *
     * @return the duration of this ban
     */
    long getDuration();

    /**
     * Returns the {@link TimeMeasure} of this bans duration.
     *
     * @return the {@link TimeMeasure} of this bans duration
     */
    @Nullable TimeMeasure getDurationTimeUnit();

    /**
     * Returns the reason for this ban.
     *
     * @return the reason for this ban
     */
    @Nullable String getReason();

    /**
     * Returns the {@link OperatorType}.
     *
     * @return the {@link OperatorType}
     */
    @NotNull OperatorType getOperatorType();

    /**
     * Returns the name of the operator.
     *
     * @return the name of the operator (possibly null)
     */
    @Nullable String getOperatorName();
}
