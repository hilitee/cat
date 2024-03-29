/*
 * Copyright (c) 2011-2018, Meituan Dianping. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dianping.cat.consumer.storage.builder;

import java.util.Arrays;
import java.util.List;

import org.unidal.lookup.annotation.Named;

import com.dianping.cat.message.Event;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;

@Named(type = StorageBuilder.class, value = StorageRPCBuilder.ID)
public class StorageRPCBuilder implements StorageBuilder {

	public final static String ID = "RPC";

	public final static int LONG_THRESHOLD = 100;

	public final static List<String> DEFAULT_METHODS = Arrays.asList("call");

	@Override
	public StorageItem build(Transaction t) {
		String id = null;
		String ip = "default";
		String method = "call";
		List<Message> messages = t.getChildren();

		for (Message message : messages) {
			if (message instanceof Event) {
				String type = message.getType();

				if (type.endsWith("Call.app")) {
					id = message.getName();
				}

				if (type.endsWith("Call.server")) {
					ip = message.getName();
					int index = ip.indexOf(':');

					if (index > -1) {
						ip = ip.substring(0, index);
					}
				}
			}
		}
		return new StorageItem(id, ID, method, ip, LONG_THRESHOLD);
	}

	@Override
	public List<String> getDefaultMethods() {
		return DEFAULT_METHODS;
	}

	@Override
	public String getType() {
		return ID;
	}

	@Override
	public boolean isEligable(Transaction t) {
		String type = t.getType();
		if(type == null) {
			return false;
		}

		return type.equals("Call") || type.endsWith(".Call");
	}

}
