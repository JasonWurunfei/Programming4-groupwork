/*
    Authors: Wu Runfei, LiuYing
    Date: May 2nd, 2021
    File Name: home.js

    This file contains the logic methods related to the home page.
*/

// Get the elements which need to be manipulated here.
const section_bars = document.getElementById("section-bars");
const slogan = document.querySelector(".slogan");
const desc = document.querySelectorAll(".desc");

/*
   Add the event listener to the menu button, and
   realizes the functionality that hide the page content
   when the menu button is open.
*/
menuBtn.addEventListener("click", () => {
  if (is_open) {
    section_bars.style = "opacity: 0";
    slogan.style = "opacity: 0";
    desc[0].style = "opacity: 0";
    desc[1].style = "opacity: 0";
  } else {
    section_bars.style = "opacity: 1";
    slogan.style = "opacity: 1";
    desc[0].style = "opacity: 1";
    desc[1].style = "opacity: 1";
  }
})


/*
   Get the <main>, and <section> here. Assign main
   to be a slidable object. Realize the functionality
   for scrolling the page.
*/
let main = document.querySelector("main");
const sections = document.querySelectorAll("section");
main = new Slidable(main, sections);

main.setEaseFunction("cubic-bezier(.86,0,.07,1)");
main.setSlideDuration(1);
const bars = document.querySelectorAll(".bar")

/*
    Realize the functionality that changes the length
    of the bars when the corresponding page is observed.
*/
let obs = new IntersectionObserver((entires, _) => {
  entires.forEach(entry => {
    let index = [].slice.call(sections).indexOf(entry.target)
    if(entry.isIntersecting) {
      bars[index].classList.add("active")
    } else {
      bars[index].classList.remove("active")
    }
  })
}, {threshold: 0.9})

sections.forEach(section => {
  obs.observe(section)
})

/*
    Realize the functionality that removes the arrow
    when the current page is not index.html.
*/
const arrow = document.getElementById("arrow")
let arrowObs = new IntersectionObserver((entries, _) => {
  entries.forEach(entry => {
    if(!entry.isIntersecting) {
      arrow.classList.add("gone")
    }
    else {
      arrow.classList.remove("gone")    
    }
  })
}, {threshold: 0.9})

arrowObs.observe(sections[0])

//Click the section bar, and goto the corresponding page.
bars.forEach((bar, i) => {
  bar.addEventListener("click", () => {
    main.goto(i)
  })
})

// Make the page be more animated. Add some animations to the page.
const hero = document.getElementById("hero")
const header1 = document.querySelector(".slogan h1")
const para1 = document.querySelector(".slogan p")
const headers = document.querySelectorAll(".desc h2")
const paras = document.querySelectorAll(".desc p")
const btns = document.querySelectorAll(".btn")
const imgFrames = document.querySelectorAll(".img-frame");

window.addEventListener("load", () => {
  hero.classList.add("big")
  header1.classList.add("enter")
  para1.classList.add("enter")
})

let homeObs = new IntersectionObserver((entries, _) => {
  entries.forEach(entry => {
    if(entry.isIntersecting) {
      hero.classList.add("big")
      header1.classList.add("enter")
      para1.classList.add("enter")
    } else {
      hero.classList.remove("big")
      header1.classList.remove("enter")
      para1.classList.remove("enter")
    }
  })
}, {threshold: 0.4})

homeObs.observe(sections[0])

let subPages = [].slice.call(sections, 0)
subPages.shift()

subPages.forEach((page, i) => {
  let obs = new IntersectionObserver((entries, _) => {
    entries.forEach(entry => {
      if(entry.isIntersecting) {
        imgFrames[i].classList.add("big")
        headers[i].classList.add("enter")
        paras[i].classList.add("enter")
        btns[i].classList.add("enter")
      } else {
        imgFrames[i].classList.remove("big")
        headers[i].classList.remove("enter")
        paras[i].classList.remove("enter")
        btns[i].classList.remove("enter")
      }
    })
  }, {threshold: 0.4})

  obs.observe(page)
})

// Click the arrow, remove it, and goto the next page.
 const findArrow = document.querySelector("#arrow");
 findArrow.addEventListener("click", () => {
   findArrow.classList.add("gone");
   main.goto(1);
 })
