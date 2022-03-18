# sicredi-teste

## Requisitos
Para a execução do projeto é necessário possuir:

Maven
Java 1.8 ou +

## Execução
Para execução do projeto navegue até a pasta raiz com um terminal de sua preferência e utilize o comando:

$ mvn test

## Casos de testes levantados a partir da documentação
### Todos os casos de testes levantados foram feitos com base no documento que foi enviado no teste.

### Restrições
GET
/api/v1/restricoes/{cpf}

Verificar se ao colocar CPF COM restrição está retornando status 200.

Verificar se a mensagem do retorno 200 é "O CPF 999999999 possui restrição"

Verificar se ao colocar CPF SEM restrição está retornando status 204.

### Simulações
#### GET /api/v1/simulacoes

Verificar se ao consultar simulações está retornando status 200.

#### POST /api/v1/simulacoes

Verificar se ao inserir simulação completa está retornando status 201.

Verificar se ao inserir simulação incompleta está retornando status 400.

Verificar se a mensagem do retorno 400 é uma lista de erros.

Verificar se ao inserir um CPF que já tem simulação está retornando status 409.

Verificar se a mensagem do retorno 409 é "CPF ja existente".

Verificar se é possível inserir uma simulação sem colocar o CPF.

Verificar se é possível inserir uma simulação sem colocar o nome.

Verificar se é possível inserir uma simulação sem colocar o email.

Verificar se é possível inserir uma simulação sem colocar o valor.

Verificar se é possível inserir uma simulação sem colocar o parcela.

Verificar se é possível inserir uma simulação sem colocar o seguro.

Verificar se é possível inserir uma simulação com valor menor que 1000.

Verificar se é possível inserir uma simulação com valor maior que 40000.

Verificar se é possível inserir uma simulação com a parcela menor que 2.

Verificar se é possível inserir uma simulação com a parcela maior que 48.

Verificar se é possível inserir uma simulação com o email inválido.

Verificar se ao inserir email inválido aparece a mensagem "email":"E-mail deve ser um e-mail válido".

#### GET /api/v1/simulacoes/{cpf}

Verificar se ao consultar um simualação com CPF cadastrado está retornando status 200.

Verificar se ao consultar um simualação com CPF NÃO cadastrado está retornando status 404.

Verificar se a mensagem do retorno 404 é "CPF 999999999 não encontrado".

#### PUT /api/v1/simulacoes/{cpf}

Verificar se ao atualizar uma simulação com CPF cadastrado está retornando status 200.

Verificar se a mensagem do retorno 200 é "Simulação alterada com sucesso".

Verificar se ao atualizar uma simulação com CPF NÃO cadastrado está retornando status 404.

Verificar se a mensagem do retorno 404 é "CPF 9999999999 não encontrada".

Verificar se é possível atualizar os campos da simulação.

#### DELETE /api/v1/simulacoes/{id}

Verificar se ao deletar uma simulação com ID cadastrado está retornando status 204.

Verificar se ao deletar uma simulação com ID NÃO cadastrado está retornando status 404.

Verificar se a mensagem do retorno 404 é "Simulação não encontrada".

## Erros

Na criação da automação dos casos de testes acima foram encontrados alguns erros no sistema, e foi adicionado um comentário acima dos métodos com o que acontece e o que era esperado.
