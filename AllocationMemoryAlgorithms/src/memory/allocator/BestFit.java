package memory.allocator;

import memory.model.Allocation;
import memory.model.MemoryAllocator;

public class BestFit extends MemoryAllocator {

    public BestFit(int size) {
        super(size);
    }

    /**
     * Tenta alocar uma nova região de memória do tamanho especificado usando o algoritmo Best-Fit.
     *
     * O algoritmo Best-Fit percorre toda a memória disponível em busca do menor bloco livre que possa
     * acomodar o tamanho desejado. A alocação é feita no bloco encontrado. Esse algoritmo pode deixar
     * muitos blocos pequenos e fragmentar a memória.
     *
     * @param size O tamanho da região de memória a ser alocada.
     * @return Um objeto Allocation representando a região de memória alocada, ou null se não houver espaço disponível.
     */
    @Override
    public Allocation allocate(int size) {
        int bestIndex = -1;
        int bestSize = Integer.MAX_VALUE;
        int bestAvailableSize = Integer.MIN_VALUE;

        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == 0) {
                int j = i;
                while (j < memory.length && memory[j] == 0) {
                    j++;
                }
                int blockSize = j - i;
                if(blockSize > bestAvailableSize) bestAvailableSize = blockSize;
                if (blockSize >= size && blockSize < bestSize) {
                    bestIndex = i;
                    bestSize = blockSize;
                }
                i = j;
            }
        }

        if (bestIndex != -1) {
            Allocation a = new Allocation(bestIndex, size);
            allocations.add(a);
            for (int i = bestIndex; i < bestIndex + size; i++) {
                memory[i] = 1;
            }
            return a;
        }
        System.out.printf("Não foi possível alocar um bloco de tamanho %s na memória. Maior bloco disponível é de tamanho %s\n", size, bestAvailableSize);
        return null;
    }
}