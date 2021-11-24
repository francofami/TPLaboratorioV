package com.example.tpcriptomonedas;

public class CalcularController {

    CalcularView calcularView;
    Arbitraje arbitraje;

    public CalcularController(Arbitraje arbitraje) {
        this.arbitraje = arbitraje;
        recuperarArbitraje();
    }

    private void recuperarArbitraje() {
        this.arbitraje.setExchangeA(this.arbitraje.getExchangeA());
        this.arbitraje.setExchangeB(this.arbitraje.getExchangeB());
        this.arbitraje.setMoneda(this.arbitraje.getMoneda());
        this.arbitraje.setPrecioCompra(this.arbitraje.getPrecioCompra());
        this.arbitraje.setPrecioVenta(this.arbitraje.getPrecioVenta());
        this.arbitraje.setPorcentajeGanancia(this.arbitraje.getPorcentajeGanancia());
    }

    public void setCalcularView(CalcularView calcularView) {
        this.calcularView = calcularView;
        this.calcularView.mostrarArbitraje();
    }

}
