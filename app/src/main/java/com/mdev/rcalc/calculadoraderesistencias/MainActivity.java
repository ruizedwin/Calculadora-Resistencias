package com.mdev.rcalc.calculadoraderesistencias;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import static java.lang.Math.pow;

public class MainActivity extends AppCompatActivity {
    Spinner sColor1,sColor2,sColor3,sColor4;
    Button bCalcularvalor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bCalcularvalor = (Button)findViewById(R.id.bCalcular);

        sColor1 = (Spinner)findViewById(R.id.sBanda1);
        sColor2 = (Spinner)findViewById(R.id.sBanda2);
        sColor3 = (Spinner)findViewById(R.id.sBanda3);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bandasCol, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sColor1.setAdapter(adapter);
        sColor2.setAdapter(adapter);
        sColor3.setAdapter(adapter);

        sColor4 = (Spinner)findViewById(R.id.sBanda4);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.bandasTol, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sColor4.setAdapter(adapter2);

        bCalcularvalor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
            ShowDialog("El valor de resistencia es:",BuildNumber(sColor1.getSelectedItemPosition(),sColor2.getSelectedItemPosition(),
                      sColor3.getSelectedItemPosition())+" "+GetTolerance(sColor4.getSelectedItemPosition()));
            }
        });
    }

    private void ShowDialog(String Title, String Caption)
    {
        new AlertDialog.Builder(this)
                .setTitle(Title)
                .setMessage(Caption)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_menu_info_details)
                .show();
    }

    private String BuildNumber(int Value1, int Value2, int Value3)
    {
        String Significant;
        Significant = Integer.toString(Value1) + Integer.toString(Value2);
        double Resultado = Integer.parseInt(Significant)*pow(10,Value3);
        if (Resultado/1000 >= 1 && Resultado/1e3 < 1000 )
        {
            return (String.valueOf(Resultado / 1e3) + "KΩ");
        }
        if (Resultado/1e6 >= 1)
        {
            return (String.valueOf(Resultado / 1e6) + "MΩ");
        }else{
            return (String.valueOf(Resultado)+"Ω");
        }

    }

    private String GetTolerance(int Value)
    {
        if (Value == 0)
            return "con +5% de tolerancia";
        else
            return "con +10% de tolerancia";
    }
}

