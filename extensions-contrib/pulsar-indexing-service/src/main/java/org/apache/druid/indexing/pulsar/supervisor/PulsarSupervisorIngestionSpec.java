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

package org.apache.druid.indexing.pulsar.supervisor;

import org.apache.druid.indexing.seekablestream.supervisor.SeekableStreamSupervisorIngestionSpec;
import org.apache.druid.indexing.seekablestream.supervisor.SeekableStreamSupervisorTuningConfig;
import org.apache.druid.segment.indexing.DataSchema;

public class PulsarSupervisorIngestionSpec extends SeekableStreamSupervisorIngestionSpec {

  private final DataSchema dataSchema;
  private final PulsarSupervisorIOConfig ioConfig;
//  private final KinesisSupervisorTuningConfig tuningConfig;

  public PulsarSupervisorIngestionSpec(DataSchema dataSchema,
                                       PulsarSupervisorIOConfig ioConfig,
                                       SeekableStreamSupervisorTuningConfig tuningConfig) {
    super(dataSchema, ioConfig, tuningConfig);
    this.dataSchema = dataSchema;
    this.ioConfig = ioConfig;
//    this.tuningConfig = tuningConfig == null ? KinesisSupervisorTuningConfig.defaultConfig() : tuningConfig;
  }
}
