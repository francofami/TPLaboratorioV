package com.example.tpcriptomonedas;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HiloConexion extends Thread {

    public static final int IMAGEN=1;
    public static final int CRIPTOS=2;

    Handler handler;
    boolean img;
    String nombreCripto;

    public HiloConexion(Handler handler, boolean img) {
        this.handler = handler;
        this.img = img;
    }

    public HiloConexion(Handler handler, boolean img, String nombreCripto) {
        this.handler = handler;
        this.img = img;
        this.nombreCripto = nombreCripto;
    }

    @Override
    public void run() {
        ConexionHTTP conexionHTTP = new ConexionHTTP();
        byte[] criptosJson = conexionHTTP.obtenerRespuesta("https://criptoya.com/api/btc/ars/1");;

        if(!img) {

            switch (nombreCripto) {
                case "bitcoin":
                    criptosJson = conexionHTTP.obtenerRespuesta("https://criptoya.com/api/btc/ars/1");
                    break;
                case "ethereum":
                    criptosJson = conexionHTTP.obtenerRespuesta("https://criptoya.com/api/eth/ars/1");
                    break;
                case "dai":
                    criptosJson = conexionHTTP.obtenerRespuesta("https://criptoya.com/api/dai/ars/1");
                    break;
                case "usdt":
                    criptosJson = conexionHTTP.obtenerRespuesta("https://criptoya.com/api/usdt/ars/1");
                    break;
                case "usdc":
                    criptosJson = conexionHTTP.obtenerRespuesta("https://criptoya.com/api/usdc/ars/1");
                    break;
            }

            String s = new String(criptosJson);

            Log.d("", "String criptosJson" + s);

            Message msg = new Message();
            msg.arg1=CRIPTOS;
            msg.obj = this.parserJson(s);
            handler.sendMessage(msg);
        } else {
            // Uso array de bytes ya que de esta forma puedo traer informacion en cualquier formato
            byte[] img = conexionHTTP.obtenerRespuesta("https://ih1.redbubble.net/image.1128082159.8014/flat,750x,075,f-pad,750x1000,f8f8f8.u1.jpg");

            Message msg = new Message();
            msg.arg1 = IMAGEN;
            msg.obj = img;
            handler.sendMessage(msg);
        }

    }

    public List<Cripto> parserJson(String s) {

        Log.d("","Entro a parserJson");

        List<Cripto> criptos = new ArrayList<>();

        String criptoString;
        Object obj;

        try {
            //JSONArray jsonArray = new JSONArray(s);

            JSONObject jsonObject = new JSONObject(s);

            Log.d("","JSONObject"+jsonObject);

            Iterator<?> keys = jsonObject.keys();

            while( keys.hasNext() ) {
                String key = (String) keys.next();
                System.out.println("Key: " + key);
                System.out.println("Value: " + jsonObject.get(key));

                System.out.println("totalAsk: " + (jsonObject.get(key).toString()) );

                // Cripto cripto = new Cripto(key, jsonObject.get(key));

                // System.out.println("totalAsk: " + (jsonObject.get(key) );

                /*JSONObject jsonObjectInsideJsonObject = new JSONObject(jsonObject.get(key).toString());
                Iterator<?> keys2 = jsonObjectInsideJsonObject.keys();

                    while (keys2.hasNext()) {
                        String key2 = (String) keys2.next();
                        System.out.println("totalAsk y eso: " + jsonObjectInsideJsonObject.get(key2));

                    }*/

                //JSONObject jsonObjectInsideJsonObject = new JSONObject(s);
                JSONObject jsonObjectInsideJsonObject = new JSONObject(jsonObject.get(key).toString());

                Double totalAsk = 0.0;
                Double totalBid = 0.0;

                for(int i = 0; i<jsonObjectInsideJsonObject.names().length(); i++){

                    obj = jsonObjectInsideJsonObject.get(jsonObjectInsideJsonObject.names().getString(i));

                    Log.d("","Esto es obj: "+ i +"  " + obj.toString());

                    if (i == 1) {
                        totalAsk = Double.parseDouble(obj.toString());
                    }

                    if (i == 3) {
                        totalBid = Double.parseDouble(obj.toString());
                    }
                }

                Cripto cripto = new Cripto(nombreCripto, key, totalAsk, totalBid);

                criptos.add(cripto);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return criptos;

    }

}
