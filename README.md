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
##Salvar produto
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
