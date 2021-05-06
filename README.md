# This repository is for programming4 course group work



## Work Description

This is a group work activity and the project should be done by two students. Both students are expected to contribute equally to the project work.



## Requirements

Functional Requirements

Must:

1. Frontend (html/CSS/JavaScript)

   1. Form for user to enter message information.

      1. Title (String)
      2. Content (String)
      3. Sender (String)
      4. URL address (String)

   2. Form should be submitted to the server by using HTTP POST.

   3. Use JavaScript to validate the form data before its submission to the server.

      1. All four data fields are mandatory (should not be an empty string) 

      2. URL should be maximum 2000 characters.

          

2. Backend (Java/Spring)

   1. Process the parameters from the POST request, create a new message object.
   2. The message object should be store inside of a local `java.util.List` variable.
   3. A page for displaying all messages.
   4. A homepage with two links: Create a new message / View all messages.

 

Could:

1. persistence
2. Separate display page
3. A nice-looking fallback page when the URL is not valid.
4. CRUD

 

Non-Functional Requirements:

1. HTML5 elements should be applied across all your web pages.
2.  Create and use at least 10 different style rules for your web pages.
3. Use different kinds of CSS selectors. 





## Reference

The UI design is inspired by https://kuon.space/ 

- All the JavaScript and CSS are original
- No third-party library is used