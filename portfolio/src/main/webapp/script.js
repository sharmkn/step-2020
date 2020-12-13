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

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['I\'ve made a huge mistake.', 'There\'s always money in the banana stand.', 'Loose seal!', 'Her?', 'Hot ham water', 'Egg?', 'They\'re not magic tricks, Michael, they\'re illusions.'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

function changeMode() {
  const dark = (document.querySelectorAll("html")[0].style.backgroundColor !== 'rgb(237, 185, 209)' ? true : false);
  if (dark) {
    document.querySelectorAll("html")[0].style.backgroundColor = "#edb9d1";
    if (document.querySelectorAll("h1").length != 0) {
      document.querySelectorAll("h1").forEach(element => element.style.color = "#7d5169");
    }
    if (document.querySelectorAll("h2").length != 0) {
      document.querySelectorAll("h2").forEach(element => element.style.color = "#7d5169");
    }
    if (document.querySelectorAll("h4").length != 0) {
      document.querySelectorAll("h4").forEach(element => element.style.color = "#7d5169");
    }
    if (document.querySelectorAll("#info").length != 0) {
      document.querySelectorAll("#info").forEach(element => element.style.background = "#deabca");
      document.querySelectorAll("#info").forEach(element => element.style.borderColor = "#a8658e");
    }
    if (document.querySelectorAll("#nav").length != 0) {
      document.querySelectorAll("a").forEach(element => element.style.borderColor = "#deabca");
      document.querySelectorAll("#nav").forEach(element => element.style.backgroundColor = "#a8658e");
    }
  } else {
    document.querySelectorAll("html")[0].style.backgroundColor = "#b9ceed";
    if (document.querySelectorAll("h1").length != 0) {
      document.querySelectorAll("h1").forEach(element => element.style.color = "#515e7d");
    }
    if (document.querySelectorAll("h2").length != 0) {
      document.querySelectorAll("h2").forEach(element => element.style.color = "#515e7d");
    }
    if (document.querySelectorAll("h4").length != 0) {
      document.querySelectorAll("h4").forEach(element => element.style.color = "#515e7d");
    }
    if (document.querySelectorAll("#info").length != 0) {
      document.querySelectorAll("#info").forEach(element => element.style.background = "#abb9de");
      document.querySelectorAll("#info").forEach(element => element.style.borderColor = "#6578a8");
    }
    if (document.querySelectorAll("#nav").length != 0) {
      document.querySelectorAll("a").forEach(element => element.style.borderColor = "#abb9de");
      document.querySelectorAll("#nav").forEach(element => element.style.backgroundColor = "#6578a8");
    }
  }
  
}