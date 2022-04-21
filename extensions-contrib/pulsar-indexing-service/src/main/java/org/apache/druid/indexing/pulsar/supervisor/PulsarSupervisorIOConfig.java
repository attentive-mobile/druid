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
 *
 *
 */

package org.apache.druid.indexing.pulsar.supervisor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import org.apache.druid.data.input.InputFormat;
import org.apache.druid.indexing.seekablestream.supervisor.SeekableStreamSupervisorIOConfig;
import org.apache.druid.indexing.seekablestream.supervisor.autoscaler.AutoScalerConfig;
import org.apache.druid.java.util.common.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;

import javax.annotation.Nullable;

import java.util.Map;

public class PulsarSupervisorIOConfig extends SeekableStreamSupervisorIOConfig
{
  public static final long DEFAULT_POLL_TIMEOUT_MILLIS = 100;
  public static final String SERVICE_URL_KEY = "serviceUrl";
  private final long pollTimeout;
  private final Map<String, Object> consumerProperties;
  private final String serviceUrl;

  public PulsarSupervisorIOConfig(@JsonProperty("topic") String topic,
                                  @JsonProperty("inputFormat") InputFormat inputFormat,
                                  @JsonProperty("replicas") Integer replicas,
                                  @JsonProperty("taskCount") Integer taskCount,
                                  @JsonProperty("taskDuration") Period taskDuration,
                                  @JsonProperty("consumerProperties") Map<String, Object> consumerProperties,
                                  @Nullable @JsonProperty("autoScalerConfig") AutoScalerConfig autoScalerConfig,
                                  @JsonProperty("pollTimeout") Long pollTimeout,
                                  @JsonProperty("startDelay") Period startDelay,
                                  @JsonProperty("period") Period period,
                                  @JsonProperty("useEarliestOffset") Boolean useEarliestOffset,
                                  @JsonProperty("completionTimeout") Period completionTimeout,
                                  @JsonProperty("lateMessageRejectionPeriod") Period lateMessageRejectionPeriod,
                                  @JsonProperty("earlyMessageRejectionPeriod") Period earlyMessageRejectionPeriod,
                                  @JsonProperty("lateMessageRejectionStartDateTime")
                                      DateTime lateMessageRejectionStartDateTime)
  {
    super(topic, inputFormat, replicas, taskCount, taskDuration, startDelay, period, useEarliestOffset,
        completionTimeout, lateMessageRejectionPeriod, earlyMessageRejectionPeriod, autoScalerConfig,
        lateMessageRejectionStartDateTime);

    this.consumerProperties = Preconditions.checkNotNull(consumerProperties, "consumerProperties");
    Preconditions.checkNotNull(
        consumerProperties.get(SERVICE_URL_KEY),
        StringUtils.format("consumerProperties must contain entry for [%s]", SERVICE_URL_KEY)
    );

    this.serviceUrl = (String) consumerProperties.get(SERVICE_URL_KEY);
    this.pollTimeout = pollTimeout != null ? pollTimeout : DEFAULT_POLL_TIMEOUT_MILLIS;
  }

  @JsonProperty
  public String getTopic()
  {
    return getStream();
  }

  @JsonProperty
  public Map<String, Object> getConsumerProperties()
  {
    return consumerProperties;
  }

  @JsonProperty
  public long getPollTimeout()
  {
    return pollTimeout;
  }

  public String getServiceUrl()
  {
    return serviceUrl;
  }
}
