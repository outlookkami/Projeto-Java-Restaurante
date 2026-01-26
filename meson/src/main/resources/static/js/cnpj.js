document.getElementById("cnpj").addEventListener("blur", verifCnpj);


function verifCnpj(){
        const cnpj = document.getElementById("cnpj").value.replace(/\D/g, '');
        if (cnpj.length !== 14) return;

        console.log("Chamando API...");

        fetch(`/api/cnpj/${cnpj}`)
          .then(response => {
            if (!response.ok) {
              throw new Error("CNPJ inválido");
            }
            return response.json();
          })
          .then(data => {
            console.log(data);
            document.getElementById("razaoSocial").value = data.razao_social;
            document.getElementById("nomeFantasia").value = data.nome_fantasia;
            document.getElementById("cnae").value = data.cnae_fiscal;
            document.getElementById("descricaoCnae").value = data.cnae_fiscal_descricao;
          })
          .catch(error => {
            console.error(error);
          });

}

