function fecharModal() {
     document.getElementById("modal").style.display = "none";
}

function modalAdd() {
    document.getElementById("modal").style.display = "flex";
}

function previewImagem(event) {
    const img = document.getElementById("preview");
    img.src = URL.createObjectURL(event.target.files[0])
    img.style.display = "block";
}


