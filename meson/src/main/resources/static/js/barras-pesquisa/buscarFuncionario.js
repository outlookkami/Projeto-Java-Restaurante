let timeout = null;

function buscar() {
    clearTimeout(timeout);

    timeout = setTimeout(() => {
        const nome = document.getElementById("buscaFunc").value;
        const perfilUsu = document.getElementById("perfil").value;

        const params = new URLSearchParams();
        if(nome) params.append("nome", nome);
        if(perfilUsu) params.append("perfil", perfilUsu);

        console.log("Perfil:", perfilUsu);

        fetch(`/admin/funcionarios/buscar?${params.toString()}`)
            .then(resp => resp.text())
                .then(html => {
                    document.querySelector("tbody").innerHTML = html;
                });
    }, 300);
}

document.getElementById("buscaFunc").addEventListener("keyup", buscar);
document.getElementById("perfil").addEventListener("change", buscar);