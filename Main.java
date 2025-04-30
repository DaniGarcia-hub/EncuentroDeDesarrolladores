import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numTickets = solicitarNum();

        if (numTickets < 0){
            System.err.println("ERROR. Cantidad de tickets inválida.");
        } else if (numTickets == 0) {
            System.err.println("ERROR. No se va a validar ningún ticket.");
        } else {
            ArrayList<String> resultados = new ArrayList<>();
            for (int i = 0; i < numTickets; i++){
                int numero = solicitarNum();
                if (verificarValidezNum(numero)){
                    resultados.add(calcularCodigoVerificacion(numero));
                }
            }
            
            for (String resultado : resultados){
                System.out.println(resultado);
            }
        }
    }

    public static int solicitarNum(){
        Scanner sc = new Scanner(System.in);
        String entrada = "";
        int valorNumerico = -1;
        try {
            entrada = sc.nextLine().trim();
            valorNumerico = Integer.parseInt(entrada);
        } catch (NumberFormatException e){
            System.err.println("ERROR. Tipo de dato inválido.");
        }
        return valorNumerico;
    }

    public static String calcularCodigoVerificacion(int numeroCalcular){
        String numConvertido = String.valueOf(numeroCalcular);
        int longitudNum = numConvertido.length();
        int posicionActual = 1;
        int divisor = 0;

        String resultadoTotal = "";
        for (int i = 0; i < longitudNum; i++){
            if (posicionActual % 2 != 0){
                divisor = 3;
                int numMenor = numConvertido.charAt(i+1);
                for (int e = i; e < longitudNum; e++){
                    if (numConvertido.charAt(e) < numMenor){
                        numMenor = numConvertido.charAt(e);
                    }
                }

                int resultado = numConvertido.charAt(i)*divisor+numMenor;
                resultadoTotal = resultadoTotal + String.valueOf(resultado);
            } else {
                divisor = 2;
                int numMayor = numConvertido.charAt(i+1);
                for (int e = longitudNum-1; e > i; e--){
                    if (numConvertido.charAt(e) > numMayor){
                        numMayor = numConvertido.charAt(e);
                    }
                }

                int resultado = numConvertido.charAt(i)*divisor+numMayor;
                resultadoTotal = resultadoTotal + String.valueOf(resultado);
            }
            System.out.println(numConvertido.charAt(i));
            posicionActual++;
        }
        return resultadoTotal;
    }

    public static boolean verificarValidezNum(int numeroValidar){
        boolean resultado = false;
        if (numeroValidar >= 1 && numeroValidar < Math.pow(10, 18)){
            int cantidadDigitos = String.valueOf(numeroValidar).length();
            if (cantidadDigitos % 2 == 0){
                resultado = true;
            }
        }
        return resultado;
    }
}