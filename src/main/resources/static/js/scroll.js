function Slidable(el, pages) {
  this.el = el
  this.pages = pages
  this.currentIndex = 0

  this.el.parentElement.style.overflow = "hidden"

  window.addEventListener("wheel", event => {
    let now = event.timeStamp;
    Slidable.scrollDetect(now, {
      event: event,
      slidable: this
    })
  });
}

Slidable.prototype.slide = function(distance) {
  this.el.style.transform = `translateY(${distance}px)`
}

Slidable.prototype.getPageHeight = function(index) {
  if (index === undefined || index >= this.pages.length )
    throw `invalid index "${index}"`;
  
  let page = this.pages[index]
  return -page.offsetTop
}

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
