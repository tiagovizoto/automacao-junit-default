# language: pt

@fluxo-regressivo
Funcionalidade: Validar fluxo regressivo para criar uma apuração

  Contexto:
    Dado que acesso a url do SAP
    E realizo login com usuario valido

  Esquema do Cenário: Validar o fluxo regressivo do IcmsIPI até a geração de uma nova apuração
    Dado que estou na página na home
    Quando acesso o menu do produto ICMS_IPI

   Exemplos:
     | campo 1 | local de negocio | campo 3 | mensagem |
     |         |                  |         | Preencher todos os campos obrigatórios |
   #  |         |                  |         | Preencher todos os campos obrigatórios |
   #  |         |                  |         | Preencher todos os campos obrigatórios |




