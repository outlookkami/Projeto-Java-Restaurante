function filtrarCategoria(categoria) {
    let produtos = document.querySelectorAll(".card-produto");

    produtos.forEach(produto => {
        if(categoria === "ALL") {
            produto.style.display = "flex";
            return;
        }

        if(produto.dataset.categoria.toLowerCase() === categoria.toLowerCase()) {
            produto.style.display = "flex";
        } else {
            produto.style.display = "none";
        }
    });

    trocarBotaoAtivo(categoria);
}

function trocarBotaoAtivo(categoria) {
    let botoes = document.querySelectorAll(".botao-categoria");

    botoes.forEach(botao => {
        botao.classList.remove("ativo");

        if(botao.dataset.categoria.toLowerCase() === categoria.toLowerCase()) {
            botao.classList.add("ativo");
        }
    });
}