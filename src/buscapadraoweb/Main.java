/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package buscapadraoweb;

import buscaweb.CapturaRecursosWeb;

import java.util.ArrayList;

/**
 *
 * @author Santiago
 */
public class Main {

    // busca char em vetor e retorna indice
    public static int get_char_ref (char[] vet, char ref ){
        for (int i=0; i<vet.length; i++ ){
            if (vet[i] == ref){
                return i;
            }
        }
        return -1;
    }

    // busca string em vetor e retorna indice
    public static int get_string_ref (String[] vet, String ref ){
        for (int i=0; i<vet.length; i++ ){
            if (vet[i].equals(ref)){
                return i;
            }
        }
        return -1;
    }

    

    //retorna o próximo estado, dado o estado atual e o símbolo lido
    public static int proximo_estado(char[] alfabeto, int[][] matriz,int estado_atual,char simbolo,char[] alfabetoII){
        //System.out.println("Dentro de proximo_estado....");

        //System.out.println("        estado_atual: " + estado_atual);
        //System.out.println("        simbolo: " + estado_atual);
        int simbol_indice = get_char_ref(alfabeto, simbolo);
        int simbol_indiceII = get_char_ref(alfabetoII, simbolo);
        //System.out.println("        Simbol_indice: " + simbol_indice);
        //System.out.println("        Simbol_indiceII: " + simbol_indiceII);
        if (simbol_indice != -1){
            //System.out.println("            Dentro de simbol_indice return....");
            //System.out.println("            matriz: " + matriz[estado_atual][simbol_indice]);
            return matriz[estado_atual][simbol_indice];
        }else if (simbol_indiceII != -1){
            simbol_indiceII += 10;
            //System.out.println("            Dentro de simbol_indiceII return....");
            //System.out.println("            matriz: " + matriz[estado_atual][simbol_indiceII]);
            return matriz[estado_atual][simbol_indiceII];
        }else{
            return -1;
        }
    }

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //instancia e usa objeto que captura código-fonte de páginas Web
        CapturaRecursosWeb crw = new CapturaRecursosWeb();
        crw.getListaRecursos().add("https://genioferramentas.com.br/blog/como-ficara-a-placa-com-o-novo-padrao-mercosul1/");
        ArrayList<String> listaCodigos = crw.carregarRecursos();

        String codigoHTML = listaCodigos.get(0);
        //String codigoHTML = "AAA0A00 adad";

        //mapa do alfabeto
        char[] alfabeto = new char[10];
        alfabeto[0] = '0';
        alfabeto[1] = '1';
        alfabeto[2] = '2';
        alfabeto[3] = '3';
        alfabeto[4] = '4';
        alfabeto[5] = '5';
        alfabeto[6] = '6';
        alfabeto[7] = '7';
        alfabeto[8] = '8';
        alfabeto[9] = '9';

        char[] alfabetoII = new char[26];
        for (int i = 0; i < 26; i++){
            int letra = i + 65; // 0 + 65 = A
            alfabetoII[i] = ((char)letra);

            //System.out.println(alfabetoII[i]);
        }

        //mapa de estados
        String[] estados = new String[8];
        estados[0] = "q0";
        estados[1] = "q1";
        estados[2] = "q2";
        estados[3] = "q3";
        estados[4] = "q4";
        estados[5] = "q5";
        estados[6] = "q6";
        estados[7] = "q7";

        String estado_inicial = "q0";

        //estados finais
        String[] estados_finais = new String[1];
        //estados_finais[0] = "q2";
        estados_finais[0] = "q7";

        //tabela de transição de AFD para reconhecimento números de dois dígitos
        //int[][] matriz = new int[3][10];
        int[][] matriz = new int[8][36];
        //transições de q0
        for (int i = 0; i < 10; i++){
            matriz[get_string_ref(estados, "q0")][get_char_ref(alfabeto, Character.forDigit(i, 10))] = -1;
        }
        for (int i = 10; i < 36; i++){
            int letra = i + 55; // 10 + 55 = A
            matriz[get_string_ref(estados, "q0")][get_char_ref(alfabetoII, ((char)letra)) + 10] = get_string_ref(estados, "q1");
        }

        //transições de q1
        for (int i = 0; i < 10; i++){
            matriz[get_string_ref(estados, "q1")][get_char_ref(alfabeto, Character.forDigit(i, 10))] = -1;
        }
        for (int i = 10; i < 36; i++){
            int letra = i + 55; // 10 + 55 = A
            matriz[get_string_ref(estados, "q1")][get_char_ref(alfabetoII, ((char)letra)) + 10] = get_string_ref(estados, "q2");
        }

