
let mesaAtualId = null;

// Modal Detalhe Mesa
function abrirModalDetalheMesa(mesaId, mesaNumero) {
    mesaAtualId = mesaId;
    var modal = document.getElementById("modalDetalheMesa-" + mesaId);
    document.getElementById("mesaIdInput").value = mesaId;
    if (modal) {
        modal.style.display = "block";
    }
}

function fecharModalDetalheMesa() {
    document.getElementById("modalDetalheMesa-" + mesaAtualId).style.display = "none";
}

// Modal Comanda
function abrirModalComanda() {
    document.getElementById("mesaIdInput").value = mesaAtualId;
    document.getElementById("modalNovaComanda").style.display = "flex";
}

function fecharModalComanda() {
    document.getElementById("modalNovaComanda").style.display = "none";
}

// Modal Detalhe Comanda
function abrirModalDetalheComanda(comandaId) {
    var modal = document.getElementById("modalDetalheComanda-" + comandaId);
    document.getElementById("comandaIdInput").value = comandaId;
    if (modal) {
        modal.style.display = "block";
    }
}

function fecharModalDetalheMesa() {
    document.getElementById("modalDetalheMesa-" + mesaAtualId).style.display = "none";
}

// Modal Pedido
function abrirModalPedido() {
    document.getElementById("comandaIdInput").value = comandaId;
    document.getElementById("modalNovoPedido").style.display = "flex";
}

function fecharModalPedido() {
    document.getElementById("modalNovoPedido").style.display = "none";
}