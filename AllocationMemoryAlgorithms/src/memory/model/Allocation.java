package memory.model;

public class Allocation {

    private int start;
    private int size;

    public Allocation(int start, int size) {
        this.start = start;
        this.size = size;
    }

    public int getStart() {
        return start;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString(){
        return String.format("índide %s tamanho %s\n", this.getStart(), this.getSize());
    }

    public int getEnd() {
        return start + size;
    }
}
