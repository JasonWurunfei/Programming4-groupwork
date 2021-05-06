// Defining a function to display error message
function printError(elemId, hintMsg, display) {
  var el = document.getElementById(elemId);
  el.innerHTML = hintMsg;
  el.style["display"] = display;
  if (display == "unset") {
    el.classList.add("active");
  } else {
    el.classList.remove("active");
  }
}

function isEmpty(form, name, message) {
  var itemValue = form[name].value;
  var empty = true;
  if (itemValue == "") {
    printError(`${name}Err`, message, "unset");
  } else {
    printError(`${name}Err`, "", "none");
    empty = false;
  }
  return empty;
}

// Defining a function to validate Title
function validateTitle(form) {
  var isValid = isEmpty(form, "title", "Please enter a title") ? false : true;
  return isValid;
}

// Defining a function to validate Content
function validateContent(form) {
  var isValid = isEmpty(
    form,
    "content",
    "Please enter content for your message"
  )
    ? false
    : true;
  return isValid;
}

// Defining a function to validate Sender
function validateSender(form) {
  var isValid = isEmpty(form, "sender", "Please enter your user name")
    ? false
    : true;
  return isValid;
}

// Defining a function to validate URLAddress
function validateURLAddress(form) {
  console.log(form.URLAddress.value.length);
  var isValid = isEmpty(form, "URLAddress", "Please enter an URL address")
    ? false
    : true;
  if (form.URLAddress.value.length > 2000) {
    isValid = false;
    printError("URLAddressErr", "URL should be maximum 2000 characters");
  }
  return isValid;
}

// Defining a function to validate form
function validateForm() {
  var form = document.messageForm;

  // Prevent the form from being submitted if there are any errors
  // validating Full Name
  if (!validateTitle(form)) return false;

  // validating Content
  if (!validateContent(form)) return false;

  // validating Sender
  if (!validateSender(form)) return false;

  // validating URLAddress
  if (!validateURLAddress(form)) return false;
}

var form = document.messageForm;
var inputs = [
  form["title"],
  form["content"],
  form["sender"],
  form["URLAddress"],
];

inputs.forEach((input) => {
  var span = input.nextElementSibling.firstChild;
  input.addEventListener("focus", () => {
    span.classList.add("up");
  });

  input.addEventListener("blur", () => {
    if (input.value == "") {
      span.classList.remove("up");
      input.style["border-bottom"] = "3px solid rgba(180, 180, 180, 1)";
    } else {
      input.style["border-bottom"] = "3px solid white";
    }
  });
});

window.addEventListener("load", () => {
  inputs.forEach((input) => {
    if (input.value != "") {
      var span = input.nextElementSibling.firstChild;
      span.classList.add("up");
      input.style["border-bottom"] = "3px solid white";
    }
  });
});
