package br.com.agibank.bo.conta;

import br.com.agibank.beans.conta.Conta;
import br.com.agibank.beans.conta.TipoConta;
import br.com.agibank.dao.conta.ContaDAO;
import br.com.agibank.dao.transacoes.ContaExternaDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ContaBO {

    private ContaDAO contaDAO = new ContaDAO();

    public ContaBO() throws SQLException {
        contaDAO = new ContaDAO();
    }

    public String buscarStatusConta(int idConta) throws SQLException{
        return contaDAO.buscarStatusConta(idConta);
    }

    public String exibirTitularConta(int idConta) throws SQLException{
        return contaDAO.exibirTitularConta(idConta);
    }

    public String exibirTiposConta(int idConta) throws SQLException {

        if (contaDAO.exibirTiposConta(idConta).isEmpty()) {
            return "Vazio";
        }

        return contaDAO.exibirTiposConta(idConta);
    }


    public int cadastrarConta(int idUsuario, int idTipo, double idClasse, int idAgencia, int numero, double saldo, String dataAbertura) {
        try {

            if (contaDAO.verificarContaExiste(idTipo, idAgencia, numero)) {
                if(idTipo == 1){
                    System.out.println("Erro: Você já possui uma conta corrente na agencia e numero digitados");
                    return 0;
                } else if (idTipo == 2) {
                    System.out.println("Erro: Você já possui uma conta poupanca na agencia e numero digitados");
                    return 0;
                }else {
                    System.out.println("Erro: Você já possui uma conta salario na agencia e numero digitados");
                    return 0;
                }
            }

            if (saldo < 10) {
                System.out.println("Erro: O saldo inicial deve ser de pelo menos R$ 10,00.");
                return 0;
            }

            int resultado = contaDAO.cadastrarConta(idUsuario, idTipo, idClasse, idAgencia, numero, saldo, dataAbertura);

            if (resultado > 0) {
                return 1;
            } else {
                System.out.println("Erro ao cadastrar conta no banco.");
                return 0;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar conta: " + e.getMessage());
            return 0;
        }
    }

    public int deletarConta(int idConta){
        try{
            contaDAO.deletarConta(idConta);
            return 1;
        }catch (SQLException e){
            System.out.println("Erro ao excluir conta: " + e.getMessage());
            return 0;
        }
    }


}
