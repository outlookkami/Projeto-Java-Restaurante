function alterarQuantidade(botao, valor) {
    const input = botao.parentElement.querySelector("input");
    let quantidade = parseInt(input.value) || 0;
    quantidade += valor;
    if(quantidade < 0) quantidade = 0;
    input.value = quantidade;
}