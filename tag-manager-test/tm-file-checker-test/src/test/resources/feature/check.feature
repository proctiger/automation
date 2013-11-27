#language: pt
Funcionalidade: Checar atualização do arquivo

  @MANUAL
  Cenário: Arquivo não sofreu modificação
    Dado que algum arquivo checado possui alguma atualização
    Quando for iniciada a checagem de arquivos
    Então os arquivos configurados são verificados
    E o sistema registra em log uma mensagem que nenhum arquivo será atualizado

  @MANUAL
  Cenário: Arquivo sofreu modificação
    Dado que algum arquivo checado possui alguma atualização
    Quando for iniciada a checagem de arquivos
    Então os arquivos configurados são verificados
    E o sistema atualiza o arquivo no svn
    E envia um e-mail informando a atualização de cada arquivo
