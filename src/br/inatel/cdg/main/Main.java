package br.inatel.cdg.main;

import java.util.Scanner;

import br.inatel.cdg.cliente.Cliente;
import br.inatel.cdg.computador.Computador;
import br.inatel.cdg.hardware.HardwareBasico;
import br.inatel.cdg.hardware.MemoriaUSB;
import br.inatel.cdg.hardware.SistemaOperacional;
import br.inatel.cdg.pedido.ProcessarPedido;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Cliente cliente = new Cliente("Eduardo Filhagosa Guimaraes", "123.456.789-00");

        Computador[] comprados = new Computador[0];

        int codigo = -1;

        while (codigo != 0) {

            System.out.println("");
            System.out.println("===== PC MANIA =====");
            System.out.println("1 - Promocao 1 (Apple)");
            System.out.println("2 - Promocao 2 (Samsung)");
            System.out.println("3 - Promocao 3 (Dell)");
            System.out.println("0 - Finalizar compra");
            System.out.print("Digite o codigo desejado: ");

            codigo = scanner.nextInt();

            Computador pc = null;

            if (codigo == 1) {
                pc = montaPromocao1();
            } else if (codigo == 2) {
                pc = montaPromocao2();
            } else if (codigo == 3) {
                pc = montaPromocao3();
            } else if (codigo != 0) {
                System.out.println("Codigo invalido!");
            }

            if (pc != null) {
                comprados = adicionaComputador(comprados, pc);
                System.out.println("PC da promocao " + codigo + " adicionado a compra!");
            }
        }

        cliente.setComputadores(comprados);

        System.out.println("");
        System.out.println("===== RESUMO DA COMPRA =====");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Quantidade de PCs: " + comprados.length);

        for (int i = 0; i < comprados.length; i++) {
            System.out.println("");
            System.out.println("--- PC " + (i + 1) + " ---");
            comprados[i].mostraPCConfigs();
        }

        System.out.println("");
        System.out.println("TOTAL: R$" + cliente.calculaTotalCompra());

        ProcessarPedido.enviarPedido(comprados);

        scanner.close();
    }

    private static Computador[] adicionaComputador(Computador[] atual, Computador novo) {
        Computador[] maior = new Computador[atual.length + 1];

        for (int i = 0; i < atual.length; i++) {
            maior[i] = atual[i];
        }

        maior[atual.length] = novo;

        return maior;
    }

    private static Computador montaPromocao1() {
        HardwareBasico[] hardwares = new HardwareBasico[3];
        hardwares[0] = new HardwareBasico("Pentium Core i5 (Mhz)", 2200);
        hardwares[1] = new HardwareBasico("Memoria RAM (Gb)", 8);
        hardwares[2] = new HardwareBasico("HD (Gb)", 500);

        SistemaOperacional so = new SistemaOperacional("macOS Sequoia", 64);

        Computador pc = new Computador("Apple", 755, hardwares, so);
        pc.addMemoriaUSB(new MemoriaUSB("Pen-drive (Gb)", 16));

        return pc;
    }

    private static Computador montaPromocao2() {
        HardwareBasico[] hardwares = new HardwareBasico[3];
        hardwares[0] = new HardwareBasico("Pentium Core i7 (Mhz)", 3370);
        hardwares[1] = new HardwareBasico("Memoria RAM (Gb)", 16);
        hardwares[2] = new HardwareBasico("HD (Gb)", 1024);

        SistemaOperacional so = new SistemaOperacional("Windows 8", 64);

        Computador pc = new Computador("Samsung", 756, hardwares, so);
        pc.addMemoriaUSB(new MemoriaUSB("Pen-drive (Gb)", 32));

        return pc;
    }

    private static Computador montaPromocao3() {
        HardwareBasico[] hardwares = new HardwareBasico[3];
        hardwares[0] = new HardwareBasico("Pentium Core i7 (Mhz)", 4500);
        hardwares[1] = new HardwareBasico("Memoria RAM (Gb)", 32);
        hardwares[2] = new HardwareBasico("HD (Gb)", 2048);

        SistemaOperacional so = new SistemaOperacional("Windows 10", 64);

        Computador pc = new Computador("Dell", 757, hardwares, so);
        pc.addMemoriaUSB(new MemoriaUSB("HD Externo (Gb)", 1024));

        return pc;
    }
}