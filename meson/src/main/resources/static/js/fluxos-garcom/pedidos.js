function filtrarProdutos(){
    const busca = document.getElementById("buscarProduto")?.value?.toLowerCase() || "";
    const produtos = document.querySelectorAll(".produto");

    produtos.forEach(produto => {
        const nome = produto.querySelector("p").innerText.toLowerCase();
        produto.style.display = nome.includes(busca) ? "" : "none";
    });
}

function atualizarPedido() {
    const produtos = document.querySelectorAll(".produto");
    const lista = document.getElementById("listaPedido");
    const totalPedido = document.getElementById("totalPedido");

    lista.innerHTML = "";
    let total = 0;

    produtos.forEach(produto => {
        const quantidade = parseInt(produto.querySelector(".input-quant-pedido").value);

        if(quantidade > 0) {
            const nome = produto.dataset.nome;
            const preco = parseFloat(produto.dataset.preco);
            const subtotal = preco * quantidade;
            total += subtotal;

            lista.innerHTML += `<div class="resumo-produto">${quantidade}x ${nome} <span>R$ ${subtotal.toFixed(2)}</span></div>`;
        }
    });

    totalPedido.innerText = total.toFixed(2);
}

function alterarQuantidade(botao, valor) {
    const input = botao.parentElement.querySelector("input");
    let quantidade = parseInt(input.value) || 0;
    quantidade += valor;
    if(quantidade < 0) quantidade = 0;
    input.value = quantidade;

    atualizarPedido();
}