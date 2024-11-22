import java.util.Random;
import java.util.Scanner;

public class Main {

    //Indica el numero maximo de jugadores que puede tener una partida
    final static int maxJugadors = 4;
    //Indico el tamaño que tiene el tablero
    final static int TABLEROTAMAÑO = 5;
    //El numero de casillas jugables en el tablero
    final static int CASILLASDELTABLERO = (TABLEROTAMAÑO - 1) * 4;
    //Matriz que contiene el tablero
    static int[][] TABLERO = new int[TABLEROTAMAÑO][TABLEROTAMAÑO];
    //Guarda las casillas que ya han sido reveladas
    static int[][] CASILLASREVELADAS = new int[CASILLASDELTABLERO][2];
    //Guarda las cordenadas de donde se encuentra cada pieza en el tablero
    static int[][] PLAYERSCORDS = new int[maxJugadors][2];
    //Almacena efectos que se ejecutaran sobre los jugadores luego
    static int[] PLAYERSEFECTOS = new int[maxJugadors];
    //Guarda las casilla donde estan los jugadores, en numeros del tablero
    static int[] PLAYERSCASILLAS = new int[maxJugadors];
    //Almacena los inconos de cada jugador
    static char[] JUGADORESICONS = new char[maxJugadors];
    final static Scanner input = new Scanner(System.in);

