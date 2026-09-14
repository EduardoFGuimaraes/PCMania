package br.inatel.cdg.computador;

import br.inatel.cdg.hardware.HardwareBasico;
import br.inatel.cdg.hardware.MemoriaUSB;
import br.inatel.cdg.hardware.SistemaOperacional;

public class Computador {

    private String marca;
    private float preco;
    private HardwareBasico[] hardwares;
    private SistemaOperacional sistemaoperacional;
    private MemoriaUSB memoriausb;

    public Computador(String marca, float preco, HardwareBasico[] hardwares, SistemaOperacional sistemaoperacional) {
        this.marca = marca;
        this.preco = preco;
        this.hardwares = hardwares;
        this.sistemaoperacional = sistemaoperacional;

    }

    public void addMemoriaUSB(MemoriaUSB mUsb) {
        this.memoriausb = mUsb;
    }

    public void mostraPCConfigs() {
        System.out.println("Marca: " + marca);
        System.out.println("Preco " + preco);

        for (int i = 0; i < hardwares.length; i++) {
            System.out.println(hardwares[i].getNome() + " - " + hardwares[i].getCapacidade());
        }

        System.out.println(sistemaoperacional.getNome() + " - " + sistemaoperacional.getTipo());

        if (memoriausb != null) {
            System.out.println(memoriausb.getNome() + " + " + memoriausb.getCapacidade());

        }

    }

    public float getPreco() {
        return preco;

    }
}
