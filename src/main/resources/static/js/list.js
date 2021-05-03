let rows = document.querySelectorAll("tr + tr")
const space = 15
const timeGap = 100

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

async function show() {
  for (const [i, row] of rows.entries()) {
    row.style['transform'] += `translateY(${(1+i)*space}px)`
    row.style['opacity']  = "1"
    await sleep(timeGap)
  }
  rows = document.querySelectorAll("tbody tr")
  rows.forEach(row => {
    const old = row.style['transform']
    row.addEventListener("mouseover", () => {
      row.style['transform'] = old + " scale(1.05)"
    })
    row.addEventListener("mouseout", () => {
      row.style['transform'] = old
    })
  })
}
window.addEventListener("load", show)

