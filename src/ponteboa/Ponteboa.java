/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ponteboa;

import java.util.Scanner;
import javax.swing.JOptionPane;


/**
 *
 * @author caiol
 */
public class Ponteboa {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tipo;
        double TensaoCompressao, TensaoTracao, temp;
        double TensaoAd, TensaoMax, L, Esbeltez, AreaSecao, e, E, I, ParteDeBaixo, Forca, Comprimento;

        
        tipo = Integer.parseInt(JOptionPane.showInputDialog("Tipo [0 - Compressao; 1 - Tração]: " ));
        e = Float.parseFloat(JOptionPane.showInputDialog("Qual o Expessura da barra mm: " ));
        E = Float.parseFloat(JOptionPane.showInputDialog("Qual o Modulo de Elasticidade: " ));
        L = Float.parseFloat(JOptionPane.showInputDialog("Qual o Largura da barra mm: " ));
        Comprimento = Float.parseFloat(JOptionPane.showInputDialog("Qual o Comprimento da barra mm: " ));
        Forca = Float.parseFloat(JOptionPane.showInputDialog("Forca aplicada na barra N: " ));

        AreaSecao = (Math.pow(L, 2)) - Math.pow((L - (2 * e)), 2);
        JOptionPane.showConfirmDialog(null, "Secao transversal: " + AreaSecao + " mm2");
        TensaoAd = Forca / AreaSecao;

        if (tipo == 0) {
            TensaoCompressao = Float.parseFloat(JOptionPane.showInputDialog("Tensao maxima suportada em compressao N: " ));
            I = Math.pow(L, 4) - Math.pow((L - (2 * e)), 4);
            I /= 12;
            JOptionPane.showConfirmDialog(null, "Inercia min: " + I + " mm4");

            ParteDeBaixo = Math.sqrt(I / AreaSecao);
            Esbeltez = Comprimento / ParteDeBaixo;
            JOptionPane.showConfirmDialog(null, "Esbeltez: " + Esbeltez);

            if (Esbeltez > 40) {
                System.out.println("1");
                TensaoMax = (Math.pow(Math.PI, 2) * E) / Math.pow(Esbeltez, 2);
                
                JOptionPane.showConfirmDialog(null, "Tensao maxima: " + TensaoMax + " MPa\nTensao atuante: " + TensaoAd + " MPa");

                if (TensaoAd > TensaoMax) {
                    
                    JOptionPane.showConfirmDialog(null, "Mude o L");
                }

            } else if (Esbeltez <= 40) {
                JOptionPane.showConfirmDialog(null, "Tensao atuante: " + TensaoAd + " MPa\nTensao maxima: " + TensaoCompressao + " MPa");
                
                if (TensaoCompressao < TensaoAd) {
                    JOptionPane.showConfirmDialog(null, "Aumente a Largura do pilar");
                }
            }
        } else if (tipo == 1) {
            TensaoTracao = Float.parseFloat(JOptionPane.showInputDialog("Tensao maxima suportada em tracao N: " ));
            for (L = 10; L >= 0; L -= 0.1) {
                TensaoAd = Forca / (L * e);
                System.out.println(TensaoAd);
                
                if (TensaoAd > TensaoTracao) {
                    JOptionPane.showConfirmDialog(null, "Tensao acima");
                    L = 0;
                    
                } else {
                    temp = L;
                    JOptionPane.showConfirmDialog(null, "Largura: " + temp);
                }
            }
        }
    }
}
