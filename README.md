# Teste Prático - Iniflex

Projeto desenvolvido como parte do teste prático para a vaga de programador junior da Prothera.

A aplicação simula o cadastro e processamento de informações de funcionarios de uma industria, 
utilizando Java e recursos da biblioteca padrão da lingaguem.

## Tecnologias utilizadas

- Java 25
- Maven
- Java Collections
- Stream API
- `LocalDate`
- `BigDecimal`

## Estrutura do projeto

```text
src/main/java/br/com/iniflex/
├── Principal.java
└── model/
    ├── Pessoa.java
    └── Funcionario.java
```

### Pessoa

Classe base que possui os atributos:

- nome (`String`)
- data de nascimento (`LocalDate`)

### Funcionario

Classe que estende `Pessoa` e adiciona os atributos:

- salário (`BigDecimal`)
- função (`String`)

## Principal 

Responsável pela execução dos requesitos propostos no teste.

## Requisitos implementados

- Inserção dos funcionários conforme os dados fornecidos no teste. 
- Remoção do funcionário João.
- Impressão dos funcionários com:
    - data no formato `dd/MM/yyyy
    - valores numéricos com separador de milhar e decimal conforme o padrão brasileiro.
- Aplicação de aumento salarial de 10%
- Agrupamento dos funcionarios por função utilizando `Map`
- Impressão dos funcionários agrupados por função.
- Identificação dos funcionários que fazem aniversário nos meses 10 e 12.
- Identificação do funcionário com maior idade.
- Impressão dos funcionários em ordem alfabetica.
- Cálculo do total dos salários.
- Cálculo da quantidade de salários minimos por cada funcionário, considerando o valor de R$ 1212,00.

## Algumas decisões de implementação

### BigDecimal para valores monetários

Os salários são representados utilizando `BigDecimal`, evitando problemas de precisão que podem ocorrer 
com tipos de pontos flutuante como `double`

### List e ArrayList

Os funcionários são armazenados em uma `List<Funcionario>`, utilizando `ArrayList` como implementação.

### Agrupamento por função

O agrupamento dos funcionários é realizado utilizando:

```java
Map<String, List<Funcionario>>
```

A função dos funcionário é utilizada como chave e a lista de funcionários daquela função com valor.

### Stream API

A Stream API é utilizada em operações como:

- agrupamento;
- filtragem;
- ordenação;
- identificação dos funcionário mais velho;
- cálculo do total dos salários.

## Como executar

### Pré-requisitos

É necessario possuir instalado:

- Java 25
- Maven

Verifique as instalações com:

```bash
java -version
javac -version
mvn -version
```

### Compilar o projeto

Na raiz do projeto:

```bash
mvn clean compile
```

### Executar

Após a compilação:

```bash
java -cp target/classes br.com.iniflex.Principal
```

### Gerar o pacote

Também é possivel executar: 

```bash
mvn clean package
```

## Observações

O projeto foi desenvolvido utilizando apenas recursos do Java e Maven, sem frameworks ou dependências externas, 
mantendo a implementação compativel com o escopo proposto pelo teste prático.