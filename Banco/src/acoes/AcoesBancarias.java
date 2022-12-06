package acoes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import conta.Contas;

public class AcoesBancarias {

    public void extrato(String nome){
        List<String> dados = new ArrayList<>();
        Scanner leitor;
        try {
            leitor = new Scanner(new FileReader(nome));
            while (leitor.hasNextLine()){
                String lerLinhas = leitor.nextLine();
                dados.add(lerLinhas);
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendario = Calendar.getInstance();
            String data = sdf.format(calendario.getTime());
            

            String mostrarNome = "Cliente: " + nome;
            String dataCriacao = "Data de Cadastro: " + dados.get(1);
            String dataAtual = "Data de hoje: " + data;
            String mostarSaldo = "Saldo Atual R$" + dados.get(3);

            String[] dadosRelevantes = {mostrarNome, dataCriacao, dataAtual, mostarSaldo};
            JOptionPane.showMessageDialog(null, dadosRelevantes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void deposito(String nome, String conta, String data, String senha, String valor) {
        List<String> dados = new ArrayList<>();
        FileReader fr;
        Scanner leitor;

        try {
            fr = new FileReader(nome);
            leitor = new Scanner(fr);
            while (leitor.hasNextLine()){
                String lerLinhas = leitor.nextLine();
                dados.add(lerLinhas);
            }

            double saldo = Double.valueOf(dados.get(3)).doubleValue();
            String valorEmString = JOptionPane.showInputDialog(null, "Valor do Deposito R$:");
            Double valorEmDouble = Double.valueOf(valorEmString);
            Double somaValores = saldo + valorEmDouble;
            String adicionarValores = somaValores.toString();

            PrintWriter pw = new PrintWriter(nome);
            pw.println(conta);
            pw.println(data);
            pw.println(senha);
            pw.println(adicionarValores);
            pw.close();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendario = Calendar.getInstance();
            String dataAtual = sdf.format(calendario.getTime());
            
            Object[] obj = {"Deposito de R$" + valorEmString + " Realizado com sucesso em " + dataAtual};
            
            JOptionPane.showMessageDialog(null, obj);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }      
        
    }

    public void saque(String nome, String conta, String data, String senha, String valor){
        List<String> dados = new ArrayList<>();
        FileReader fr;
        Scanner leitor;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendario = Calendar.getInstance();
        String dataAtual = sdf.format(calendario.getTime());

        try {
            fr = new FileReader(nome);
            leitor = new Scanner(fr);
            while (leitor.hasNextLine()){
                String lerLinhas = leitor.nextLine();
                dados.add(lerLinhas);
            }

            double saldo = Double.valueOf(dados.get(3)).doubleValue();
            String valorEmString = JOptionPane.showInputDialog(null, "Valor do Saque R$:");
            Double valorEmDouble = Double.valueOf(valorEmString);
            Double subtraiValores = saldo - valorEmDouble;
            String adicionarValores = subtraiValores.toString();
            
            if(saldo >= valorEmDouble) {
            	
                PrintWriter pw = new PrintWriter(nome);
                pw.println(conta);
                pw.println(data);
                pw.println(senha);
                pw.println(adicionarValores);
                pw.close();
            	
            	Object[] obj = {"Saque de R$" + valorEmString + " Realizado em " + dataAtual};
            	JOptionPane.showMessageDialog(null, obj);
            }else if(saldo < valorEmDouble){
            	JOptionPane.showMessageDialog(null, "Você não possui saldo suficiente!");
            	extrato(nome);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        
    }

    
}
