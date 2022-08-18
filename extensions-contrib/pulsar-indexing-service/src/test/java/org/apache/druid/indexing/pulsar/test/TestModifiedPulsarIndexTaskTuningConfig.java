/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.druid.indexing.pulsar.test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.apache.druid.indexing.pulsar.PulsarIndexTaskTuningConfig;
import org.apache.druid.segment.IndexSpec;
import org.apache.druid.segment.incremental.AppendableIndexSpec;
import org.apache.druid.segment.writeout.SegmentWriteOutMediumFactory;
import org.joda.time.Period;

import javax.annotation.Nullable;

import java.io.File;

@JsonTypeName("PulsarTuningConfig")
public class TestModifiedPulsarIndexTaskTuningConfig extends PulsarIndexTaskTuningConfig
{
  private final String extra;

  @JsonCreator
  public TestModifiedPulsarIndexTaskTuningConfig(
      @JsonProperty("appendableIndexSpec") @Nullable AppendableIndexSpec appendableIndexSpec,
      @JsonProperty("maxRowsInMemory") @Nullable Integer maxRowsInMemory,
      @JsonProperty("maxBytesInMemory") @Nullable Long maxBytesInMemory,
      @JsonProperty("skipBytesInMemoryOverheadCheck") @Nullable Boolean skipBytesInMemoryOverheadCheck,
      @JsonProperty("maxRowsPerSegment") @Nullable Integer maxRowsPerSegment,
      @JsonProperty("maxTotalRows") @Nullable Long maxTotalRows,
      @JsonProperty("intermediatePersistPeriod") @Nullable Period intermediatePersistPeriod,
      @JsonProperty("basePersistDirectory") @Nullable File basePersistDirectory,
      @JsonProperty("maxPendingPersists") @Nullable Integer maxPendingPersists,
      @JsonProperty("indexSpec") @Nullable IndexSpec indexSpec,
      @JsonProperty("indexSpecForIntermediatePersists") @Nullable IndexSpec indexSpecForIntermediatePersists,
      // This parameter is left for compatibility when reading existing configs, to be removed in Druid 0.12.
      @Deprecated @JsonProperty("reportParseExceptions") @Nullable Boolean reportParseExceptions,
      @JsonProperty("handoffConditionTimeout") @Nullable Long handoffConditionTimeout,
      @JsonProperty("resetOffsetAutomatically") @Nullable Boolean resetOffsetAutomatically,
      @JsonProperty("skipSequenceNumberAvailabilityCheck") @Nullable Boolean skipSequenceNumberAvailabilityCheck,
      @JsonProperty("segmentWriteOutMediumFactory") @Nullable SegmentWriteOutMediumFactory segmentWriteOutMediumFactory,
      @JsonProperty("intermediateHandoffPeriod") @Nullable Period intermediateHandoffPeriod,
      @JsonProperty("logParseExceptions") @Nullable Boolean logParseExceptions,
      @JsonProperty("maxParseExceptions") @Nullable Integer maxParseExceptions,
      @JsonProperty("maxSavedParseExceptions") @Nullable Integer maxSavedParseExceptions,
      @JsonProperty("extra") String extra)
  {
    super(appendableIndexSpec, maxRowsInMemory, maxBytesInMemory, skipBytesInMemoryOverheadCheck, maxRowsPerSegment,
        maxTotalRows, intermediatePersistPeriod, basePersistDirectory, maxPendingPersists, indexSpec,
        indexSpecForIntermediatePersists, reportParseExceptions, handoffConditionTimeout, resetOffsetAutomatically,
        skipSequenceNumberAvailabilityCheck, segmentWriteOutMediumFactory, intermediateHandoffPeriod,
        logParseExceptions,
        maxParseExceptions, maxSavedParseExceptions);
    this.extra = extra;
  }

  public TestModifiedPulsarIndexTaskTuningConfig(PulsarIndexTaskTuningConfig base, String extra)
  {
    super(
        base.getAppendableIndexSpec(),
        base.getMaxRowsInMemory(),
        base.getMaxBytesInMemory(),
        base.isSkipBytesInMemoryOverheadCheck(),
        base.getMaxRowsPerSegment(),
        base.getMaxTotalRows(),
        base.getIntermediatePersistPeriod(),
        base.getBasePersistDirectory(),
        base.getMaxPendingPersists(),
        base.getIndexSpec(),
        base.getIndexSpecForIntermediatePersists(),
        base.isReportParseExceptions(),
        base.getHandoffConditionTimeout(),
        base.isResetOffsetAutomatically(),
        base.isSkipSequenceNumberAvailabilityCheck(),
        base.getSegmentWriteOutMediumFactory(),
        base.getIntermediateHandoffPeriod(),
        base.isLogParseExceptions(),
        base.getMaxParseExceptions(),
        base.getMaxSavedParseExceptions()
    );
    this.extra = extra;
  }

  @JsonProperty("extra")
  public String getExtra()
  {
    return extra;
  }

}
