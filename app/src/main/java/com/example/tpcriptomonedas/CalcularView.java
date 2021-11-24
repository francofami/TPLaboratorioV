package com.example.tpcriptomonedas;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class CalcularView {

    private Activity activity;
    private Arbitraje arbitraje;

    TextView precioCompraCalcular;
    TextView precioVentaCalcular;
    EditText cantidadCompraCalcular;
    TextView cantidadPesosgananciaCalcular;
    TextView nombreExchangeACalcular;
    TextView nombreExchangeBCalcular;

    public CalcularView(Activity activity, Arbitraje arbitraje, CalcularController calcularController) {
        this.activity = activity;
        this.arbitraje = arbitraje;

        this.precioCompraCalcular = this.activity.findViewById(R.id.precioCompraCalcular);
        this.precioVentaCalcular = this.activity.findViewById(R.id.precioVentaCalcular);
        this.cantidadCompraCalcular = this.activity.findViewById(R.id.cantidadCompraCalcular);
        this.cantidadPesosgananciaCalcular = this.activity.findViewById(R.id.cantidadPesosgananciaCalcular);
        this.nombreExchangeACalcular = this.activity.findViewById(R.id.nombreExchangeACalcular);
        this.nombreExchangeBCalcular = this.activity.findViewById(R.id.nombreExchangeBCalcular);

        cantidadCompraCalcular.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                // cantidadPesosgananciaCalcular.setText(calcularGanancia(cantidadCompraCalcular.getText().toString(), precioCompraCalcular.getText().toString(), precioVentaCalcular.getText().toString()));

                if (Double.parseDouble(cantidadCompraCalcular.getText().toString()) > 0) {
                    cantidadPesosgananciaCalcular.setText(calcularGanancia(Float.parseFloat(cantidadCompraCalcular.getText().toString()), Float.parseFloat(precioCompraCalcular.getText().toString()), Float.parseFloat(precioVentaCalcular.getText().toString())));
                } else if (cantidadCompraCalcular.getText().toString() == "") {
                    cantidadPesosgananciaCalcular.setText("0");
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void mostrarArbitraje() {
        this.precioCompraCalcular.setText(this.arbitraje.getPrecioCompra().toString());
        this.precioVentaCalcular.setText(this.arbitraje.getPrecioVenta().toString());
        this.nombreExchangeACalcular.setText(this.arbitraje.getExchangeA());
        this.nombreExchangeBCalcular.setText(this.arbitraje.getExchangeB());
    }

    /*private String calcularGanancia(String cantidadCompraCalcular, String precioCompraCalcular, String precioVentaCalcular) {

        String retorno = "";
        Double retornoTemp = 0.00;

        retornoTemp = ((Double.parseDouble(precioVentaCalcular) -  Double.parseDouble(precioCompraCalcular)) * Double.parseDouble(cantidadCompraCalcular));

        retorno = retornoTemp.toString();

        return  retorno;
    }*/

    private String calcularGanancia(Float cantidadCompraCalcular, Float precioCompraCalcular, Float precioVentaCalcular) {

        Float retorno = Float.parseFloat("0");

        // retorno = ((precioVentaCalcular -  precioCompraCalcular) * cantidadCompraCalcular);

        retorno = (cantidadCompraCalcular * (precioVentaCalcular - precioCompraCalcular)) / precioCompraCalcular;

        return  retorno.toString();
    }

}
