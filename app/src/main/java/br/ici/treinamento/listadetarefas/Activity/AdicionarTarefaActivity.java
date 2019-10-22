package br.ici.treinamento.listadetarefas.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.ici.treinamento.listadetarefas.Helper.TarefaDAO;
import br.ici.treinamento.listadetarefas.Model.Tarefa;
import br.ici.treinamento.listadetarefas.R;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.textTarefa);

        //RECUPERAR TAREFA ACASO SEJA EDIÇÃO
        tarefaAtual = (Tarefa)getIntent().getSerializableExtra("tarefaSelecionada");

        //CONFIGURAR A TAREFA NA CAIXA DE TEXTO
        if(tarefaAtual!= null){
            editTarefa.setText(tarefaAtual.getNomeTarefa());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.itemSalvar:
                //EXECUTA A AÇÃO PARA SALVAR O ITEM

                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
               if(tarefaAtual!=null){
                   String nomeTarefa = editTarefa.getText().toString();
                   if(!nomeTarefa.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        tarefa.setId(tarefaAtual.getId());

                        //ATUALIZAR BANCO DE DADOS
                       if(tarefaDAO.atualizar(tarefa)){
                           finish();
                           Toast.makeText(getApplicationContext(), "Sucesso ao atualizar a tarefa!", Toast.LENGTH_SHORT).show();
                       }else {
                           Toast.makeText(getApplicationContext(), "Erro ao atualizar a tarefa!", Toast.LENGTH_SHORT).show();
                       }
                   }else{
                       Toast.makeText(this, "Informe uma tarefa.", Toast.LENGTH_SHORT).show();
                   }
               }else{
                   String nomeTarefa = editTarefa.getText().toString();
                   if(!nomeTarefa.isEmpty()){
                       Tarefa tarefa = new Tarefa();
                       tarefa.setNomeTarefa(nomeTarefa);
                       if(tarefaDAO.salvar(tarefa)){
                           finish();
                           Toast.makeText(getApplicationContext(), "Sucesso ao salvar a tarefa!", Toast.LENGTH_SHORT).show();
                       }else{
                           Toast.makeText(getApplicationContext(), "Erro ao salvar a tarefa!", Toast.LENGTH_SHORT).show();
                       }

                   }else{
                       Toast.makeText(this, "Informe uma tarefa.", Toast.LENGTH_SHORT).show();
                   }
               }
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
