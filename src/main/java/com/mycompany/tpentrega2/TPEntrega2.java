/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.tpentrega2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author grupo6
 */
public class TPEntrega2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        // Asignando variable a la ruta de acceso del archivo EQUIPOS.CSV
        String ubicacionArchivoEquipos = "C:\\tpintegradorgrupo6\\equipos.csv";

        // Trayendo el archivo EQUIPOS.CSV a través del método PATH
        Path pathEquipos = Paths.get(ubicacionArchivoEquipos);

        // Creando lista de equipos
        List<Equipos> listaEquipos = new ArrayList<>();
        // Leyendo cada línea del archivo EQUIPOS.CSV e instanciando objetos Equipos
        for (String equipo : Files.readAllLines(pathEquipos)) {
            Equipos equipos = new Equipos(equipo);
            listaEquipos.add(equipos);
        }
        //Asigno variables y rutas de acceso a resultados.csv y pronosticos.csv
        String ruta2 = "C:\\tpintegradorgrupo6\\resultados.csv";
        String ruta3 = "C:\\tpintegradorgrupo6\\pronosticos.csv";
        Path archivo2 = Paths.get(ruta2);
        Path archivo3 = Paths.get(ruta3);

//Creo lista de personas y al leer el archivo pronosticos, instancio Personas(o participantes)
        List<Personas> listaPersonas = new ArrayList<>();
        //Leyendo el archivo pronosticos.csv desde la segunda linea, instanciando Personas
        //Se verifica que esa Persona no esté cargada previamente. Asumimos que no hay dos peronas con mismo nombre
        for (String linea3 : Files.readAllLines(archivo3).subList(1, Files.readAllLines(archivo3).size())) {
            String[] split = linea3.split(";");
            String check = split[0];
            boolean repetido = false;
            for (Personas iterar1 : listaPersonas) {
                if (iterar1.getNombre().equals(check)) {
                    repetido = true;
                }
            }
            if (repetido == false) {
                Personas persona = new Personas(split[0]);
                listaPersonas.add(persona);
            }
        }


//Creando lista de pronósticos 
        List<Pronosticos> listaPronosticos = new ArrayList<>();
        //Leyendo el archivo pronosticos.csv desde la segunda linea, instanciando Pronosticos, determina resultado
        for (String linea3 : Files.readAllLines(archivo3).subList(1, Files.readAllLines(archivo3).size())) {
            String[] split = linea3.split(";");
            if ("true".equals(split[3])) {
                Pronosticos pronostico = new Pronosticos(split[0], split[1], Resultados.ResultadosEnum.GANA_EQUIPO_1);
                listaPronosticos.add(pronostico);
            } else if ("true".equals(split[4])) {
                Pronosticos pronostico = new Pronosticos(split[0], split[1], Resultados.ResultadosEnum.EMPATE);
                listaPronosticos.add(pronostico);
            } else if ("true".equals(split[5])) {
                Pronosticos pronostico = new Pronosticos(split[0], split[1], Resultados.ResultadosEnum.GANA_EQUIPO_2);
                listaPronosticos.add(pronostico);
            }
        }

//Verifica cantidad de campos del archivo resultados.csv, en caso de error, da aviso en pantalla y cierra el programa        
        for (String linea2 : Files.readAllLines(archivo2).subList(1, Files.readAllLines(archivo2).size())) {
            String[] split = linea2.split(";");
            if (split.length != 6) {
                System.out.println("ERROR: La cantidad de campos no es la correcta en al menos una de las líneas del archivo resultados.csv");
                return;
            }
        };
// Utilizo Try - Catch para el caso de que los goles de un equipo no sea nro.entero, al momento de leer resultados.csv
        for (String linea2 : Files.readAllLines(archivo2).subList(1, Files.readAllLines(archivo2).size())) {
            String[] split = linea2.split(";");
            try {
                int golesEquipo1 = Integer.parseInt(split[3]);
                int golesEquipo2 = Integer.parseInt(split[4]);
            } catch (java.lang.NumberFormatException e) {
                System.out.println("ERROR: Goles de al menos un equipo no es un nro. entero");
                return;

            }
        }

//Leyendo el archivo resultados.csv desde la segunda linea, para obtener la cantidad de rondas
        int cantidadRondas = 0;
        for (String linea2 : Files.readAllLines(archivo2).subList(1, Files.readAllLines(archivo2).size())) {
            String[] split = linea2.split(";");
            if (cantidadRondas < Integer.parseInt(split[0])) {
                cantidadRondas = Integer.parseInt(split[0]);
            }
        };
//Instanciando Rondas
        List<Ronda> rondaLista = new ArrayList<>();
        //instanciando rondas
        for (int i = 1; i <= cantidadRondas; i++) {
            Ronda ronda = new Ronda(i);
            rondaLista.add(ronda);
        }
//Cargando partidos a Rondas
        for (Ronda iterar1 : rondaLista) {
            iterar1.setPartidos();
        }


// Compara los objetos "pronosticos" con los objetos "partidos" y si el codigo es el mismo determina si hubo o no hubo acierto
        for (Pronosticos iterar1 : listaPronosticos) {
            for (Ronda iterar2 : rondaLista) {
                for (Partidos iterar3 : iterar2.getPartidos()) {
                    if (iterar1.getCodigo().equals(iterar3.getCodigo())) {
                        iterar1.asignaAciertos(iterar1.getResultado(), iterar3.getResultado());
                    } else {
                    }
                }
            }
        }
//Para cada Persona, calcula los aciertos totales y los aciertos de cada ronda guardandolo con Hashmap
        for (Personas iterar1 : listaPersonas) {
            int aciertosTotales = 0;
            int aciertos = 0;
            String nombrePersona = iterar1.getNombre();
            for (Ronda iterar2 : rondaLista) {
                int nroRonda = iterar2.getNroRonda();
                for (Pronosticos iterar3 : listaPronosticos) {
                    for (Partidos iterar4 : iterar2.getPartidos()) {
                        if ((nombrePersona.equals(iterar3.getParticipante())) && (iterar3.getCodigo().equals(iterar4.getCodigo()))) {
                            aciertos = aciertos + iterar3.getAciertos();
                            aciertosTotales = aciertosTotales + iterar3.getAciertos();
                        }
                    }
                }
                iterar1.mapAciertosRonda.put(nroRonda, aciertos);
                aciertos = 0;
            }
            iterar1.setAciertosTotales(aciertosTotales);
        }
//Imprime en pantalla los participantes y el puntaje obtenido en cada ronda y en total
        for (Personas iterar1 : listaPersonas) {
            System.out.println("El/La participante " + iterar1.getNombre() + " obtuvo el siguiente puntaje:");
            for (Ronda iterar2 : rondaLista) {
                System.out.println("Ronda:" + iterar2.getNroRonda() + ", Aciertos:" + iterar1.mapAciertosRonda.get(iterar2.getNroRonda()) + ", Puntos:" + iterar1.calculaPuntaje(iterar1.mapAciertosRonda.get(iterar2.getNroRonda())));
            }
            System.out.println("El puntaje total es " + iterar1.calculaPuntaje(iterar1.getAciertosTotales()));

        }
    }
}
