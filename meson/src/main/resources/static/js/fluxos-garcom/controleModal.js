
let mesaAtualId = null;
let comandaAtualId = null;

// Modal Detalhe Mesa
function abrirModalDetalheMesa(mesaId) {
    const url = `/garcom/mesas/${mesaId}/detalhe`;
    fetch(url)
        .then(response => response.text())
        .then(html => {
            document.getElementById("modalConteudo").innerHTML = html;
            document.getElementById("modalPrincipal").style.display = "block";
        });
}

function fecharModalDetalheMesa() {
    document.getElementById("modalConteudo").innerHTML = "";
    document.getElementById("modalPrincipal").style.display = "none";
}

//-----------------------------------------------------------------------

// Modal Comanda
function abrirModalComanda(mesaId) {
    document.getElementById("mesaIdInput").value = mesaId;
    document.getElementById("modalNovaComanda").style.display = "flex";
}

function fecharModalComanda() {
    document.getElementById("modalNovaComanda").style.display = "none";
}

// Modal Detalhe Comanda
function abrirModalDetalheComanda(comandaId) {
    const url = `/garcom/comandas/${comandaId}/detalhe`;

    fetch(url)
        .then(response => response.text())
        .then(html => {
            document.getElementById("modalConteudo").innerHTML = html;
            document.getElementById("modalPrincipal").style.display = "block";
        });
}

//-----------------------------------------------------------------------

// Modal Pedido
function abrirModalPedido(comandaId) {
    document.getElementById("comandaIdInput").value = comandaId;
    document.getElementById("modalNovoPedido").style.display = "flex";
}

function fecharModalPedido() {
    document.getElementById("modalNovoPedido").style.display = "none";
}

function selecionarProduto(id) {
    console.log("Produto selecionado: ", id)
}

//-----------------------------------------------------------------------

// Modal único
function abrirModal(url) {
    fetch(url)
        .then(response => response.text())
        .then(html => {
            const modalPrincipal = document.getElementById("modalPrincipal");
            modalPrincipal.innerHTML = html;
            modalPrincipal.style.display = "flex";
        })
        .catch(error => console.error('Erro ao carregar modal: ', error));
}

function fecharModal() {
    document.getElementById("modalPrincipal").style.display = "none";
}
