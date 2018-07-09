package principal.control;

public class Tecla {
    private boolean pulsada = false;
    private long ultimaPulsacion = System.nanoTime();

//metodos    
    public void teclaPulsada(){
        pulsada = true;
        ultimaPulsacion = System.nanoTime();
    }
    
    public void teclaLiberada(){
        pulsada = false;
    }

//getters    
    public boolean isPulsada() {
        return pulsada;
    }

    public long getUltimaPulsacion() {
        return ultimaPulsacion;
    }
}
