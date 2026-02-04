let timeout = null;

function buscar() {
    clearTimeout(timeout);

    timeout = setTimeout(() => {
        const nome = document.getElementById("busca").value;
        const idCategoria = document.getElementById("categoria").value;

        const params = new URLSearchParams();
        if(nome) params.append("nome", nome);
        if(idCategoria) params.append("idCategoria", idCategoria);

        console.log("Categoria:", idCategoria);

        fetch(`/admin/produtos/buscar?${params.toString()}`)
            .then(resp => resp.text())
                .then(html => {
                    document.querySelector("tbody").innerHTML = html;
                });
    }, 300);
}

document.getElementById("busca").addEventListener("keyup", buscar);
document.getElementById("categoria").addEventListener("change", buscar);