/*
    Authors: Wu Runfei, LiuYing
    Date: May 1st, 2021
    File Name: manual.js

    This file realizes the functionalities of the menu button under the mobile mode.
*/

// Get elements which are needed to be manipulated.
const menuBtn = document.querySelector('.menu-btn');
const navEl = document.querySelector('nav');
const nav_links = document.querySelector('.nav-links')
const links = document.querySelectorAll('.nav-link')

// Animation about the menu button under the mobile mode.
let is_open = false
menuBtn.addEventListener("click", () => {
  if(is_open) {
    setTimeout(() => {
      navEl.classList.toggle("open")
    }, 500)
  } else {
    navEl.classList.toggle("open")
  }
  is_open = !is_open
  
  nav_links.classList.toggle("open");
  menuBtn.classList.toggle("open");
  links.forEach(link => {
    link.classList.toggle("open")
  })
});