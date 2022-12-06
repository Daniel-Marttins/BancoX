package conta;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import acoes.AcoesBancarias;

public class Contas {

    AcoesBancarias cb = new AcoesBancarias();
    
    public void opcoes(){

        String[] op = {"Entrar", "Cadastrar"};

        int opcoes = JOptionPane.showOptionDialog(null, "Escolha as opções abaixo:", "Bem vindo(a) ao BancoBanco!", 0, 1, null, op, getClass());

        if(opcoes == 0){
            login();
        }else if(opcoes == 1){
            cadastrar();
        }
    }

    public void login(){
        String nome = JOptionPane.showInputDialog(null, "Digite seu nome: ");
        String numeroConta = JOptionPane.showInputDialog(null, "Numero da Conta: ");
        JPasswordField password = new JPasswordField();
        int senha = JOptionPane.showConfirmDialog(null, new Object[]{"Informe sua senha: ", password}, "Senha", 0, 1);
        String pegarSenha = new String(password.getPassword());

        verificaDado(nome, numeroConta, pegarSenha);
    }

    public void cadastrar(){

        String nome = JOptionPane.showInputDialog(null, "Nome completo: ", "Nome", 1);
        Random rd = new Random();
        int geraNumeroConta = rd.nextInt(1000, 2000);
        String converterNumeroConta = Integer.toString(geraNumeroConta);
        Object[] obj = {"O Numero da sua conta: " + converterNumeroConta};
        String data = JOptionPane.showInputDialog(null, "Informe a data(dd/MM/yyyy): ", "Data", 1);
        JOptionPane.showMessageDialog(null, obj);
        JPasswordField password = new JPasswordField();
        int senha = JOptionPane.showConfirmDialog(null, new Object[]{"Infome uma senha: ", password}, "Senha", 0);
        String pegarSenha = new String(password.getPassword());
        String valor = "0";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try {
			Date pegarData = formato.parse(data);
			String novaDataFormat = formato.format(pegarData);
			
			salvarDados(nome, converterNumeroConta, pegarSenha, novaDataFormat, valor);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
   
    
    public void salvarDados(String nome, String conta, String senha, String data, String valor){
        FileWriter fw;
        PrintWriter pw;

        try {
            fw = new FileWriter(nome);
            pw = new PrintWriter(nome);

            pw.println(conta);
            pw.println(data);
            pw.println(senha);
            pw.println("0");
            pw.close();

        } catch (IOException e) {
            System.out.println("Não foi possivel realizar o cadastro, tente novamente!");
        }

        JOptionPane.showMessageDialog(null, "Cadastro Realizado, agora faça o login na sua conta!");

        login();
    }

    private void verificaDado(String nome, String conta, String senha) {
        List<String> dados = new ArrayList<>();
        Scanner reader;
        String valor;
        String data;
        
        try {
            reader = new Scanner(new FileReader(nome));
            while(reader.hasNextLine()){
                String lerLinhas = reader.nextLine();
                dados.add(lerLinhas);
            }
            
            valor = dados.get(3);
            data = dados.get(1);
            
            if(conta.equals(dados.get(0)) && senha.equals(dados.get(2))){
                servicos(nome, conta, data, senha, valor);
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Usuário(a) não encontrado, verifique os dados ou realize um cadastro!");
            opcoes();
        }
    }

    public void servicos(String nome, String conta, String data, String senha, String valor){

        while(true){

            String[] opcoes = {
                "[1] - Extrato",
                "[2] - Deposito", 
                "[3] - Saque", 
                "[4] - Transferência",
                "[5] - Sair"}; 
    
            int servicos = JOptionPane.showOptionDialog(null, "Bem vindo(a) ao BancoX", nome, 0, 1, null, opcoes, getClass());

            if(servicos == 0){
                cb.extrato(nome);
            }else if(servicos == 1){
                cb.deposito(nome, conta, data, senha, valor);
            }else if(servicos == 2){
                cb.saque(nome, conta, data, senha, valor);
            }else if(servicos == 3){
                JOptionPane.showMessageDialog(null, "Em andamento!");
            }else if(servicos == 4){
            	Object[] obj = {"Até Logo " + nome};
            	JOptionPane.showMessageDialog(null, obj);
                break;
            }
        }

    }
}
