package com.example.meu_primeiro_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private TextView txtImc;
    private Button btnCalcularImc;
    private Button btnLimpar;
    private EditText edtPeso;
    private EditText edtAltura;

    // o método onCreate é o primeiro método invocado no ciclo de vida
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // a classe R faz referência a pasta res
        // a propriedade layout faz referência a minha pasta layout
        // activity_main representa o layout da minha activity
        setContentView(R.layout.activity_main);
        // mapeando os elementos de interface
        this.txtImc = findViewById(R.id.txt_imc);
        this.btnCalcularImc = findViewById(R.id.btn_calcular_imc);
        this.btnLimpar = findViewById(R.id.btn_limpar);
        this.edtPeso = findViewById(R.id.edt_peso);
        this.edtAltura = findViewById(R.id.edt_altura);
        // mapeando eventos
        this.btnCalcularImc.setOnClickListener(this);
        this.btnLimpar.setOnClickListener(this);
        // mapeando o evento de clique longo
        this.btnCalcularImc.setOnLongClickListener(this);
    }

    private void limpar() {
        this.edtPeso.setText("");
        this.edtAltura.setText("");
        this.txtImc.setText("0.0");
    }

    private void calcularImc() {
        Log.i("teste", "Caiu no método para calcular o imc!");
        double imc = 0;
        String pesoString = this.edtPeso.getText().toString().trim();
        String alturaString = this.edtAltura.getText().toString().trim();
        boolean ok = true;

        if (pesoString.isEmpty()) {
            ok = false;
            // Toast.makeText(getApplicationContext(), "Informe o peso", Toast.LENGTH_LONG).show();
            /**
             * o método setError() adicionar um efeito de erro no campo
             * com uma mensagem que você passar como parâmetro para o método
             */
            edtPeso.setError("O campo de peso deve ser preenchido!");
        }

        if (alturaString.isEmpty()) {
            ok = false;
            // Toast.makeText(getApplicationContext(), "Informe aaltura", Toast.LENGTH_LONG).show();
            edtAltura.setError("O campo altura deve ser preenchido!");
        }

        if (ok) {
            double peso = Double.parseDouble(pesoString);
            double altura = Double.parseDouble(alturaString);
            imc = peso / (altura * altura);
            Log.i("imc_calculado", String.valueOf(imc));
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            this.txtImc.setText("IMC: " + decimalFormat.format(imc));
        }

    }

    @Override
    public void onClick(View view) {
        // método para controlar o evento de clique

        if (view.getId() == this.btnCalcularImc.getId()) {
            // clicou no botão para calcular o imc
            this.calcularImc();
        } else {
            // clicou no botão para limpar os campos e o texto com o calculo do imc
            this.limpar();
        }

    }

    @Override
    public boolean onLongClick(View view) {
        // método para controlar o evento de clique longo
        Toast.makeText(getApplicationContext(), "Clique longo no botão!", Toast.LENGTH_LONG)
                .show();

        return false;
    }

}