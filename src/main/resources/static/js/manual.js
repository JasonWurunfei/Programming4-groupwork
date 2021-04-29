const menuBtn = document.querySelector('.menu-btn');
const navEl = document.querySelector('nav');
const nav_links = document.querySelector('.nav-links')
const links = document.querySelectorAll('.nav-link')

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