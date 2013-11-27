#language: pt
Funcionalidade: Notificar alterações por e-mail

  @MANUAL
  Cenário: Apenas um e-mail configurado para receber o e-mail
    Dado que algum arquivo checado possui alguma atualização
    E apenas um e-mail está configurado para receber e-mail
    Quando for iniciada a checagem de arquivos
    Então os arquivos configurados são verificados
    E o sistema atualiza o arquivo no svn
    E envia um e-mail informando a atualização de cada arquivo

  @MANUAL
  Cenário: Mais de um e-mail configurado para receber o e-mail
    Dado que algum arquivo checado possui alguma atualização
    E apenas mais de um e-mail está configurado para receber e-mail
    Quando for iniciada a checagem de arquivos
    Então os arquivos configurados são verificados
    E o sistema atualiza o arquivo no svn
    E envia um e-mail informando a atualização de cada arquivo

  @MANUAL
  Cenário: Erro ao enviar e-mail
    Dado que algum arquivo checado possui alguma atualização
    E não é possível enviar e-mails para informar a atualização
    Quando for iniciada a checagem de arquivos
    Então os arquivos configurados são verificados
    E o sistema atualiza o arquivo no svn
    E o sistema registra em log o erro ao enviar o e-mail
