const section_bars = document.getElementById("section-bars");
const slogan = document.querySelector(".slogan");

menuBtn.addEventListener("click", () => {
  if (is_open) {
    section_bars.style = "opacity: 0";
    slogan.style = "opacity: 0";
  } else {
    section_bars.style = "opacity: 1";
    slogan.style = "opacity: 1";
  }
})


let main = document.querySelector("main");
const sections = document.querySelectorAll("section");
main = new Slidable(main, sections);

main.setEaseFunction("cubic-bezier(.86,0,.07,1)");
main.setSlideDuration(1);
const bars = document.querySelectorAll(".bar")

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

const arrow = document.getElementById("arrow")
let arrowObs = new IntersectionObserver((entries, _) => {
  entries.forEach(entry => {
    if(!entry.isIntersecting) {
      arrow.classList.add("gone")
      console.log(1)
    }
    else {
      arrow.classList.remove("gone")    
      console.log(2)  
    }
  })
}, {threshold: 0.9})

arrowObs.observe(sections[0])

bars.forEach((bar, i) => {
  bar.addEventListener("click", () => {
    main.goto(i)
  })
})