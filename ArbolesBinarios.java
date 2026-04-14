class Nodo {
    int valor;
    Nodo izquierdo, derecho;

    public Nodo(int item) {
        valor = item;
        izquierdo = derecho = null;
    }
}

class ArbolBinarioBusqueda {
    Nodo raiz;

    ArbolBinarioBusqueda() {
        raiz = null;
    }

    void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    Nodo insertarRec(Nodo raiz, int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            return raiz;
        }

        if (valor < raiz.valor)
            raiz.izquierdo = insertarRec(raiz.izquierdo, valor);
        else if (valor > raiz.valor)
            raiz.derecho = insertarRec(raiz.derecho, valor);

        return raiz;
    }

    boolean buscar(int valor) {
        return buscarRec(raiz, valor) != null;
    }

    Nodo buscarRec(Nodo raiz, int valor) {
        if (raiz == null || raiz.valor == valor)
            return raiz;

        if (raiz.valor > valor)
            return buscarRec(raiz.izquierdo, valor);

        return buscarRec(raiz.derecho, valor);
    }

    void eliminar(int valor) {
        raiz = eliminarRec(raiz, valor);
    }

    Nodo eliminarRec(Nodo raiz, int valor) {
        if (raiz == null) return raiz;

        if (valor < raiz.valor)
            raiz.izquierdo = eliminarRec(raiz.izquierdo, valor);
        else if (valor > raiz.valor)
            raiz.derecho = eliminarRec(raiz.derecho, valor);
        else {

            if (raiz.izquierdo == null)
                return raiz.derecho;
            else if (raiz.derecho == null)
                return raiz.izquierdo;

            raiz.valor = valorMinimo(raiz.derecho);

            raiz.derecho = eliminarRec(raiz.derecho, raiz.valor);
        }
        return raiz;
    }

    int valorMinimo(Nodo raiz) {
        int minv = raiz.valor;
        while (raiz.izquierdo != null) {
            minv = raiz.izquierdo.valor;
            raiz = raiz.izquierdo;
        }
        return minv;
    }

    void inorden() {
        inordenRec(raiz);
        System.out.println();
    }

    void inordenRec(Nodo raiz) {
        if (raiz != null) {
            inordenRec(raiz.izquierdo);
            System.out.print(raiz.valor + " ");
            inordenRec(raiz.derecho);
        }
    }
}

public class ArbolesBinarios {
    public static void main(String[] args) {
        System.out.println(" Árbol Balanceado ");
        ArbolBinarioBusqueda arbolBalanceado = new ArbolBinarioBusqueda();

        int[] datos = {49, 20, 72, 5, 23, 87, 92};
        for (int dato : datos) {
            arbolBalanceado.insertar(dato);
        }
        
        System.out.print("Recorrido Inorden inicial: ");
        arbolBalanceado.inorden();
        
        System.out.println("Buscando el valor 23: " + arbolBalanceado.buscar(23));
        
        System.out.println("\nEliminando nodo hoja (92):");
        arbolBalanceado.eliminar(92); 
        arbolBalanceado.inorden();
        
        System.out.println("Eliminando nodo con 2 hijos (el 20):");
        arbolBalanceado.eliminar(20); 
        arbolBalanceado.inorden();


        System.out.println("\n Árbol Degenerado");
        ArbolBinarioBusqueda arbolDegenerado = new ArbolBinarioBusqueda();
        int[] datosOrdenados = {7, 15, 32, 44, 58, 81, 99};
        for (int dato : datosOrdenados) {
            arbolDegenerado.insertar(dato);
        }
        
        System.out.print("Recorrido Inorden: ");
        arbolDegenerado.inorden();
        System.out.println("De esta manera, todos los nodos se van a ubicar a la derecha, generando un árbol degenerado y perdiendo sus atributos y ventajas principales.");
    }
}