        //transições de q2
        for (int i = 0; i < 10; i++){
            matriz[get_string_ref(estados, "q2")][get_char_ref(alfabeto, Character.forDigit(i, 10))] = -1;
        }
        for (int i = 10; i < 36; i++){
            int letra = i + 55; // 10 + 55 = A
            matriz[get_string_ref(estados, "q2")][get_char_ref(alfabetoII, ((char)letra)) + 10] = get_string_ref(estados, "q3");
        }

        //transições de q3
        for (int i = 0; i < 10; i++){
            matriz[get_string_ref(estados, "q3")][get_char_ref(alfabeto, Character.forDigit(i, 10))] = get_string_ref(estados, "q4");
        }
        for (int i = 10; i < 36; i++){
            int letra = i + 55; // 10 + 55 = A
            matriz[get_string_ref(estados, "q3")][get_char_ref(alfabetoII, ((char)letra)) + 10] = -1;
        }

        //transições de q4
        for (int i = 0; i < 10; i++){
            matriz[get_string_ref(estados, "q4")][get_char_ref(alfabeto, Character.forDigit(i, 10))] = -1;
        }
        for (int i = 10; i < 36; i++){
            int letra = i + 55; // 10 + 55 = A
            matriz[get_string_ref(estados, "q4")][get_char_ref(alfabetoII, ((char)letra)) + 10] = get_string_ref(estados, "q5");
        }

        //transições de q5
        for (int i = 0; i < 10; i++){
            matriz[get_string_ref(estados, "q5")][get_char_ref(alfabeto, Character.forDigit(i, 10))] = get_string_ref(estados, "q6");
        }
        for (int i = 10; i < 36; i++){
            int letra = i + 55; // 10 + 55 = A
            matriz[get_string_ref(estados, "q5")][get_char_ref(alfabetoII, ((char)letra)) + 10] = -1;
        }

        //transições de q6
        for (int i = 0; i < 10; i++){
            matriz[get_string_ref(estados, "q6")][get_char_ref(alfabeto, Character.forDigit(i, 10))] = get_string_ref(estados, "q7");
        }
        for (int i = 10; i < 36; i++){
            int letra = i + 55; // 10 + 55 = A
            matriz[get_string_ref(estados, "q6")][get_char_ref(alfabetoII, ((char)letra)) + 10] = -1;
        }

        //transições de q7
        for (int i = 0; i < 10; i++){
            matriz[get_string_ref(estados, "q7")][get_char_ref(alfabeto, Character.forDigit(i, 10))] = -1;
        }
        for (int i = 10; i < 36; i++){
            int letra = i + 55; // 10 + 55 = A
            matriz[get_string_ref(estados, "q7")][get_char_ref(alfabetoII, ((char) letra)) + 10] = -1;
        }
        
        int estado = get_string_ref (estados, estado_inicial);
        int estado_anterior = -1;
        ArrayList<String> palavras_reconhecidas = new ArrayList();


        String palavra = "";

        //varre o código-fonte de um código
        for (int i=0; i<codigoHTML.length(); i++){

           /* if (estado != 0 && (codigoHTML.charAt(i) == 'A' || codigoHTML.charAt(i) == '0')){

            }*/

            //System.out.println("Char: " + codigoHTML.charAt(i));
            //System.out.println("Estado: " + estado);
            //System.out.println("estado_anterior: " + estado_anterior);

            estado_anterior = estado;
            estado = proximo_estado(alfabeto, matriz, estado, codigoHTML.charAt(i), alfabetoII);
            //System.out.println("Estado(novo): " + estado);
            //se o não há transição
            if (estado == -1){
                //pega estado inicial
                estado = get_string_ref(estados, estado_inicial);
                // se o estado anterior foi um estado final
                if (get_string_ref(estados_finais, estados[estado_anterior]) != -1){
                    //se a palavra não é vazia adiciona palavra reconhecida
                    if ( ! palavra.equals("")){
                        palavras_reconhecidas.add(palavra);
                    }
                    // se ao analisar este caracter não houve transição
                    // teste-o novamente, considerando que o estado seja inicial
                    i--;
                }
                //zera palavra
                palavra = "";

            }else{
                //se houver transição válida, adiciona caracter a palavra
                palavra += codigoHTML.charAt(i);
            }
        }


        //foreach no Java para exibir todas as palavras reconhecidas
        for (String p: palavras_reconhecidas){
            System.out.println (p);
        }


    }



}
