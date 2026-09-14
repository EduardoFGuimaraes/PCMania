# PCMania

Exercício prático da disciplina **C06 — Programação Orientada a Objetos**.

| | |
|---|---|
| **Aluno** | Eduardo Filhagosa Guimarães |
| **Curso** | Engenharia Biomédica |
| **Matrícula** | 755 |
| **Professor** | Christopher Lima |
| **Instituição** | Inatel — Instituto Nacional de Telecomunicações |

---

## Sobre o projeto

Sistema de compras da loja fictícia PC Mania. O cliente escolhe, por código, qual
PC de qual promoção deseja comprar; ao digitar `0` a compra é encerrada e o sistema
exibe os dados do cliente, a configuração completa de cada PC adquirido e o valor
total da compra.

As três promoções são fixas e têm seus preços derivados da matrícula do aluno:

| Promoção | Marca | Preço | Processador | RAM | HD | Sistema Operacional | Acompanha |
|---|---|---|---|---|---|---|---|
| 1 | Apple | R$ 755 | Pentium Core i5 (2200 Mhz) | 8 Gb | 500 Gb | macOS Sequoia (64 bits) | Pen-drive 16 Gb |
| 2 | Samsung | R$ 756 | Pentium Core i7 (3370 Mhz) | 16 Gb | 1024 Gb | Windows 8 (64 bits) | Pen-drive 32 Gb |
| 3 | Dell | R$ 757 | Pentium Core i7 (4500 Mhz) | 32 Gb | 2048 Gb | Windows 10 (64 bits) | HD Externo 1024 Gb |

---

## Estrutura de pacotes

```
src/
└── br/inatel/cdg/
    ├── hardware/
    │   ├── HardwareBasico.java       (nome, capacidade)
    │   ├── SistemaOperacional.java   (nome, tipo)
    │   └── MemoriaUSB.java           (nome, capacidade)
    ├── computador/
    │   └── Computador.java           (marca, preco, hardwares[], SO, memoriaUSB)
    ├── cliente/
    │   └── Cliente.java              (nome, cpf, computadores[])
    ├── pedido/
    │   └── ProcessarPedido.java      (método utilitário estático)
    └── main/
        └── Main.java                 (interface de compra)
```

## Como o código atende ao diagrama UML

| Relação no diagrama | Implementação |
|---|---|
| `Computador` ◆— `HardwareBasico` `*` | atributo `HardwareBasico[] hardwares` |
| `Computador` ◆— `SistemaOperacional` | atributo `SistemaOperacional sistemaOperacional` |
| `Computador` ◇— `MemoriaUSB` `0..1` | atributo opcional, atribuído por `addMemoriaUSB()` |
| `Cliente` —compra→ `Computador` `2..*` | atributo `Computador[] computadores` |

Decisões de projeto:

- **Todos os atributos são `private`.** Foram criados apenas os getters efetivamente
  utilizados (9 getters e 1 setter), conforme exigência do enunciado.
- **Nenhuma biblioteca de coleções foi utilizada.** O array de computadores do cliente
  cresce manualmente: a cada compra, um novo array de tamanho `n+1` é alocado, o
  conteúdo anterior é copiado e o novo item é inserido na última posição
  (método `adicionaComputador` em `Main`).
- **`ProcessarPedido.enviarPedido()` é `static`**, sendo chamado diretamente pela
  classe, sem instanciação — conforme pedido para o método utilitário (*helper*).

---

## Como executar

Requisito: JDK 17 ou superior.

```bash
javac -d bin $(find src -name "*.java")
java -cp bin br.inatel.cdg.main.Main
```

No VS Code, basta abrir `Main.java` e clicar em **Run**.

---

## Declaração de uso de Inteligência Artificial

Conforme solicitado no enunciado, declaro o uso de IA na realização deste exercício.

**Modelo utilizado:** Claude Opus 5 (Anthropic), através do Claude Cowork.

**Como foi utilizada:** a IA foi usada como tutor, em formato passo a passo. O código
foi construído de forma incremental, classe por classe, com explicação do conceito
antes de cada etapa. Eu escrevi e salvei os arquivos, e a cada classe concluída a IA
revisava, apontava os erros e explicava o motivo de cada correção.

**Prompts utilizados (resumo):**

1. *"Analise o arquivo .pdf Projeto - PCMania, me ajude a construir o projeto do zero,
   você deve caminhar comigo até o resultado final, cumprindo com aquilo que se pede no
   pdf, analise também os slides das aulas de acordo com o tipo de material de estudo
   necessário em cada uma das etapas, você escolhe por onde iremos começar, seja
   didático mas vamos sem enrolação."*
2. *"Verifique"* / *"feito, confira"* — usado a cada etapa concluída, para que a IA
   lesse os arquivos, compilasse o projeto e apontasse erros.
3. *"Revise o código"* — revisão final completa, comparando o código com cada exigência
   do PDF e com o diagrama UML.
4. *"Eu realmente não vejo as promoções antes de escolher?"* — questionamento sobre a
   interface de compra.

**Erros que eu cometi e foram corrigidos ao longo do processo:** chave de abertura
posicionada incorretamente no construtor de `Computador`; impressão do sistema
operacional e da memória USB dentro do laço `for` dos hardwares (o que causaria
repetição); `lenght` no lugar de `length`; e chamada de `getPreco()` no array inteiro
em vez do elemento indexado (`computadores[i]`).

**O resultado foi satisfatório?** Sim. O ganho principal não foi o código pronto, e sim
a explicação do *porquê* de cada decisão — especialmente a tradução do diagrama UML em
atributos (losango de composição virando array ou referência única), a diferença entre
`array` e `array[i]`, e o motivo de `static` no método utilitário. Os erros que cometi
foram corrigidos com explicação da causa, e não apenas com o código substituído.
