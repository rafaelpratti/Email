package dettmann.pratti.rafael.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obter o botão de enviar a partir do id
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);

        /*Ação a ser realizada ao pressionar o botão*/
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            // executado quando o botão é clicado
            public void onClick(View v) {

                // obtendo o edittext de cada campo pelo id
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                EditText etTexto = (EditText) findViewById(R.id.etTexto);

                // transformando o texto dos campos edittext em string
                String email = etEmail.getText().toString();
                String assunto = etAssunto.getText().toString();
                String texto = etTexto.getText().toString();

                // procurar app a partir do intent implícito para a ação de enviar
                Intent i = new Intent(Intent.ACTION_SENDTO);

                // especifica o tipo de app requisitado (app de envio e recebimento de email)
                i.setData(Uri.parse("mailto:"));

                // cria uma lista com os endereços de email de destinatário
                String[] emails = new String[]{email};

                // envia com a intent os dados digitados
                i.putExtra(Intent.EXTRA_EMAIL,emails);
                i.putExtra(Intent.EXTRA_SUBJECT,assunto);
                i.putExtra(Intent.EXTRA_TEXT,texto);

                try {
                    // Se o usuário possuir mais de um app de email, ele pode escolher
                    startActivity(Intent.createChooser(i,"Escolha o APP"));
                }
                catch (ActivityNotFoundException e){
                    // caso contrário, aparece mensagem de erro
                    Toast.makeText(MainActivity.this, "Não há nenhum app que possa realizar essa operação", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}