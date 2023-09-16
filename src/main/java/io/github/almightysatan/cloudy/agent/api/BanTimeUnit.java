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

public enum BanTimeUnit {

    SECONDS(1000L),
    MINUTES(SECONDS.multiplier * 60L),
    HOURS(MINUTES.multiplier * 60L),
    DAYS(HOURS.multiplier * 24L);

    private final long multiplier;

    BanTimeUnit(long multiplier) {
        this.multiplier = multiplier;
    }

    public long getMultiplier() {
        return multiplier;
    }

    public long toMillis(long duration) {
        return this.multiplier * duration;
    }
}
