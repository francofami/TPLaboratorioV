package com.example.tpcriptomonedas;

import java.io.Serializable;

public class Cripto implements Serializable{

    private String cripto;
    private String abreviacion;
    private String exchange;
    private Double totalAsk;
    private Double totalBid;

    public Cripto(String cripto, String exchange, Double totalAsk, Double totalBid) {
        this.cripto = cripto;
        this.exchange = exchange;
        this.totalAsk = totalAsk;
        this.totalBid = totalBid;
    }

    public String getCripto() {
        return cripto;
    }

    public void setCripto(String cripto) {
        this.cripto = cripto;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Double getTotalAsk() {
        return totalAsk;
    }

    public void setTotalAsk(Double totalAsk) {
        this.totalAsk = totalAsk;
    }

    public Double getTotalBid() {
        return totalBid;
    }

    public void setTotalBid(Double totalBid) {
        this.totalBid = totalBid;
    }

    @Override
    public String toString() {
        return "Cripto{" +
                "cripto='" + cripto + '\'' +
                ", exchange='" + exchange + '\'' +
                ", totalAsk=" + totalAsk +
                ", totalBid=" + totalBid +
                '}';
    }
}
