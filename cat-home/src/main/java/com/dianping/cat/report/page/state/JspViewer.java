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
package com.dianping.cat.report.page.state;

import org.unidal.web.mvc.view.BaseJspViewer;

import com.dianping.cat.report.ReportPage;

public class JspViewer extends BaseJspViewer<ReportPage, Action, Context, Model> {
	@Override
	protected String getJspFilePath(Context ctx, Model model) {
		Action action = model.getAction();

		switch (action) {
		case HOURLY:
			return JspFile.HOURLY.getPath();
		case HISTORY:
			return JspFile.HISTORY.getPath();
		case GRAPH:
			return JspFile.GRAPH.getPath();
		case HISTORY_GRAPH:
			return JspFile.HISTORY_GRAPH.getPath();
		case HOURLY_P:
			return JspFile.HOURLY_P.getPath();
		case HISTORY_P:
			return JspFile.HISTORY_P.getPath();
		case GRAPH_P:
			return JspFile.GRAPH_P.getPath();
		case HISTORY_GRAPH_P:
			return JspFile.HISTORY_GRAPH_P.getPath();
		}

		throw new RuntimeException("Unknown action: " + action);
	}
}
