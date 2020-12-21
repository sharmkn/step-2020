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

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/data")
public class DataServlet extends HttpServlet {

  private ArrayList<String> comments = new ArrayList<String>();
  private ArrayList<String> commenter = new ArrayList<String>();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    int count = 0;
    while (count < comments.size()) {
        String comment = "\"" + comments.get(count) + "\"" + " -" + commenter.get(count);
        response.getWriter().println(comment);
        count++;
    }    
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    if ((request.getParameter("comment") != null) && (request.getParameter("username") != null)) {
        Entity commentEntity = new Entity("Comment");
        commentEntity.setProperty("comment", request.getParameter("comment"));
        commentEntity.setProperty("user", request.getParameter("username"));
        datastore.put(commentEntity);
        comments.add(request.getParameter("comment"));
        commenter.add(request.getParameter("username"));
    }
    response.sendRedirect("/form.html");
  }
}