    final static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("""
                Bienvenido a SuperVueltas, elije una opcion
                1.Jugar partida clasica
                2.Jugar una partida personalizada
                3.Cargar una partida
                4.Salir
                """);
        int opcio = inputU.between(1, 4, "Introduce un numero que este entre las opciones");
        switch (opcio) {
            case 1 -> {
                empezarPartidaClasica();
            }
            /**
             case 2 -> {
             personalizarPartida();
             }
             case 3 -> {
             cargarPartida();
             }
             case 4 -> {
             salir();
             }*/
        }
    }

    /**
     *Empieza una partida con parametros normales
     */
    public static void empezarPartidaClasica() {
        boolean finPartida = false;

        System.out.println("Cuantos jugadores jugaran esta partida?");
        int numJugadores = inputU.between(2, 4, "Solo pueden haber de 2 a 4 jugadores");

        elijePersonaje(numJugadores);

        System.out.println("El tablero ha sido generado, tenemos casillas vacias [ ] y casillas especiales [X]");
        TABLERO = generarTablero(numJugadores);

        //Bucle que le da los turnos a cada jugador hasta que acabe la partida
        while (!finPartida) {
            for (int i = 0; i < numJugadores; i++) {
                    turnoJugador(i, numJugadores);
                    mostrarTablero(TABLERO, numJugadores);
            }
        }
    }

    /**
     * Almacena todas las acciones que puede hacer un jugador en su turno
     * @param jugador, el jugador del que es el turno (0,1,2,3) del 1ro al 4to
     * @param numJugadores, el numero de jugadores que esta jugando la partida
     */
    public static void turnoJugador(int jugador, int numJugadores) {
        System.out.println("Turno del jugador " + (jugador + 1));
        System.out.println("Escribe algo para tirar los dados");
        input.next();
        int movimiento = lanzarDados(jugador);
        contadorCasillas(jugador, movimiento);
        moverJugador(jugador, movimiento, numJugadores);
        System.out.println("Tu movimiento se ejecuto correctamente");
        efectoCasilla(jugador,numJugadores);
        System.out.println("Turno del siguiente jugador");
    }

    /**
     * Ejecuta el efecto de la casilla
     * @param jugador, jugador del cual es el turno
     * @param numJugadores, numero de jugadores
     */
    public static void efectoCasilla(int jugador, int numJugadores) {
        int casillaValor = TABLERO[PLAYERSCORDS[jugador][0]][PLAYERSCORDS[jugador][1]];
        switch (casillaValor) {
            case 4 :break;
            case 5 : break;
            case 6 : break;
            case 7 : break;
            case 8 : break;
            case 9 : break;
        }
    }

    /**
     *Mueve a un jugador hacia atras
     * @param player, jugador que se movera
     * @param pasos, pasos que se movera
     * @param numJugadores, numero de jugadores de la partida
     */
    public static void moverJugadorAtras(int player, int pasos,int numJugadores) {

        int[] playerCords = PLAYERSCORDS[player];
        int pasosFaltantes = pasos;

        int fila = playerCords[0];
        int columna = playerCords[1];

        while (pasosFaltantes > 0) {

            if (fila == 0 && columna != TABLEROTAMAÑO - 1) {
                columna--;
                pasosFaltantes--;
            } else if (columna == TABLEROTAMAÑO - 1 && fila != TABLEROTAMAÑO - 1) {
                fila--;
                pasosFaltantes--;
            } else if (fila == TABLEROTAMAÑO - 1 && columna != 0) {
                columna++;
                pasosFaltantes--;
            } else {
                fila++;
                pasosFaltantes--;
            }

        }
        PLAYERSCORDS[player] = new int[]{fila, columna};
        CASILLASREVELADAS[PLAYERSCASILLAS[player]] = new int[]{fila, columna};
        mostrarTablero(TABLERO,numJugadores);
    }

    /**
     * Calcula la casilla(por numero) en la  que se encuentra un jugador al moverse
     * @param jugador, jugador a saber casilla
     * @param pasos, pasos que se movio
     */
    public static void contadorCasillas(int jugador, int pasos) {
        PLAYERSCASILLAS[jugador] += pasos;
        if (PLAYERSCASILLAS[jugador] > (CASILLASDELTABLERO - 1)) {
            PLAYERSCASILLAS[jugador] -= CASILLASDELTABLERO;
        }
    }
    /**
     *Mueve a un jugador hacia adelante
     * @param player, jugador que se movera
     * @param pasos, pasos que se movera
     * @param numJugadores, numero de jugadores de la partida
     */
    public static void moverJugador(int player, int pasos, int numJugadores) {

        int[] playerCords = PLAYERSCORDS[player];
        int pasosFaltantes = pasos;

        int fila = playerCords[0];
        int columna = playerCords[1];

        while (pasosFaltantes > 0) {

            if (fila == 0 && columna != TABLEROTAMAÑO - 1) {
                columna++;
                pasosFaltantes--;
            } else if (columna == TABLEROTAMAÑO - 1 && fila != TABLEROTAMAÑO - 1) {
                fila++;
                pasosFaltantes--;
            } else if (fila == TABLEROTAMAÑO - 1 && columna != 0) {
                columna--;
                pasosFaltantes--;
            } else {
                fila--;
                pasosFaltantes--;
            }

        }
        PLAYERSCORDS[player] = new int[]{fila, columna};
        CASILLASREVELADAS[PLAYERSCASILLAS[player]] = new int[]{fila, columna};
        mostrarTablero(TABLERO,numJugadores);
    }

    /**
     * Ejecuta la accion de lanzar dados para un jugador
     * @param jugador, el jugador que lanza los dados
     * @return , el numero que salio en el dado
     */
    public static int lanzarDados(int jugador) {
        int dadoUno = random.nextInt(1, 7);
        int dadoDos=random.nextInt(1, 7);;
        int dado;

        int valor = 0;

        System.out.println("Dado #1 " + dadoUno);
        System.out.println("Dado #2 " + dadoDos);

        System.out.println("Elije cual dado usaras");

        dado = inputU.between(1, 2, "Solo hay 2 dados");

        if (dado == 1) {
            valor = dadoUno;
        } else {
            valor = dadoDos;
        }
        return valor;
    }

    /**
     * Le asigna una ficha a un jugador
     * @param numJug, el jugador que elejira su ficha
     */
    public static void elijePersonaje(int numJug) {
        for (int i = 0; i < numJug; i++) {
            System.out.println("Jugador " + (i + 1) + " introduce una letra para que sea su ficha");
            JUGADORESICONS[i] = pedirChar();
        }
    }

    /**
     *Pide un caracter
     * @return , el caracter
     */
    public static char pedirChar() {
        char ficha = (input.next()).charAt(0);
        if (!Character.isLetter(ficha)) {
            System.out.println("El simbolo introducido no es valido vuelve a intentarlo");
            pedirChar();
        }
        return ficha;

    }

    /**
     * Genera el tablero y le asigna valor a las casillas
     * @param numJugadores, numero de jugadores
     * @return ,
     */
    public static int[][] generarTablero(int numJugadores) {
        int[][] tablero = new int[TABLEROTAMAÑO][TABLEROTAMAÑO];

        for (int i = 0; i < TABLEROTAMAÑO; i++) {
            for (int j = 0; j < TABLEROTAMAÑO; j++) {
                if (i == 0 && j == 0) {
                    tablero[i][j] = 1;
                } else if (i == 0 || i == TABLEROTAMAÑO - 1 || j == 0 || j == TABLEROTAMAÑO - 1) {
                    tablero[i][j] = casillaRandom();
                } else {
                    tablero[i][j] = 0;
                }
            }
        }

        mostrarTablero(tablero, numJugadores);
        return tablero;
    }

    /**
     * Genera un numero random del 1 al 9
     * @return , el numero generado
     */
    public static int casillaRandom() {

        return random.nextInt(1, 10);
    }

    /**
     * Imprime el tablero segun los valores
     * @param tablero , matriz que almacena los valores
     * @param numJugadores , numero de jugadores
     */
    public static void mostrarTablero(int[][] tablero, int numJugadores) {

        for (int i = 0; i < TABLEROTAMAÑO; i++) {
            for (int j = 0; j < TABLEROTAMAÑO; j++) {

                boolean hayPieza = false;
                boolean casillaRevelada = false;
                int jugadorEncontrado = 0;

                int casilla = tablero[i][j];

                for (int k = 0; k < numJugadores; k++) {
                    if (i == PLAYERSCORDS[k][0] && j == PLAYERSCORDS[k][1]) {

                        hayPieza = true;
                        jugadorEncontrado = k;

                    }
                }
                for (int k = 0; k < CASILLASDELTABLERO; k++) {
                    if (i == CASILLASREVELADAS[k][0] && j == CASILLASREVELADAS[k][1]) {

                        casillaRevelada = true;
                    }
                }

                if (hayPieza) {
                    System.out.print("  " + JUGADORESICONS[jugadorEncontrado] + "  ");
                } else if (!casillaRevelada) {

                    if (casilla == 0) {
                        System.out.print("     ");
                    } else if (0 < casilla && casilla < 4) {
                        System.out.print(" [ ] ");
                    } else {
                        System.out.print(" [?] ");
                    }
                } else {
                    if (casilla > 0 && casilla < 4) {
                        System.out.print(" [ ] ");
                    } else {
                        switch (casilla) {
                            case 4 -> System.out.print(" [/] ");
                            case 5 -> System.out.print(" [<] ");
                            case 6 -> System.out.print(" [>] ");
                            case 7 -> System.out.print(" [+] ");
                            case 8 -> System.out.print(" [-] ");
                            case 9 -> System.out.print(" [x] ");
                        }
                    }
                }
            }
            System.out.println("\n");
        }
        for (int i = 0; i < maxJugadors; i++) {
            System.out.println("El jugador " + (i + 1) + " esta en la casilla " + PLAYERSCASILLAS[i]);
        }
    }
}