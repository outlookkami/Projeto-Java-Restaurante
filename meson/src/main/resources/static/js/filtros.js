function filtrarCategoria(categoria) {
    let produtos = document.querySelectorAll(".card-produto");

    produtos.forEach(produto => {
        if(categoria === "ALL") {
            produto.style.display = "block";
            return;
        }

        if(produto.categoria === categoria) {
            produto.style.display = "block";
        } else {
            produto.style.display = "none";
        }
    });
}