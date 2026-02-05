let timeout = null;

function buscar() {
    clearTimeout(timeout);

    timeout = setTimeout(() => {
        const nome = document.getElementById("busca").value;
        const perfil = document.getElementById("perfil").value;

        const params = new URLSearchParams();
        if(nome) params.append("nome", nome);
        if(perfil) params.append("perfil", perfil);

        console.log("Perfil:", perfil);

        fetch(`/admin/produtos/buscar?${params.toString()}`)
            .then(resp => resp.text())
                .then(html => {
                    document.querySelector("tbody").innerHTML = html;
                });
    }, 300);
}

document.getElementById("busca").addEventListener("keyup", buscar);
document.getElementById("perfil").addEventListener("change", buscar);