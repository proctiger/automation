#language: pt
Funcionalidade: Checar lista de arquivos

  @MANUAL
  Cenário: Validar apenas um arquivo
    Dado que há apenas um arquivo a ser verificado
    Quando for iniciada a checagem de arquivos
    Então os arquivos configurados são verificados

  @MANUAL
  Cenário: Validar mais de um arquivo
    Dado que há mais de um arquivo a ser verificado
    Quando for iniciada a checagem de arquivos
    Então os arquivos configurados são verificados

  @MANUAL
  Cenário: Erro ao efetuar o download de um arquivo da lista - Apenas um arquivo configurado
    Dado que há apenas um arquivo a ser verificado
    E não é possível efetuar o download do arquivo
    Quando for iniciada a checagem de arquivos
    Então o sistema registra o erro em log

  @MANUAL
  Cenário: Erro ao efetuar o download de um arquivo da lista - Mais de um arquivo configurado
    Dado que há mais de um arquivo a ser verificado
    E não é possível efetuar o download de um arquivo
    Quando for iniciada a checagem de arquivos
    Então o sistema registra o erro em log
    E efetua a verificação dos demais arquivos

  @MANUAL
  Cenário: Erro ao acessar um arquivo da lista - Apenas um arquivo configurado
    Dado que há apenas um arquivo a ser verificado
    E ocorre um erro na leitura do arquivo existente
    Quando for iniciada a checagem de arquivos
    Então o sistema registra o erro em log

  @MANUAL
  Cenário: Erro ao acessar de um arquivo da lista - Mais de um arquivo configurado
    Dado que há mais de um arquivo a ser verificado
    E ocorre um erro na leitura de um arquivo existente
    Quando for iniciada a checagem de arquivos
    Então o sistema registra o erro em log
    E efetua a verificação dos demais arquivos

  @MANUAL
  Cenário: Erro ao comitar no svn - Apenas um arquivo configurado
    Dado que há apenas um arquivo a ser verificado
    E o arquivo checado possui alguma atualização
    E não é possível efetuar o commit no svn
    Quando for iniciada a checagem de arquivos
    Então o sistema registra o erro em log

  @MANUAL
  Cenário: Erro ao comitar no svn - Mais de um arquivo configurado
    Dado que há mais de um arquivo a ser verificado
    E algum arquivo checado possui alguma atualização
    E não é possível efetuar o commit no svn
    Quando for iniciada a checagem de arquivos
    Então o sistema registra o erro em log
    E efetua a verificação dos demais arquivos
