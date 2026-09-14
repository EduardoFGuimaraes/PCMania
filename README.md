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

Conforme exigido no enunciado, declaro o uso de IA na realização deste exercício.

**Modelo utilizado:** Claude Opus 5 (Anthropic), através do Claude Cowork.

**Formato de uso:** tutoria passo a passo, e não geração do projeto pronto. A IA leu o
PDF do enunciado e os slides das aulas, explicou como cada elemento do diagrama UML se
traduz em código Java, e a cada etapa propôs o código da classe correspondente
acompanhado da explicação do porquê de cada decisão. Eu escrevi e salvei todos os
arquivos, executei o programa e corrigi os erros apontados nas revisões. Nenhuma classe
foi incorporada ao projeto sem que eu entendesse o que ela faz.

**Decisões que foram minhas ao longo do processo:**

- Questionei a interface de compra ("eu realmente não vejo as promoções antes de
  escolher?"), o que levou à discussão sobre exibir a configuração de cada promoção
  antes do menu.
- Decidi **não** implementar essa exibição prévia, por entender que o enunciado pede
  apenas "uma interface simples de compra, perguntando ao cliente qual PC de qual
  promoção deseja comprar" — a funcionalidade extra não era requisito.
- Decidi não alterar o código depois que ele passou a cumprir integralmente o que o
  enunciado pede, evitando refatorações que não agregavam à entrega.

**Prompts utilizados:** o prompt inicial, que define o formato de todo o trabalho, foi:

> *"Analise o arquivo .pdf Projeto - PCMania, me ajude a construir o projeto do zero,
> você deve caminhar comigo até o resultado final, cumprindo com aquilo que se pede no
> pdf, analise também os slides das aulas de acordo com o tipo de material de estudo
> necessário em cada uma das etapas, você escolhe por onde iremos começar, seja
> didático mas vamos sem enrolação."*

Os demais foram pedidos pontuais de verificação e revisão a cada classe concluída
(para que a IA lesse os arquivos salvos, compilasse o projeto e apontasse erros),
questionamentos sobre a interface de compra e a decisão de encerrar as alterações
quando o código passou a cumprir integralmente o enunciado.

**Erros que cometi e corrigi durante o processo:** chave de abertura posicionada
incorretamente na assinatura do construtor de `Computador`; impressão do sistema
operacional e da memória USB dentro do laço `for` dos hardwares, o que causaria
repetição a cada iteração; `lenght` no lugar de `length`; e chamada de `getPreco()`
sobre o array inteiro em vez do elemento indexado (`computadores[i]`). Em todos os
casos a IA apontou o erro e explicou a causa, e a correção foi feita por mim.

**O resultado foi satisfatório?** Sim. O ganho principal não foi o código em si, e sim
a compreensão do *porquê* de cada decisão de modelagem: a tradução do diagrama UML em
atributos (o losango de composição virando array ou referência única), a diferença
entre `array` e `array[i]`, o motivo de `static` no método utilitário, e por que a
`MemoriaUSB` nasce `null` em vez de vir pelo construtor. São conceitos que eu consigo
explicar e reaplicar sem a ferramenta.
