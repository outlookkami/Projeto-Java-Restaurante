function abrirModalAnalise(element) {
    const id = element.getAttibute("data-id");

    fetch(`/admin-geral/analisarRestaurantes/${id}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById("modalNomeFantasia").innerText = data.nomeFantasia;
            document.getElementById("modalCnpj").innerText = data.cnpj;
            document.getElementById("modalRazaoSocial").innerText = data.razaoSocial;
            document.getElementById("modalCnae").innerText = data.cnae;
            document.getElementById("modalDescCnae").innerText = data.descricaoCnae;
            document.getElementById("modalTelefone").innerText = data.telefone;
            document.getElementById("modalRestaurante").style.display = "block";
        })
        .catch(error => {
            console.error("Erro ao encontrar restaurante: ", error);
        });
}

function fecharModalAnalise() {
    document.getElementById("modalRestaurante").style.display = "none";
}