package iticbcn.xifratge;

public class TextXifrat {
    private byte[] dades; 

    public TextXifrat(byte[] dades) {
        this.dades = dades;
    }

    public byte[] getBytes() {
        return dades;
    }

    @Override
    public String toString() {
        return new String (dades);
    }
}
