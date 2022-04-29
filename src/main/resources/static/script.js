function helpPopup() {
    const helps = document.getElementsByClassName("help");
        for (let i = 0; i < helps.length; i++) {
            helps[i].classList.toggle("show");
            helps[i].classList.toggle("hide");
        }
    }