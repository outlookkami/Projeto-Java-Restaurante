
let mesaAtualId = null;

// Modal Detalhe Mesa
function abrirModalDetalheMesa(mesaId, mesaNumero) {
    mesaAtualId = mesaId;
    document.getElementById("mesaTitulo").innerText = "Mesa " + mesaNumero;
    document.getElementById("mesaIdInput").value = mesaId;
    document.getElementById("modalDetalheMesa").style.display = "flex";
}

function fecharModalDetalheMesa() {
    document.getElementById("modalDetalheMesa").style.display = "none";
}

// Modal Comanda
function abrirModalComanda() {
    document.getElementById("mesaIdInput").value = mesaAtualId;
    document.getElementById("modalNovaComanda").style.display = "flex";
}

function fecharModalComanda() {
    document.getElementById("modalNovaComanda").style.display = "none";
}