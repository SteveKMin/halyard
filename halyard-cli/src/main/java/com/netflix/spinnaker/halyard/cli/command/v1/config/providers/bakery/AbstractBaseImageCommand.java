/*
 * Copyright 2017 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.netflix.spinnaker.halyard.cli.command.v1.config.providers.bakery;

import com.netflix.spinnaker.halyard.cli.services.v1.Daemon;
import com.netflix.spinnaker.halyard.config.model.v1.node.BaseImage;

public abstract class AbstractBaseImageCommand extends AbstractHasBaseImageCommand {
  @Override
  public String getCommandName() {
    return "base-image";
  }

  @Override
  public String getDescription() {
    return "Manage and view Spinnaker configuration for the " + getProviderName() + " provider's base image.";
  }

  protected AbstractBaseImageCommand() {
    registerSubcommand(new DeleteBaseImageCommandBuilder()
        .setProviderName(getProviderName())
        .build()
    );

    registerSubcommand(new GetBaseImageCommandBuilder()
        .setProviderName(getProviderName())
        .build()
    );

    registerSubcommand(new ListBaseImagesCommandBuilder()
        .setProviderName(getProviderName())
        .build()
    );
  }

  @Override
  protected void executeThis() {
    showHelp();
  }

  private BaseImage getBaseImage(String baseImageId) {
    String currentDeployment = getCurrentDeployment();
    return Daemon.getBaseImage(currentDeployment, getProviderName(), baseImageId, !noValidate);
  }
}