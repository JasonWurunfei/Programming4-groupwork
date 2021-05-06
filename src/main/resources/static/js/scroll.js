/*
    Authors: Wu Runfei, LiuYing
    Date: May 3rd, 2021
    File Name: scroll.js

    This file realizes the functionality which can make the page scrollable.
*/

// Encapsulate the lib for the page => Slidable
function Slidable(el, pages) {
  this.el = el
  this.pages = pages
  this.currentIndex = 0

  this.el.parentElement.style.overflow = "hidden"

  // Monitor the resize operation from the user.
  window.addEventListener("wheel", event => {
    let now = event.timeStamp;
    Slidable.scrollDetect(now, {
      event: event,
      slidable: this
    })
  });
  window.addEventListener("resize", this.snap())
}

// Functionality about scrolling the page.
Slidable.prototype.slide = function(distance) {
  this.el.style.transform = `translateY(${distance}px)`
}

Slidable.prototype.getPageHeight = function(index) {
  if (index === undefined || index >= this.pages.length )
    throw `invalid index "${index}"`;
  
  let page = this.pages[index]
  return -page.offsetTop
}

// Functionality about going to the page with the corresponding index.
Slidable.prototype.goto = function(index) {
  this.slide(this.getPageHeight(index))
}

Slidable.prototype.nextPage = function() {
  if (!(this.currentIndex+1 == this.pages.length)) {
    this.currentIndex += 1
  }
  this.goto(this.currentIndex)
}

Slidable.prototype.previousPage = function() {
  if (!(this.currentIndex == 0)) {
    this.currentIndex -= 1
  }
  this.goto(this.currentIndex)
}

Slidable.prototype.setEaseFunction = function(func) {
  this.el.style['transition-timing-function'] = func
}

Slidable.prototype.setSlideDuration = function(duration) {
  this.el.style['transition-duration'] = `${duration}s`
}

Slidable.prototype.getCurrentPage = function() {
  return this.pages[this.currentIndex]
}

Slidable.prototype.snap = function() {
  this.goto(this.currentIndex)
}

// static methods
Slidable.scrollDetect = function(
  {event, slidable}) {
  if (event.deltaY > 0) {
    slidable.nextPage()
  } else {
    slidable.previousPage()
  }
}

Slidable.timeBlock = 500
Slidable.scrollDetect = timeFilter(
  Slidable.scrollDetect, Slidable.timeBlock)

function timeFilter(callback, duration) {
  let last = 0;
  let wrap = function(now, args) {
    let returnValue
    if (now - last > duration) {
      returnValue = callback(args)
      last = now
    }
    return returnValue
  }
  return wrap
}
