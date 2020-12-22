// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/data")
public class DataServlet extends HttpServlet {

  DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  int commentSize = 5;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query("Comment").addSort("comment", SortDirection.ASCENDING);
    PreparedQuery results = datastore.prepare(query);

    List<String> comments = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
      String text = (String) entity.getProperty("comment");
      String username = (String) entity.getProperty("username");
      String comment = username + " said:" + "\n" + "\"" + text + "\"";
      comments.add(comment);
    }

    Gson gson = new Gson();

    int displaySize = commentSize < comments.size() ? commentSize : comments.size();

    List<String> displayedComments = comments.subList(0, displaySize);

    response.setContentType("text/html;");
    for (int i = 0; i < displaySize; i++) {
      response.getWriter().println(comments.get(i));
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String requestedSize = request.getParameter("commentSize");
    if (requestedSize == " ") {
        commentSize = 5;
    } else {
        commentSize = Integer.parseInt(requestedSize);
    }
    if (!request.getParameter("comment").isEmpty() && !request.getParameter("username").isEmpty()) {
        Entity commentEntity = new Entity("Comment");
        commentEntity.setProperty("comment", request.getParameter("comment"));
        commentEntity.setProperty("username", request.getParameter("username"));
        datastore.put(commentEntity);
    }
    response.sendRedirect("/form.html");
  }
}
