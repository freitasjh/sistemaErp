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

-- Retorno
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
- tipoProduto=SERV "Serviço" / tipoProduto=PROD "Produto"
```console
curl --location --request GET 'localhost:8080/sistema_erp/v1/produto/page?tipoProduto=SERV'
```

### Atualizar produto
Retorno = "Status 204 No content"
```console
55a6ab79-aff3-44a3-8ec2-3c89a0ed41b3 = "Id do Produto".

curl --location --request PUT 'localhost:8080/sistema_erp/v1/produto/55a6ab79-aff3-44a3-8ec2-3c89a0ed41b3' \
--header 'Content-Type: application/json' \
--data-raw '{
    "descricao": "Produto 1",
    "valorAtacado": 22.00,
    "valorVarejo": 24.50,
    "ativado": false,
    "tipoProduto": "PROD",
    "codigoEan": null,
    "codigoFabricante": null
}'
```
### Deletar Produto
Retorno = "Status 204 No content"
```console
edb0f74d-4e54-4186-a527-b12b0e1b8efc = "id do produto"
curl --location --request DELETE 'localhost:8080/sistema_erp/v1/produto/edb0f74d-4e54-4186-a527-b12b0e1b8efc'
```

### Salvar Produto
Retorno = "Status 204 no content"
```console
curl --location --request POST 'localhost:8080/sistema_erp/v1/pedido' \
--header 'Content-Type: application/json' \
--data-raw '{
    "tipoVenda" : "V",
    "valorTotal": 450.00,
    "subTotal": 450.00,
    "descontoPercentual": 0.00,
    "descontoValor": 25.00,
    "quantidadeTotal": 0.00,
    "situacaoVenda": "A",
    "listaDeItem": [
        {
            "produto": {
                "id": "9c600384-41e3-464e-9823-28d3868df7c5",
                "descricao": "Produto 1",
                "valorAtacado": 0.00,
                "valorVarejo": 150.00,
                "ativado": true,
                "tipoProduto": "PROD"
            },
            "quantidade": "2",
            "valorUnitario": 150.00,
            "valorTotal": 300.00
        },
        {
            "produto": {
                "id": "edb0f74d-4e54-4186-a527-b12b0e1b8efc",
                "descricao": "Servico 2",
                "valorAtacado": 0.00,
                "valorVarejo": 150.00,
                "ativado": true,
                "tipoProduto": "SERV"
            },
             "quantidade": "1",
            "valorUnitario": 150.00,
            "valorTotal": 150.00
        }
        
    ]
}'
```
### Listar Pedido
```console
curl --location --request GET 'localhost:8080/sistema_erp/v1/pedido/page'
```
### Finalizar Pedido
Retorno = "Status 204 No content"
```console
75002426-1f7d-4b56-96a3-1cbb3b032514 = "Id do Pedido".
curl --location --request PUT 'localhost:8080/sistema_erp/v1/pedido/fecharVenda/75002426-1f7d-4b56-96a3-1cbb3b032514' \
--header 'Content-Type: application/json' \
--data-raw ' {
            "codigo": "2",
            "tipoVenda": "V",
            "valorTotal": 425.00,
            "subTotal": 450.00,
            "descontoPercentual": 0.00,
            "descontoValor": 25.00,
            "quantidadeTotal": 0.00,
            "dataCadastro": "2020-11-01T15:51:42.908+00:00",
            "dataFinalizado": "2020-11-01T17:20:04.446+00:00",
            "situacaoVenda": "F",
            "listaDeItem": [
                {
                    "id": "fe22513d-a6d6-4b20-a7e8-148112b35c4b",
                    "produto": {
                        "id": "9c600384-41e3-464e-9823-28d3868df7c5",
                        "codigoInterno": null,
                        "descricao": "Produto 1",
                        "valorAtacado": 0.00,
                        "valorVarejo": 150.00,
                        "ativado": true,
                        "tipoProduto": "PROD",
                        "codigoEan": null,
                        "codigoFabricante": null
                    },
                    "quantidade": 2.00,
                    "valorUnitario": 150.00,
                    "valorTotal": 300.00
                },
                {
                    "id": "e32c3b61-1684-427b-9074-ba42ad945e1b",
                    "produto": {
                        "id": "edb0f74d-4e54-4186-a527-b12b0e1b8efc",
                        "codigoInterno": null,
                        "descricao": "Servico 2",
                        "valorAtacado": 0.00,
                        "valorVarejo": 150.00,
                        "ativado": true,
                        "tipoProduto": "SERV",
                        "codigoEan": null,
                        "codigoFabricante": null
                    },
                    "quantidade": 1.00,
                    "valorUnitario": 150.00,
                    "valorTotal": 150.00
                }
            ]
        }'
```
