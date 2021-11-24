package com.example.tpcriptomonedas;

import android.util.Log;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Arbitraje implements Serializable {

    private String exchangeA;
    private Double precioCompra;
    private String exchangeB;
    private Double precioVenta;
    private String moneda;
    private String porcentajeGanancia;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Arbitraje(String exchangeA, String exchangeB, Double precioCompra, Double precioVenta, String moneda) {

        df.setRoundingMode(RoundingMode.UP);

        this.exchangeA = exchangeA;
        this.exchangeB = exchangeB;

        this.precioCompra = Double.parseDouble(df.format(precioCompra));
        Log.d("PRECIOCOMPRA", this.precioCompra.toString());
        this.precioVenta = Double.parseDouble(df.format(precioVenta));
        this.moneda = moneda;

        this.porcentajeGanancia = this.calcularPorcentajeGanancia(precioCompra, precioVenta);
    }

    public String calcularPorcentajeGanancia(Double precioCompra, Double precioVenta) {

        String retorno = "";
        Double retornoTemp = 0.00;

        retornoTemp = (100 - ((100 * precioVenta) / precioCompra)) * -1;

        df.setRoundingMode(RoundingMode.UP);
        retorno = df.format(retornoTemp) + " %";

        return retorno;
    }

    public String getExchangeA() {
        return exchangeA;
    }

    public void setExchangeA(String exchangeA) {
        this.exchangeA = exchangeA;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getExchangeB() {
        return exchangeB;
    }

    public void setExchangeB(String exchangeB) {
        this.exchangeB = exchangeB;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(String porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
    }

    @Override
    public String toString() {
        return "Arbitraje{" +
                "exchangeA='" + exchangeA + '\'' +
                ", precioCompra=" + precioCompra +
                ", exchangeB='" + exchangeB + '\'' +
                ", precioVenta=" + precioVenta +
                ", moneda='" + moneda + '\'' +
                ", porcentajeGanancia=" + porcentajeGanancia +
                '}';
    }

    public static List<Arbitraje> CalcularArbitrajes(List<Cripto> criptoList) {

        List<Arbitraje> arbitrajeList = new ArrayList<>();

        for (Cripto cripto: criptoList) {

            for (Cripto cripto2:criptoList) {

                if (cripto.getCripto() == cripto2.getCripto() && cripto.getTotalAsk() < cripto2.getTotalBid()) {

                    Arbitraje arbitraje = new Arbitraje(cripto.getExchange(), cripto2.getExchange(), cripto.getTotalAsk(), cripto2.getTotalBid(), cripto.getCripto());
                    arbitrajeList.add(arbitraje);

                    Log.d("", arbitraje.toString());

                }

            }

        }

        return arbitrajeList;
    }

    public static List<Arbitraje> EliminarDuplicados(List<Arbitraje> arbitrajeList) {

        List<Arbitraje> tmpList = new ArrayList<Arbitraje>();

        for (Arbitraje arbitraje: arbitrajeList) {

            if (!tmpList.contains(arbitraje)) {

                tmpList.add(arbitraje);

            }

        }

        return tmpList;

    }

    public static List<Arbitraje> ordenarArbitrajes(List<Arbitraje> arbitrajeList) {

        arbitrajeList.sort(Comparator.comparing(Arbitraje::getPorcentajeGanancia).reversed());

        return arbitrajeList;
    }

    @Override
    public boolean equals(Object o) {

        Boolean retorno = false;

        if (o instanceof Arbitraje) {
            Arbitraje tmpArbitraje = (Arbitraje) o;
            if (this.exchangeA.equals(tmpArbitraje.exchangeA) && this.exchangeB.equals(tmpArbitraje.exchangeB) && this.moneda.equals(tmpArbitraje.moneda)) {
                retorno = true;
            }
        }

        return retorno;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchangeA, precioCompra, exchangeB, precioVenta, moneda, porcentajeGanancia);
    }
}
