package memory.model;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public abstract class MemoryAllocator {

    protected int[] memory;
    protected List<Allocation> allocations;

    public MemoryAllocator(int size) {
        memory = new int[size];
        allocations = new ArrayList<>();
    }

    public abstract Allocation allocate(int size);

    public void deallocate(Allocation a) {
        for (int i = a.getStart(); i < a.getStart() + a.getSize(); i++) {
            memory[i] = 0;
        }
        allocations.remove(a);
    }

    public String getMemoryPrint() {
        StringBuilder print = new StringBuilder();
        for (int bit : memory) {
            print.append(bit).append(" ");
        }
        return print.toString();
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    @Override
    public String toString() {
        String className = getClass().getSimpleName();
        int allocatedSize = allocations.stream().mapToInt(Allocation::getSize).sum();
        int freeSize = memory.length - allocatedSize;
        return String.format("%s - Memoria Total: %d, Alocado: %d, Disponível: %d\n%s", className, memory.length, allocatedSize, freeSize, getMemoryPrint());
    }
}
