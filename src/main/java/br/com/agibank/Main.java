package br.com.agibank;
import br.com.agibank.controller.ScoreController;
import br.com.agibank.view.MenuInicial;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException{
        Scanner sc = new Scanner(System.in);
        //MenuInicial.exibirMenuInicial(sc);
        ScoreController scoreController = new ScoreController();

        System.out.println(scoreController.atualizarScore(2));
    }
}