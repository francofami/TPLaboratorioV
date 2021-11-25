package com.example.tpcriptomonedas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnItemClick, Handler.Callback, SwipeRefreshLayout.OnRefreshListener {

    List<Cripto> criptoList = new ArrayList<>();
    List<Arbitraje> arbitrajeList = new ArrayList<>();

    SwipeRefreshLayout swipeRefreshLayout;

    Integer posicionActual = -1;

    ListView listView;
    ArrayAdapter<String> adapter;

    String[] arrayExchanges = {"Ripio", ""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Arbitrajes");

        Handler handler = new Handler(this);

        HiloConexion hiloEthereum = new HiloConexion(handler, false, "ethereum");
        hiloEthereum.start();

        HiloConexion hiloBitcoin = new HiloConexion(handler, false, "bitcoin");
        hiloBitcoin.start();

        HiloConexion hiloDai = new HiloConexion(handler, false, "dai");
        hiloDai.start();

        HiloConexion hiloUsdt = new HiloConexion(handler, false, "usdt");
        hiloUsdt.start();

        HiloConexion hiloUsdc = new HiloConexion(handler, false, "usdc");
        hiloUsdc.start();



        /*HiloConexion hiloImg = new HiloConexion(handler, true);
        hiloImg.start();*/

        // this.crearRecyclerView();

        // SwipeRefreshLayout
        swipeRefreshLayout = (SwipeRefreshLayout) super.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.design_default_color_primary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        swipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                swipeRefreshLayout.setRefreshing(true);

                // Fetching data from server

                crearRecyclerView();
            }
        });

        /*listView = this.findViewById(R.id.listView_data);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arrayExchanges);

        listView.setAdapter(adapter);*/
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {

        if(message.arg1==HiloConexion.CRIPTOS) {
            // TextView tv = super.findViewById(R.id.tv);

            System.out.println("ENTRE AL HANDLEMESSAGE");

            List<Cripto> criptos = (List<Cripto>) message.obj;
            System.out.println("Entre al handle" + criptos.toString());

            for (Cripto cripto : criptos) {
                System.out.println("Entré al for");
                criptoList.add(cripto);
            }

        } else if(message.arg1==HiloConexion.IMAGEN) {
            // ImageView iv = super.findViewById(R.id.img);
            byte[] img = (byte[]) message.obj;
            // iv.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));
        }

        this.arbitrajeList.addAll(Arbitraje.CalcularArbitrajes(this.criptoList));

        this.arbitrajeList = Arbitraje.EliminarDuplicados(this.arbitrajeList);

        Arbitraje.ordenarArbitrajes(this.arbitrajeList);

        this.crearRecyclerView();

        // Log.d("Mejor arbitraje: ", this.arbitrajeList.get(0).toString());

        return false;
    }

    private void actualizarCripto(Cripto cripto) {
        this.criptoList.set(this.posicionActual, cripto);
        this.crearRecyclerView();
    }

    private void crearRecyclerView() {

        swipeRefreshLayout.setRefreshing(true);

        ArbitrajeAdapter adapter = new ArbitrajeAdapter(arbitrajeList, this);
        RecyclerView rv = super.findViewById(R.id.rvCriptos);

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, CalcularActivity.class);

        intent.putExtra("arbitraje", arbitrajeList.get(position));

        this.posicionActual = position;

        //startActivity(intent);
        startActivityForResult(intent,999);
    }

    @Override
    public void onRefresh() {
        finish();
        overridePendingTransition( 0, 0);
        startActivity(getIntent());
        overridePendingTransition( 0, 0);
    }

}