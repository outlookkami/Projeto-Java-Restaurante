//alert("JS carregou");
document.getElementById("cnpj").addEventListener("blur", verifCnpj);


function verifCnpj(){
        const cnpj = document.getElementById("cnpj").value.replace(/\D/g, '');
        if (cnpj.length !== 14) return;

        console.log("Chamando API...");

        fetch(`/api/cnpj/${cnpj}`)
            .then(res => {
                console.log("status:", res.status);
                return res.json();
            })
            .then(data => {
                console.log("RETORNO:", data);
            })
            .catch(err => console.error("ERRO:", err));
}

//function verifCnpj() {
//
//    const cnpj = document.getElementById("cnpj").value.replace(/\D/g, '');
//    if(cnpj.lenght !== 14) return;
//
//    fetch(`/api/cnpj/${cnpj}`)
//        .then(res => {
//            if(!res.ok) throw new Error();
//            return res.json();
//        })
//
//        .then(data => {
//            document.getElementById("razaoSocial").value = data.razaoSocial;
//            document.getElementById("nomeFantasia").value = data.nomeFantasia;
//            document.getElementById("cnae").value = data.cnae;
//            document.getElementById("descricaoCnae").value = data.descricaoCnae;
//        })
//
//        .catch(() => alert("CNPJ inválido!"));
//}