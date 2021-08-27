let pointsAdd = document.querySelector(".points-add");
let form = document.querySelector(".form");

pointsAdd.addEventListener("click", () => {
  console.log(form);

  form.classList.toggle("active");

  if (form.classList.contains("active")) {
    pointsAdd.textContent = "Отмена";
    pointsAdd.style.marginBottom = "50px";
  } else {
    pointsAdd.textContent = "Добавить баллы";
    pointsAdd.style.marginBottom = 0;
  }
});
