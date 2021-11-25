package com.example.tpcriptomonedas;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DialogExchanges implements View.OnClickListener{

    final android.app.Dialog dialog;

    private Activity activity;
    private Arbitraje arbitraje;

    EditText dialog_username;
    TextView dialog_rol;
    Spinner spinner;
    ToggleButton admin;

    Button botonGuardar;
    Button botonCerrar;

    // private IOnGuardarClick listener;

    public DialogExchanges(Activity mainActivity) {
        this.activity = mainActivity;
        this.dialog = new android.app.Dialog(this.activity);

        // this.dialog_username = (EditText) dialog.findViewById(R.id.dialog_username);
        // this.dialog_rol = (EditText) dialog.findViewById(R.id.dialog_username);
    }


    /*public void agregarUsuarioASharedPreference(JSONObject usuarioJsonObject) {

        SharedPreferences preferencesUsuarios = activity.getSharedPreferences("usuarios", 0);

        String s = preferencesUsuarios.getString("usuarios", "");

        try {
            JSONArray jsonArray = new JSONArray(s);

            jsonArray.put(usuarioJsonObject);

            Log.d("", jsonArray.toString());

            SharedPreferences.Editor edit = preferencesUsuarios.edit();
            edit.putString("usuarios", jsonArray.toString());
            edit.apply();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        s = preferencesUsuarios.getString("usuarios", "");

        TextView tv = activity.findViewById(R.id.tv);
        tv.setText(s);

    }*/



    void showDialog() {




        // IOnGuardarClick listener;
        /*dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);

        this.botonGuardar = (Button) dialog.findViewById(R.id.dialog_guardar);
        botonGuardar.setOnClickListener(this);

        this.botonCerrar = (Button) dialog.findViewById(R.id.dialog_cerrar);
        botonCerrar.setOnClickListener(v -> {dialog.dismiss();});

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.activity, R.array.spinner, android.R.layout.simple_spinner_item);

        this.spinner = dialog.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        this.admin = dialog.findViewById(R.id.admin);*/

        dialog.show();
    }

    @Override
    public void onClick(View view) {
        Log.d("","hice click en guardar XD");

        // .guardarUsuario();
    }
}
