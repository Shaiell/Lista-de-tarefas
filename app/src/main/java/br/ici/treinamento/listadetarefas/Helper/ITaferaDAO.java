package br.ici.treinamento.listadetarefas.Helper;

import java.util.List;

import br.ici.treinamento.listadetarefas.Model.Tarefa;

public interface ITaferaDAO {

    public boolean salvar(Tarefa tarefa);
    public boolean atualizar(Tarefa tarefa);
    public boolean deletar (Tarefa tarefa);
    public List<Tarefa> listar ();

}
