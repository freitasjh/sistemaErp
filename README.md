## Sistema ERP PROVA III 

## Instalação 

```console 
Criar o banco de dados sistemaerp no postgres 
``` 
#
Puxar o projeto e depois dentro da pasta do projeto rodar o comando para gerar as tabelas do projeto.

```console 
git clone https://github.com/freitasjh/sistemaerp.git 
mvn liquibase:update 
```
#
Para rodar o projeto rode o comando
```console 
mvn spring-boot:run 
```
#
### Salvar produto
```console
curl --location --request POST 'localhost:8080/sistema_erp/v1/produto/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "descricao": "Produto 1",
    "tipoProduto": "PROD",
    "valorVarejo": "25.45",
    "valorAtacado": "22.30",
    "ativado": "true"
}'
```
### Salvar Serviço
```console
curl --location --request POST 'localhost:8080/sistema_erp/v1/produto/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "descricao": "Servico 2",
    "tipoProduto": "SERV",
    "valorVarejo": "150.00",
    "valorAtacado": "0.00",
    "ativado": "true"
}'
```
### Retorno ao salvar produto e serviço
Status 204 No Content

### Listar Produto
```console
curl --location --request GET 'localhost:8080/sistema_erp/v1/produto/page'

{
    "content": [
        {
            "id": "27955736-f23a-4f1d-a7f9-f2da32678e71",
            "codigoInterno": "1",
            "descricao": "Servico 2",
            "valorAtacado": 0.00,
            "valorVarejo": 150.00,
            "ativado": true,
            "tipoProduto": "SERVICO",
            "codigoEan": null,
            "codigoFabricante": null
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 24,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 1,
    "last": true,
    "number": 0,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "size": 24,
    "first": true,
    "numberOfElements": 1,
    "empty": false
}
```
### Listar produto com filtro de Tipo de Serviço
```console
curl --location --request GET 'localhost:8080/sistema_erp/v1/produto/page?tipoProduto=SERV'
```
