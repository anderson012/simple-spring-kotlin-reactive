# Just a simple spring boot project with kotlin reactive

## Motivação
Projeto foi criado apenas para estudo do paradigma da progração reativa com banco de dados relacional

## Funcionamento do projeto 
Nesse simples projeto utilizo o Kotlin como backend e o padrão WebFux do SprintBoot para retornar os dados em um endpoint convencional do SprintWeb utilizando o conceito de progração reativa. para guardar os registros utilizo o H2 como banco de dados e o driver `R2DBC`

No frontend eu criei dois tipos de chamadas ao endpoint `/heroes` onde irá retornar uma lista de herois de forma assíncrona.  

No primeiro exemplo de request eu utilizo o [`EventSource`](https://developer.mozilla.org/pt-BR/docs/Web/API/EventSource) que irá dar uma resposta ao frontend logo no primeiro registro encontrado no stream de dados

No segundo exemplo eu utilizo o [`fetch`](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API) para buscar os dados no backend de forma convencional.

### Analisando os resultados 

O retorno dos dados utilizando o `EventSouce` ocorre por volta de `550ms` porque a cada item retornado no `stream` do backend um evento é disparado no `frontend` por outro lado utilizando o `fetch` os dados somente serão retornados quando **TODOS** os itens estiverem prontos no backend tendo uma demora de mais de `22500ms` para ter um feedback ao usuário;

![image](https://user-images.githubusercontent.com/7342177/151296786-44ce2e5c-698a-4588-a69b-6b4b1e4d4da8.png)
